package engine.core;

import com.jcmlabs.engine.core.EventProcessor;
import com.jcmlabs.engine.event.Event;
import com.jcmlabs.engine.ingestion.EventSource;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PipelineTest {

    @Test
    void shouldProcessEventsFromSource() {
        AtomicInteger counter = new AtomicInteger();

        EventProcessor processor = event -> counter.incrementAndGet();

        EventSource source = consumer -> {
            for (int i = 0; i < 10; i++) {
                consumer.accept(new Event(
                        UUID.randomUUID(),
                        "TEST",
                        Instant.now(),
                        Map.of()
                ));
            }
        };

        source.start(processor::process);

        assertTrue(counter.get() == 10);
    }
}

