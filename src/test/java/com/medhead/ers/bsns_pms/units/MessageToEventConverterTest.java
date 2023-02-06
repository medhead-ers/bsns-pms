package com.medhead.ers.bsns_pms.units;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.medhead.ers.bsns_pms.application.messaging.service.implementation.RedisMessageToEventConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageToEventConverterTest {
    @Test
    void test_failCreateEventFromMessageBecauseInvalidEventType() {
        // Given
        String eventString = "{\r\n  \"eventType\" : \"UnknownEvent\",\r\n  \"metadata\" : {\r\n    \"userName\" : \"David\"\r\n  }\r\n}";
        // Then
        Assertions.assertThrows(InvalidFormatException.class, () -> {
            // When
            new RedisMessageToEventConverter().convertMessageToEvent(eventString);
        });
    }

    @Test
    void test_failCreateEventFromMessageBecauseInvalidJSONMessage() {
        // Given
        String eventString = "Not a JSON String";
        // Then
        Assertions.assertThrows(JsonProcessingException.class, () -> {
            // When
            new RedisMessageToEventConverter().convertMessageToEvent(eventString);
        });
    }
}
