package io.folcadyn.core.saga;

/**
 * Describes a saga type known by the framework.
 *
 * <p>A definition represents the static description of a saga, while
 * {@link SagaExecution} represents a concrete runtime instance of that saga.
 * Step metadata and annotation discovery details are intentionally modeled
 * separately in later concepts.</p>
 *
 * @param name the non-blank saga definition name
 */
public record SagaDefinition(String name) {

    /**
     * Creates a saga definition with the given name.
     *
     * @param name the saga definition name
     * @throws IllegalArgumentException when the name is {@code null} or blank
     */
    public SagaDefinition {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Saga definition name must not be null or blank.");
        }
    }
}
