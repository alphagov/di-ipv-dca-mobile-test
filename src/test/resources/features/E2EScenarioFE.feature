Feature: E2E Scenario FE Journey

  @web
#  Scenario: E2E with FE and BE system checking the details of all pages
#    Given the user navigates to the authorize homepage
#    When the user clicks on the continue button
#    Then the user is presented with the flashing colours screen
#    When the user clicks YES and continue to flashing colours screen
#    Then the user is taken to the valid license confirmation screen
#    When the user clicks YES and continue to the valid license screen
#    Then the user is presented with workable camera screen
#    When the user clicks YES and continue to the workable camera screen
#    Then the user is presented with the ready to begin screen
#    And the user selects YES and continue to the ready to begin screen
#    Then the user is presented with the download page
#    And the user is directed back to HMRC

  Scenario: E2E with FE and BE system
    Given the user navigates to the authorize homepage
    When the user clicks on the continue button
    And the user clicks YES and continue to flashing colours screen
    And the user clicks YES and continue to the valid license screen
    And the user clicks YES and continue to the workable camera screen
    And the user selects YES and continue to the ready to begin screen
    Then the user clicks the stub button and is directed back to HMRC
