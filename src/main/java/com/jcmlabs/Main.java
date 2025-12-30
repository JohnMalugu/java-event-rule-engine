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
        Rule rule = new Rule(
                "Poor Network Quality",
                new LatencyAndLossCondition(),
                new AlertAction()
        );

        EventQueue queue = new EventQueue(10_000);
        RuleEngine ruleEngine = new RuleEngine(List.of(rule));

        EventProcessor eventProcessor = new LoggingEventProcessor(ruleEngine);

        int workers = Runtime.getRuntime().availableProcessors();

        for(int i = 0; i < workers; i++){
            Thread worker = new Thread(new Worker(queue,eventProcessor));
            worker.start();
        }

        EventSource source = new RandomEventSource();

        source.start(event -> {

        });




        EventProcessor processor = new LoggingEventProcessor(ruleEngine);

    }
}