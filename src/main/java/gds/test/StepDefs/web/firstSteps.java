package gds.test.StepDefs.web;

import deloitte.qte.examples.cucumberJUnit.pageObjects.HomePage;
import io.cucumber.java8.En;

public class firstSteps implements En {

    public HomePage homePage;

    public firstSteps(){

        homePage = new HomePage();

        Given("I load the google homepage", () -> {
            homePage.goTo();
        });


    }

}
