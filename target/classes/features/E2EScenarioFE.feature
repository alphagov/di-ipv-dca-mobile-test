Feature: E2E Scenario FE Journey

  @web
  Scenario: E2E with FE and BE system
    Given I navigate to the authorize homepage
    When I click the continue button on
    And I click the Fraud CRI Integration option
    Then I search for user number 12 in the Experian table
