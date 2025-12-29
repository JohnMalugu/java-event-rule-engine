package com.jcmlabs.engine.core;

import com.jcmlabs.engine.event.Event;

public class Worker implements Runnable {
    private final EventQueue queue;
    private final EventProcessor processor;;

    public Worker(EventQueue queue, EventProcessor processor) {
        this.queue = queue;
        this.processor = processor;
    }


    @Override
    public void run() {
        try{
            while (true){
                Event event = queue.take();
                processor.process(event);
            }
        }
        catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }
    }
}
