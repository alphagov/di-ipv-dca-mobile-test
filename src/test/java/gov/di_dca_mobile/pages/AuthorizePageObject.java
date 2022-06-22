package gov.di_dca_mobile.pages;

import gov.di_dca_mobile.utilities.ConfigurationReader;
import gov.di_dca_mobile.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizePageObject extends gov.di_dca_mobile.pages.UniversalSteps {
    @FindBy(xpath = "//*[@id='main-content']/div/div/div/div[1]/div[3]/form/button/text()")
    public WebElement authorizeContinueButton;

    // Flashing Screen
    @FindBy(xpath ="//*[@id=\"main-content\"]/div/div/h1") public WebElement flashingScreenHeader;
    @FindBy(xpath ="//*[@id=\"main-content\"]/div/div/p") public WebElement flashingScreenHintText;
    @FindBy(xpath ="//*[@id=\"flashing-colours-info\"]") public WebElement flashingScreenYesButton;
    @FindBy(xpath ="//*[@id=\"flashing-colours-info-2\"]") public WebElement flashingScreenNoButton;
    @FindBy(xpath ="//*[@id=\"main-content\"]/div/div/details/summary/span") public WebElement flashingScreenHiddenTextLink;
    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div/details/div") public WebElement flashingScreenHiddenText;

    // Valid License Confirmation Screen
//    @FindBy(xpath ="//*[@id=\"main-content\"]/div/div/h1") public WebElement verifyLicenseScreenHeader;
//    @FindBy(xpath ="") public WebElement verifyLicenseHintText;
//    @FindBy(xpath ="") public WebElement verifyLicenseYesButton;
//    @FindBy(xpath ="") public WebElement verifyLicenseNoButton;
//    @FindBy(xpath ="") public WebElement verifyLicenseHiddenText;



    public void navigateToAuthorizeHomepage() {
        Driver.get().get(ConfigurationReader.get("homepageDev"));
        waitForFiveSeconds();
    }

    public void clickAuthorizeContinueButton() {
        authorizeContinueButton.click();
    }

    public String flashingScreenHeader(){
        flashingScreenHeader.getText();
        return null;
    }



}
