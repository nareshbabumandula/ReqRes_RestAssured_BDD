package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = {"stepdefinitions", "hooks"},
plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", 
        "html:reports/html-report.html",                   
        "json:reports/cucumber.json"
    },
    monochrome = true,
    publish = true,tags = "@smoke")
public class TestRunner {

}
