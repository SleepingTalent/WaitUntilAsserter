package com.noveria.assertion.asserter;

import com.noveria.assertion.expection.WaitUntilAssertionError;
import com.noveria.assertion.task.AssertTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public final class WaitUntilAsserter {

    Logger log = LoggerFactory.getLogger(WaitUntilAsserter.class);

    private static final long DEFAULT_MAX_WAIT_TIME = 5000;
    private static final long SLEEP = 500;

    private AssertTask assertTask;
    private long maxWaitTime;

    private long accumulatedTime;

    public WaitUntilAsserter(AssertTask assertTask) {
        this(assertTask,DEFAULT_MAX_WAIT_TIME);
    }

    public WaitUntilAsserter(AssertTask assertTask, long maxWaitTime) {
        this.assertTask = assertTask;
        this.maxWaitTime = maxWaitTime;
    }

    public void performAssertion() throws InterruptedException {
        accumulatedTime = 0;

        log.debug("Initial Accumulated Time = {}",accumulatedTime);

        boolean success = assertTask.execute();

        log.debug("Initial Assertion Result = {}",success);

        while(!success && accumulatedTime < maxWaitTime) {

            accumulatedTime += sleep();
            log.debug("Accumulated Time = {}",accumulatedTime);

            log.debug("Retrying Assertion");
            success = assertTask.execute();

            log.debug("Assertion Result = {}",success);
        }

        if(!success) {
          throw new WaitUntilAssertionError(
                  assertTask.getTaskName() + " assertionFailed : " + assertTask.getFailureMessage());
        }
    }

    public long getAccumulatedTime() {
        return accumulatedTime;
    }

    private long sleep() throws InterruptedException {

        long sleepStart = System.currentTimeMillis();

        log.debug("Sleeping for = {}",SLEEP);
        TimeUnit.MILLISECONDS.sleep(SLEEP);

        long sleepEnd = System.currentTimeMillis();

        return sleepEnd - sleepStart;
    }
}
