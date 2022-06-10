@test @DCMAW- @api
Feature: /callback GET request Happy Path

  Scenario: Auth sends a the correct JWT token on request
    Given the /callback endpoint is working
    When I sent a valid GET request to /callback endpoint
    Then I should receive a 200 OK response is displayed