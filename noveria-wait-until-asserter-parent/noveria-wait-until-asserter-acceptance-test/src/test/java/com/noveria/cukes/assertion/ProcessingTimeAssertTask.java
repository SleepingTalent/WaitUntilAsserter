package com.noveria.cukes.assertion;

import com.noveria.assertion.task.AssertTask;
import com.noveria.cukes.helpers.rest.RestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessingTimeAssertTask implements AssertTask {

    Logger log = LoggerFactory.getLogger(ProcessingTimeAssertTask.class);

    RestHelper restHelper;
    String failureMessage;

    public ProcessingTimeAssertTask(RestHelper restHelper) {
        this.restHelper = restHelper;
    }

    @Override
    public boolean execute() {

        boolean assertionPassed;

        assertionPassed = restHelper.processingComplete();
        log.debug("ProcessingComplete Call Result {}",assertionPassed);

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
