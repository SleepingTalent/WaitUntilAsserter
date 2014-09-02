package com.noveria.assertion.task;

public interface AssertTask {

    boolean execute();

    String getTaskName();

    String getFailureMessage();
}
