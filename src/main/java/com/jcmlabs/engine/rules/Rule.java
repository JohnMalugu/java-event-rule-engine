package com.jcmlabs.engine.rules;

public record Rule(
        String name,
        Condition condition,
        Action action
) {}
