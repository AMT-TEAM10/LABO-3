Feature: Application chefs
  Scenario: Register a new chef
    Given I have an chef payload
    When I POST it to the chefs endpoint
    Then I receive a 201 status code

  Scenario:  Update an existing chef
    Given I have an chef payload
    When I PUT it to the chefs endpoint
    Then I receive a 200 status code

