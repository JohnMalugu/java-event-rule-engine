package com.jcmlabs;

import com.jcmlabs.engine.core.EventProcessor;
import com.jcmlabs.engine.core.EventQueue;
import com.jcmlabs.engine.core.LoggingEventProcessor;
import com.jcmlabs.engine.core.Worker;
import com.jcmlabs.engine.ingestion.EventSource;
import com.jcmlabs.engine.ingestion.RandomEventSource;
import com.jcmlabs.engine.rules.AlertAction;
import com.jcmlabs.engine.rules.LatencyAndLossCondition;
import com.jcmlabs.engine.rules.Rule;
import com.jcmlabs.engine.rules.RuleEngine;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Define rules
        Rule rule = new Rule("Poor Network Quality", new LatencyAndLossCondition(), new AlertAction());

        RuleEngine ruleEngine = new RuleEngine(List.of(rule));

        //Create processor
        EventProcessor eventProcessor = new LoggingEventProcessor(ruleEngine);

        //Create queue(backpressure)
        EventQueue queue = new EventQueue(10_000);

        //Start workers
        int workers = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < workers; i++) {
            Thread worker = new Thread(new Worker(queue, eventProcessor));
            worker.start();
        }

        //Start event source
        EventSource source = new RandomEventSource();
        source.start(event -> {
            try {
                queue.publish(event);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

}