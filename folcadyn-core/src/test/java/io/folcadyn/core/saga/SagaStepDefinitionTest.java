package io.folcadyn.core.saga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SagaStepDefinitionTest {

    @Test
    void constructorShouldCreateSagaStepDefinitionWithProvidedValues() {
        SagaStepDefinition stepDefinition = new SagaStepDefinition("reserveInventory", 0);

        assertEquals("reserveInventory", stepDefinition.name());
        assertEquals(0, stepDefinition.order());
    }

    @Test
    void constructorShouldRejectNullName() {
        assertThrows(IllegalArgumentException.class, () -> new SagaStepDefinition(null, 0));
    }

    @Test
    void constructorShouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new SagaStepDefinition("   ", 0));
    }

    @Test
    void constructorShouldRejectNegativeOrder() {
        assertThrows(IllegalArgumentException.class, () -> new SagaStepDefinition("reserveInventory", -1));
    }
}
