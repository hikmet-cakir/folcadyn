package io.folcadyn.core.saga;

/**
 * Represents the lifecycle state of a saga execution.
 *
 * <p>The status is intentionally small and framework-agnostic so the core
 * module can describe saga lifecycle semantics without depending on Spring,
 * persistence, messaging, or orchestration infrastructure.</p>
 */
public enum SagaStatus {

    /**
     * The saga execution has been created but has not started running yet.
     */
    CREATED,

    /**
     * The saga execution is currently running one or more forward steps.
     */
    RUNNING,

    /**
     * The saga execution completed all forward steps successfully.
     */
    COMPLETED,

    /**
     * The saga execution failed before successful completion.
     */
    FAILED,

    /**
     * The saga execution is currently running compensation logic.
     */
    COMPENSATING,

    /**
     * The saga execution completed compensation after a failure.
     */
    COMPENSATED
}
