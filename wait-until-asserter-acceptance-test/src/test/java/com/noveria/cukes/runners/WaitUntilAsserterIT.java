package com.noveria.cukes.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict=false, format={"pretty","html:target/cucumber-report","json:target/cucumber-report/result.json"}, glue="com.noveria.cukes",
        features="classpath:features", tags={"~@wip"}, monochrome = true)
public class WaitUntilAsserterIT {


}
