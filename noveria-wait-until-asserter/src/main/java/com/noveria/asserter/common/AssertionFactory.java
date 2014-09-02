package com.noveria.asserter.common;

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
