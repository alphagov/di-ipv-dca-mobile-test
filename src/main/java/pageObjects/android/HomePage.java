package deloitte.qte.examples.cucumberJUnit.pageObjects;

public class HomePage extends BasePage {

    String URL = "https://google.com";


    public HomePage() {
        super();
    }

    public void goTo(){
        visit(URL);
    }





}
