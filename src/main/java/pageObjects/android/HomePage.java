package pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.iOS.BasePage;

public class HomePage extends BasePage {

    String URL = "https://google.com";
    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")
    private WebElement clickGoogleBar;
    @FindBy(xpath = "//*[@id=\"rso\"]/div[1]/div/div/div[1]/div/div/div[1]/div/a/h3")
    private WebElement firstResultPage;

    public HomePage() {
        super();
    }

    public void goTo() {
        visit(URL);
    }

    public void getClickGoogleBar() {
        clickGoogleBar.click();
    }

    public void enterGoogleBarChar() {
        clickGoogleBar.sendKeys("hello world");
    }

    public String readFirstChar() {
        return firstResultPage.getText();

    }


}
