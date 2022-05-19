package Mobile.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class APITests {


    public void exampleTest() {

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        assertEquals(response.getStatusCode(), "200");
    }
@Test
    public void exampleTest2() {

        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200);

    }
}
