Feature: Direct Communication with Store Owners and Suppliers

  Scenario Outline: User sends a message to a recipient
    Given I am a registered user with the email "<userEmail>" and the password "<userPassword>"
    And I am logged into the system
    And I have selected the "<recipientType>" with the email "<recipientEmail>"
    When I send a "<messageContent>" to the "<recipientType>" with the email "<recipientEmail>"

    And I should receive a confirmation that the message was sent successfully

    Examples:
      | userEmail                | userPassword | recipientType | recipientEmail                | messageContent    |
      | abdelrahmanmasri3@gmail.com | 123          | store owner    | mota12@gmail.com                    | Can you provide more details about the ingredients of the Chocolate Cake? |
      | abdelrahmanmasri3@gmail.com | 123          | provider      | abdelrahmanmasri333@gmail.com      | I need assistance with bulk ordering ingredients for a Berry Cake. Can you help? |

  Scenario Outline: User receives a response to their inquiry or request
    Given I am a "<userType>" with the email "<ownerEmail>" and the password "<ownerPassword>"
    And I am logged into the system
    And I have received a "<messageContent>" from a user with the email "<userEmail>"
    When I respond to "<responseContent>"
    Then the user with the email "<userEmail>" should receive my response as a message
    And I should see a confirmation that my response was sent successfully

    Examples:
      | userType  | ownerEmail                | ownerPassword      | messageContent                                                   | userEmail                  | responseContent                                                               |
      | store owner| mota12@gmail.com           | 12                   | Can you provide more details about the ingredients of the Chocolate Cake? | abdelrahmanmasri3@gmail.com | The Chocolate Cake contains flour, sugar, cocoa powder, eggs, and butter. It is gluten-free. |
      | provider  | abdelrahmanmasri333@gmail.com | 123                   | Yes, we can provide bulk ingredients for Berry Cake. Please specify the quantities and delivery date. | abdelrahmanmasri3@gmail.com | Yes, we can provide bulk ingredients for Berry Cake. Please specify the quantities and delivery date. |
