Feature: End to end scenarios for the BE
@api
  Scenario: E2E with Oauth and BE system
    Given the verifyAuthorize request session is posted
    When the status code is valid
    And the sessionId from verifyAuthorize is used in finishBiometricSession endpoint
    Then the sessionId from the response body is taken and used in authorizationCode with valid status code
    When the authorizationCode from the response body is taken and used in token request with valid status code
    Then the accessToken is retrieved from the response body and is used in userInfo request


