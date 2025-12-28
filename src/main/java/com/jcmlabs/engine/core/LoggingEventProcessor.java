package com.jcmlabs.engine.core;

import com.jcmlabs.engine.event.Event;

import java.util.concurrent.atomic.AtomicLong;

public class LoggingEventProcessor implements EventProcessor{

    private final AtomicLong processedCount = new AtomicLong();

    @Override
    public void process(Event event) {
        long count = processedCount.incrementAndGet();

        if(count % 1000 == 0){
            System.out.println("Processed events: " + count);
        }
    }

    public long getProcessedCount(){
        return processedCount.get();
    }
}
