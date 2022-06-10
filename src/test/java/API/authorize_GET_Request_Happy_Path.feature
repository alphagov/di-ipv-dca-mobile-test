@test @DCMAW- @api
Feature: /authorize GET request Happy Path

  Scenario: Auth sends a the correct JWT token on request
    Given the /authorize endpoint is working
    When I sent a valid GET request to /authorize endpoint
    Then I should receive a 200 OK response is displayed