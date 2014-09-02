package com.noveria.assertion.factory;

import com.noveria.assertion.task.AssertTask;
import com.noveria.assertion.asserter.WaitUntilAsserter;

public class AssertionFactory {

    public static void performAssertion(AssertTask assertTask) {
        WaitUntilAsserter waitUntilAsserter = new WaitUntilAsserter(assertTask);
        waitUntilAsserter.performAssertion();
    }

    public static void performAssertion(AssertTask assertTask, long maxWaitTime) {
        WaitUntilAsserter waitUntilAsserter = new WaitUntilAsserter(assertTask,maxWaitTime);
        waitUntilAsserter.performAssertion();
    }
}
