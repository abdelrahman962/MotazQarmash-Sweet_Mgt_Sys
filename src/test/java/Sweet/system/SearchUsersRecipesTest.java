package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import java.util.List;

public class SearchUsersRecipesTest {

    private Login login;
    private String currentUserEmail;
    private List<Recipe> searchResults;
    private Recipe foundRecipe;

    // Constructor to initialize the Login system
    public SearchUsersRecipesTest() {
        login = new Login(); // Initialize or mock the Login system
        login.initiateRecipe(); // Setup recipes
        login.initializeUsers(); // Initialize users
    }

    @Given("a user with email {string} is logged in")
    public void aUserWithEmailIsLoggedIn(String email) {
        currentUserEmail = email;
        login.setLogInStatus(true); // Simulate user login
    }

    @When("the user searches for a dessert recipe by name {string}")
    public void theUserSearchesForADessertRecipeByName(String recipeName) {
        searchResults = login.searchRecipes(recipeName); // Search for recipes
        foundRecipe = null; // Initialize to null in case no recipe is found

        // Iterate over the search results to find the first matching recipe
        for (Recipe recipe : searchResults) {
            if (recipe.getName().equalsIgnoreCase(recipeName)) {
                foundRecipe = recipe; // Set foundRecipe if a match is found
                break; // Exit the loop after finding the first match
            }
        }
    }

    @Then("the content of the recipe {string} should be {string}")
    public void theContentOfTheRecipeShouldBe(String recipeName, String expectedContent) {
        assertNotNull("Recipe should be found", foundRecipe); // Ensure the recipe is found
        assertEquals("Recipe name should match", recipeName, foundRecipe.getName()); // Check recipe name
        assertEquals("Recipe content should match", expectedContent, foundRecipe.getContent()); // Check recipe content
    }

    @Then("the result should be {string}")
    public void theResultShouldBe(String expectedResult) {

            assertNull("Recipe could not be found", foundRecipe); // Ensure no recipe is found

    }
}
