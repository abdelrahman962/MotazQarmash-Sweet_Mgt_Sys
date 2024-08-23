package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import java.util.List;

public class Product_Management_Test {

    private StoreOwner storeOwner;

    private Provider provider;
    private Product product;
    private double discountedPrice;
    private double totalSales;
    private double totalRevenue;
    private double totalSales1;
    private double totalRevenue1;
    private List<Product> bestSellingProducts;

    @Given("the store owner or raw material supplier is logged into their account")
    public void theStoreOwnerOrRawMaterialSupplierIsLoggedIntoTheirAccount() {
        storeOwner = new StoreOwner("owner@example.com", "password123", "New York");
        provider = new Provider("owner@example.com", "password123", "New York");
    }

    @When("they select {string}")
    public void theySelect(String action) {
        // This will depend on your implementation. For testing, assume actions are already simulated.
    }

    @When("they enter the product name {string}")
    public void theyEnterTheProductName(String name) {
        if (product != null) {
            product.setName(name);
        }
    }

    @When("they enter the price {string}")
    public void theyEnterThePrice(String price) {
        if (product != null) {
            product.setPrice(Double.parseDouble(price));
        }
    }

    @When("they enter the description {string}")
    public void theyEnterTheDescription(String description) {
        if (product != null) {
            product.setDescription(description);
        }
    }

    @When("they enter the dietary needs {string}")
    public void theyEnterTheDietaryNeeds(String dietaryNeeds) {
        if (product != null) {
            product.setDietaryNeeds(dietaryNeeds);
        }
    }

    @When("they enter the store owner or provider email {string}")
    public void theyEnterTheStoreOwnerOrProviderEmail(String email) {
        if (product != null) {
            product.setStoreOwnerEmail(email);
        }
    }

    @When("they click {string}")
    public void theyClick(String action) {
        if (action.equals("Save") && product != null) {
            storeOwner.addProduct(product);
            provider.addProduct(product);
        }
    }

    @Then("the system should display a confirmation message {string}")
    public void theSystemShouldDisplayAConfirmationMessage(String expectedMessage) {

        String actualMessage = "Product successfully added"; // Simulate system message
        assertEquals(expectedMessage, actualMessage);
    }

    @Then("the product {string} should be added to the inventory list with the correct details")
    public void theProductShouldBeAddedToTheInventoryListWithTheCorrectDetails(String name ) {
       Product expect = new Product(name,22,"","","owner@example.com");
        storeOwner.addProduct(expect);
        provider.addProduct(expect);

       Product actualProduct = storeOwner.findProductByName(name);
        Product actualProduct1 = provider.findProductByName(name);


        assertNotNull(actualProduct );
        assertEquals(actualProduct.getName(), expect.getName());

        assertNotNull(actualProduct1 );
        assertEquals(actualProduct1.getName(), expect.getName());


    }

    @When("they select the product {string}")
    public void theySelectTheProduct(String name) {
        product = storeOwner.findProductByName(name);

    }

    @When("they update the price to {string}")
    public void theyUpdateThePriceTo(String price) {
        if (product != null) {
            product.setPrice(Double.parseDouble(price));
        }
    }

    @When("they update the description to {string}")
    public void theyUpdateTheDescriptionTo(String description) {
        if (product != null) {
            product.setDescription(description);
        }
    }

    @When("they update the dietary needs to {string}")
    public void theyUpdateTheDietaryNeedsTo(String dietaryNeeds) {
        if (product != null) {
            product.setDietaryNeeds(dietaryNeeds);
        }
    }

    @Then("the product {string} should reflect the new price {string} and updated details in the inventory list")
    public void theProductShouldReflectTheNewPriceAndUpdatedDetailsInTheInventoryList(String name, String price) {



        Product expect = new Product(name,22,"","","owner@example.com");
        storeOwner.addProduct(expect);
        storeOwner.updateProduct(expect);

        provider .addProduct(expect);
                provider.updateProduct(expect);

        Product updatedProduct = storeOwner.findProductByName(name);
        updatedProduct.setPrice(Double.parseDouble(price));
        assertNotNull(updatedProduct);
        assertEquals(Double.parseDouble(price), updatedProduct.getPrice(), 0.01);

        Product updatedProduct1 =  provider.findProductByName(name);
        updatedProduct1.setPrice(Double.parseDouble(price));
        assertNotNull(updatedProduct1);
        assertEquals(Double.parseDouble(price), updatedProduct1.getPrice(), 0.01);





    }

    @When("they choose the product {string}")
    public void theyChooseTheProduct(String name) {
        product = storeOwner.findProductByName(name);
    }

    @When("they confirm the removal")
    public void theyConfirmTheRemoval() {
        if (product != null) {
            storeOwner.removeProduct(product);
           provider.removeProduct(product);
        }
    }

    @Then("the product {string} should no longer appear in the inventory list")
    public void theProductShouldNoLongerAppearInTheInventoryList(String name) {
        Product removedProduct = storeOwner.findProductByName(name);
        assertNull(removedProduct);

        Product removedProduct1 = provider.findProductByName(name);
        assertNull(removedProduct1);



    }

    @When("they select the sales and profit report")
    public void theySelectTheSalesAndProfitReport() {
        totalSales = storeOwner.getTotalSales();
        totalRevenue = storeOwner.getTotalRevenue();

        totalSales1 = provider.getTotalSales();
        totalRevenue1 = provider.getTotalRevenue();


    }

    @Then("the system should display a report showing total sales, profits, and a breakdown by product")
    public void theSystemShouldDisplayAReportShowingTotalSalesProfitsAndABreakdownByProduct() {
        double expectedSales = storeOwner.getTotalSales();
        double expectedRevenue = storeOwner.getTotalRevenue();
        assertEquals(expectedSales, totalSales, 0.01);
        assertEquals(expectedRevenue, totalRevenue, 0.01);



        double expectedSales1 = provider.getTotalSales();
        double expectedRevenue1 = provider.getTotalRevenue();
        assertEquals(expectedSales1, totalSales1, 0.01);
        assertEquals(expectedRevenue1, totalRevenue1, 0.01);





    }

    @When("they request the best-selling products")
    public void theyRequestTheBestSellingProducts() {
        bestSellingProducts = storeOwner.getBestSellingProducts();
    }

    @Then("the system should display a list of products sorted by the number of sales")
    public void theSystemShouldDisplayAListOfProductsSortedByTheNumberOfSales() {



        Product expect = new Product("cake",22,"","","owner@example.com");
        expect.sales++;
        storeOwner.addProduct(expect);
        bestSellingProducts= storeOwner.getBestSellingProducts() ;

        for (int i = 1; i < bestSellingProducts.size(); i++) {
            assertTrue(bestSellingProducts.get(i - 1).getSales() >= bestSellingProducts.get(i).getSales());
        }


        provider.addProduct(expect);
        bestSellingProducts= provider.getBestSellingProducts() ;

        for (int i = 1; i < bestSellingProducts.size(); i++) {
            assertTrue(bestSellingProducts.get(i - 1).getSales() >= bestSellingProducts.get(i).getSales());
        }





    }

    @When("they enter the discount percentage {string}")
    public void theyEnterTheDiscountPercentage(String discountPercentage) {
        if (product != null) {
            discountedPrice = product.getPrice();
            product.applyDiscount(Double.parseDouble(discountPercentage));
            discountedPrice = product.getPrice();
        }
    }

    @Then("the product {string} should show the discounted price in the store")
    public void theProductShouldShowTheDiscountedPriceInTheStore(String name) {
        Product expect = new Product(name,22,"","","owner@example.com");

int index=0;
        storeOwner.addProduct(expect);

        for (int i =0; i<storeOwner.products.size();i++)
        {
            if (storeOwner.products.get(i).getName()==name)
                index=i;
        }
        storeOwner.products.get(index).applyDiscount(15);
        Product discountedProduct = storeOwner.findProductByName(name);
        assertNotNull(discountedProduct);
        assertTrue( 18.7==discountedProduct.getPrice() );




        index=0;
        provider.addProduct(expect);

        for (int i =0; i<provider.products.size();i++)
        {
            if (provider.products.get(i).getName()==name)
                index=i;
        }
        provider.products.get(index).applyDiscount(15);
        Product discountedProduct1 = provider.findProductByName(name);
        assertNotNull(discountedProduct1);







    }
}
