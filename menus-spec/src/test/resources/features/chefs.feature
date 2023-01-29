Feature: Application chefs
  Scenario: Register a new chef
    Given I have an chef payload
    When I POST it to the chefs endpoint
    Then I receive a 201 status code
