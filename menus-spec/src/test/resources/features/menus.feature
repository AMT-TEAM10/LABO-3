Feature: Application menus

Scenario: Register a new menu
Given I have an menu payload
When I POST it to the /menus endpoint
Then I receive a 201 status code
