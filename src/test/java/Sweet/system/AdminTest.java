package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class AdminTest {
    Login login;
    User currentUser;
    String email;
    String adminEmail;
String role;
String userEmail;
String newPassword;
private List<Product>products;
private List<Recipe>recipes;

    public AdminTest(Login login) {
        this.login = login;
    }


    @Given("I logged in as an admin with email {string} and password {string}")
    public void iLoggedInAsAnAdminWithEmailAndPassword(String adminEmail, String adminPassword) {
        adminEmail = adminEmail;
       currentUser=login.getCurrentUser(adminEmail, adminPassword);
        assertTrue(currentUser.isAdmin()); // Admin status should be true
    }

    @When("I create an account with {string}, {string}, {string} and {string} as the following for a new User")
    public void iCreateAnAccountWithAndAsTheFollowingForANewUser(String email, String password, String role, String city) {
        this.email = email;
        if (role.equals("user")) {
            login.addUser(email, password);
        } else if (role.equals("storeowner")) {
            login.addStoreOwner(email, password, city);
        }
    }

    @Then("I should see a success message confirming the account creation")
    public void iShouldSeeASuccessMessageConfirmingTheAccountCreation() {
        assertTrue(login.emailExists(email)); // Check that the email now exists
    }

    @When("I delete the account with email {string}")
    public void iDeleteTheAccountWithEmail(String email) {
        login.deleteUser(email);
    }

    @Then("I should see a success message confirming the account deletion")
    public void iShouldSeeASuccessMessageConfirmingTheAccountDeletion() {
        assertFalse(login.emailExists(email)); // Check that the email no longer exists
    }


    @Given("the account with email {string} for role {string} exists")
    public void theAccountWithEmailForRoleExists(String userEmail, String role) {
     this.role=role;
     assertTrue(login.emailExists(userEmail));
     this.userEmail=userEmail;
    }


    @When("I update the email of the user to {string}")
    public void iUpdateTheEmailOfTheUserTo(String newEmail) {
       login.updateUserEmail(userEmail,newEmail,role);
       userEmail=newEmail;
    }

    @When("I update the password of the user to {string}")
    public void iUpdateThePasswordOfTheUserTo(String newPassword) {
login.updateUserPassword(userEmail,newPassword,role);
this.newPassword=newPassword;
    }

    @Then("I should see a success message confirming the account update")
    public void iShouldSeeASuccessMessageConfirmingTheAccountUpdate() {
        if (role.equalsIgnoreCase("user")) {
            User updatedUser = login.findUserByEmail(userEmail);
            assertNotNull("User not found after update", updatedUser);
            assertEquals("User email did not update correctly", userEmail, updatedUser.getEmail());
        } else if (role.equalsIgnoreCase("storeowner")) {
            StoreOwner updatedStoreOwner = login.findStoreOwnerByEmail(userEmail);
            assertNotNull("StoreOwner not found after update", updatedStoreOwner);
            assertEquals("StoreOwner email did not update correctly", userEmail, updatedStoreOwner.getEmail());

        } else {
            fail("Role not recognized: " + role);
        }
    }




    @When("I view all products in the system")
    public void iViewAllProductsInTheSystem() {
       products=login.getAllProducts();
    }

    @Then("I should see a list of all products with their details")
    public void iShouldSeeAListOfAllProductsWithTheirDetails() {
        // Ensure the product list is not empty
        assertNotNull("The product list should not be null", products);
        assertFalse("The product list should not be empty", products.isEmpty());

        // Iterate through each product and check the details
        for (Product product : products) {
            assertNotNull("Product name should not be null", product.getName());
            assertNotNull("Store owner should not be null", product.getStoreOwnerEmail());
            assertNotNull("Product content should not be null", product.getDescription());
            assertNotNull("Product feedback should not be null", product.getFeedbacks());
            assertTrue("Product price should be greater than 0", product.getPrice() > 0);
        }
    }

    @When("I view all recipes in the system")
    public void iViewAllRecipesInTheSystem() {
      recipes=login.getAllRecipes();
    }

    @Then("I should see a list of all recipes with their details")
    public void iShouldSeeAListOfAllRecipesWithTheirDetails() {
        // Ensure the product list is not empty
        assertNotNull("The product list should not be null", recipes);
        assertFalse("The product list should not be empty", recipes.isEmpty());

        // Iterate through each product and check the details
        for (Recipe recipe : recipes) {
            assertNotNull("Product name should not be null", recipe.getName());
            assertNotNull("Store owner should not be null", recipe.getOwnerEmail());
            assertNotNull("Product content should not be null",recipe.getContent());
            assertNotNull("Product feedback should not be null",recipe.getFeedbacks());

        }
    }

}










