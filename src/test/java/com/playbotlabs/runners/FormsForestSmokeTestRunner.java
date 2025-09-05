package com.playbotlabs.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/forms-forest.feature",
        glue = {"com.playbotlabs.steps"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/forms-forest-smoke/html-report",
                "json:target/cucumber-reports/forms-forest-smoke/json/Cucumber.json",
                "junit:target/cucumber-reports/forms-forest-smoke/xml/Cucumber.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        tags = "@forms-forest and @smoke"
)
@Test(groups = {"forms-forest", "smoke"})
public class FormsForestSmokeTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}