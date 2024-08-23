package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Account_Manegment_Test {

    private StoreOwner storeOwner;
    private Provider provider;


    @Given("the store owner or provider is logged into their account")
    public void theStoreOwnerOrProviderIsLoggedIntoTheirAccount() {
        // Initialize objects
        storeOwner = new StoreOwner("oldemail@example.com", "password123", "OldCity");
        provider = new Provider("oldemail@example.com", "password123", "OldCity");


    }

    @When("they enter the new email address {string}")
    public void theyEnterTheNewEmailAddress(String newEmail) {
        storeOwner.setEmail(newEmail);
        provider.setEmail(newEmail);
    }

    @Then("the store owner's or provider's email should be updated to {string}")
    public void theStoreOwnerSOrProviderSEmailShouldBeUpdatedTo(String expectedEmail) {
        System.out.print(provider.getEmail());
        assertEquals (expectedEmail,storeOwner.getEmail());

        assertEquals(expectedEmail , provider.getEmail());
    }

    @When("they enter the current password {string}")
    public void theyEnterTheCurrentPassword(String currentPassword) {
        // This step might be used for validation in a real test
    }

    @When("they enter the new password {string}")
    public void theyEnterTheNewPassword(String newPassword) {
        storeOwner.setPassword(newPassword);
        provider.setPassword(newPassword);
    }

    @Then("the store owner or provider should be able to log in with the new password {string}")
    public void theStoreOwnerOrProviderShouldBeAbleToLogInWithTheNewPassword(String newPassword) {
        assertEquals(storeOwner.getPassword(), newPassword);
        assertEquals(provider.getPassword(), newPassword);
    }

    @When("they enter the new city {string}")
    public void theyEnterTheNewCity(String newCity) {
        storeOwner.setCity(newCity);
        provider.setCity(newCity);
    }

    @Then("the store owner's or provider's city should be updated to {string}")
    public void theStoreOwnerSOrProviderSCityShouldBeUpdatedTo(String expectedCity) {
        assertEquals(expectedCity , storeOwner.getCity());
        assertEquals(expectedCity , provider.getCity());
    }
}
