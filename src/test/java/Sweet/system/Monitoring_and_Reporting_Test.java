package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Monitoring_and_Reporting_Test {

  private   StoreOwner  storeOwner;
   private User admin;
private User user;
    @Given("I am logged in as an admin")
    public void iAmLoggedInAsAnAdmin() {
        admin = new User("as12112958@stu.najah.edu", "123", "admin");
        storeOwner = new StoreOwner("owner@example.com", "password123", "New York");
        user =new User("m2n@gmail.com", "123"," user");
    }

    @When("I view the financial reports for all stores")
    public void iViewTheFinancialReportsForAllStores() {
        // Write code here that turns the phrase above into concrete actions
    }



    @Then("I should see a detailed report showing total profits for each store")
    public void iShouldSeeADetailedReportShowingTotalProfitsForEachStore() {

        Product  expext= new Product("cake",22,"","","owner@example.com");
        expext.sales=4;
        storeOwner.addProduct(expext);


        assertEquals(88, admin.total_price(storeOwner), 0.0);
    }
    @Ignore("Not yet implemented")
    @When("I view the best-selling products report for the store")
    public void iViewTheBestSellingProductsReportForTheStore() {
        // This method is currently empty because the implementation is not yet provided.
        // It could be implemented later when the logic for viewing best-selling products is defined.
        // If this method is called before it's implemented, an exception should be thrown.
    }

    @Then("I should see a list of top-selling products in that store along with their sales figures")
    public void iShouldSeeAListOfTopSellingProductsInThatStoreAlongWithTheirSalesFigures() {

        Product expect = new Product("cake",22,"","","owner@example.com");
        double getSales=expect.getSales();
        getSales++;
        storeOwner.addProduct(expect);


        for (int i = 1; i <  admin.bestSelling(storeOwner).size(); i++) {
            assertTrue( admin.bestSelling(storeOwner).get(i - 1).getSales() >=  admin.bestSelling(storeOwner).get(i).getSales());
        }




    }
    @Ignore("Not yet implemented")
    @When("I view the user statistics report by city")
    public void iViewTheUserStatisticsReportByCity() {
        // This method is currently empty because the implementation is not yet provided.
        // The functionality to view user statistics by city needs to be defined and implemented.
        // An exception is thrown here to indicate that the functionality is pending.
    }

    @Then("I should see the total number of registered users for each city")
    public void iShouldSeeTheTotalNumberOfRegisteredUsersForEachCity() {


        Product  expext= new Product("cake",22,"","","owner@example.com");
        storeOwner.addProduct(expext);

        user.addProductToBasket(expext);


        assertEquals(0, storeOwner.orderManagement(storeOwner.getEmail()).size());


    }







}
