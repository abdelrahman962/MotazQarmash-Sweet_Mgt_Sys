package Sweet.system;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StoreOwner {
    private String email;
    private String password;
    private String city;
    public List<Product> products;

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

    public List<User> Order_Management()
    {
        List<User> user_returns = new ArrayList<>();
        Login user = new Login();

        for (int i =0 ; i <user.users.size();i++ )
        {
            System.out.print(user.users.size());

            for (int j =0 ; j< user.users.get(i).getBasket().size();j++ )


            {
                for (int k = 0; k < this.products.size(); k++)
                {
                    if (this.products.get(k).getName() == user.users.get(i).getBasket().get(j).getName())
                    {
                        user_returns.add(user.users.get(i));
                        j = user.users.get(i).getBasket().size();
                        break;
                    }
                }
            }



        }

        return user_returns ;
    }


}
