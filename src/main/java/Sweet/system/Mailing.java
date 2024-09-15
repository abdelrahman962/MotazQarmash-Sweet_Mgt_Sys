package Sweet.system;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.SecureRandom;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;

public class Mailing {
    String to;
    String from;
    List<Product>urgentProduct;
    SecureRandom random;
    int verificationCode;
    String appPassword;
    Login l=new Login();
    Mailing(String to){
        this.to = to;
        from = "s12112958@stu.najah.edu";
        random = new SecureRandom();
        verificationCode = 10000 + random.nextInt(90000);
    }

    public Mailing(String userEmail, String storeOwnerEmail, List<Product>urgentProduct) {
        this.to = storeOwnerEmail;
        this.from=userEmail;
this.urgentProduct = urgentProduct;
    }

    private void sending(String subject, String text){
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");


            Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
                @Override
                protected  PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication("s12112958@stu.najah.edu","veea jaxw bjhy mbwn");
                }
            });
            session.setDebug(false);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to,false));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        }
        catch (MessagingException ppp) {
            Main.kop();
        }

    }
    // Assuming Product class has a getQuantity() method to get the quantity
    public void sendUrgentOrderMessage(String userEmail) {
        // Simulate sending an email notification to the store owner
        String from = userEmail;
        String subject = "Urgent Order";
        StringBuilder text = new StringBuilder("I want this order to be marked as urgent: ");

        // Append each product name and quantity to the text
        for (Product product : urgentProduct) {
            text.append(product.getName())
                    .append(" (Quantity: ")
                    .append(product.getProductQuantity())
                    .append("), ");
        }

        // Remove the last comma and space if there were products added
        if (!urgentProduct.isEmpty()) {
            text.setLength(text.length() - 2);
        }

        // Append the store owner's city to the message
        StoreOwner storeOwner =l.findStoreOwnerByEmail(to); // Assumes you have a method to get the store owner by email
        if (storeOwner != null) {
            text.append("\n\n User City: ").append(storeOwner.getCity());
        }

        l.sendMessageToStoreOwner("s12112958@stu.najah.edu", to, text.toString());
        sending(subject, text.toString());
    }



}
