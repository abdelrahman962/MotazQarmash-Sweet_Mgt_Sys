Feature: Browse and search for dessert recipes by name from other users

  Scenario Outline: Search for a dessert recipe by name from other users
    Given a user with email "<email>" is logged in
    When the user searches for a dessert recipe by name "<recipe_name>"
    Then the content of the recipe "<recipe_name>" should be "<recipe_content>"

    Examples:
      | email                          | recipe_name          | recipe_content               |
      | abdelrahmanmasri3@gmail.com     | Berry Chocolate Cake | Chocolate cake with berries. |
| as12112958@stu.najah.edu      |    Berry Chocolate Cake                  |                 Chocolate cake with berries.             |
  Scenario: Search for a dessert recipe by name that does not exist
    Given a user with email "john.doe@example.com" is logged in
    When the user searches for a dessert recipe by name "Vanilla Cake"
    Then the result should be '   '
