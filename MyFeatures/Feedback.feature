Feature: Feedback on Purchased Products and Shared Recipes

  Scenario Outline: User provides feedback on a purchased product
    Given a user "<userEmail>" with password "<userPassword>" has purchased the product "<productName>"
    When the user provides feedback "<feedbackContent>" on the product
    Then the store owner "<storeOwnerEmail>" can view the feedback content
    And the store owner can see which user provided the feedback
    And other users can view the feedback for the product

    Examples:
      | userEmail                    | userPassword | productName     | storeOwnerEmail      | feedbackContent                                  |
      | abdelrahmanmasri3@gmail.com  | 123          | Chocolate Cake  | mota12@gmail.com     | Great chocolate cake, loved it!               |
      | s1211161@stu.najah.edu       | 123          | Berry Cake      | mota12@gmail.com     | The berry cake was delicious, highly recommend!|

  Scenario Outline: User provides feedback on a shared recipe
    Given a user "<userEmail>" with password "<userPassword>" has viewed the shared recipe "<recipeName>" owned by "<recipeOwnerEmail>"
    When the user provides feedback "<feedbackContent>" on the shared recipe

    And other users can view the feedback for the shared recipe

    Examples:
      | userEmail                    | userPassword | recipeName      | recipeOwnerEmail             | feedbackContent                              |
      | john.doe@example.com         | 123          | Chocolate Cake | as12112958@stu.najah.edu      | Tried this recipe, and it turned out great!|
      | jane.doe@example.com         | 123          |Berry Chocolate Cake    | abdelrahmanmasri3@gmail.com  | Fantastic recipe, will make again!         |
