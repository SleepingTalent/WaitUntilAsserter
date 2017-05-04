package com.noveria.cukes.assertion;

import com.noveria.assertion.asserter.WaitUntilAsserter;
import com.noveria.cukes.helpers.rest.RestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessingTimeAssertTask extends WaitUntilAsserter {

    Logger log = LoggerFactory.getLogger(ProcessingTimeAssertTask.class);

    RestHelper restHelper;
    String failureMessage;

    public ProcessingTimeAssertTask(RestHelper restHelper, long assertionTimeOut) {
        this.restHelper = restHelper;
        setMaxWaitTime(assertionTimeOut);
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
