@Epic @UserStory @api

  Feature: Not Found page request shown
    Scenario Outline: Not Found request sent
      Given I am user on the web journey
      When I post the request to the page not found url
      Then I should receive the required page
      Examples:
