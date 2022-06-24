package gov.di_dca_mobile.step_definitions;

import gov.di_dca_mobile.pages.AuthorizePageObject;
import io.cucumber.java.en.And;
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

//    @Then("the user is presented with the flashing colours screen")
//    public void theUserIsPresentedWithTheFlashingColoursScreen() {
//        Assert.assertEquals("The app uses flashing colours. Are you OK to continue?", flashingScreenHeader());
//        Assert.assertEquals("A unique pattern of flashing colours is used during the face scan to confirm your identity.", flashingScreenHintText());
//        flashingScreenHiddenTextLink();
//        Assert.assertEquals("The technology we use is tested to make sure it meets the regulations on safe flash rates for photosensitive epilepsy.", flashingScreenHiddenText());
//    }

    @When("the user clicks YES and continue to flashing colours screen")
    public void theUserClicksYESAndContinueToFlashingColoursScreen() {
        clickFlashingScreenYesButton();
        clickContinueButton();
    }

    @And("the user clicks YES and continue to the valid license screen")
    public void theUserClicksYESAndContinueToTheValidLicenseScreen() {
        clickValidLicenseYesButton();
        clickContinueButton();
    }

    @And("the user clicks YES and continue to the workable camera screen")
    public void theUserClicksYESAndContinueToTheWorkableCameraScreen() {
        clickWorkableCameraYesButton();
        clickContinueButton();
    }

    @And("the user selects YES and continue to the ready to begin screen")
    public void theUserSelectsYESAndContinueToTheReadyToBeginScreen() {
        clickReadyToBeginYesButton();
        clickContinueButton();
    }

    @Then("the user clicks the stub button and is directed back to HMRC")
    public void theUserClicksTheStubButtonAndIsDirectedBackToHMRC() {
        clickStubButton();
    }
}
