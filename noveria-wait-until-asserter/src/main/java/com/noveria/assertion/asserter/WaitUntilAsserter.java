package com.noveria.assertion.asserter;

import com.noveria.assertion.task.AssertTask;

import static org.junit.Assert.fail;

public class WaitUntilAsserter {

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

    public void performAssertion() {
        long accumulatedTime = 0;

        boolean success = assertTask.execute();

        while(!success && accumulatedTime < maxWaitTime) {

            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            success = assertTask.execute();
            accumulatedTime += SLEEP;
        }

        if(!success) {
          fail(assertTask.getTaskName()+" assertionFailed : "+assertTask.getFailureMessage());
        }
    }
}
