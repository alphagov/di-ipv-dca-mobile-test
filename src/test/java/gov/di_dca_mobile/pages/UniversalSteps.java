package gov.di_dca_mobile.pages;

import gov.di_dca_mobile.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;


public class UniversalSteps {

    @FindBy (xpath = "//button[contains(text(), 'Continue')]") public WebElement continueButton;

    public void waitForFiveSeconds() {
        BrowserUtils.waitForPageToLoad(5);
    }

    public void continueButton(){

        continueButton.click();}
}
