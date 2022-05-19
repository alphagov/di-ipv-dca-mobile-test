import io.appium.java_client.ios.IOSDriver;

public class ClickCommand extends BasePage {

    public static void clickCommand() throws Exception {

        IOSDriver driver = DriverCapabilities();
        driver.findElementByAccessibilityId("Alert Views").click();

        //To create own xpath it's: tagname[@attribute='value']
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Text Entry").click();
        driver.findElementByXPath("//XCUIElementTypeCell").sendKeys("My name is");
        driver.findElementByAccessibilityId("OK").click();
        driver.findElementByAccessibilityId("Confirm / Cancel").click();
        driver.findElementByXPath("//*[contains(@name, 'message')").getText();
        driver.findElementByAccessibilityId("Confirm").click();
        }
    }

