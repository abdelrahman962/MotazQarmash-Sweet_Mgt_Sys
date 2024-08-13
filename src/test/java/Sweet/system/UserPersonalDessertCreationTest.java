package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class UserPersonalDessertCreationTest {
    private Login login;
    private User currentUser;
    private String postedRecipeName;
    private String postedRecipeContent;

    public UserPersonalDessertCreationTest() {
        login = new Login();
    }

    @Given("the user is logged in with email {string} and password {string}")
    public void theUserIsLoggedInWithEmailAndPassword(String email, String password) {
        login.initializeUsers();

        currentUser = login.getCurrentUser(email, password);
        assertNotNull("User should be logged in", currentUser);
        login.setLogInStatus(true);
    }

    @When("the user posts a new dessert recipe with name {string} and content {string}")
    public void theUserPostsANewDessertRecipeWithNameAndContent(String name, String content) {
        assertNotNull("User should be logged in", currentUser);
        login.addRecipe(currentUser.getEmail(), currentUser.getPassword(), name, content);
        postedRecipeName = name;
        postedRecipeContent = content;
    }

    @Then("the recipe is added to the user's recipes")
    public void theRecipeIsAddedToTheUserSRecipes() {
        login.initiateRecipe();
        List<Recipe> recipes = login.getPosts(currentUser.getEmail(), currentUser.getPassword());
        assertFalse("User's recipes should not be empty", recipes.isEmpty());
        Recipe lastRecipe = recipes.get(recipes.size() - 1);
        assertEquals("The last recipe's name should match the posted name", postedRecipeName, lastRecipe.getName());
        assertEquals("The last recipe's content should match the posted content", postedRecipeContent, lastRecipe.getContent());
    }

    @When("the user views their recipes")
    public void theUserViewsTheirRecipes() {
        assertNotNull("User should be logged in", currentUser);
    }

    @Then("the user can see their dessert recipes")
    public void theUserCanSeeTheirDessertRecipes() {
        List<Recipe> recipes = login.getPosts(currentUser.getEmail(), currentUser.getPassword());
        assertFalse("User's recipes should not be empty", recipes.isEmpty());
    }

    @When("the user views other users' recipes")
    public void theUserViewsOtherUsersRecipes() {
        // No specific actions are needed here as we're just checking visibility
    }

    @Then("the user can see other users' recipes")
    public void theUserCanSeeOtherUsersRecipes() {
        login.initiateRecipe();
        List<Recipe> recipes = login.getOtherUsersRecipes(currentUser.getEmail());
        assertFalse("There should be recipes available to view", recipes.isEmpty());
    }

    @Given("the user has posted a dessert recipe with name {string} and content {string}")
    public void theUserHasPostedADessertRecipeWithNameAndContent(String name, String content) {
        assertNotNull("User should be logged in", currentUser);
        login.addRecipe(currentUser.getEmail(), currentUser.getPassword(), name, content);
        postedRecipeName = name;
        postedRecipeContent = content;
    }

    @When("another user views all recipes")
    public void anotherUserViewsAllRecipes() {
        // You might need to set up another user for this action if not already done
    }

    @Then("the other user can see the recipe with name {string} and content {string}")
    public void theOtherUserCanSeeTheRecipeWithNameAndContent(String name, String content) {
        List<Recipe> recipes = login.getAllRecipes();
        boolean found = recipes.stream().anyMatch(recipe -> recipe.getName().equals(name) && recipe.getContent().equals(content));
        assertTrue("The recipe should be visible to other users", found);
    }
}
