Feature: Application menus

  Scenario Outline: Creating a menu
    Given a menu named "<menu>"
    When I create the menu
    Then the menu should be created

    Examples:
      | menu |
      | "Test menu" |
