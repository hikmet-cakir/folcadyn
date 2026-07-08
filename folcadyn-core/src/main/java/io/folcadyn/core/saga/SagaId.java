package io.folcadyn.core.saga;

import java.util.UUID;

/**
 * Strongly typed identifier for a saga execution.
 *
 * <p>The identifier stores its value as a {@link String} to allow applications
 * to use their own business identifiers while still providing a UUID-based
 * default generation strategy through {@link #random()}.</p>
 *
 * @param value the non-blank saga identifier value
 */
public record SagaId(String value) {

    /**
     * Creates a saga identifier from the given value.
     *
     * @param value the saga identifier value
     * @throws IllegalArgumentException when the value is {@code null} or blank
     */
    public SagaId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Saga id value must not be null or blank.");
        }
    }

    /**
     * Creates a saga identifier from an application-provided value.
     *
     * @param value the saga identifier value
     * @return a saga identifier containing the given value
     */
    public static SagaId of(String value) {
        return new SagaId(value);
    }

    /**
     * Creates a saga identifier using a random UUID value.
     *
     * @return a saga identifier containing a random UUID string
     */
    public static SagaId random() {
        return new SagaId(UUID.randomUUID().toString());
    }
}
