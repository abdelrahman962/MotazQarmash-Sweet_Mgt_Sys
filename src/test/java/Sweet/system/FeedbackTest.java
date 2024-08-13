package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;
public class FeedbackTest {
    private Login login;
    private User userFeedback;
    private StoreOwner storeOwner;
    private Product product;
    private String feedbackContent;
    private String currentUserEmail;
    private String currentRecipeName;
    private String recipeOwnerEmail;

    public FeedbackTest() {
    login = new Login();

}

    @Given("a user {string} with password {string} has purchased the product {string}")
    public void aUserWithPasswordHasPurchasedTheProduct(String userEmail, String userPassword, String productName) {
        userFeedback=login.getCurrentUser(userEmail,userPassword);
        login.setLogInStatus(true);
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
    @Then("the feedback is recorded")
    public void theFeedbackIsRecorded() {
        boolean feedbackRecorded = false;

        for (Product product : login.getUserBasket(userFeedback.getEmail(), userFeedback.getPassword())) {
            feedbackRecorded = product.getFeedbacks().contains(feedbackContent);
        }
        assertTrue("Feedback should be recorded", feedbackRecorded);
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
        boolean feedbackFound = false;
        for (Product product : products) {
            if (product.getFeedbacks().contains(feedbackContent)) {
                feedbackFound = true;
                break;
            }
        }
        assertTrue("Other users should be able to view the feedback for the product", feedbackFound);
    }

    @Given("a user {string} with password {string} has viewed the shared recipe {string} owned by {string}")
    public void aUserWithPasswordHasViewedTheSharedRecipeOwnedBy(String userEmail, String userPassword, String recipeName, String recipeOwnerEmail) {
        currentUserEmail = userEmail;
        currentRecipeName = recipeName;
        this.recipeOwnerEmail=recipeOwnerEmail;
        assertNotNull("User should be able to view the shared recipe", login.getRecipeByName(recipeName));
    }

    @When("the user provides feedback {string} on the shared recipe")
    public void theUserProvidesFeedbackOnTheSharedRecipe(String feedbackContent) {
        this.feedbackContent = feedbackContent;
        Recipe recipe = login.getRecipeByName(currentRecipeName);
        if (recipe != null) {
            recipe.addFeedback(feedbackContent);
        }
    }

    @Then("the recipe owner {string} can view the feedback content")
    public void theRecipeOwnerCanViewTheFeedbackContent(String recipeOwnerEmail) {
        Recipe recipe = login.getRecipeByName(currentRecipeName);
        boolean feedbackFound = false;
        if (recipe != null) {
            for (String feedback : recipe.getFeedbacks()) {
                if (feedback.equals(feedbackContent)) {
                    feedbackFound = true;
                    break;
                }
            }
        }
        assertTrue("Recipe owner should be able to view the feedback content", feedbackFound);
    }

    @Then("the recipe owner can see which user provided the feedback")
    public void theRecipeOwnerCanSeeWhichUserProvidedTheFeedback() {

    }

    @Then("other users can view the feedback for the shared recipe")
    public void otherUsersCanViewTheFeedbackForTheSharedRecipe() {

    }

    @Given("a store owner {string} with password {string} has received feedback on the product {string}")
    public void aStoreOwnerWithPasswordHasReceivedFeedbackOnTheProduct(String string, String string2, String string3) {

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

    @Then("the user can see which user provided the feedback")
    public void theUserCanSeeWhichUserProvidedTheFeedback() {

    }


}
