package io.folcadyn.core.saga;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SagaIdTest {

    @Test
    void ofShouldCreateSagaIdFromProvidedValue() {
        SagaId sagaId = SagaId.of("order-saga-123");

        assertEquals("order-saga-123", sagaId.value());
    }

    @Test
    void ofShouldRejectNullValue() {
        assertThrows(IllegalArgumentException.class, () -> SagaId.of(null));
    }

    @Test
    void ofShouldRejectBlankValue() {
        assertThrows(IllegalArgumentException.class, () -> SagaId.of("   "));
    }

    @Test
    void randomShouldCreateNonBlankSagaId() {
        SagaId sagaId = assertDoesNotThrow(SagaId::random);

        assertFalse(sagaId.value().isBlank());
    }
}
