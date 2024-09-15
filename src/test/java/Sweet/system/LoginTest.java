package Sweet.system;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class LoginTest {
     Login login;
    User currentUser;

    public LoginTest(Login login) {

        this.login = login;
        currentUser = new User("user@email", "123", "user");

    }


    @Given("that the user is not logged in")
    public void that_the_user_is_not_logged_in() {
        login.initializeUsers();
        assertFalse(login.isLoggedIn());
    }

    @When("the information is valid email is {string} and password is {string}")
    public void the_information_is_valid_email_is_and_password_is(String email, String password) {
        if(login.getTypeNumber(email, password ) == 1 || login.getTypeNumber(email, password ) == 2){
            login.setLogInStatus(true);
        }
    }

    @Then("user successfully log in")
    public void user_successfully_log_in() {
        assertTrue(login.isLoggedIn());
        assertEquals(currentUser, login.getCurrentUser("user@email", "123"));
    }

    @When("the email is invalid email is {string} and password is {string}")
    public void the_email_is_invalid_email_is_and_password_is(String email, String password) {
        if(login.getTypeNumber(email, password ) == 0){
            login.setLogInStatus(false);
        }
    }

    @Then("user failed in log in")
    public void user_failed_in_log_in() {
        assertFalse(login.isLoggedIn());
    }

    @When("the password is invalid email is {string} and password is {string}")
    public void the_password_is_invalid_email_is_and_password_is(String email, String password) {
        if(login.getTypeNumber(email, password) == 0){
            login.setLogInStatus(false);
        }
        assertFalse(login.isLoggedIn());
    }

    @When("the information is invalid, email is {string} and password is {string}")
    public void the_information_is_invalid_email_is_and_password_is(String email, String password) {
        if(login.getTypeNumber(email, password) == 0){
            login.setLogInStatus(false);
        }
        assertFalse(login.isLoggedIn());

    }

    @When("the information exists, the email is {string}")
    public void theInformationExistsTheEmailIs(String email) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(login.emailExists(email));
    }

    @Then("signing up fails")
    public void signingUpFails() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the email format is incorrect")
    public void theEmailFormatIsIncorrect() {
        // Write code here that turns the phrase above into concrete actions
    }


    @When("the information exists, the email is not {string} and password is {string}")
    public void theInformationExistsTheEmailIsNotAndPasswordIs(String email, String password) {
        assertFalse(login.emailExists(email));
        login.addUser(email, password);
    }
    @Then("signing up succeeds")
    public void signingUpSucceeds() {
        // Write code here that turns the phrase above into concrete actions
    }



}
