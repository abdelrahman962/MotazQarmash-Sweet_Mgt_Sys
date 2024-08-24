package Sweet.system;

public class Message {
    private String senderEmail;
    private String content;
    private String receiverEmail;

    public Message(String senderEmail, String recieverEmail, String content) {
        this.senderEmail = senderEmail;
        this.content = content;
        this.receiverEmail = recieverEmail;
    }



    public String getSenderEmail() {
        return senderEmail;
    }

    public String getContent() {
        return content;
    }
public String getReceiverEmail(){
        return receiverEmail;
}
  /*  @Override
    public String toString() {
        return "From: " + senderEmail + " - Message: " + content;
    }*/

}