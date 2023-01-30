Feature: Application dishes

  Scenario: Register a new dish
    Given I have an dish payload
    When I POST it to the dishes endpoint
    Then I receive a 201 status code for dish

  Scenario:  Update an existing dish
    Given I have an dish payload
    When I PUT it to the dishes endpoint
    Then I receive a 200 status code for dish

  Scenario:  Delete an existing dish
    Given I have an dish payload
    When I DELETE it to the dishes endpoint
    Then I receive a 204 status code for dish
