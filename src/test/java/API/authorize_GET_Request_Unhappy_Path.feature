@test @DCMAW- @api
Feature: /authorize GET request Unhappy Path

  Scenario: Auth sends a the correct JWT token on request with invalid uri
    Given the /dca/oauth2/authorize endpoint is working
    When I sent a GET request to an invalid redirect uri
    And all mandatory parameters are valid
    Then I should receive a 400 BR response is displayed

  Scenario Outline: Auth sends a the correct JWT token with request parameter unhappy scenarios
    Given the /dca/oauth2/authorize endpoint is working
    When I sent a valid GET request to /dca/oauth2/authorize endpoint
    And the request parameter is "<type>"
    Then I should receive a 200 OK response is displayed
    Examples:
    |type|
    |invalid|
    |blank_value|
    |removed|

  Scenario Outline : Auth sends a the correct JWT token with response_type unhappy scenarios
    Given the /dca/oauth2/authorize endpoint is working
    When I sent a valid GET request to /dca/oauth2/authorize endpoint
    And the response_type parameter is "<type>"
    Then I should receive a 200 OK response is displayed
    Examples:
      |type|
      |invalid|
      |blank_value|
      |removed|

  Scenario Outline: Auth sends a the correct JWT token with client_id parameter unhappy scenarios
    Given the /dca/oauth2/authorize endpoint is working
    When I sent a valid GET request to /dca/oauth2/authorize endpoint
    And the client_id parameter is "<type>"
    Then I should receive a 200 OK response is displayed
    Examples:
      |type|
      |invalid|
      |blank_value|
      |removed|
