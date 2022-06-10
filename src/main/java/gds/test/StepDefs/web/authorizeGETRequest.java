package gds.test.StepDefs.web;

import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;

public class authorizeGETRequest implements En{

    public authorizeGETRequest() {

        RestAssured.baseURI="https://www.review-b.build.account.gov.uk/";

        Given("^the /authorize endpoint is working$", () -> {
            RestAssured.given()
                    .get()
                    .then()
                    .statusCode(200);
        });
    }
}
