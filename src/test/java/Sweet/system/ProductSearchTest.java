package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductSearchTest {

    private Login login;
    private User loggedInUser;
    private double totalBasketPrice;
    private String userEmail;
    private String userPassword;
    private List<Product> basket;
    private List<Product> searchResults;
    private Product searchedProduct;
 private String storeOwnerEmail;
    public ProductSearchTest() {
        this.login = new Login();  // Initialize Login instance
    }

    @Given("a user with email {string} and password {string} is logged in")
    public void aUserWithEmailAndPasswordIsLoggedIn(String email, String password) {
        userEmail=email;
        userPassword=password;
        int userType = login.getTypeNumber(email, password);
        if (userType == 1) {
            loggedInUser = login.getCurrentUser(email, password);
            login.setLogInStatus(true);
        } else {
            fail("Login failed for user: " + email);
        }
    }

    @When("the user searches for a product by name {string} with dietary needs or food allergies {string} from store owner {string}")
    public void theUserSearchesForAProductByNameWithDietaryNeedsOrFoodAllergiesFromStoreOwner(String productName, String dietaryNeed, String storeOwnerEmail) {
        this.storeOwnerEmail=storeOwnerEmail;
        searchResults =login.searchProductsByDietaryNeed(dietaryNeed, storeOwnerEmail);
        searchedProduct = searchResults.isEmpty() ? null : searchResults.get(0);
    }


    @When("the user searches for a product by name {string} and {string} from store owner {string}")
    public void theUserSearchesForAProductByNameAndFromStoreOwner(String productName, String string2, String storeOwnerEmail) {
        searchResults = filterProductsByCriteria(productName, string2, storeOwnerEmail);
        searchedProduct = searchResults.isEmpty() ? null : searchResults.get(0);
    }

    @Then("the price of the product {string} should be {double}")
    public void thePriceOfTheProductShouldBe(String productName, Double expectedPrice) {
        if (searchedProduct != null && searchedProduct.getName().equalsIgnoreCase(productName)) {
            assertEquals("Product price does not match", expectedPrice, searchedProduct.getPrice(), 0.01);
        } else {
            fail("Product not found or does not match: " + productName);
        }
    }

    @Then("the description of the product {string} should be {string}")
    public void theDescriptionOfTheProductShouldBe(String productName, String expectedDescription) {
        if (searchedProduct != null && searchedProduct.getName().equalsIgnoreCase(productName)) {
            assertEquals("Product description does not match", expectedDescription, searchedProduct.getDescription());
        } else {
            fail("Product not found or does not match: " + productName);
        }
    }

    @Then("the product {string} should be {string}")
    public void theProductShouldBe(String productName, String expectedStatus) {
        if (searchedProduct != null && searchedProduct.getName().equalsIgnoreCase(productName)) {
            assertTrue("Product status does not match: " + expectedStatus,
                    searchedProduct.getDescription().toLowerCase().contains(expectedStatus.toLowerCase()));
        } else {
            fail("Product not found or does not match: " + productName);
        }
    }

    @When("the user searches for products with dietary needs or food allergies {string}")
    public void theUserSearchesForProductsWithDietaryNeedsOrFoodAllergies(String dietaryNeed) {
        searchResults = login.searchProductsByDietaryNeed(dietaryNeed,storeOwnerEmail);
    }

    @Then("the result should contain products that are {string}")
    public void theResultShouldContainProductsThatAre(String dietaryNeed) {
        boolean allProductsAreDietaryNeed = searchResults.stream()
                .allMatch(product -> product.getDescription().toLowerCase().contains(dietaryNeed.toLowerCase()));
        assertTrue("Not all products are " + dietaryNeed, allProductsAreDietaryNeed);
    }

    @When("the user searches for a product by name {string}")
    public void theUserSearchesForAProductByName(String productName) {
        searchResults = login.searchProducts(productName);
        searchedProduct = searchResults.isEmpty() ? null : searchResults.get(0);
    }
    @Then("the no match result should be {string}")
    public void theNoMatchResultShouldBe(String expectedResult) {
        if (searchResults.isEmpty()) {
            assertEquals("No products found", expectedResult);
        } else {
            fail("Products were found but none should have matched the criteria.");
        }
    }
    private List<Product> filterProductsByCriteria(String productName, String dietaryNeed, String storeOwnerEmail) {
        List<Product> storeOwnerProducts = login.getStoreOwnerProducts(storeOwnerEmail);
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : storeOwnerProducts) {
            boolean matchesName = product.getName().toLowerCase().contains(productName.toLowerCase());
            boolean matchesDietaryNeed = dietaryNeed == null || product.getDietaryNeeds().toLowerCase().contains(dietaryNeed.toLowerCase());
            if (matchesName && matchesDietaryNeed) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @When("the user purchases {string} with quantity {int}")
    public void theUserPurchasesWithQuantity(String productName, Integer quantity) {
        assertTrue(login.purchaseProduct("jane.doe@example.com", "123", productName, quantity));
        totalBasketPrice = login.getUserTotalBasketPrice("jane.doe@example.com", "123");
        basket = login.getUserBasket("jane.doe@example.com", "123");
    }

    @Then("the total basket price should be {double}")
    public void theTotalBasketPriceShouldBe(Double expectedTotalPrice) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(expectedTotalPrice, totalBasketPrice, 0.01);

    }

    @Then("the basket should contain the product {string} with quantity {int}")
    public void theBasketShouldContainTheProductWithQuantity(String productName, Integer quantity) {
        long count = basket.stream().filter(product -> product.getName().equals(productName)).count();
        assertEquals((long) quantity, count);
    }

    @When("the user clears the basket")
    public void theUserClearsTheBasket() {
        // Write code here that turns the phrase above into concrete actions
        login.clearUserBasket(userEmail, userPassword);
    }

    @Then("the basket should be empty")
    public void theBasketShouldBeEmpty() {

        basket = login.getUserBasket(userEmail,userPassword );
        assertTrue(basket.isEmpty());
    }



}
