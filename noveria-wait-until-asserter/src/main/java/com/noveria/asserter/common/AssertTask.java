package com.noveria.asserter.common;

public interface AssertTask {

    boolean execute();

    String getTaskName();

    String getFailureMessage();
}
