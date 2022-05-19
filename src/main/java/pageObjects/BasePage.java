import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BasePage {

    public static IOSDriver DriverCapabilities() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12 Pro");
        // iOS XCUITests
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 50000);
        capabilities.setCapability("commandTimeouts", "12000");

        //Need to add the .app local filepath within the quotation
//        public void initializeDriver(String deviceID) {
//            switch (deviceID) {
//                case "iOS":
//                    capabilities.setCapability(MobileCapabilityType.APP, "ios");
//                    break;
//                case "android":
//                    capabilities.setCapability(MobileCapabilityType.APP, "android");
//                    break;
//                default:
//                    throw new IllegalStateException("Invalid device ID" + deviceID);
//            }
//        }
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/mmikely/Downloads/ios-uicatalog-master/UIKitCatalog.app");

        //Add the appium port that it's listening too - similar to ChromeDriver
        IOSDriver driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
        return driver;
    }
}
