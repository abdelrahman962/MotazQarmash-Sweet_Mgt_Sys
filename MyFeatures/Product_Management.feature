Feature: Product Management



  Scenario: Store owner or raw material supplier adds a new product to their inventory
    Given the store owner or raw material supplier is logged into their account
    When they select "Add a new product"
    And they enter the product name "Chocolate Cake"
    And they enter the price "20.00"
    And they enter the description "Delicious dark chocolate cake"
    And they enter the dietary needs "Gluten-Free"
    And they enter the store owner or provider email "owner@example.com"
    And they click "Save"
    Then the system should display a confirmation message "Product successfully added"
    And the product "Chocolate Cake" should be added to the inventory list with the correct details




  Scenario: Store owner or raw material supplier updates the details of an existing product
    Given the store owner or raw material supplier is logged into their account
    When they select "Update existing product"
    And they select the product "Chocolate Cake"
    And they update the price to "0.01"
    And they update the description to "Rich and moist dark chocolate cake"
    And they update the dietary needs to "Vegan, Gluten-Free"
    And they click "Save"

    Then the product "Chocolate Cake" should reflect the new price "22.00" and updated details in the inventory list





  Scenario: Store owner or raw material supplier removes an existing product from their inventory
    Given the store owner or raw material supplier is logged into their account
    When they select "Remove a product"
    And they choose the product "Chocolate Cake"
    And they confirm the removal

    Then the product "Chocolate Cake" should no longer appear in the inventory list



  Scenario: Monitor sales and profits
    Given the store owner or raw material supplier is logged into their account
    When they select the sales and profit report
    And they click "Generate report"
    Then the system should display a report showing total sales, profits, and a breakdown by product



  Scenario: Identify best-selling products
    Given the store owner or raw material supplier is logged into their account
    When they click "View best-selling products"
    Then the system should display a list of products sorted by the number of sales




  Scenario: Store owner or raw material supplier applies a discount to a product
    Given the store owner or raw material supplier is logged into their account
    When they select "Apply discount"
    And they choose the product "Chocolate Cake"
    And they enter the discount percentage "15%"
    And they click "Apply"
    Then the product "Chocolate Cake" should show the discounted price in the store




