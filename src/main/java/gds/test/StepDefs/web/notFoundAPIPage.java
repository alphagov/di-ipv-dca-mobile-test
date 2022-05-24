package gds.test.StepDefs.web;


import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

class notFoundAPIPage implements En {
    public static String not_found_url = "https://www.review-b.build.account.gov.uk/";


    public notFoundAPIPage(){
        RestAssured.baseURI="https://www.review-b.build.account.gov.uk/";

//        Given("I load the google homepage", () -> {
//            homePage.goTo();
//        });
        Given("^I post the request to the page not found url$", () -> {
            RestAssured.given()
                    .get()
                    .then()
                    .statusCode(404);

        });
        Then("^I should receive the required page$", () -> {
           Response response =  RestAssured.given()
                   .get();
           response.statusCode();
           response.getBody().print().contains("Page not found");
            System.out.println(response.toString());
        });

    }

}
