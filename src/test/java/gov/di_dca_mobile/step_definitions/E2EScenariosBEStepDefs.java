package gov.di_dca_mobile.step_definitions;

import gov.di_dca_mobile.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

public class E2EScenariosBEStepDefs {

    public String requestValue = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBOTY1MjE0MS1BNzNELTQyQjUtOEU3OC03RDA1RUQzQkNBMDMiLCJyZWRpcmVjdF91cmkiOiJodHRwczovL3d3dy5yZXZpZXctYi5idWlsZC5hY2NvdW50Lmdvdi51ay9zdHViL2NhbGxiYWNrIiwicmVzcG9uc2VfdHlwZSI6ImNvZGUiLCJhdWQiOiJodHRwczovL2V4cGVyaWFuLmNyaS5hY2NvdW50Lmdvdi51ay8iLCJpc3MiOiJodHRwczovL2lwdi5jb3JlLmFjY291bnQuZ292LnVrIiwiY2xpZW50X2lkIjoiY2QyY2M4YjUtMzA0YS00NmU4LTliMDQtMGU5MDQzOGMxOGJlIiwic3RhdGUiOiJhZjBpZmpzbGRraiIsImlhdCI6MTY1NTQ4MjM2MiwibmJmIjoxNjU1NDgyMzYyLCJleHAiOjE5NzA4NDIzNjJ9.H6ytRxbxb2l5zJVB1IYuXZe845dboHzJwM-g3ufjTF_GZHbZxxljsdDfPyNmZvqotWZ8lF0Q_EcAP5G1KAvQIQ";
    public String responseTypeValue = "code";
    public String clientIdValue = "cd2cc8b5-304a-46e8-9b04-0e90438c18be";
    public String codeValue = "22a2435-8e17-41c9-a65e-b827b3592124";
    public String stateValue = "af0ifsldkj";
    public String redirectUriValue = "https%3A%2F%2Fwww.review-b.build.account.gov.uk%2Fstub%2Fcallback";
    public String grantTypeValue = "authorization_code";
    public String biometricSessionIdValue = "5678";

    @Given("the verifyAuthorize request session is posted")
    public void theVerifyAuthorizeRequestSessionIsPosted() {
        RestAssured.baseURI = ConfigurationReader.get("APIDev");
        RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("client_id", clientIdValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("request", requestValue)
                .when().post(RestAssured.baseURI +"/verifyAuthorizeRequest");
    }

    @When("the status code is valid")
    public void theStatusCodeIsValid() {
        RestAssured.baseURI =  ConfigurationReader.get("APIDev");
//        System.out.println(System.getenv(String.valueOf(inputStream)));
        Response verifyAuthRequest =  RestAssured.given()
                .queryParam("client_id", clientIdValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("request", requestValue)
                .when().post(RestAssured.baseURI +"/verifyAuthorizeRequest");
        Assert.assertEquals(200, verifyAuthRequest.statusCode());
        verifyAuthRequest.getBody().asString();
    }

    @Then("the sessionId from the response body is taken and used in authorizationCode with valid status code")
    public void theSessionIdFromTheResponseBodyIsTakenAndUsedInAuthorizationCodeWithValidStatusCode() {
        RestAssured.baseURI =  ConfigurationReader.get("APIDev");

        Response verifyAuthRequest =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("request", requestValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("client_id", clientIdValue)
                .when().post(RestAssured.baseURI +"/verifyAuthorizeRequest");
        Assert.assertEquals(200, verifyAuthRequest.statusCode());
        verifyAuthRequest.body().asString().contains("sessionId");
        JsonPath extract = verifyAuthRequest.jsonPath();
        String sessionId = extract.get("sessionId");
        System.out.println(sessionId);

        Response finishBiometricRequest =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("authSessionId", sessionId)
                .queryParam("biometricSessionId", biometricSessionIdValue)
                .when().post(RestAssured.baseURI +"/finishBiometricSession");
        Assert.assertEquals(200, finishBiometricRequest.statusCode());

        Response verifyAuthCode =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("sessionId", sessionId)
                .when().get(RestAssured.baseURI +"/authorizationCode");
        Assert.assertEquals(200, verifyAuthCode.statusCode());

    }

    @When("the authorizationCode from the response body is taken and used in token request with valid status code")
    public void theAuthorizationCodeFromTheResponseBodyIsTakenAndUsedInTokenRequestWithValidStatusCode() {
        RestAssured.baseURI =  ConfigurationReader.get("APIDev");

        Response verifyAuthRequest =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("request", requestValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("client_id", clientIdValue)
                .when().post(RestAssured.baseURI +"/verifyAuthorizeRequest");
        Assert.assertEquals(200, verifyAuthRequest.statusCode());
        verifyAuthRequest.body().asString().contains("sessionId");
        JsonPath extract = verifyAuthRequest.jsonPath();
        String sessionId = extract.get("sessionId");
        System.out.println(sessionId);

        Response finishBiometricRequest =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("authSessionId", sessionId)
                .queryParam("biometricSessionId", biometricSessionIdValue)
                .when().post(RestAssured.baseURI +"/finishBiometricSession");
        Assert.assertEquals(200, finishBiometricRequest.statusCode());

        Response verifyAuthCode =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("sessionId", sessionId)
                .when().get(RestAssured.baseURI +"/authorizationCode");
        Assert.assertEquals(200, verifyAuthCode.statusCode());
        verifyAuthCode.body().asString().contains("authorizationCode");
        JsonPath authExtract = verifyAuthCode.jsonPath();
        String authCode = authExtract.get("authorizationCode");
        System.out.println(authCode);

        Response tokenCode =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("code", authCode)
                .queryParam("grant_type", grantTypeValue)
                .queryParam("redirect_uri", redirectUriValue)
                .when().post(RestAssured.baseURI +"/token");
        Assert.assertEquals(200, tokenCode.statusCode());
        tokenCode.body().asString().contains("access_token");
        JsonPath tokenExtract = tokenCode.jsonPath();
        String accessToken = tokenExtract.get("access_token");
        System.out.println(accessToken);
    }

    @Then("the accessToken is retrieved from the response body and is used in userInfo request")
    public void theAccessTokenIsRetrievedFromTheResponseBodyAndIsUsedInUserInfoRequest() {
        RestAssured.baseURI =  ConfigurationReader.get("APIDev");

        Response verifyAuthRequest =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("request", requestValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("client_id", clientIdValue)
                .when().post(RestAssured.baseURI +"/verifyAuthorizeRequest");
        Assert.assertEquals(200, verifyAuthRequest.statusCode());
        verifyAuthRequest.body().asString().contains("sessionId");
        JsonPath extract = verifyAuthRequest.jsonPath();
        String sessionId = extract.get("sessionId");
        System.out.println("sessionId: " + sessionId);

        Response finishBiometricRequest =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("authSessionId", sessionId)
                .queryParam("biometricSessionId", biometricSessionIdValue)
                .when().post(RestAssured.baseURI +"/finishBiometricSession");
        Assert.assertEquals(200, finishBiometricRequest.statusCode());

        Response verifyAuthCode =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("sessionId", sessionId)
                .when().get(RestAssured.baseURI +"/authorizationCode");
        Assert.assertEquals(200, verifyAuthCode.statusCode());
        verifyAuthCode.body().asString().contains("authorizationCode");
        JsonPath authExtract = verifyAuthCode.jsonPath();
        String authCode = authExtract.get("authorizationCode");
        System.out.println("authorizationCode: " + authCode);

        Response tokenCode =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("code", authCode)
                .queryParam("grant_type", grantTypeValue)
                .queryParam("redirect_uri", redirectUriValue)
                .when().post(RestAssured.baseURI +"/token");
        Assert.assertEquals(200, tokenCode.statusCode());
        tokenCode.body().asString().contains("access_token");
        JsonPath tokenExtract = tokenCode.jsonPath();
        String accessToken = tokenExtract.get("access_token");
        System.out.println("access_token: " + accessToken);

        Response userInfo =  RestAssured.given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when().post(RestAssured.baseURI +"/userinfo");
        userInfo.body().asString();
        Assert.assertEquals(200, userInfo.statusCode());
    }

    @And("the sessionId from verifyAuthorize is used in finishBiometricSession endpoint")
    public void theSessionIdFromVerifyAuthorizeIsUsedInFinishBiometricSessionEndpoint() {

        RestAssured.baseURI =  ConfigurationReader.get("APIDev");

        Response verifyAuthRequest =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("request", requestValue)
                .queryParam("response_type", responseTypeValue)
                .queryParam("client_id", clientIdValue)
                .when().post(RestAssured.baseURI +"/verifyAuthorizeRequest");
        Assert.assertEquals(200, verifyAuthRequest.statusCode());
        verifyAuthRequest.body().asString().contains("sessionId");
        JsonPath extract = verifyAuthRequest.jsonPath();
        String sessionId = extract.get("sessionId");
        System.out.println(sessionId);

        Response finishBiometricRequest =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("authSessionId", sessionId)
                .queryParam("biometricSessionId", biometricSessionIdValue)
                .when().post(RestAssured.baseURI +"/finishBiometricSession");
        Assert.assertEquals(200, finishBiometricRequest.statusCode());

    }
}


