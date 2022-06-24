package gov.di_dca_mobile.pages;

import gov.di_dca_mobile.utilities.ConfigurationReader;
import gov.di_dca_mobile.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AuthorizePageObject extends gov.di_dca_mobile.pages.UniversalSteps {


    @FindBy(xpath = "//button[contains(text(), 'Continue')]")
    public WebElement authorizeContinueButton;

    // Flashing Screen
//    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div/h1")
//    public WebElement flashingScreenHeader;
//    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div/p")
//    public WebElement flashingScreenHintText;
    @FindBy(xpath = "//*[@id=\"flashing-colours-choice\"]")
    public WebElement flashingScreenYesButton;
    @FindBy(xpath = "//*[@id=\"flashing-colours-info-2\"]")
    public WebElement flashingScreenNoButton;
//    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div/details/summary/span")
//    public WebElement flashingScreenHiddenTextLink;
//    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div/details/div")
//    public WebElement flashingScreenHiddenText;

    // Valid License Confirmation Screen
//    @FindBy(xpath ="//*[@id=\"main-content\"]/div/div/h1") public WebElement verifyLicenseScreenHeader;
//    @FindBy(xpath ="") public WebElement verifyLicenseHintText;
    @FindBy(xpath ="//*[@id=\"driving-licence-choice\"]") public WebElement verifyLicenseYesButton;
    @FindBy(xpath ="//*[@id=\"driving-licence-choice-2\"]") public WebElement verifyLicenseNoButton;
//    @FindBy(xpath ="") public WebElement verifyLicenseHiddenText;

    //Workable Camera Screen
    @FindBy(xpath = "//*[@id=\"working-camera-choice\"]") public WebElement workableCameraYesButton;
    @FindBy(xpath = "//*[@id=\"working-camera-choice-2\"]") public WebElement workableCameraNoButton;

    //Ready To Begin Screen
    @FindBy(xpath = "//*[@id=\"ready-to-begin-choice\"]") public WebElement readyToBeginYesButton;
    @FindBy(xpath = "//*[@id=\"ready-to-begin-choice-2\"]") public WebElement readyToBeginNoButton;

    //STUB Button
    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div/a[2]") public WebElement stubButton;
    public AuthorizePageObject() {
        PageFactory.initElements(Driver.get(), this);
    }


    public void navigateToAuthorizeHomepage() {
        Driver.get().get(ConfigurationReader.get("homepageDev"));
        waitForFiveSeconds();
    }

    public void clickAuthorizeContinueButton() {
        waitForFiveSeconds();
        authorizeContinueButton.click();
    }
//
//    public String flashingScreenHeader() {
//       return flashingScreenHeader.getText();
//    }

    public void clickFlashingScreenYesButton() {
        waitForFiveSeconds();
        flashingScreenYesButton.click();
    }

    public void flashingScreenNoButton() {
        flashingScreenNoButton.click();
    }
//
//    public String flashingScreenHintText() {
//        return flashingScreenHintText.getText();
//    }
//
//    public String flashingScreenHiddenTextLink() {
//        return flashingScreenHiddenTextLink.getText();
//    }
//
//    public String flashingScreenHiddenText() {
//        return flashingScreenHiddenText.getText();
//    }

    public void clickContinueButton (){
        waitForFiveSeconds();
        continueButton.click();
    }

    public void clickValidLicenseYesButton() {
        waitForFiveSeconds();
        verifyLicenseYesButton.click();
    }

    public void clickWorkableCameraYesButton() {
        waitForFiveSeconds();
        workableCameraYesButton.click();
    }

    public void clickReadyToBeginYesButton() {
        waitForFiveSeconds();
        readyToBeginYesButton.click();
    }

    public void clickStubButton() {
        waitForFiveSeconds();
        stubButton.click();
    }

}
