
Feature: Monitor profits and generate financial reports

  Scenario: Monitor profits and generate financial reports
    Given I am logged in as an admin
    When I view the financial reports for all stores
    Then I should see a detailed report showing total profits for each store



  Scenario: Identify best-selling products in each store
    Given I am logged in as an admin
    When I view the best-selling products report for the store
    Then I should see a list of top-selling products in that store along with their sales figures




  Scenario: Gather and display statistics on registered users by City
    Given I am logged in as an admin
    When I view the user statistics report by city
    Then I should see the total number of registered users for each city


