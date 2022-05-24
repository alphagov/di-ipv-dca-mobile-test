package gds.test.StepDefs.web;
import io.cucumber.java8.En;
import pageObjects.android.HomePage;

public class firstSteps implements En {

    public HomePage homePage;

    public firstSteps(){

        homePage = new HomePage();

        Given("I load the google homepage", () -> {
            homePage.goTo();
        });


    }

}
