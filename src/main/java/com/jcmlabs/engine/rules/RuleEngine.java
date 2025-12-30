package com.jcmlabs.engine.rules;

import com.jcmlabs.engine.event.Event;

import java.util.List;

public class RuleEngine {

    private final List<Rule> rules;

    public RuleEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public void evaluate(Event event) {
        for (Rule rule : rules) {
            if (rule.condition().evaluate(event)) {
                rule.action().execute(event);
            }
        }
    }
}
