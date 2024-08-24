package Sweet.system;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Provider {
    private String email;
    private String password;
    private String city;
    public List<Product> products;

    public Provider(String email, String password , String city) {
        this.email = email;
        this.password = password;
        this.city = city;
        this.products = new ArrayList<>();

    }


    public void addProduct(Product product) {
        product.setproviderEmail(this.email);
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
    public String getCity() {
        return city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String newEmail) {
        this.email=newEmail;
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


    // Method to view messages for this provider
  /*  public List<Communication.Message> viewMessages() {
        return communication.getMessagesForProvider(email);
    }
    // Method to view messages as a string
    public String viewMessagesAsString() {
        List<Communication.Message> messages = viewMessages();
        StringBuilder sb = new StringBuilder();
        for (Communication.Message message : messages) {
            sb.append("From: ").append(message.getSenderEmail()).append("\n");
            sb.append("Content: ").append(message.getContent()).append("\n\n");
        }
        return sb.toString();
    }*/

}
