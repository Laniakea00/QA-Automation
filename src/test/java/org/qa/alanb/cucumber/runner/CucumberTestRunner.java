package org.qa.alanb.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.qa.alanb.cucumber.steps", "org.qa.alanb.cucumber.hooks"},

plugin = {
                "pretty",
                "html:build/reports/cucumber-report.html",
                "json:build/reports/cucumber-report.json"
        },
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
