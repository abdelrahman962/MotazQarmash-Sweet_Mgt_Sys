package Sweet.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class
User {
    private String email;
    private String password;
    private final String role;
    private final int type;
    private boolean admin = false;
    private List<Recipe> recipes;
    private List<Product> basket;
    private List<String> notifications;
    private List<Message> messages;  // Added field to store messages

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.recipes = new ArrayList<>();
        this.basket = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.messages = new ArrayList<>();  // Initialize messages
        if(role.equals("admin")) {
            type=0;
        }
        else{
            type=1;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return Objects.equals(this.getRole(), "admin");
    }

    public String getEmail() {
        return email;
    }

    public void setAsAdmin() {
        this.admin = true;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void addPost(Recipe recipe) {
        recipes.add(recipe);
    }

    public List<Recipe> getPosts() {
        return recipes;
    }

    public void addProductToBasket(Product product) {
        if (!basket.contains(product)) {
            basket.add(product);
        }
    }

    public double getTotalBasketPrice() {
        return basket.stream().mapToDouble(Product::getPrice).sum();
    }

    public List<Product> getBasket() {
        return new ArrayList<>(new HashSet<>(basket));

    }

    public void clearBasket() {
        basket.clear();
    }

    public void sendMessageToStoreOwner(Login login, String storeOwnerEmail, String message, Product currentProduct) {
        login.sendMessageToStoreOwner(email, storeOwnerEmail, message);
    }



    public void addNotification(String notification) {
        notifications.add(notification);
    }

    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }

    public void addMessage(Message message) {
        messages.add(message);  // Store the message in the user's message list
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);  // Return a copy of the message list
    }

    public void respondToMessage(Login login, String senderEmail, String responseContent) {
        login.sendMessageToStoreOwner(this.email, senderEmail, responseContent);
    }

    public double  total_price(StoreOwner StoreOwner)
    {
        double total =0;

        for (int i =0 ; i<StoreOwner.products.size();i++ )
        {
            total+= StoreOwner.products.get(i).getPrice() * StoreOwner.products.get(i).getSales();

        }


        return total;
    }



    public List<Product> best_selling(StoreOwner StoreOwner)
    {
        return  StoreOwner.getBestSellingProducts();
    }


    public int getType() {
        return type;
    }

}
