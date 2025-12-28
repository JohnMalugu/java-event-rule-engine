package com.jcmlabs;

import com.jcmlabs.engine.core.EventProcessor;
import com.jcmlabs.engine.core.LoggingEventProcessor;
import com.jcmlabs.engine.ingestion.EventSource;
import com.jcmlabs.engine.ingestion.RandomEventSource;

public class Main {
    public static void main(String[] args) {

        EventProcessor eventProcessor = new LoggingEventProcessor();
        EventSource eventSource = new RandomEventSource();

        eventSource.start(eventProcessor::process);
    }
}