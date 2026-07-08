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
    void markRunningShouldTransitionCreatedExecutionToRunning() {
        SagaExecution execution = SagaExecution.created(SagaId.of("order-saga-123"));

        SagaExecution runningExecution = execution.markRunning();

        assertEquals(execution.id(), runningExecution.id());
        assertEquals(SagaStatus.RUNNING, runningExecution.status());
    }

    @Test
    void markCompletedShouldTransitionRunningExecutionToCompleted() {
        SagaExecution execution = SagaExecution.created(SagaId.of("order-saga-123"))
                .markRunning();

        SagaExecution completedExecution = execution.markCompleted();

        assertEquals(execution.id(), completedExecution.id());
        assertEquals(SagaStatus.COMPLETED, completedExecution.status());
    }

    @Test
    void markFailedShouldTransitionRunningExecutionToFailed() {
        SagaExecution execution = SagaExecution.created(SagaId.of("order-saga-123"))
                .markRunning();

        SagaExecution failedExecution = execution.markFailed();

        assertEquals(execution.id(), failedExecution.id());
        assertEquals(SagaStatus.FAILED, failedExecution.status());
    }

    @Test
    void markCompensatingShouldTransitionFailedExecutionToCompensating() {
        SagaExecution execution = SagaExecution.created(SagaId.of("order-saga-123"))
                .markRunning()
                .markFailed();

        SagaExecution compensatingExecution = execution.markCompensating();

        assertEquals(execution.id(), compensatingExecution.id());
        assertEquals(SagaStatus.COMPENSATING, compensatingExecution.status());
    }

    @Test
    void markCompensatedShouldTransitionCompensatingExecutionToCompensated() {
        SagaExecution execution = SagaExecution.created(SagaId.of("order-saga-123"))
                .markRunning()
                .markFailed()
                .markCompensating();

        SagaExecution compensatedExecution = execution.markCompensated();

        assertEquals(execution.id(), compensatedExecution.id());
        assertEquals(SagaStatus.COMPENSATED, compensatedExecution.status());
    }

    @Test
    void markCompletedShouldRejectCreatedExecution() {
        SagaExecution execution = SagaExecution.created(SagaId.of("order-saga-123"));

        assertThrows(IllegalStateException.class, execution::markCompleted);
    }

    @Test
    void markRunningShouldRejectCompletedExecution() {
        SagaExecution execution = SagaExecution.created(SagaId.of("order-saga-123"))
                .markRunning()
                .markCompleted();

        assertThrows(IllegalStateException.class, execution::markRunning);
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
