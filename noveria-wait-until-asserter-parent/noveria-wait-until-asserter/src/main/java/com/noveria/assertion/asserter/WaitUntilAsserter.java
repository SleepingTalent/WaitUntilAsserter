package com.noveria.assertion.asserter;

import com.noveria.assertion.task.AssertTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public final class WaitUntilAsserter {

    Logger log = LoggerFactory.getLogger(WaitUntilAsserter.class);

    private static final long DEFAULT_MAX_WAIT_TIME = 30000;
    private static final long SLEEP = 500;

    private final AssertTask assertTask;
    private final long maxWaitTime;

    public WaitUntilAsserter(AssertTask assertTask) {
        this(assertTask,DEFAULT_MAX_WAIT_TIME);
    }

    public WaitUntilAsserter(AssertTask assertTask, long maxWaitTime) {
        this.assertTask = assertTask;
        this.maxWaitTime = maxWaitTime;
    }

    public void performAssertion() throws InterruptedException {
        long accumulatedTime = 0;

        log.debug("Initial Accumulated Time = {}",accumulatedTime);

        boolean success = assertTask.execute();

        log.debug("Initial Assertion Result = {}",success);

        while(!success && accumulatedTime < maxWaitTime) {

            log.debug("Sleeping for = {}",SLEEP);
            TimeUnit.MILLISECONDS.sleep(SLEEP);

            log.debug("Retrying Assertion");
            success = assertTask.execute();

            log.debug("Assertion Result = {}",success);

            accumulatedTime += SLEEP;
            log.debug("Accumulated Time = {}",accumulatedTime);
        }

        log.debug("MaxWaitTime {} Expired!",maxWaitTime);

        if(!success) {
          log.debug(assertTask.getTaskName() + " assertionFailed : " + assertTask.getFailureMessage());
          fail(assertTask.getTaskName() + " assertionFailed : " + assertTask.getFailureMessage());
        }else {
          log.debug("{} Assertion Passed!",assertTask.getTaskName());
        }
    }
}
