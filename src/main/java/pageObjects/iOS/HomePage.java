package pageObjects.iOS;

public class HomePage extends BasePage {

    String URL = "";


    public HomePage() {
        super();
    }

    public void goTo(){
        visit(URL);
    }





}
