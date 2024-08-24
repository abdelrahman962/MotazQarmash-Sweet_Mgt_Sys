package Sweet.system;

import java.util.*;

public class Product {
    private String name;
    private double price;
    private String description;
    private String dietaryNeeds;  // Add this field
    private String storeOwnerEmail; // New field to store the email of the StoreOwner
    private int productQuantity;
    private Map<User, List<String>> userFeedbacks; // Use a Map to store feedbacks

    private String providerEmail;
    private List<String>feedbacks;

    public double sales=0; // New field to track total sales
    public double totalRevenue; // New field to track total revenue






    public Product(String name, double price, String description, String dietaryNeeds,String storeOwnerEmail) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.dietaryNeeds = dietaryNeeds;  // Initialize the dietaryNeeds
        this.feedbacks = new ArrayList<>(); // Initialize the feedback list
        this.storeOwnerEmail = storeOwnerEmail;
        this.userFeedbacks = new HashMap<>(); // Initialize the feedback map

        this.sales = 0;
        this.totalRevenue = 0;
    }

    public void Product_provider(String name, double price, String description, String dietaryNeeds, String providerEmail) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.dietaryNeeds = dietaryNeeds;  // Initialize the dietaryNeeds
        this.feedbacks = new ArrayList<>(); // Initialize the feedback list
        this.providerEmail = providerEmail;
        this.sales = 0;
        this.totalRevenue = 0;
    }



    public Product(String productName, double price, String description, String dietaryNeeds) {
        this.name = productName;
        this.price = price;
        this.description = description;
        this.dietaryNeeds = dietaryNeeds;  // Initialize the dietaryNeeds
        this.feedbacks = new ArrayList<>(); // Initialize the feedback list
        this.sales = 0;
        this.totalRevenue = 0;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getStoreOwnerEmail() {
        return storeOwnerEmail;
    }
    public String getproviderEmail() {
        return providerEmail;
    }

    public void setproviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }
    // Setter for storeOwnerEmail
    public void setStoreOwnerEmail(String storeOwnerEmail) {
        this.storeOwnerEmail = storeOwnerEmail;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDietaryNeeds(String dietaryNeeds) {  // Add this setter
        this.dietaryNeeds = dietaryNeeds;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Map<User, List<String>> getUserProvidedFeedback() {
        return userFeedbacks;
    }

    public List<String> getFeedbacks() {
        return feedbacks;
    }

    public String getDietaryNeeds() {  // Add this getter
        return dietaryNeeds;
    }

    public Map<User, List<String>> getUserFeedbacks() {
        return userFeedbacks;
    }

    public void addFeedback(User user, String feedback) {
        userFeedbacks.putIfAbsent(user, new ArrayList<>());
        userFeedbacks.get(user).add(feedback);
        feedbacks.add(feedback); // Keep this for backward compatibility or remove if not needed
    }

    public void applyDiscount(double discountPercentage) {
        this.price = this.price * (1 - discountPercentage / 100.0);
    }

    public void recordSale(int quantity) {
        this.sales += quantity;
        this.totalRevenue += quantity * this.price;
    }

    public double getSales() {
        return sales;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }


    public void setQuantity(int quantity) {
        this.productQuantity = quantity;

    }
    public int getProductQuantity(){
        return productQuantity;
    }
}
