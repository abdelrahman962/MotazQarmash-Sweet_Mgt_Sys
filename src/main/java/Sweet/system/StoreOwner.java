package Sweet.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StoreOwner extends ProductOwner {
    private List<User> userpurchased;

    public StoreOwner(String email, String password, String city) {
        super(email, password, city);
        this.userpurchased = new ArrayList<>();
    }

    public void addUserPurchased(User user) {
        if (!userpurchased.contains(user)) {
            userpurchased.add(user);
        }
    }

    public List<User> getUserpurchased() {
        return new ArrayList<>(new HashSet<>(userpurchased));
    }

    public List<Message> viewMessages(Login login) {
        return login.getMessagesForStoreOwner(email);
    }

    public void respondToMessage(Login login, String userEmail, String response) {
        login.sendMessageToStoreOwner(email, userEmail, response);
    }

    public List<User> Order_Management(String ownerEmail) {
        List<User> user_returns = new ArrayList<>();
        Set<User> uniqueUsers = new HashSet<>();
        List<String> output = new ArrayList<>();


        Login login = new Login();
        List<Product> storeProducts = login.getStoreOwnerProducts(ownerEmail);


        for (User currentUser : getUserpurchased()) {
            StringBuilder userInfo = new StringBuilder();
            userInfo.append("Processing user: ").append(currentUser.getEmail());

            boolean userHasOrdered = false;


            for (Product userProduct : currentUser.getBasket()) {
                userInfo.append("\nChecking product: ").append(userProduct.getName());

                // Compare the user's basket product with the store owner's products
                for (Product storeProduct : storeProducts) {
                    if (storeProduct.getName().equals(userProduct.getName())) {
                        userHasOrdered = true;
                        break;
                    }
                }

                if (userHasOrdered) {
                    break;
                }
            }


            if (userHasOrdered && !uniqueUsers.contains(currentUser)) {
                uniqueUsers.add(currentUser);
                user_returns.add(currentUser);

                output.add("User Email: " + currentUser.getEmail());
            }
        }

     
        return user_returns;
    }
}
