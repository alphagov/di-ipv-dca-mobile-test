package gov.di_dca_mobile.step_definitions;

import gov.di_dca_mobile.pages.AuthorizePageObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class E2EScenariosFEStepDefs extends AuthorizePageObject {

    @Given("the user navigates to the authorize homepage")
    public void theUserNavigatesToTheAuthorizeHomepage() {
        navigateToAuthorizeHomepage();
    }

    @When("the user clicks on the continue button")
    public void theUserClicksOnTheContinueButton() {
        clickAuthorizeContinueButton();
//        check URL is matches the existing
    }

    @Then("the user is presented with the flashing colours screen")
    public void theUserIsPresentedWithTheFlashingColoursScreen() {
        Assert.assertEquals("The app uses flashing colours. Are you OK to continue?", flashingScreenHeader());
    }
}
