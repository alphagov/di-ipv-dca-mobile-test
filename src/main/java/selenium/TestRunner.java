package deloitte.qte.examples.cucumberJUnit.selenium;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@jack",
        plugin = {"json:target/cucumber-report/cucumber.json"},
        features = "classpath:features",
        glue = {"deloitte.cucumber.example.StepDefs"}
)
public class TestRunner {
}
