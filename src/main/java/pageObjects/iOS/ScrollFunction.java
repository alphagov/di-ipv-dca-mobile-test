package pageObjects.iOS;

import io.appium.java_client.ios.IOSDriver;

import java.util.HashMap;

public class ScrollFunction extends BasePage {
    public static void scrollFunction() throws Exception {

        IOSDriver driver = BasePage.DriverCapabilities();

        // If scroll function does not work with Appium, use the below JS
        HashMap<String, Object>scrollObject = new HashMap<>();
        //Direction to scroll
        scrollObject.put("direction", "down");
        //Which element it needs to scroll until
        scrollObject.put("name", "Web View");

        //Scroll function in JS
        driver.executeScript("mobile:scroll", scrollObject);

        driver.findElementByAccessibilityId("Web View").click();
    }
}
