package com.jcmlabs.engine.ingestion;

import com.jcmlabs.engine.event.Event;

import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

public class RandomEventSource implements EventSource{
    private final Random random = new Random();
    private  volatile  boolean running = true;


    @Override
    public void start(Consumer<Event> eventConsumer) {
        while(running){
            Event event = new Event(
                    UUID.randomUUID(),
                    "NETWORK_METRIC",
                    Instant.now(),
                    Map.of(
                            "latency", random.nextInt(300),
                            "packetLoss", random.nextDouble()
                    )
            );

            eventConsumer.accept(event);

            try{
                Thread.sleep(1);
            }
            catch (InterruptedException exception){
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop(){
        running = false;
    }
}
