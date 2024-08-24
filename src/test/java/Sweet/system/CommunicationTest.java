package Sweet.system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class CommunicationTest {

    private Login login;
    private User currentUser;
    private StoreOwner currentStoreOwner;
    private Provider currentProvider;
    private String recipientEmail;
    private String userEmail;
    private User user;
    private String messageContent;
    private String responseContent;
    private String confirmationMessage;
    private String recipientType;
private String recipientSenderEmail;
    public CommunicationTest() {
        login = new Login();
    }

    @Given("I am a registered user with the email {string} and the password {string}")
    public void iAmARegisteredUserWithTheEmailAndThePassword(String email, String password) {
        currentUser = login.getCurrentUser(email, password);
        assertNotNull("User should be registered", currentUser);
    }

    @Given("I am logged into the system")
    public void iAmLoggedIntoTheSystem() {
        login.setLogInStatus(true);
        assertTrue(login.isLoggedIn());
    }

    @Given("I have selected the {string} with the email {string}")
    public void iHaveSelectedTheWithTheEmail(String recipientType, String email) {
        recipientEmail = email;
        this.recipientType = recipientType;
        if (recipientType.equalsIgnoreCase("store owner")) {
            currentStoreOwner = login.findStoreOwnerByEmail(email);
            assertNotNull("Store owner should be found", currentStoreOwner);
        } else if (recipientType.equalsIgnoreCase("provider")) {
            currentProvider = login.findProviderByEmail(email);
            assertNotNull("Provider should be found", currentProvider);
        }
    }

    @When("I send a {string} to the {string} with the email {string}")
    public void iSendAToTheWithTheEmail(String message, String recipientType, String email) {
        messageContent = message;
        recipientEmail = email;
        if (recipientType.equalsIgnoreCase("store owner")) {
            confirmationMessage = login.sendMessageToStoreOwner(currentUser.getEmail(), recipientEmail, messageContent);
        } else if (recipientType.equalsIgnoreCase("provider")) {
            confirmationMessage = login.sendMessageToProvider(currentUser.getEmail(), recipientEmail, messageContent);
        }
    }

    @Then("I should receive a confirmation that the message was sent successfully")
    public void iShouldReceiveAConfirmationThatTheMessageWasSentSuccessfully() {
       List<Message>storeOwnerMessages= login.getMessagesForStoreOwner(recipientEmail);
        assertEquals("Message sent successfully to " + recipientType + ".", confirmationMessage);
    }

    @Given("I am a {string} with the email {string} and the password {string}")
    public void iAmAWithTheEmailAndThePassword(String userType, String email, String password) {
        if (userType.equalsIgnoreCase("store Owner")) {
            recipientSenderEmail=email;
            currentStoreOwner = login.getStoreOwner(email, password);

            assertNotNull("Store owner should be logged in", currentStoreOwner);
        } else if (userType.equalsIgnoreCase("provider")) {
            recipientSenderEmail=email;
            currentProvider = login.findProviderByEmail(email);
            assertNotNull("Provider should be logged in", currentProvider);
        }
    }

    @Given("I have received a {string} from a user with the email {string}")
    public void iHaveReceivedAFromAUserWithTheEmail(String messageContent, String userEmail) {
        this.userEmail = userEmail;

        user = login.findUserByEmail(userEmail);
Message m=new Message(recipientSenderEmail,userEmail,messageContent);
user.addMessage(m);
        // Assuming messages are stored in a list within the User object
        List<Message> messages = user.getMessages();

        // Check if the message is in the user's messages list
        boolean messageReceived = false;
        for (Message message : messages) {
            if (message.getContent().equals(messageContent)) {
                messageReceived = true;
                break;
            }
        }

        // Assert that the message was indeed received
        assertTrue("The message should have been received by the user", messageReceived);
    }

    @When("I respond to {string}")
    public void iRespondTo(String responseContent) {
        this.responseContent = responseContent;
        if (currentStoreOwner != null) {
            confirmationMessage = login.sendMessageToUser(currentStoreOwner.getEmail(), userEmail, responseContent);
        } else if (currentProvider != null) {
            confirmationMessage = login.sendMessageToUser(currentProvider.getEmail(), userEmail, responseContent);
        }
    }

    @Then("the user with the email {string} should receive my response as a message")
    public void theUserWithTheEmailShouldReceiveMyResponseAsAMessage(String userEmail) {
        User user = login.findUserByEmail(userEmail);
        assertNotNull("User should be found", user);
        List<Message> messages = user.getMessages();

        boolean responseReceived = false;
        for (Message message : messages) {
            if (message.getContent().equals(responseContent)) {
                responseReceived = true;
                break;
            }
        }
        assertTrue("User should receive the response message", responseReceived);
    }

    @Then("I should see a confirmation that my response was sent successfully")
    public void iShouldSeeAConfirmationThatMyResponseWasSentSuccessfully() {
        assertEquals("Message sent successfully to user.", confirmationMessage);
    }
}
