package Mobile.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class APITests {

    public static String not_found_url = "https://www.review-b.build.account.gov.uk/";

    public void exampleTest() {

            RestAssured.given()
                    .post(not_found_url);
    }
}
