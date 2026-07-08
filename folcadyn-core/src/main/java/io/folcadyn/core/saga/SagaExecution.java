package io.folcadyn.core.saga;

/**
 * Represents a single saga execution instance and its current lifecycle status.
 *
 * <p>This model intentionally contains only the identity and status of the
 * execution. Step results, failure details, timestamps, and persistence
 * metadata belong to later, more specific domain concepts.</p>
 *
 * @param id the unique identifier of the saga execution
 * @param status the current lifecycle status of the saga execution
 */
public record SagaExecution(SagaId id, SagaStatus status) {

    /**
     * Creates a saga execution with the given identifier and status.
     *
     * @param id the unique identifier of the saga execution
     * @param status the current lifecycle status of the saga execution
     * @throws IllegalArgumentException when the id or status is {@code null}
     */
    public SagaExecution {
        if (id == null) {
            throw new IllegalArgumentException("Saga execution id must not be null.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Saga execution status must not be null.");
        }
    }

    /**
     * Creates a new saga execution in the {@link SagaStatus#CREATED} status.
     *
     * @param id the unique identifier of the saga execution
     * @return a saga execution initialized with the created status
     */
    public static SagaExecution created(SagaId id) {
        return new SagaExecution(id, SagaStatus.CREATED);
    }

    /**
     * Marks this saga execution as running.
     *
     * @return a new saga execution with the running status
     * @throws IllegalStateException when the current status cannot transition to running
     */
    public SagaExecution markRunning() {
        return transitionTo(SagaStatus.CREATED, SagaStatus.RUNNING);
    }

    /**
     * Marks this saga execution as completed.
     *
     * @return a new saga execution with the completed status
     * @throws IllegalStateException when the current status cannot transition to completed
     */
    public SagaExecution markCompleted() {
        return transitionTo(SagaStatus.RUNNING, SagaStatus.COMPLETED);
    }

    /**
     * Marks this saga execution as failed.
     *
     * @return a new saga execution with the failed status
     * @throws IllegalStateException when the current status cannot transition to failed
     */
    public SagaExecution markFailed() {
        return transitionTo(SagaStatus.RUNNING, SagaStatus.FAILED);
    }

    /**
     * Marks this saga execution as compensating.
     *
     * @return a new saga execution with the compensating status
     * @throws IllegalStateException when the current status cannot transition to compensating
     */
    public SagaExecution markCompensating() {
        return transitionTo(SagaStatus.FAILED, SagaStatus.COMPENSATING);
    }

    /**
     * Marks this saga execution as compensated.
     *
     * @return a new saga execution with the compensated status
     * @throws IllegalStateException when the current status cannot transition to compensated
     */
    public SagaExecution markCompensated() {
        return transitionTo(SagaStatus.COMPENSATING, SagaStatus.COMPENSATED);
    }

    private SagaExecution transitionTo(SagaStatus expectedCurrentStatus, SagaStatus nextStatus) {
        if (status != expectedCurrentStatus) {
            throw new IllegalStateException(
                    "Cannot transition saga execution from " + status + " to " + nextStatus + "."
            );
        }
        return new SagaExecution(id, nextStatus);
    }
}
