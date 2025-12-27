package com.jcmlabs.engine.core;

import com.jcmlabs.engine.event.Event;

public interface EventProcessor {
    void process(Event event);
}
