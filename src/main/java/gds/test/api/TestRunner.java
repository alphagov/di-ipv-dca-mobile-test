package gds.test.api;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@test",
        plugin = {"json:target/cucumber-report/cucumber.json"},
        features = "classpath:features",
        glue = {"src.main.java.gds.test.StepDefs"}
)
public class TestRunner {

}
