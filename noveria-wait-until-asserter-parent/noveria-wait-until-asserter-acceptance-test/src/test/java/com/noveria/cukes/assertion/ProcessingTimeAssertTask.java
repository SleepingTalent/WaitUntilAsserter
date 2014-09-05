package com.noveria.cukes.assertion;

import com.noveria.assertion.task.AssertTask;
import com.noveria.cukes.helpers.rest.RestHelper;

public class ProcessingTimeAssertTask implements AssertTask {

    RestHelper restHelper;
    String failureMessage;

    public ProcessingTimeAssertTask(RestHelper restHelper) {
        this.restHelper = restHelper;
    }

    @Override
    public boolean execute() {

        boolean assertionPassed = false;

        assertionPassed = restHelper.processingComplete();

        if(!assertionPassed) {
            failureMessage = "Processing Not Complete!";
        }

        return assertionPassed;
    }

    @Override
    public String getTaskName() {
        return "ProcessingTimeAssertTask";
    }

    @Override
    public String getFailureMessage() {
        return failureMessage;
    }
}
