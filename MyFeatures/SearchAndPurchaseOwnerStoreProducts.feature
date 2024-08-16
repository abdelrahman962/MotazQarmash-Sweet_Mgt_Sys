Feature: Browse and search for dessert recipes and store owner products by name

  Scenario Outline: Search for a store owner's product by name based on dietary needs or food allergies
    Given a user with email "<userEmail>" and password "<userPassword>" is logged in
    When the user searches for a product by name "<productName>" with dietary needs or food allergies "<dietaryNeed>" from store owner "<storeOwnerEmail>"
    Then the price of the product "<productName>" should be <price>
    And the description of the product "<productName>" should be "<description>"
    And the product "<productName>" should be "<dietaryNeed>"
    When the user searches for products with dietary needs or food allergies "<dietaryNeed>"
    Then the result should contain products that are "<dietaryNeed>"

    Examples:
      | userEmail              | userPassword | productName     | dietaryNeed | storeOwnerEmail       | price | description                                      |
      | jane.doe@example.com   | 123          | Chocolate Cake  | gluten-free | mota12@gmail.com      | 10.00 | Delicious chocolate cake with rich frosting. Gluten-free. |
      | s1211161@stu.najah.edu       | 123       | Berry Cake      |    contains gluten      |mota12@gmail.com     | 12.00|     Delicious Berry cake recipe.contains gluten|
    |   as12112958@stu.najah.edu  |      123      |  Fruit Cake     |   |     moa123@gmail.com              |  20    |    Fruit cake is a dense, rich cake filled with a variety of dried fruits and nuts.         |
  Scenario Outline: Search for a store owner's product by name that does not exist or did not meet dietary needs or food allergies
    Given a user with email "<userEmail>" and password "<userPassword>" is logged in
    When the user searches for a product by name "<productName>" and "<dietaryNeed>" from store owner "<storeOwnerEmail>"
    Then the result should be "<result>"

    Examples:
      | userEmail              | userPassword | productName     | storeOwnerEmail       | result           |dietaryNeed|
      | jane.doe@example.com   | 123          | Vanilla Cake    | mota12@gmail.com      | No product found |           |
      | jane.doe@example.com   | 123          | Chocolate Cake  | mota12@gmail.com      | No product found | contains gluten          |
      | as12112958@stu.najah.edu  |123        |Caramel Cake     | moa123@gmail.com      |      No product found             ||
  Scenario Outline: Search for products with dietary needs that have no matches
    Given a user with email "<userEmail>" and password "<userPassword>" is logged in
    When the user searches for products with dietary needs or food allergies "<dietaryNeed>"
    Then the no match result should be "<result>"

    Examples:
      | userEmail              | userPassword | dietaryNeed | result             |
      | jane.doe@example.com   | 123          | vegan       | No products found  |
      | as12112958@stu.najah.edu  |123        |    contains gluten         |      No products found              |

  Scenario: Purchase products and add to basket
    Given a user with email "jane.doe@example.com" and password "123" is logged in
    When the user purchases "Chocolate Cake" with quantity 2
    Then the total basket price should be 20.00
    And the basket should contain the product "Chocolate Cake" with quantity 2

  Scenario: Clear user's basket
    Given a user with email "jane.doe@example.com" and password "123" is logged in
    When the user clears the basket
    Then the basket should be empty


