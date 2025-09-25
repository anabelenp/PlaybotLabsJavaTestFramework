package com.playbotlabs.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * TestRunner for Basic Form Elements tests
 * Executes Cucumber scenarios tagged with @basic-forms
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.playbotlabs.steps"},
    tags = "@wip",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/basic-forms/html-report",
        "json:target/cucumber-reports/basic-forms/json/Cucumber.json",
        "junit:target/cucumber-reports/basic-forms/xml/Cucumber.xml",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true,
    publish = false
)
@Test(groups = {"basic-forms"})
public class BasicFormElementsTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}