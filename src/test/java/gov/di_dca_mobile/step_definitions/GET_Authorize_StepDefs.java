package gov.di_dca_mobile.step_definitions;

import gov.di_dca_mobile.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GET_Authorize_StepDefs {

    public String requestValue = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL3d3dy5yZXZpZXctYi5idWlsZC5hY2NvdW50Lmdvdi51ay9zdHViL2NhbGxiYWNrIiwicmVzcG9uc2VfdHlwZSI6ImNvZGUiLCJhdWQiOiJodHRwczovL2V4cGVyaWFuLmNyaS5hY2NvdW50Lmdvdi51ay8iLCJpc3MiOiJodHRwczovL2lwdi5jb3JlLmFjY291bnQuZ292LnVrIiwiY2xpZW50X2lkIjoiY2QyY2M4YjUtMzA0YS00NmU4LTliMDQtMGU5MDQzOGMxOGJlIiwic3RhdGUiOiJhZjBpZmpzbGRraiIsImlhdCI6MTY1NTQ4MjM2MiwibmJmIjoxNjU1NDgyMzYyLCJleHAiOjE5NzA4NDIzNjJ9.H6ytRxbxb2l5zJVB1IYuXZe845dboHzJwM-g3ufjTF_GZHbZxxljsdDfPyNmZvqotWZ8lF0Q_EcAP5G1KAvQIQ";
    public String responseTypeValue = "code";
    public String clientIdValue = "cd2cc8b5-304a-46e8-9b04-0e90438c18be";
    public String codeValue = "22a2435-8e17-41c9-a65e-b827b3592124";
    public String stateValue = "af0ifsldkj";
    public String redirectUriValue = "https%3A%2F%2Fwww.review-b.build.account.gov.uk%2Fstub%2Fcallback";
    public String grantTypeValue = "authorization_code";

    String result = "";
    InputStream inputStream;

    public String getPropValues() throws IOException {
        try {
            Properties prop = new Properties();
            String propFileName = "configuration.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Given("the authorize endpoint is working")
    public void the_authorize_endpoint_is_working() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get("https://www.review-b.build.account.gov.uk/dca/oauth2/authorize");
    }

    @When("I sent a valid GET request to authorize endpoint")
    public void iSentAValidGETRequestToAuthorizeEndpoint() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("request", requestValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("client_id", clientIdValue)
                .when().post(ConfigurationReader.get("verifyDev"));

    }

    @Then("I should receive a valid OK response is displayed")
    public void iShouldReceiveAValidOKResponseIsDisplayed() {
        Response authorizeRequest = RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("request", requestValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("client_id", clientIdValue)
                .when().post(ConfigurationReader.get("verifyDev"));
        Assert.assertEquals(200, authorizeRequest.statusCode());
        authorizeRequest.getBody().print();
    }

    @When("I sent a valid GET request to authorize endpoint with no parameters added")
    public void iSentAValidGETRequestToAuthorizeEndpointWithNoParametersAdded() {
        Response verifyAuth = RestAssured.given()
                .accept(ContentType.JSON)
                .when().post(ConfigurationReader.get("verifyDev"));
        verifyAuth.getBody().asString();
    }


    @Then("I should receive a invalid response is displayed")
    public void iShouldReceiveAInvalidResponseIsDisplayed() {
        Response verifyAuth = RestAssured.given()
                .accept(ContentType.JSON)
                .when().post(ConfigurationReader.get("verifyDev"));
        verifyAuth.getBody().asString();
        Assert.assertEquals(401, verifyAuth.statusCode());
    }

    @When("I sent a valid GET request to authorize endpoint with redirect uri is missing")
    public void iSentAValidGETRequestToAuthorizeEndpointWithRedirectUriIsMissing() {
        Response verifyAuth = RestAssured.given()
                .accept(ContentType.JSON)
                .when().post(ConfigurationReader.get("verifyDev"));
        verifyAuth.getBody().asString();
        Assert.assertEquals(401, verifyAuth.statusCode());
    }

    @And("add the following {string} to the request")
    public void addTheFollowingToTheRequest(String invalid_JWT) {
        switch (invalid_JWT){

            case "invalid_client_id":
                System.out.println("eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL3d3dy5yZXZpZXctYi5idWlsZC5hY2NvdW50Lmdvdi51ay9zdHViL2NhbGxiYWNrIiwicmVzcG9uc2VfdHlwZSI6ImNvZGUiLCJhdWQiOiJodHRwczovL2V4cGVyaWFuLmNyaS5hY2NvdW50Lmdvdi51ay8iLCJpc3MiOiJodHRwczovL2lwdi5jb3JlLmFjY291bnQuZ292LnVrIiwiY2xpZW50X2lkIjoiYmFkIGNsaWVudCBpZCIsInN0YXRlIjoiYWYwaWZqc2xka2oiLCJpYXQiOjE2NTU4MDk1MTgsIm5iZiI6MTY1NTgwOTUxOCwiZXhwIjoxOTcwODIzMTExMDAwfQ.3K-p33Uf4O0pHLgU7Wqt1MR9iK8GOzVsaUKldzX3zDWzpl5EZ2uZzaK2vuH251WJaGq_15KKZfrYTh6fdd96ow");
                break;
            case "bad_private_key":
                break;
            case "missing_redirect_uri":
                break;
            case "invalid_redirect_uri":
                break;
            case "invalid_response_type":
                break;
            case "missing_iss_field":
                break;
            case "expired_jwt":
                break;
            case "future_jwt":
                break;
        }
        Response verifyAuth = RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("client_id", clientIdValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("", invalid_JWT)
                .when().post(ConfigurationReader.get("verifyDev"));
        verifyAuth.getBody().asString();
        Assert.assertEquals(401, verifyAuth.statusCode());
    }
}

