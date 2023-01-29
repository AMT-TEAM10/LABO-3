Feature: Application menus

  Scenario Outline: Creating a menu
    Given I have a menu named '<menu>'
    When I create the menu
    Then the menu should be created with name '<answer>'

    Examples:
      | menu          | answer        |
      | "Test menu"   | "Test menu"   |
      | "Test menu 2" | "Test menu 2" |
