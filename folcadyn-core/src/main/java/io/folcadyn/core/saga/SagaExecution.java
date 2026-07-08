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
}
