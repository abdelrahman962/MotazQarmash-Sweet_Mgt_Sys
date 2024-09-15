package Sweet.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StoreOwner extends ProductOwner {
    private final List<User> userpurchased;

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

    public List<User>orderManagement(String ownerEmail) {
        List<User> userReturns = new ArrayList<>(); // Renamed local variable
        Set<User> uniqueUsers = new HashSet<>();
        List<Product> storeProducts = new Login().getStoreOwnerProducts(ownerEmail);

        for (User currentUser : getUserpurchased()) {
            if (hasOrderedProducts(currentUser, storeProducts) && !uniqueUsers.contains(currentUser)) {
                uniqueUsers.add(currentUser);
                userReturns.add(currentUser);
            }
        }

        return userReturns;
    }

    private boolean hasOrderedProducts(User user, List<Product> storeProducts) {
        for (Product userProduct : user.getBasket()) {
            for (Product storeProduct : storeProducts) {
                if (storeProduct.getName().equals(userProduct.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

}
