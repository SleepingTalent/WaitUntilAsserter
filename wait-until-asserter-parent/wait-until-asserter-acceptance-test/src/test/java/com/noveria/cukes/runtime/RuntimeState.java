package com.noveria.cukes.runtime;

import com.noveria.assertion.asserter.WaitUntilAsserter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class RuntimeState {

    private WaitUntilAsserter waitUntilAsserter;
    private int processingTime;

    public void setInitialState() {
        System.err.println("Setting an initial state");
    }

    public void somethingHappens() {
        System.err.println("Something has happened!");
    }

    public void expectResult() {
        System.err.println("Expected Result");
    }

    public void setWaitUntilAsserter(WaitUntilAsserter waitUntilAsserter) {
        this.waitUntilAsserter = waitUntilAsserter;
    }

    public WaitUntilAsserter getWaitUntilAsserter() {
        return waitUntilAsserter;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }
}
