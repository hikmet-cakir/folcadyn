package io.folcadyn.core.saga;

/**
 * Describes a forward step within a saga definition.
 *
 * <p>The step definition is intentionally limited to a name and order. Method
 * references, compensation metadata, retry policy, and annotation discovery
 * details are modeled by later framework concepts.</p>
 *
 * @param name the non-blank saga step name
 * @param order the zero-based execution order of the step
 */
public record SagaStepDefinition(String name, int order) {

    /**
     * Creates a saga step definition with the given name and order.
     *
     * @param name the saga step name
     * @param order the zero-based execution order of the step
     * @throws IllegalArgumentException when the name is {@code null} or blank, or the order is negative
     */
    public SagaStepDefinition {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Saga step definition name must not be null or blank.");
        }
        if (order < 0) {
            throw new IllegalArgumentException("Saga step definition order must not be negative.");
        }
    }
}
