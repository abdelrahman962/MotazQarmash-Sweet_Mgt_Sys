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
        Set<User> uniqueUsers = new HashSet<>();  // To avoid duplicate user entries
        List<String> output = new ArrayList<>();  // To store the output

        // Retrieve the store owner's products based on their email
        Login login = new Login();
        List<Product> storeProducts = login.getStoreOwnerProducts(ownerEmail);

        // Iterate through each user in the userpurchased list
        for (User currentUser : getUserpurchased()) {
            StringBuilder userInfo = new StringBuilder();
            userInfo.append("Processing user: ").append(currentUser.getEmail());

            boolean userHasOrdered = false;

            // Iterate through each product in the user's basket
            for (Product userProduct : currentUser.getBasket()) {
                userInfo.append("\nChecking product: ").append(userProduct.getName());

                // Compare the user's basket product with the store owner's products
                for (Product storeProduct : storeProducts) {
                    if (storeProduct.getName().equals(userProduct.getName())) {
                        userHasOrdered = true;
                        break; // Found a match, no need to check further
                    }
                }

                if (userHasOrdered) {
                    break; // User has ordered from this store, no need to check further products
                }
            }

            // Add the user to the return list if they have ordered from the store owner and is not already in the list
            if (userHasOrdered && !uniqueUsers.contains(currentUser)) {
                uniqueUsers.add(currentUser);
                user_returns.add(currentUser);
                // Add user details to the output list
                output.add("User Email: " + currentUser.getEmail());
            }
        }

        // Return the list of output messages
        return user_returns;
    }



}
