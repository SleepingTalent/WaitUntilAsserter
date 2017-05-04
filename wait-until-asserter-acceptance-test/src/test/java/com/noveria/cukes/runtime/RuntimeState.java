package com.noveria.cukes.runtime;

import com.noveria.assertion.asserter.WaitUntilAsserter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class RuntimeState {

    private WaitUntilAsserter waitUntilAsserter;
    private int processingTime;
    private String host;

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

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}
