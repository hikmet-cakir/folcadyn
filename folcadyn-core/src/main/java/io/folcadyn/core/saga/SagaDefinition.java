package io.folcadyn.core.saga;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Describes a saga type known by the framework.
 *
 * <p>A definition represents the static description of a saga, while
 * {@link SagaExecution} represents a concrete runtime instance of that saga.
 * Annotation discovery details are intentionally modeled separately in later
 * concepts.</p>
 *
 * @param name the non-blank saga definition name
 * @param steps the non-empty ordered saga step definitions
 */
public record SagaDefinition(String name, List<SagaStepDefinition> steps) {

    /**
     * Creates a saga definition with the given name and steps.
     *
     * @param name the saga definition name
     * @param steps the saga step definitions
     * @throws IllegalArgumentException when the name is {@code null} or blank, or steps is {@code null}, empty,
     *                                  contains {@code null}, or contains duplicate order values
     */
    public SagaDefinition {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Saga definition name must not be null or blank.");
        }
        if (steps == null || steps.isEmpty()) {
            throw new IllegalArgumentException("Saga definition steps must not be null or empty.");
        }
        if (steps.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Saga definition steps must not contain null elements.");
        }
        long distinctOrderCount = steps.stream()
                .mapToInt(SagaStepDefinition::order)
                .distinct()
                .count();
        if (distinctOrderCount != steps.size()) {
            throw new IllegalArgumentException("Saga definition steps must not contain duplicate order values.");
        }
        steps = steps.stream()
                .sorted(Comparator.comparingInt(SagaStepDefinition::order))
                .toList();
    }
}
