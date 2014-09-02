package com.noveria.task;

public interface AssertTask {

    boolean execute();

    String getTaskName();

    String getFailureMessage();
}
