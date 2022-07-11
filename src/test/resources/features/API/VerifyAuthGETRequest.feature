@test @DCMAW-589 @api
Feature: /verifyAuthorize GET request valid and invalid scenarios

  Scenario: Happy Path - Auth to send a valid JWT token on request
    Given the authorize endpoint is working
    When I sent a valid GET request to authorize endpoint
    Then I should receive a valid OK response is displayed

  Scenario Outline: Unhappy Path - no query parameters added
    Given the authorize endpoint is working
    When I sent a valid GET request to authorize endpoint with no parameters added
    Then I should receive a invalid response is displayed
    Examples:

  Scenario: Unhappy Path - no redirect uri available within the request
    Given the authorize endpoint is working
    When I sent a valid GET request to authorize endpoint with redirect uri is missing
    Then I should receive a invalid response is displayed

  Scenario: Unhappy Path - the redirect uri is not registered within the system
    Given the authorize endpoint is working
    When I sent a valid GET request to authorize endpoint with redirect uri is not registered
    Then I should receive a invalid response is displayed

  Scenario: Unhappy Path - invalid client id is sent within the request
    Given the authorize endpoint is working
    When I sent a valid GET request to authorize endpoint with invalid client id
    Then I should receive a invalid response is displayed

  Scenario Outline: Unhappy Path - invalid JWT tokens for /authorize request
    Given the authorize endpoint is working
    When I sent a valid GET request to authorize endpoint
    And add the following "<invalid_JWT>" to the request
    Then I should receive a invalid response is displayed
    Examples:
      | invalid_JWT           |
      | invalid_client_id     |
      | bad_private_key       |
      | missing_redirect_uri  |
      | invalid_redirect_uri  |
      | invalid_response_type |
      | missing_iss_field     |
      | expired_jwt           |
      | future_jwt            |

