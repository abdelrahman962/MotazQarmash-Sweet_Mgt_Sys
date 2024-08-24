Feature: Manage account details and update business information

  Scenario: Store owner or provider updates their email address
    Given the store owner or provider is logged into their account
    When they select "Update email address"
    And they enter the new email address "newemail@example.com"
    And they click "Save"
    Then the store owner's or provider's email should be updated to "newemail@example.com"

  Scenario: Store owner or provider updates their password
    Given the store owner or provider is logged into their account
    When they select "Update password"
    And they enter the current password "OldPassword123"
    And they enter the new password "NewSecurePassword123"
    And they click "Save"
    Then the store owner or provider should be able to log in with the new password "NewSecurePassword123"

  Scenario: Store owner or provider updates their city
    Given the store owner or provider is logged into their account
    When they select "Update city"
    And they enter the new city "Sweet City"
    And they click "Save"
    Then the store owner's or provider's city should be updated to "Sweet City"
