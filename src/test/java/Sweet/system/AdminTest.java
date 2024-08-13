package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AdminTest {
 Login login;
 User currentUser;
 User testUser;
 String email;
 public AdminTest(Login login){
     this.login = login;

     currentUser = new User("email@email", "123", "Admin");
     testUser = new User("Hi", "123", "User");
 }
    @Given("I logged in as an admin")
    public void iLoggedInAsAnAdmin() {
        // Write code here that turns the phrase above into concrete actions
        currentUser.setAsAdmin();
        assertFalse(currentUser.isAdmin());    }

    @When("I create an account with {string}, {string}, {string} and {string} as the following for a new User")
    public void iCreateAnAccountWithAndAsTheFollowingForANewUser(String email, String password, String role, String city, io.cucumber.datatable.DataTable dataTable) {

        this.email = email;
        if(role.equals("user")){
            login.addUser(email, password);
        }
        else if(role.equals("storeowner")){
            login.addStoreOwner(email, password,city);
        }
        else{
            login.addServiceProvider(email, password);
        }
    }


    @Then("I should see a success message confirming the account creation")
    public void iShouldSeeASuccessMessageConfirmingTheAccountCreation() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(login.emailExists(email));
        assertTrue(login.emailExists("s1211161@stu.najah.edu"));    }

    @When("I delete the account with email {string}")
    public void iDeleteTheAccountWithEmail(String email) {
        // Write code here that turns the phrase above into concrete actions
        login.users.add(testUser);
        login.deleteUser(testUser.getEmail());
    }

    @Then("I should see a success message confirming the account deletion")
    public void iShouldSeeASuccessMessageConfirmingTheAccountDeletion() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(login.emailExists(testUser.getEmail()));
    }


}
