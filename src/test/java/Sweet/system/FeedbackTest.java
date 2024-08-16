package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;
public class FeedbackTest {
    private Login login;
    private User userFeedback;
  private  User userViewedSharedRecipe;
    private StoreOwner storeOwner;
    private Product product;
    private String feedbackContent;
    private String currentUserEmail;
    private String currentRecipeName;
    private String recipeOwnerEmail;
   private Recipe recipe;
private     String expectedFeedback;
private User recipeOwner;

    public FeedbackTest() {
    login = new Login();

}

    @Given("a user {string} with password {string} has purchased the product {string}")
    public void aUserWithPasswordHasPurchasedTheProduct(String userEmail, String userPassword, String productName) {
        userFeedback = login.getCurrentUser(userEmail, userPassword);
        login.setLogInStatus(true);
        assertNotNull("User should be logged in successfully", userFeedback); // Ensure user is logged in

        boolean purchaseSuccess = login.purchaseProduct(userEmail, userPassword, productName, 1);
        assertTrue("User should have successfully purchased the product.", purchaseSuccess);
    }



    @When("the user provides feedback {string} on the product")
    public void theUserProvidesFeedbackOnTheProduct(String feedbackContent) {
        this.feedbackContent=feedbackContent;

        product = userFeedback.getBasket().get(0);  // Assuming the product is the last added to the basket
        String storeOwnerEmail = product.getStoreOwnerEmail();
        storeOwner = login.findStoreOwnerByEmail(storeOwnerEmail);
        String messageResponse = login.sendMessageToStoreOwner(userFeedback.getEmail(), storeOwner.getEmail(), feedbackContent);
        product.addFeedback(feedbackContent);
        assertEquals("Message sent successfully to store owner.", messageResponse);
    }


    @Then("the store owner {string} can view the feedback content")
    public void theStoreOwnerCanViewTheFeedbackContent(String storeOwnerEmail) {
        storeOwner.setEmail(storeOwnerEmail); ;
        List<Product> products = login.getStoreOwnerProducts(storeOwnerEmail);
        boolean feedbackFound = false;
        for (Product product : products) {
            if (product.getFeedbacks().contains(feedbackContent)) {
                feedbackFound = true;
                break;
            }
        }
        assertTrue("Store owner should be able to view the feedback content", feedbackFound);
    }

    @Then("the store owner can see which user provided the feedback")
    public void theStoreOwnerCanSeeWhichUserProvidedTheFeedback() {
        List<Message> messages = login.getMessagesForStoreOwner(storeOwner.getEmail());
        boolean userIdentified = false;
        for (Message message : messages) {
            if (message.getSenderEmail().equals(userFeedback.getEmail())) {
                userIdentified = true;
                break;
            }
        }
        assertTrue("Store owner should be able to see which user provided the feedback", userIdentified);
    }

    @Then("other users can view the feedback for the product")
    public void otherUsersCanViewTheFeedbackForTheProduct() {
        List<Product> products = login.searchProducts(product.getName());
        String actualFeedback = null; // Variable to store the feedback found

        for (Product product : products) {
            for (String feedback : product.getFeedbacks()) {
                if (feedback.equals(feedbackContent)) {
                    actualFeedback = feedback; // Store the found feedback
                    break;
                }
            }
            if (actualFeedback != null) break; // Exit outer loop if feedback is found
        }

        assertNotNull("Feedback should be found for the product", actualFeedback);
        assertEquals("Other users should be able to view the feedback for the product", feedbackContent, actualFeedback);
    }


    @Given("a user {string} with password {string} has viewed the shared recipe {string} owned by {string}")
    public void aUserWithPasswordHasViewedTheSharedRecipeOwnedBy(String userEmail, String userPassword, String recipeName, String recipeOwnerEmail) {
        currentUserEmail = userEmail;
        currentRecipeName = recipeName;
        userViewedSharedRecipe = login.getCurrentUser(userEmail, userPassword);
        recipeOwner=login.getUserByEmail(recipeOwnerEmail);
        assertNotNull("User should be logged in successfully", userViewedSharedRecipe); // Added assertion
        this.recipeOwnerEmail = recipeOwnerEmail;
        Recipe recipe = login.getRecipeByName(recipeName);
        assertNotNull("User should be able to view the shared recipe", recipe);
    }


    @When("the user provides feedback {string} on the shared recipe")
    public void theUserProvidesFeedbackOnTheSharedRecipe(String feedbackContent) {
        this.feedbackContent = feedbackContent;

        recipe = login.getRecipeByName(currentRecipeName);

        assertNotNull("Recipe should exist before providing feedback", recipe); // Ensure recipe is found
        login.addFeedbackToRecipe(userViewedSharedRecipe.getEmail(),userViewedSharedRecipe.getPassword(),currentRecipeName,feedbackContent);
        recipe.addFeedback(userViewedSharedRecipe,feedbackContent); // Add feedback
    }





    @Then("other users can view the feedback for the shared recipe")
    public void otherUsersCanViewTheFeedbackForTheSharedRecipe() {
        Recipe recipe = login.getRecipeByName(currentRecipeName);
        boolean feedbackFound = false;
        for (String feedback : recipe.getFeedbacks()) {
            if (feedback.equals(feedbackContent)) {
                expectedFeedback = feedbackContent;
                feedbackFound = true;
                break;
            }
        }
        assertEquals(expectedFeedback,feedbackContent);
    }


}
/*

  Scenario Outline: Store owner views feedback on their product
    Given a store owner "<storeOwnerEmail>" with password "<storeOwnerPassword>" has received feedback on the product "<productName>"
    When the store owner views the feedback
    Then the store owner can see the feedback content
    And the store owner can see which user "<userEmail>" provided the feedback

    Examples:
      | storeOwnerEmail   | storeOwnerPassword | productName    |userEmail|
      | mota12@gmail.com  | 12                 | Chocolate Cake |abdelrahmanmasri3@gmail.com|
      | mota12@gmail.com  | 12                 | Berry Cake     |s1211161@stu.najah.edu     |

  Scenario Outline: User views feedback on their shared recipe
    Given a user "<userEmail>" with password "<userPassword>" has received feedback on their shared recipe "<recipeName>"
    When the user views the feedback
    Then the user can see the feedback content
    And the user can see which user "<userFeedback>" provided the feedback

    Examples:
      | userEmail                    | userPassword | recipeName      |userFeedback|
      | as12112958@stu.najah.edu     | 123          | Chocolate Cake |      john.doe@example.com         |
      | abdelrahmanmasri3@gmail.com  | 123          | Berry Cake     |      jane.doe@example.com        |

 *
 */

/*
*
    @Given("a store owner {string} with password {string} has received feedback on the product {string}")
    public void aStoreOwnerWithPasswordHasReceivedFeedbackOnTheProduct(String storeOwnerEmail, String storeOwnerPassword, String productName) {

    }

    @When("the store owner views the feedback")
    public void theStoreOwnerViewsTheFeedback() {

    }

    @Then("the store owner can see the feedback content")
    public void theStoreOwnerCanSeeTheFeedbackContent() {

    }

    @Then("the store owner can see which user {string} provided the feedback")
    public void theStoreOwnerCanSeeWhichUserProvidedTheFeedback(String string) {

    }

    @Given("a user {string} with password {string} has received feedback on their shared recipe {string}")
    public void aUserWithPasswordHasReceivedFeedbackOnTheirSharedRecipe(String string, String string2, String string3) {

    }

    @When("the user views the feedback")
    public void theUserViewsTheFeedback() {

    }

    @Then("the user can see the feedback content")
    public void theUserCanSeeTheFeedbackContent() {

    }


    @Then("the user can see which user {string} provided the feedback")
    public void theUserCanSeeWhichUserProvidedTheFeedback(String string) {

    }
*
*
*
* */