package deloitte.qte.examples.cucumberJUnit.pageObjects;

import deloitte.qte.examples.cucumberJUnit.config.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BasePage{

    protected WebDriver driver;
    DriverFactory driverFactory = new DriverFactory();

    public BasePage() {
        this.driver = driverFactory.getDriver();
    }

    public WebElement find(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void submit(By locator) {
        find(locator).submit();
    }

    public void type(String inputText, By locator) {
        find(locator).sendKeys(inputText);
    }

    public void clear(By locator) {
        find(locator).clear();
    }

    public void click(By locator) {
        find(locator).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }



    public void visit(String url) {
        driver.get(url);
    }

    /**
     * returns the text from the provided DOM locator using Selenium's getText method
     * @param locator
     * @return String representing the inner HTML of the DOM element (MW: To check it is actually inner-text
     */
    public String getText(By locator) {
        return find(locator).getText();
    }

    /**
    *  searches for the provided text in the current page Url
    *  @param searchString partial text to locate within the page url
    *  @exception IllegalStateException
    */
    public void valCorrectPage(String searchString) {
        if (!getPageTitle().contains(searchString)) {
            throw new IllegalStateException("This is not " + searchString + " .The actual Url is: " + getUrl());
        }
    }

    protected void takeScreenshot(RuntimeException e, String fileName) {
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File(fileName + ".png"));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
        throw e;
    }
}
