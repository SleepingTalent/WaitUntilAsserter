package com.noveria.assertion.factory;

import com.noveria.assertion.task.AssertTask;
import com.noveria.assertion.asserter.WaitUntilAsserter;

public class AssertionFactory {

    public void performAssertion(AssertTask assertTask) throws InterruptedException {
        WaitUntilAsserter waitUntilAsserter = new WaitUntilAsserter(assertTask);
        waitUntilAsserter.performAssertion();
    }

    public void performAssertion(AssertTask assertTask, long maxWaitTime) throws InterruptedException {
        WaitUntilAsserter waitUntilAsserter = new WaitUntilAsserter(assertTask,maxWaitTime);
        waitUntilAsserter.performAssertion();
    }

}
