package com.jcmlabs.engine.event;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record Event(
    UUID id,
    String type,
    Instant timestamp,
    Map<String, Object> attributes
) { }
