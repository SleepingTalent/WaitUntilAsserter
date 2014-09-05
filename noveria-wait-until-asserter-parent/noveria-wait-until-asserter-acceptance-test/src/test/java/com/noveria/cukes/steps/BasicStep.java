package com.noveria.cukes.steps;

import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
@StepDefAnnotation
public class BasicStep {

    @Autowired
    RuntimeState runtimeState;

    @Given("an initial state")
    public void an_initial_state() {
        runtimeState.setInitialState();
    }

    @When("something happens")
    public void something_happens() {
        runtimeState.somethingHappens();
    }

    @Then("expect result")
    public void expected_result() {
        runtimeState.expectResult();
    }
}
