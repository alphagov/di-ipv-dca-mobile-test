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
    public String jwtInvalidPrivateKey = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL3d3dy5yZXZpZXctYi5idWlsZC5hY2NvdW50Lmdvdi51ay9zdHViL2NhbGxiYWNrIiwicmVzcG9uc2VfdHlwZSI6ImNvZGUiLCJhdWQiOiJodHRwczovL2V4cGVyaWFuLmNyaS5hY2NvdW50Lmdvdi51ay8iLCJpc3MiOiJodHRwczovL2lwdi5jb3JlLmFjY291bnQuZ292LnVrIiwiY2xpZW50X2lkIjoiY2QyY2M4YjUtMzA0YS00NmU4LTliMDQtMGU5MDQzOGMxOGJlIiwic3RhdGUiOiJhZjBpZmpzbGRraiIsImlhdCI6MTY1NTgwODk1OCwibmJmIjoxNjU1ODA4OTU4LCJleHAiOjE5NzA4MjMxMTEwMDB9.Yhbs55CBAdpmnaVfJ804XWwYq4AfTRz-8D007Zf-NSiltBMtqDlzddIsahrzWnoe9fPHUZdX9Zm3kJ8TXZ-M_Q";
    public String jwtMissingRedirectUri = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZXNwb25zZV90eXBlIjoiY29kZSIsImF1ZCI6Imh0dHBzOi8vZXhwZXJpYW4uY3JpLmFjY291bnQuZ292LnVrLyIsImlzcyI6Imh0dHBzOi8vaXB2LmNvcmUuYWNjb3VudC5nb3YudWsiLCJjbGllbnRfaWQiOiJjZDJjYzhiNS0zMDRhLTQ2ZTgtOWIwNC0wZTkwNDM4YzE4YmUiLCJzdGF0ZSI6ImFmMGlmanNsZGtqIiwiaWF0IjoxNjU1ODA5MTEwLCJuYmYiOjE2NTU4MDkxMTAsImV4cCI6MTk3MDgyMzExMTAwMH0.JfMMpq4y3DH2mG7D_GhejyRESephTr32sYFu240GAHEjP-8z3jZXKjpHTyBMIM2OD47Mc9o4zBu720ke5sfdzA";
    public String jwtInvalidRedirectUri = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL25vdC1yZWdpc3RlcmVkLmNvbSIsInJlc3BvbnNlX3R5cGUiOiJjb2RlIiwiYXVkIjoiaHR0cHM6Ly9leHBlcmlhbi5jcmkuYWNjb3VudC5nb3YudWsvIiwiaXNzIjoiaHR0cHM6Ly9pcHYuY29yZS5hY2NvdW50Lmdvdi51ayIsImNsaWVudF9pZCI6ImNkMmNjOGI1LTMwNGEtNDZlOC05YjA0LTBlOTA0MzhjMThiZSIsInN0YXRlIjoiYWYwaWZqc2xka2oiLCJpYXQiOjE2NTU4MDkyNDIsIm5iZiI6MTY1NTgwOTI0MiwiZXhwIjoxOTcwODIzMTExMDAwfQ.UXKaFdduVPKFUiKTRH7SUeSTLkNXpo8D4uUrD7g_yHDFY-iUznccvbAwr-L5oD9NaCrckG2bFgrWfDXWqF7DQg";
    public String jwtInvalidClientId = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL3d3dy5yZXZpZXctYi5idWlsZC5hY2NvdW50Lmdvdi51ay9zdHViL2NhbGxiYWNrIiwicmVzcG9uc2VfdHlwZSI6ImNvZGUiLCJhdWQiOiJodHRwczovL2V4cGVyaWFuLmNyaS5hY2NvdW50Lmdvdi51ay8iLCJpc3MiOiJodHRwczovL2lwdi5jb3JlLmFjY291bnQuZ292LnVrIiwiY2xpZW50X2lkIjoiYmFkIGNsaWVudCBpZCIsInN0YXRlIjoiYWYwaWZqc2xka2oiLCJpYXQiOjE2NTU4MDk1MTgsIm5iZiI6MTY1NTgwOTUxOCwiZXhwIjoxOTcwODIzMTExMDAwfQ.3K-p33Uf4O0pHLgU7Wqt1MR9iK8GOzVsaUKldzX3zDWzpl5EZ2uZzaK2vuH251WJaGq_15KKZfrYTh6fdd96ow";
    public String jwtInvalidResponseType = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL3d3dy5yZXZpZXctYi5idWlsZC5hY2NvdW50Lmdvdi51ay9zdHViL2NhbGxiYWNrIiwicmVzcG9uc2VfdHlwZSI6ImJhZCByZXNwb25zZSB0eXBlIiwiYXVkIjoiaHR0cHM6Ly9leHBlcmlhbi5jcmkuYWNjb3VudC5nb3YudWsvIiwiaXNzIjoiaHR0cHM6Ly9pcHYuY29yZS5hY2NvdW50Lmdvdi51ayIsImNsaWVudF9pZCI6ImNkMmNjOGI1LTMwNGEtNDZlOC05YjA0LTBlOTA0MzhjMThiZSIsInN0YXRlIjoiYWYwaWZqc2xka2oiLCJpYXQiOjE2NTU4MDk2MjIsIm5iZiI6MTY1NTgwOTYyMiwiZXhwIjoxOTcwODIzMTExMDAwfQ.EAJjYxS0ewuUrAi4Ccht9YPrzibORbtEMrHeo-J85r7P8r4QtlLhOXKGJRvTRTWgwp63AFs4RxUaQ3H25AKzew";
    public String jwtMissingIss = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL3d3dy5yZXZpZXctYi5idWlsZC5hY2NvdW50Lmdvdi51ay9zdHViL2NhbGxiYWNrIiwicmVzcG9uc2VfdHlwZSI6ImNvZGUiLCJhdWQiOiJodHRwczovL2V4cGVyaWFuLmNyaS5hY2NvdW50Lmdvdi51ay8iLCJjbGllbnRfaWQiOiJjZDJjYzhiNS0zMDRhLTQ2ZTgtOWIwNC0wZTkwNDM4YzE4YmUiLCJzdGF0ZSI6ImFmMGlmanNsZGtqIiwiaWF0IjoxNjU1ODA5MzA3LCJuYmYiOjE2NTU4MDkzMDcsImV4cCI6MTk3MDgyMzExMTAwMH0.xqNUjAmlRGepWK1rAcwTgcv8ZnwsWJUU-kLaMgi_pCTYbnlXiseBp_2arzRFYRb9h2P1InJaWoXlqkVmdS8Khw";
    public String jwtExpired = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL3d3dy5yZXZpZXctYi5idWlsZC5hY2NvdW50Lmdvdi51ay9zdHViL2NhbGxiYWNrIiwicmVzcG9uc2VfdHlwZSI6ImNvZGUiLCJhdWQiOiJodHRwczovL2V4cGVyaWFuLmNyaS5hY2NvdW50Lmdvdi51ay8iLCJpc3MiOiJodHRwczovL2lwdi5jb3JlLmFjY291bnQuZ292LnVrIiwiY2xpZW50X2lkIjoiY2QyY2M4YjUtMzA0YS00NmU4LTliMDQtMGU5MDQzOGMxOGJlIiwic3RhdGUiOiJhZjBpZmpzbGRraiIsImlhdCI6MTY1NTgxMDA4MCwibmJmIjoxNjU1ODEwMDgwLCJleHAiOjE2NTU4MDk5MDB9.EWEgEUe4HSsUHMHw1nbsD23MvpeDI-uX1Q1IbF04LwXYNUQTNTpVFRVY1X_gE66KQtVVz9h6hk4QBwYijJIGvw";
    public String jwtFuture = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL3d3dy5yZXZpZXctYi5idWlsZC5hY2NvdW50Lmdvdi51ay9zdHViL2NhbGxiYWNrIiwicmVzcG9uc2VfdHlwZSI6ImNvZGUiLCJhdWQiOiJodHRwczovL2V4cGVyaWFuLmNyaS5hY2NvdW50Lmdvdi51ay8iLCJpc3MiOiJodHRwczovL2lwdi5jb3JlLmFjY291bnQuZ292LnVrIiwiY2xpZW50X2lkIjoiY2QyY2M4YjUtMzA0YS00NmU4LTliMDQtMGU5MDQzOGMxOGJlIiwic3RhdGUiOiJhZjBpZmpzbGRraiIsImlhdCI6MTY1NTgwOTkyOCwibmJmIjoxOTcwODIzMTExMDAwLCJleHAiOjE5NzA4MjMxMTEwMDB9.KQ2wmUS__PoDVw9ZqA_biSaOSFSvm2zuvYzAVlKYWmGuAhvzga7m1YPTYOybpsTpX92_MruG16ykoc9fUJmy8A";

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
                .when().get(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
    }

    @When("I sent a valid GET request to authorize endpoint")
    public void iSentAValidGETRequestToAuthorizeEndpoint() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("request", requestValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("client_id", clientIdValue)
                .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");

    }

    @Then("I should receive a valid OK response is displayed")
    public void iShouldReceiveAValidOKResponseIsDisplayed() {
        Response authorizeRequest = RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("request", requestValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("client_id", clientIdValue)
                .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
        Assert.assertEquals(200, authorizeRequest.statusCode());
        authorizeRequest.getBody().print();
    }

    @When("I sent a valid GET request to authorize endpoint with no parameters added")
    public void iSentAValidGETRequestToAuthorizeEndpointWithNoParametersAdded() {
        Response verifyAuth = RestAssured.given()
                .accept(ContentType.JSON)
                .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
        verifyAuth.getBody().asString();
    }


    @Then("I should receive a invalid response is displayed")
    public void iShouldReceiveAInvalidResponseIsDisplayed() {
        Response verifyAuth = RestAssured.given()
                .accept(ContentType.JSON)
                .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
        verifyAuth.getBody().asString();
        Assert.assertEquals(401, verifyAuth.statusCode());
    }

    @When("I sent a valid GET request to authorize endpoint with redirect uri is missing")
    public void iSentAValidGETRequestToAuthorizeEndpointWithRedirectUriIsMissing() {
        Response verifyAuth = RestAssured.given()
                .accept(ContentType.JSON)
                .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
        verifyAuth.getBody().asString();
        Assert.assertEquals(401, verifyAuth.statusCode());
    }

    @And("add the following {string} to the request")
    public void addTheFollowingToTheRequest(String invalid_JWT) {
        switch (invalid_JWT){

            case "invalid_client_id": RestAssured.given()
                    .accept(ContentType.JSON)
                    .queryParam("response_type", responseTypeValue)
                    .queryParam("client_id", clientIdValue)
                    .queryParam("request", jwtInvalidClientId)
                    .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
                break;
            case "bad_private_key": RestAssured.given()
                    .accept(ContentType.JSON)
                    .queryParam("response_type", responseTypeValue)
                    .queryParam("client_id", clientIdValue)
                    .queryParam("request", jwtInvalidPrivateKey)
                    .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
                break;
            case "missing_redirect_uri": RestAssured.given()
                    .accept(ContentType.JSON)
                    .queryParam("response_type", responseTypeValue)
                    .queryParam("client_id", clientIdValue)
                    .queryParam("request", jwtMissingRedirectUri)
                    .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
                break;
            case "invalid_redirect_uri":
                RestAssured.given()
                        .accept(ContentType.JSON)
                        .queryParam("response_type", responseTypeValue)
                        .queryParam("client_id", clientIdValue)
                        .queryParam("request", jwtInvalidRedirectUri)
                        .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
                break;
            case "invalid_response_type":RestAssured.given()
                    .accept(ContentType.JSON)
                    .queryParam("response_type", responseTypeValue)
                    .queryParam("client_id", clientIdValue)
                    .queryParam("request", jwtInvalidResponseType)
                    .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
                break;
            case "missing_iss_field":
                RestAssured.given()
                        .accept(ContentType.JSON)
                        .queryParam("response_type", responseTypeValue)
                        .queryParam("client_id", clientIdValue)
                        .queryParam("request", jwtMissingIss)
                        .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
                break;
            case "expired_jwt":RestAssured.given()
                    .accept(ContentType.JSON)
                    .queryParam("response_type", responseTypeValue)
                    .queryParam("client_id", clientIdValue)
                    .queryParam("request", jwtExpired)
                    .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
                break;
            case "future_jwt":
                RestAssured.given()
                        .accept(ContentType.JSON)
                        .queryParam("response_type", responseTypeValue)
                        .queryParam("client_id", clientIdValue)
                        .queryParam("request", jwtFuture)
                        .when().post(ConfigurationReader.get("APIDev") + "/verifyAuthorizeRequest");
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

