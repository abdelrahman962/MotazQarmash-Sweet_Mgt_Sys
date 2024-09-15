package Sweet.system;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class ProductOwner {
    protected String email;
    protected String password;
    protected String city;
    protected List<Product> products;

    protected ProductOwner(String email, String password, String city) {
        this.email = email;
        this.password = password;
        this.city = city;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        // You might want to have different implementations for this in subclasses
        // So it's defined as an abstract method
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    // You can also include abstract methods for functionalities that differ between subclasses
}
