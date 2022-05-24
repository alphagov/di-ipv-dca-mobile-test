package gds.test.api;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() { driver.quit(); }
        });
    }

    public static WebDriver driver;

    public WebDriver getDriver(){
        if (driver == null){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            return driver;
        }else {
            return driver;
        }
    }



}
