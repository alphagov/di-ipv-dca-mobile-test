@Epic @UserStory @api @test

  Feature: Not Found page request shown
    Scenario Outline: Not Found request sent
      Given I post the request to the page not found url
      Then I should receive the required page
      Examples:
