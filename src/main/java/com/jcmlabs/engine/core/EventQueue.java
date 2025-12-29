package com.jcmlabs.engine.core;

import com.jcmlabs.engine.event.Event;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EventQueue {
    private final BlockingQueue<Event> queue;

    public EventQueue(int capacity){
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    public void publish(Event event) throws InterruptedException {
        queue.put(event); //blocks when full (backpressure)
    }

    public Event take() throws InterruptedException{
        return queue.take(); //blocks when empty
    }
}
