package com.jcmlabs.engine.core;

import com.jcmlabs.engine.event.Event;
import com.jcmlabs.engine.rules.RuleEngine;

import java.util.concurrent.atomic.AtomicLong;

public class LoggingEventProcessor implements EventProcessor{

    private final AtomicLong processedCount = new AtomicLong();
    private final RuleEngine ruleEngine;

    public LoggingEventProcessor(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    @Override
    public void process(Event event) {
        long count = processedCount.incrementAndGet();
        ruleEngine.evaluate(event);

        if(count % 1000 == 0){
            System.out.println("Processed events: " + count);
        }
    }

    public long getProcessedCount(){
        return processedCount.get();
    }
}
