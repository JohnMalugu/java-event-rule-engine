package engine.core;

import com.jcmlabs.engine.core.EventProcessor;
import com.jcmlabs.engine.event.Event;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class EventProcessorTest {

    @Test
    void shouldProcessEventWithoutFailure(){
        EventProcessor processor = event -> {};

        Event event = new Event(
                UUID.randomUUID(),
                "Test",
                Instant.now(),
                Map.of()
        );
        
        assertDoesNotThrow(() -> processor.process(event));
    }
}
