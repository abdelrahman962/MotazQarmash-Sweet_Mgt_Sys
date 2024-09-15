Feature: Admin Management

  @CREATE_SP_ACCOUNT1
  Scenario Outline: Create new account for Service Provider
    Given I logged in as an admin with email "as12112958@stu.najah.edu" and password "123"
    When I create an account with "<Email>", "<Password>", "<Role>" and "<City>" as the following for a new User
    Then I should see a success message confirming the account creation

    Examples:
      | Email                   | Password | Role |City|
      | s1211261@stu.najah.edu | 123      |user |Nablus|

  @DELETE_ACCOUNT1
  Scenario: Delete account
    Given I logged in as an admin with email "as12112958@stu.najah.edu" and password "123"
    When I delete the account with email "s1211161@stu.najah.edu"
    Then I should see a success message confirming the account deletion

  @UPDATE_ACCOUNT
  Scenario Outline: Update an existing account
    Given the account with email "<OldEmail>" for role "<Role>" exists
    When I update the email of the user to "<NewEmail>"
    And I update the password of the user to "<NewPassword>"
    Then I should see a success message confirming the account update

    Examples:
      | Role       | OldEmail               | NewEmail                    | NewPassword |
      | user       | m2n@gmail.com          | newuser1@example.com        | newpass123  |
      | storeowner | abdelrahmanmasri3@gmail.com      | newstoreowner1@example.com  | newpass123  |
      | provider   | johnnn.doe@example.com | newprovider1@example.com    | newpass123  |



  Scenario: Admin views all products
    Given I logged in as an admin with email "as12112958@stu.najah.edu" and password "123"
    When I view all products in the system
    Then I should see a list of all products with their details



  Scenario: Admin views all recipes
    Given I logged in as an admin with email "as12112958@stu.najah.edu" and password "123"
    When I view all recipes in the system
    Then I should see a list of all recipes with their details
