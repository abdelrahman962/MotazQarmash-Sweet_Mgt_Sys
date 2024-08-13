Feature: Admin Management

  @CREATE_SP_ACCOUNT1
  Scenario: Create new account for Service Provider
    Given I logged in as an admin
    When I create an account with "<Email>", "<Password>", "<Role>" and "<City>" as the following for a new User
      | Email                   | Password | Role |City|
      | s1211261@stu.najah.edu | 123      |user |Nablus|
      | s121121@stu.najah.edu | 123      |provider |Nablus|
    Then I should see a success message confirming the account creation

  @DELETE_ACCOUNT1
  Scenario: Delete account
    Given I logged in as an admin
    When I delete the account with email "s1211161@stu.najah.edu"
    Then I should see a success message confirming the account deletion