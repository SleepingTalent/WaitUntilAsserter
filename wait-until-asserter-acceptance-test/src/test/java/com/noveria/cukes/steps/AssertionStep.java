package com.noveria.cukes.steps;

import com.noveria.assertion.asserter.WaitUntilAsserter;
import com.noveria.assertion.exception.WaitUntilAssertionError;
import com.noveria.cukes.assertion.ProcessingTimeAssertTask;
import com.noveria.cukes.helpers.rest.RestHelper;
import com.noveria.cukes.helpers.thread.ThreadHelper;
import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class AssertionStep {

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    RestHelper restHelper;

    @Autowired
    ThreadHelper threadHelper;

    @Given("^a service process takes \"([^\"]*)\" milliseconds to complete$")
    public void an_initial_state(int processingTime) {
        runtimeState.setProcessingTime(processingTime);
    }

    @And("^the assertion time out is set to \"([^\"]*)\" milliseconds$")
    public void the_assertion_time_out_is_set_to(long assertionTimeOut) {
        WaitUntilAsserter waitUntilAsserter = new ProcessingTimeAssertTask(restHelper,assertionTimeOut);
        runtimeState.setWaitUntilAsserter(waitUntilAsserter);
    }

    @Then("^the assertion will pass")
    public void the_assertion_will_pass() throws InterruptedException {
        threadHelper.startProcessCompleteUpdaterThread(runtimeState.getProcessingTime());
        runtimeState.getWaitUntilAsserter().performAssertion();
    }

    @Then("^the assertion will fail")
    public void the_assertion_will_fail() throws InterruptedException {
        boolean failsAsExpected = false;

        try {
            threadHelper.startProcessCompleteUpdaterThread(runtimeState.getProcessingTime());
            runtimeState.getWaitUntilAsserter().performAssertion();

        }catch (WaitUntilAssertionError waitUntilAssertionError) {
            failsAsExpected = true;
        }

        assertTrue("Expected Assertion to Fail!",failsAsExpected);
    }

    @After
    public void tearDown() {
        restHelper.setProcessingComplete(false);
    }
}
