package io.folcadyn.core.saga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SagaExecutionTest {

    @Test
    void createdShouldInitializeExecutionWithCreatedStatus() {
        SagaId sagaId = SagaId.of("order-saga-123");

        SagaExecution execution = SagaExecution.created(sagaId);

        assertEquals(sagaId, execution.id());
        assertEquals(SagaStatus.CREATED, execution.status());
    }

    @Test
    void constructorShouldRejectNullId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SagaExecution(null, SagaStatus.CREATED)
        );
    }

    @Test
    void constructorShouldRejectNullStatus() {
        SagaId sagaId = SagaId.of("order-saga-123");

        assertThrows(
                IllegalArgumentException.class,
                () -> new SagaExecution(sagaId, null)
        );
    }
}
