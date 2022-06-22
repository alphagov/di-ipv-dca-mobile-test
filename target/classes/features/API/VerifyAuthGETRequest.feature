@test @DCMAW-589 @api
Feature: /verifyAuthorize GET request

  Scenario: Happy Path - Auth to send a valid JWT token on request
    Given the authorize endpoint is working
    When I sent a valid GET request to authorize endpoint
    Then I should receive a valid OK response is displayed

  Scenario Outline: Unhappy Path - no query parameters added


    Examples:

  Scenario: Unhappy Path - no redirect uri available within the request

  Scenario: Unhappy Path - the redirect uri is not registered within the system

  Scenario: Unhappy Path - a request uri is sent instead of the redirect uri

  Scenario: Unhappy Path - invalid client id is sent within the request

  Scenario Outline: Unhappy Path - invalid JWT tokens for /authorize request
    Examples:
    |invalid_JWT|
    |invalid_client_id|
    |

  Scenario: Unhappy Path - invalid response type is sent within the request

  Scenario: Unhappy Path - no redirect uri available within the request
