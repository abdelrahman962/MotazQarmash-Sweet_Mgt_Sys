package Sweet.system;

import java.util.*;

public class StoreOwner {
    private String email;
    private String password;
    private String city;
    public List<Product> products;
    public List<User> userpurchased;
    public StoreOwner(String email, String password, String city) {
        this.email = email;
        this.password = password;
        this.city = city;
        this.products = new ArrayList<>();
        this.userpurchased = new ArrayList<>();

    }


    public void addProduct(Product product) {
        product.setStoreOwnerEmail(this.email);
        products.add(product);

    }


    public void removeProduct(Product product) {
        products.remove(product);

    }

    public void updateProduct(Product updatedProduct) {
        Product existingProduct = findProductByName(updatedProduct.getName());
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setDietaryNeeds(updatedProduct.getDietaryNeeds());

        }
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

    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public double getTotalSales() {
        return products.stream().mapToDouble(Product::getSales).sum();
    }

    public double getTotalRevenue() {
        return products.stream().mapToDouble(Product::getTotalRevenue).sum();
    }

    public List<Product> getBestSellingProducts() {
        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparingDouble(Product::getSales).reversed());
        return sortedProducts;
    }


    public List<User> Order_Management(String ownerEmail) {
        List<User> user_returns = new ArrayList<>();
        Set<User> uniqueUsers = new HashSet<>();
        List<String> output = new ArrayList<>();

        // Retrieve store products
        Login login = new Login();
        List<Product> storeProducts = login.getStoreOwnerProducts(ownerEmail);

        // Iterate through each user
        for (User currentUser : getUserpurchased()) {
            if (hasOrderedFromStore(currentUser, storeProducts)) {
                if (uniqueUsers.add(currentUser)) {
                    user_returns.add(currentUser);
                    output.add("User Email: " + currentUser.getEmail());
                }
            }
        }

        // Optionally handle the output list or return it if needed
        handleOutput(output);

        return user_returns;
    }

    // Check if the user has ordered any product from the store
    private boolean hasOrderedFromStore(User user, List<Product> storeProducts) {
        for (Product userProduct : user.getBasket()) {
            if (storeProducts.stream().anyMatch(storeProduct -> storeProduct.getName().equals(userProduct.getName()))) {
                return true;
            }
        }
        return false;
    }

    // Handle the output list (logging, printing, etc.)
    private void handleOutput(List<String> output) {
        // Implement the method to handle the output, e.g., logging or printing
        for (String entry : output) {
            System.out.println(entry);
        }
    }



}
