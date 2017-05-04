package com.noveria.assertion.asserter;


public class TestWaitAsserter extends WaitUntilAsserter {

    int value = 0;

    protected boolean execute() {
        boolean result = false;

        if(value == 2) {
            result = true;
        } else {
            value++;
        }

        return result;
    }

    protected String getTaskName() {
        return "TestWaitAsserter";
    }

    protected String getFailureMessage() {
        return "TestWaitAsserter Faillure Message";
    }
}
