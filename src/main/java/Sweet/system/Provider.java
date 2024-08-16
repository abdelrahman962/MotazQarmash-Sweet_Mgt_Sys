package Sweet.system;


public class Provider {
    private String email;
    private String password;

    public Provider(String email, String password) {
        this.email = email;
        this.password = password;
    }

public void setPassword(String password) {
        this.password = password;
}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String newEmail) {
        this.email=newEmail;
    }

    // Method to view messages for this provider
  /*  public List<Communication.Message> viewMessages() {
        return communication.getMessagesForProvider(email);
    }
    // Method to view messages as a string
    public String viewMessagesAsString() {
        List<Communication.Message> messages = viewMessages();
        StringBuilder sb = new StringBuilder();
        for (Communication.Message message : messages) {
            sb.append("From: ").append(message.getSenderEmail()).append("\n");
            sb.append("Content: ").append(message.getContent()).append("\n\n");
        }
        return sb.toString();
    }*/

}
