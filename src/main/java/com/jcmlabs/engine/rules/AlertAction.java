package com.jcmlabs.engine.rules;

import com.jcmlabs.engine.event.Event;

public class AlertAction implements Action{

    @Override
    public void execute(Event event) {
        System.out.println(
                "ALERT triggered for event " + event.id() +
                        " | attributes=" + event.attributes()
        );
    }
}
