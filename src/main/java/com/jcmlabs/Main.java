package com.jcmlabs;

import com.jcmlabs.engine.core.EventProcessor;
import com.jcmlabs.engine.core.EventQueue;
import com.jcmlabs.engine.core.LoggingEventProcessor;
import com.jcmlabs.engine.core.Worker;
import com.jcmlabs.engine.ingestion.EventSource;
import com.jcmlabs.engine.ingestion.RandomEventSource;

public class Main {
    public static void main(String[] args) {

        EventQueue queue = new EventQueue(10_000);
        EventProcessor eventProcessor = new LoggingEventProcessor();

        int workers = Runtime.getRuntime().availableProcessors();

        for(int i = 0; i < workers; i++){
            Thread worker = new Thread(new Worker(queue,eventProcessor));
            worker.start();
        }

        EventSource source = new RandomEventSource();

        source.start(event -> {

        });
    }
}