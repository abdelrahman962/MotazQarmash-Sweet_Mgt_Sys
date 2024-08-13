Feature: Browse and search for dessert recipes by name from other users

  Scenario: Search for a dessert recipe by name from other users
    Given a user with email "abdelrahmanmasri3@gmail.com" is logged in
    When the user searches for a dessert recipe by name "Berry Chocolate Cake"
    Then the content of the recipe "Berry Chocolate Cake" should be "Chocolate cake with berries."

  Scenario: Search for a dessert recipe by name that does not exist
    Given a user with email "john.doe@example.com" is logged in
    When the user searches for a dessert recipe by name "Vanilla Cake"
    Then the result should be '   '