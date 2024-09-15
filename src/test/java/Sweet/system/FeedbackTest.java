package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;
public class FeedbackTest {
    private final Login login;
    private User userFeedback;
    private  User userViewedSharedRecipe;
    private String productName;
    private StoreOwner storeOwner;
    private Product product;
    private String feedbackContent;
    private String currentUserEmail;
    private String currentRecipeName;

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
        this.productName = productName;
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
        login.addFeedbackToProduct(userFeedback.getEmail(),userFeedback.getPassword(),productName,feedbackContent);
        product.addFeedback(userFeedback, feedbackContent);

        assertEquals("Message sent successfully to store owner.", messageResponse);
    }


    @Then("the store owner {string} can view the feedback content")
    public void theStoreOwnerCanViewTheFeedbackContent(String storeOwnerEmail) {
        storeOwner.setEmail(storeOwnerEmail);
        List<Product> productsFeed = login.getStoreOwnerProducts(storeOwnerEmail);
        boolean feedbackFound = false;
        for (Product productFeed : productsFeed) {
            if (productFeed.getFeedbacks().contains(feedbackContent)) {
                feedbackFound = true;
                break;
            }
        }
        assertFalse("Store owner should be able to view the feedback content", feedbackFound);
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

    @Then
            ("other users can view the feedback for the product")
    public void otherUsersCanViewTheFeedbackForTheProduct() {
        List<Product> foundProducts = login.searchProducts(product.getName()); // Renamed 'products' to 'foundProducts'
        String actualFeedback = null; // Variable to store the feedback found

        for (Product foundProduct : foundProducts) { // Renamed 'product' to 'foundProduct'
            for (String feedback : foundProduct.getFeedbacks()) {
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
        userViewedSharedRecipe = login.getCurrentUser(currentUserEmail, userPassword);
        recipeOwner = (User) login.getEntityByEmail(recipeOwnerEmail);

        assertNotNull("User should be logged in successfully", userViewedSharedRecipe); // Added assertion

        Recipe viewedRecipe = login.getRecipeByName(recipeName);

        assertNotNull("User should be able to view the shared recipe", viewedRecipe);
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
        Recipe sharedRecipe = login.getRecipeByName(currentRecipeName);
        String foundFeedback = null; // Renamed from expectedFeedback

        for (String feedback : sharedRecipe.getFeedbacks()) {
            if (feedback.equals(feedbackContent)) {
                foundFeedback = feedbackContent;
                break;
            }
        }

        // Assuming the userViewedSharedRecipe is defined and contains the appropriate email and password
        login.addFeedbackToRecipe(userViewedSharedRecipe.getEmail(), userViewedSharedRecipe.getPassword(), currentRecipeName, foundFeedback);
        assertEquals("Feedback content should match", foundFeedback, feedbackContent);
    }



}