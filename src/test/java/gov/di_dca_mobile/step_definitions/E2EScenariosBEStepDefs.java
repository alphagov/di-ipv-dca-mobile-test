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

    public String requestValue = "eyJhbGciOiJSU0EtT0FFUC0yNTYiLCJlbmMiOiJBMjU2R0NNIn0.AISIEvimTkm_1gfG9gQA5btB7lHu2riU90KI-UyuH35CABCJk_KKUTsptasmNG5n6Zo6jL8vlMrj3y4A44nDrnTeH_OGLByerCd-EKNx9EZrplcsQ8VM8dip7LCUQy10OGH6ggoMGq9TbiC7lP_8md8UgvcNzd_zm6q6Ade5zd_sUzTWNSX3q6lVfpkpuMdj-2l18zk-yJEvaDArP7U1VBT8531ixxW1lUKflTHlyC-t6B-45R04o3KubhVb-_gVt7KKXo7UQUY-wmHiTUGsxWb3xrROKs6tv4Ug7Oi1MVngesL8CahjyLamvk-tdS1ji2rxn47Xs2Q21FQf3cHPtQ.9bNdWPzw2Cf6uyR_.VVj96Ejcc84yKv3gfHY6Nond9FJrQ-AoQ7pQSlmnzicoJ7hpHwyHUuNiSJNO4q631VKAblNbdprAA11PcYL3f_hcocxwORR8e3tQlDG9TFn6lxg3l9M34ccJrkD51ippIpsChQhIFqS2hU5W9BFyOPdpQhlhtc7R-d0zqbylh-z-snzxRG7Q-hVsBQpOzOLMDWcJNOCHlF8kZjp4dlWYFj3Yl_9HAa-hrY-DpoxdkvXVIrBdJarD77Bwwxo8y1IWEgCuDA8t5ZmrZMY9fxoufQg9jW5D7hB3qysgSo3S-6SFyt3rP_WD5ecezRrTCY-voDeWBtfdwuKo5UUaPidN4ut1n8SXAdoANxgbuEhyABjvMESi3AnWwZRx8yh3xEMVSwZ8kFQ4cVeBW2hrkiSXMjL1Wtz6nhhY9itFcmzBU4JMvoYfdf-ZZP-F4ph_Xh9CRS7rBzmrgUt81LahzYfCY91lS93qT7r3Wrh0OfIb0vvIkVUebDdCZw25PoG2oREty4k59z7j9ho8VqbtSS6_kpJGWx8Sikut1I-bVduk79Vf1MwEGxSRvZ_pyZHxxZDe19GPfamhctFXMcebyJoT2zwWhcW4AWFSUtm2JI36sZhFcsdRIRBfSjJ4MNXdlrHdOyhvo8SwTWpfO5lU9yyF8lANbXxjdnRSV6sR1iaBs_16slbWJX3WC6RmC76x8MlHn1fLBK_lU_IeZChZqLCwqUC3CXbk17OkTkdeCqFyZSS5rlfm-EiPvam6iW51tA-ywxrmUjkCDE_k-zZIZTVVSg.3YAfgcQ4ZkPtYPNvOzWCLQ";
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

        Response startBiometricRequest = RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("authSessionId", sessionId)
                .when().get(RestAssured.baseURI +"/biometricToken");
        Assert.assertEquals(200, startBiometricRequest.statusCode());

        Response finishBiometricRequest =  RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("authSessionId", sessionId)
                .queryParam("biometricSessionId", biometricSessionIdValue)
                .when().post(RestAssured.baseURI +"/finishBiometricSession");
        Assert.assertEquals(200, finishBiometricRequest.statusCode());

    }

    @Then("the sessionId from verifyAuthorize is used in biometricToken endpoint")
    public void theSessionIdFromVerifyAuthorizeIsUsedInBiometricTokenEndpoint() {
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

        Response startBiometricRequest = RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("authSessionId", sessionId)
                .when().get(RestAssured.baseURI +"/biometricToken");
        Assert.assertEquals(200, startBiometricRequest.statusCode());
    }
}


