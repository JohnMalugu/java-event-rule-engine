package com.jcmlabs.engine.ingestion;

import com.jcmlabs.engine.event.Event;

import java.util.function.Consumer;

public interface EventSource {
    void start(Consumer<Event> eventConsumer);
}
