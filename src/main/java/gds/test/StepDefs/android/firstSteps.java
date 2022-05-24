package gds.test.StepDefs.android;

import io.cucumber.java8.En;
import org.junit.Assert;
import pageObjects.android.HomePage;

public class firstSteps implements En {

    public HomePage homePage;

    public firstSteps() {

        homePage = new HomePage();

        Given("I load the google homepage", () -> {
            homePage.goTo();
        });
        When("^I enter the Hello World text$", () -> {
            homePage.getClickGoogleBar();
            homePage.enterGoogleBarChar();
        });
        Then("^I am able to receive a Hello World response$", () -> {
        homePage.readFirstChar();
        });

    }

}
