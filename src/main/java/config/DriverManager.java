import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.options.CanSetCapability;

public class DriverManager extends BasePage {

    //    #DriverPage
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
//    private static CanSetCapability<> capabilities = new CanSetCapability<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }
    public static void setDriver(AppiumDriver driver1) {
        driver.set(driver1);
    }

//    public static void initializeDriver(String deviceID) throws Exception{
//        switch (deviceID){
//            case "iOS":
//                capabilities.setCapability(MobileCapabilityType.APP, "");
//                break;
//            case "android":
//                capabilities.setCapability(MobileCapabilityType.APP, "");
//                break;
//            default:
//                throw new IllegalStateException("Invalid device ID" + deviceID);
//
//
//        }
    }

