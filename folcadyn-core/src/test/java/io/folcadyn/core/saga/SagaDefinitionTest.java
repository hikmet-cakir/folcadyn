package io.folcadyn.core.saga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SagaDefinitionTest {

    @Test
    void constructorShouldCreateSagaDefinitionWithProvidedName() {
        SagaDefinition definition = new SagaDefinition("OrderSaga");

        assertEquals("OrderSaga", definition.name());
    }

    @Test
    void constructorShouldRejectNullName() {
        assertThrows(IllegalArgumentException.class, () -> new SagaDefinition(null));
    }

    @Test
    void constructorShouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new SagaDefinition("   "));
    }
}
