package com.noveria.cukes.steps;

import com.noveria.assertion.asserter.WaitUntilAsserter;
import com.noveria.assertion.expection.WaitUntilAssertionError;
import com.noveria.assertion.task.AssertTask;
import com.noveria.cukes.assertion.ProcessingTimeAssertTask;
import com.noveria.cukes.helpers.rest.RestHelper;
import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
@StepDefAnnotation
public class BasicStep {

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    RestHelper restHelper;

    @Given("^a service takes \"([^\"]*)\" milliseconds to complete$")
    public void an_initial_state(int processingTime) {
        restHelper.setProcessingTime(processingTime);
    }

    @And("^the assertion time out is set to \"([^\"]*)\" milliseconds$")
    public void the_assertion_time_out_is_set_to(long assertionTimeOut) {
        AssertTask assertTask = new ProcessingTimeAssertTask(restHelper);
        WaitUntilAsserter waitUntilAsserter = new WaitUntilAsserter(assertTask,assertionTimeOut);

        runtimeState.setWaitUntilAsserter(waitUntilAsserter);
    }

    @Then("^the assertion will pass")
    public void the_assertion_will_pass() throws InterruptedException {
        restHelper.callProcessOnServer();
        runtimeState.getWaitUntilAsserter().performAssertion();
    }

    @Then("^the assertion will fail")
    public void the_assertion_will_fail() throws InterruptedException {
        boolean failsAsExpected = false;

        try {
            restHelper.callProcessOnServer();
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
