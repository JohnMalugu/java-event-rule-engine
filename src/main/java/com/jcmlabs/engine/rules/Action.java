package com.jcmlabs.engine.rules;

import com.jcmlabs.engine.event.Event;

@FunctionalInterface
public interface Action {
    void execute(Event event);
}
