package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import java.util.List;

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



       assertTrue(88==admin.total_price(storeOwner));
    }

    @When("I view the best-selling products report for the store")
    public void iViewTheBestSellingProductsReportForTheStore() {

    }

    @Then("I should see a list of top-selling products in that store along with their sales figures")
    public void iShouldSeeAListOfTopSellingProductsInThatStoreAlongWithTheirSalesFigures() {

        Product expect = new Product("cake",22,"","","owner@example.com");
        expect.sales++;
        storeOwner.addProduct(expect);


        for (int i = 1; i <  admin.best_selling(storeOwner).size(); i++) {
            assertTrue( admin.best_selling(storeOwner).get(i - 1).getSales() >=  admin.best_selling(storeOwner).get(i).getSales());
        }




    }

    @When("I view the user statistics report by city")
    public void iViewTheUserStatisticsReportByCity() {

    }

    @Then("I should see the total number of registered users for each city")
    public void iShouldSeeTheTotalNumberOfRegisteredUsersForEachCity() {


        Product  expext= new Product("cake",22,"","","owner@example.com");
        storeOwner.addProduct(expext);

        user.addProductToBasket(expext);




       assertTrue(storeOwner.Order_Management().size()==0);


    }







}
