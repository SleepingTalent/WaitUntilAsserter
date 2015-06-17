package com.noveria.assertion.asserter;

import com.noveria.assertion.exception.WaitUntilAssertionError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public abstract class WaitUntilAsserter {

    private static final Logger log = LoggerFactory.getLogger(WaitUntilAsserter.class);

    private static final long DEFAULT_MAX_WAIT_TIME = 5000;
    private static final long SLEEP = 500;

    private long maxWaitTime;
    private long accumulatedTime;

    public WaitUntilAsserter() {
        this(DEFAULT_MAX_WAIT_TIME);
    }

    public WaitUntilAsserter(long maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    protected abstract boolean execute();

    protected abstract String getTaskName();

    protected abstract String getFailureMessage();

    public void performAssertion() throws InterruptedException {
        accumulatedTime = 0;

        log.debug("Initial Accumulated Time = {}",accumulatedTime);

        boolean success = execute();

        log.debug("Initial Assertion Result = {}",success);

        while(!success && accumulatedTime < getMaxWaitTime()) {

            accumulatedTime += sleep();
            log.debug("Accumulated Time = {}",accumulatedTime);

            log.debug("Retrying Assertion");
            success = execute();

            log.debug("Assertion Result = {}",success);
        }

        if(!success) {
          throw new WaitUntilAssertionError(getTaskName()+" assertionFailed : "+getFailureMessage());
        }
    }

    public long getAccumulatedTime() {
        return accumulatedTime;
    }

    public long getMaxWaitTime() {
        return maxWaitTime;
    }

    private long sleep() throws InterruptedException {

        long sleepStart = System.currentTimeMillis();

        log.debug("Sleeping for = {}",SLEEP);
        TimeUnit.MILLISECONDS.sleep(SLEEP);

        long sleepEnd = System.currentTimeMillis();

        return sleepEnd - sleepStart;
    }
}
