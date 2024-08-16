package Sweet.system;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private double price;
    private String description;
    private String dietaryNeeds;  // Add this field
    private String storeOwnerEmail; // New field to store the email of the StoreOwner

    private List<String>feedbacks;
    public Product(String name, double price, String description, String dietaryNeeds,String storeOwnerEmail) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.dietaryNeeds = dietaryNeeds;  // Initialize the dietaryNeeds
        this.feedbacks = new ArrayList<>(); // Initialize the feedback list
this.storeOwnerEmail = storeOwnerEmail;
    }

    public Product(String productName, double price, String description, String dietaryNeeds) {
        this.name = productName;
        this.price = price;
        this.description = description;
        this.dietaryNeeds = dietaryNeeds;  // Initialize the dietaryNeeds
        this.feedbacks = new ArrayList<>(); // Initialize the feedback list

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
    public void addFeedback(String feedback) {
        feedbacks.add(feedback);
    }

    // Method to get all feedbacks
    public List<String> getFeedbacks() {
        return feedbacks;
    }

    public String getDietaryNeeds() {  // Add this getter
        return dietaryNeeds;
    }


}
