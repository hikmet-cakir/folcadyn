package io.folcadyn.core.saga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SagaDefinitionTest {

    @Test
    void constructorShouldCreateSagaDefinitionWithProvidedValues() {
        SagaStepDefinition stepDefinition = new SagaStepDefinition("reserveInventory", 0);

        SagaDefinition definition = new SagaDefinition("OrderSaga", List.of(stepDefinition));

        assertEquals("OrderSaga", definition.name());
        assertEquals(List.of(stepDefinition), definition.steps());
    }

    @Test
    void constructorShouldSortStepsByOrder() {
        SagaStepDefinition secondStep = new SagaStepDefinition("capturePayment", 1);
        SagaStepDefinition firstStep = new SagaStepDefinition("reserveInventory", 0);

        SagaDefinition definition = new SagaDefinition("OrderSaga", List.of(secondStep, firstStep));

        assertEquals(List.of(firstStep, secondStep), definition.steps());
    }

    @Test
    void stepsShouldBeImmutable() {
        List<SagaStepDefinition> steps = new ArrayList<>();
        steps.add(new SagaStepDefinition("reserveInventory", 0));

        SagaDefinition definition = new SagaDefinition("OrderSaga", steps);
        steps.add(new SagaStepDefinition("capturePayment", 1));

        assertEquals(1, definition.steps().size());
        assertThrows(
                UnsupportedOperationException.class,
                () -> definition.steps().add(new SagaStepDefinition("shipOrder", 2))
        );
    }

    @Test
    void constructorShouldRejectNullName() {
        SagaStepDefinition stepDefinition = new SagaStepDefinition("reserveInventory", 0);

        assertThrows(IllegalArgumentException.class, () -> new SagaDefinition(null, List.of(stepDefinition)));
    }

    @Test
    void constructorShouldRejectBlankName() {
        SagaStepDefinition stepDefinition = new SagaStepDefinition("reserveInventory", 0);

        assertThrows(IllegalArgumentException.class, () -> new SagaDefinition("   ", List.of(stepDefinition)));
    }

    @Test
    void constructorShouldRejectNullSteps() {
        assertThrows(IllegalArgumentException.class, () -> new SagaDefinition("OrderSaga", null));
    }

    @Test
    void constructorShouldRejectEmptySteps() {
        assertThrows(IllegalArgumentException.class, () -> new SagaDefinition("OrderSaga", List.of()));
    }

    @Test
    void constructorShouldRejectNullStepElement() {
        List<SagaStepDefinition> steps = new ArrayList<>();
        steps.add(null);

        assertThrows(IllegalArgumentException.class, () -> new SagaDefinition("OrderSaga", steps));
    }

    @Test
    void constructorShouldRejectDuplicateStepOrder() {
        SagaStepDefinition firstStep = new SagaStepDefinition("reserveInventory", 0);
        SagaStepDefinition secondStep = new SagaStepDefinition("capturePayment", 0);

        assertThrows(IllegalArgumentException.class, () -> new SagaDefinition("OrderSaga", List.of(firstStep, secondStep)));
    }

    @Test
    void constructorShouldRejectDuplicateStepName() {
        SagaStepDefinition firstStep = new SagaStepDefinition("reserveInventory", 0);
        SagaStepDefinition secondStep = new SagaStepDefinition("reserveInventory", 1);

        assertThrows(IllegalArgumentException.class, () -> new SagaDefinition("OrderSaga", List.of(firstStep, secondStep)));
    }
}
