package com.jcmlabs.engine.rules;

import com.jcmlabs.engine.event.Event;

public class LatencyAndLossCondition implements Condition{

    @Override
    public boolean evaluate(Event event) {
        Object latencyObj = event.attributes().get("latency");
        Object lossObj = event.attributes().get("packetLoss");

        if (!(latencyObj instanceof Number) || !(lossObj instanceof Number)) {
            return false;
        }

        int latency = ((Number) latencyObj).intValue();
        double packetLoss = ((Number) lossObj).doubleValue();

        return latency > 150 && packetLoss > 0.03;
    }
}
