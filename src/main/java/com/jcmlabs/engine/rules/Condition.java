package com.jcmlabs.engine.rules;

import com.jcmlabs.engine.event.Event;

@FunctionalInterface
public interface Condition {
    boolean evaluate(Event event);
}
