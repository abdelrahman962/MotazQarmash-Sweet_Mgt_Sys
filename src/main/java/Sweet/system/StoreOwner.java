package Sweet.system;

import java.util.ArrayList;
import java.util.List;

public class StoreOwner {
    private String email;
    private String password;
    private String city;
    private List<Product> products;

    public StoreOwner(String email, String password, String city) {
        this.email = email;
        this.password = password;
        this.city = city;
        this.products = new ArrayList<>();

    }



    public void addProduct(Product product) {
        product.setStoreOwnerEmail(this.email);
        products.add(product);
    }


    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public List<Message> viewMessages(Login login) {
        return login.getMessagesForStoreOwner(email);
    }

    public void respondToMessage(Login login, String userEmail, String response) {
        login.sendMessageToStoreOwner(email, userEmail, response);
    }

    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

}
