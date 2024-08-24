package Sweet.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Login {
    String admin = "admin";
    String user = "user";
    public List<User> users = new ArrayList<>();
    public List<StoreOwner> storeOwners = new ArrayList<>();
    public List<Provider> providers = new ArrayList<>();
    private boolean logInStatus;
    public List<Recipe> recipes = new ArrayList<>();
    public List<Product> products = new ArrayList<>();
    public List<String>productFeedback=new ArrayList<>();
    public List<String>recipeFeedback=new ArrayList<>();
    private final Map<String, List<Message>> messagesToStoreOwners = new HashMap<>();
    private final Map<String, List<Message>> messagesToProviders = new HashMap<>();
    private final Map<String, List<Message>> messagesToUsers = new HashMap<>();



    public Login() {
        initializeUsers();
        initiateRecipe();
        initiateProduct();
        initMessages();
        initfeedback();
    }

    public void initializeUsers() {
        // Initialize users and store owners
        User u1 = new User("as12112958@stu.najah.edu", "123", admin);
        User u2 = new User("m2n@gmail.com", "123", user);
        User u3 = new User("abdelrahmanmasri3@gmail.com", "123", user);
        User u4 = new User("john.doe@example.com", "123", user);
        User u5 = new User("jane.doe@example.com", "123", user);
        User u6 = new User("s1211161@stu.najah.edu", "123", user);
        StoreOwner s1 = new StoreOwner("mota12@gmail.com", "12", "Nablus");
        StoreOwner s2 = new StoreOwner("motar2@gmail.com", "12", "Jenin");
        StoreOwner s3 = new StoreOwner("moa123@gmail.com", "12", "Nablus");
        Provider p1 = new Provider("abdelrahmanmasri333@gmail.com", "123","Nablus");
        Provider p2 = new Provider("johnnn.doe@example.com", "123","Nablus");
        providers.add(p1);
        providers.add(p2);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        storeOwners.add(s1);
        storeOwners.add(s2);
        storeOwners.add(s3);

        logInStatus = false;
    }

    public void initfeedback(){
        Message message1=new Message("john.doe@example.com","as12112958@stu.najah.edu ","Tried this recipe, and it turned out great!");

        addFeedbackToRecipe("john.doe@example.com ","123","Chocolate Cake","Tried this recipe, and it turned out great!");
        sendMessageToUser(message1.getSenderEmail(),message1.getReceiverEmail(),message1.getContent());


    }
    public void initiateRecipe() {
        // Initialize recipes

        addRecipe("m2n@gmail.com", "123", "Chocolate Cake", "Delicious chocolate cake recipe.");
        addRecipe("m2n@gmail.com", "123", "Berry Chocolate Cake", "Chocolate cake with berries.");
        addRecipe("jane.doe@example.com", "123", "Chocolate Cake", "Delicious chocolate cake recipe.");
        addRecipe("jane.doe@example.com", "123", "Berry Chocolate Cake", "Chocolate cake with berries.");
        addRecipe("john.doe@gmail.com", "123", "Berry Chocolate Cake", "Berry cake with mixed berries.");
        addRecipe("s1211161@stu.najah.edu ","123","Berry Cake","Delicious Berry cake recipe.");
    }


    public void initiateProduct() {
        addProduct("mota12@gmail.com", "12", "Nablus", "Chocolate Cake", 10.00, "Delicious chocolate cake with rich frosting. Gluten-free.", "gluten-free");
        addProduct("mota12@gmail.com", "12", "Nablus", "Berry Cake", 12.00, "Delicious Berry cake recipe.contains gluten", "contains gluten");
        addProduct("moa123@gmail.com","12","Jenin","Fruit Cake",20,"Fruit cake is a dense, rich cake filled with a variety of dried fruits and nuts.","");


    }

    public void initMessages() {
        // Sending messages from users to store owners and providers
        Message message1=new Message("abdelrahmanmasri3@gmail.com","mota12@gmail.com ","Great chocolate cake, loved it!  ");
        addFeedbackToProduct("abdelrahmanmasri3@gmail.com","123","Chocolate Cake","Great chocolate cake, loved it!");
        addFeedbackToProduct("s1211161@stu.najah.edu ","123","Berry Cake","The berry cake was delicious, highly recommend!");
        sendMessageToStoreOwner(message1.getSenderEmail(),message1.getReceiverEmail(),message1.getContent());
        // User: abdelrahmanmasri3@gmail.com to Store Owner: mota12@gmail.com
        sendMessageToStoreOwner("abdelrahmanmasri3@gmail.com", "mota12@gmail.com",
                "Can you provide more details about the ingredients of the Chocolate Cake?");

        // User: abdelrahmanmasri3@gmail.com to Provider: abdelrahmanmasri333@gmail.com
        sendMessageToProvider("abdelrahmanmasri3@gmail.com", "abdelrahmanmasri333@gmail.com",
                "I need assistance with bulk ordering ingredients for a Berry Cake. Can you help?");

        // Responses from Store Owner and Provider to the user

        // Store Owner: mota12@gmail.com responds to User: abdelrahmanmasri3@gmail.com
        sendMessageToUser("mota12@gmail.com", "abdelrahmanmasri3@gmail.com",
                "The Chocolate Cake contains flour, sugar, cocoa powder, eggs, and butter. It is gluten-free.");

        // Provider: abdelrahmanmasri333@gmail.com responds to User: abdelrahmanmasri3@gmail.com
        sendMessageToUser("abdelrahmanmasri333@gmail.com", "abdelrahmanmasri3@gmail.com",
                "Yes, we can provide bulk ingredients for Berry Cake. Please specify the quantities and delivery date.");
    }

    public String sendMessageToUser(String fromEmail, String toEmail, String content) {
        User user = findUserByEmail(toEmail);
        if (user != null) {
            Message message = new Message(fromEmail, toEmail, content);
            messagesToUsers.computeIfAbsent(toEmail, k -> new ArrayList<>()).add(message);
            user.addMessage(message);
            return "Message sent successfully to user.";
        }
        return "Failed to send message to user.";
    }

    public String sendMessageToStoreOwner(String fromEmail, String toEmail, String content) {
        StoreOwner storeOwner = findStoreOwnerByEmail(toEmail);
        if (storeOwner != null) {
            Message message = new Message(fromEmail, toEmail, content);
            messagesToStoreOwners.computeIfAbsent(toEmail, k -> new ArrayList<>()).add(message);

            return "Message sent successfully to store owner.";
        }
        return "Failed to send message to store owner.";
    }

    public String sendMessageToProvider(String fromEmail, String toEmail, String content) {
        Provider provider = findProviderByEmail(toEmail);
        if (provider != null) {
            Message message = new Message(fromEmail, toEmail, content);
            messagesToProviders.computeIfAbsent(toEmail, k -> new ArrayList<>()).add(message);
            return "Message sent successfully to provider.";
        }
        return "Failed to send message to provider.";
    }

    public List<Message> getMessagesForStoreOwner(String email) {
        return messagesToStoreOwners.getOrDefault(email, new ArrayList<>());
    }


    public List<Message> getMessagesForUser(String email) {
        return messagesToUsers.getOrDefault(email, new ArrayList<>());
    }

    public boolean isLoggedIn() {
        return this.logInStatus;
    }

    public void setLogInStatus(boolean logInStatus) {
        this.logInStatus = logInStatus;
    }

    public int getTypeNumber(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) return 0; // Invalid credentials

        for (User i : users) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                logInStatus = true;
                return 1;
            }
        }
        for (StoreOwner i : storeOwners) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                logInStatus = true;
                return 2;
            }
        }
        for (Provider i : providers) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                logInStatus = true;
                return 3;
            }
        }
        return 0;
    }

    public boolean emailExists(String email) {
        for (User i : users) {
            if (i.getEmail().equals(email)) {
                return true;
            }
        }
        for(StoreOwner i : storeOwners) {
            if (i.getEmail().equals(email)) {
                return true;

            }
        }
        for(Provider i : providers) {
            if (i.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void addServiceProvider(String email, String password,String city) {
        Provider newProvider = new Provider(email, password,city);
        providers.add(newProvider);
    }

    public User getCurrentUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) return null;

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(String email, String password) {
        User newUser = new User(email, password, user);
        users.add(newUser);
    }

    public void deleteUser(String email) {
        Object entity = getEntityByEmail(email);

        switch (entity) {
            case User _ -> {
                // Delete from Users
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getEmail().equals(email)) {
                        users.remove(i);
                        break;  // Exit the loop after removing the user
                    }
                }
            }
            case StoreOwner _ -> {
                // Delete from StoreOwners
                for (int i = 0; i < storeOwners.size(); i++) {
                    if (storeOwners.get(i).getEmail().equals(email)) {
                        storeOwners.remove(i);
                        break;  // Exit the loop after removing the store owner
                    }
                }
            }
            case Provider _ -> {
                // Delete from Providers
                for (int i = 0; i < providers.size(); i++) {
                    if (providers.get(i).getEmail().equals(email)) {
                        providers.remove(i);
                        break;  // Exit the loop after removing the provider
                    }
                }
            }
            case null, default -> System.out.println("No matching entity found for deletion.");
        }
    }




    public void addStoreOwner(String email, String password, String city) {
        StoreOwner newSo = new StoreOwner(email, password, city);
        storeOwners.add(newSo);
    }

    public void updateUserEmail(String oldEmail, String newEmail, String role) {
        if(role.equals("user")){
            for (User user : users) {
                if (user.getEmail().equals(oldEmail)) {
                    user.setEmail(newEmail);
                    break;
                }

            }}
        else if(role.equals("storeowner")){
            for (StoreOwner i : storeOwners) {
                if (i.getEmail().equals(oldEmail)) {
                    i.setEmail(newEmail);
                    break;
                }
            }
        }
        else{
            for (Provider i : providers) {
                if (i.getEmail().equals(oldEmail)) {
                    i.setEmail(newEmail);
                    break;
                }
            }
        }
    }

    public void updateUserPassword(String email, String newPassword, String role) {
        if(role.equals("user")) {
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    user.setPassword(newPassword);
                    break;
                }
            }
        }
        else if(role.equals("storeowner")){
            for(StoreOwner storeOwner: storeOwners) {
                if (storeOwner.getEmail().equals(email)) {
                    storeOwner.setPassword(newPassword);
                    break;
                }
            }
        }
        else{
            for(Provider i: providers) {
                if (i.getEmail().equals(email)) {
                    i.setPassword(newPassword);
                    break;
                }
            }
        }
    }
    public void addRecipe(String email, String password, String name, String content) {
        User user = getCurrentUser(email, password);
        if (user != null) {
            Recipe recipe = new Recipe(name, content, email);
            user.addPost(recipe);
            recipes.add(recipe);
        }
    }

    public List<Recipe> getPosts(String email, String password) {
        User user = getCurrentUser(email, password);
        if (user != null) {
            return user.getPosts();
        }
        return new ArrayList<>();
    }

    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }

    public List<Recipe> getOtherUsersRecipes(String currentUserEmail) {
        return recipes.stream()
                .filter(recipe -> !recipe.getOwnerEmail().equals(currentUserEmail))
                .collect(Collectors.toList());
    }

    public Recipe getRecipeByName(String recipeName) {
        return recipes.stream()
                .filter(recipe -> recipe.getName().equalsIgnoreCase(recipeName))
                .findFirst()
                .orElse(null);
    }

    public List<Recipe> searchRecipes(String query) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : getAllRecipes()) {
            if (recipe.getName().equalsIgnoreCase(query)) {
                result.add(recipe);
            }
        }
        return result;
    }
    public void addFeedbackToRecipe(String email, String password, String recipeName, String feedbackContent) {
        User user = getCurrentUser(email, password);
        if (user != null) {
            Recipe recipes = searchRecipes(recipeName.trim()).get(0);
            if (recipes !=null) {
                recipeFeedback.add(feedbackContent);
                recipes.addFeedback(user, feedbackContent);

            }
        }
    }

    public void addFeedbackToProduct(String email, String password, String productName, String feedbackContent) {
        User user = getCurrentUser(email, password);
        if (user != null) {
            Product product = searchProducts(productName.trim()).get(0);
            if (product != null) {
                product.addFeedback(user, feedbackContent);
                productFeedback.add(feedbackContent);
            }
        }
    }

    public void addProduct(String email, String password, String city, String productName, double price, String description, String dietaryNeed) {
        StoreOwner storeOwner = getStoreOwner(email, password);
        if (storeOwner != null) {
            Product product = new Product(productName, price, description, dietaryNeed,storeOwner.getEmail());
            storeOwner.addProduct(product);
            products.add(product);
        }
    }

    public List<Product> searchProducts(String query) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> searchProductsByDietaryNeed(String dietaryNeed, String storeOwnerEmail) {
        // Get the products for the specific store owner
        List<Product> storeOwnerProducts = getStoreOwnerProducts(storeOwnerEmail);
        List<Product> result = new ArrayList<>();

        for (Product product : storeOwnerProducts) {
            if (product.getDietaryNeeds().toLowerCase().contains(dietaryNeed.toLowerCase())) {
                result.add(product);
            }
        }

        return result;
    }

    public List<Product> getStoreOwnerProducts(String email) {
        List<Product> result = new ArrayList<>();
        for (StoreOwner storeOwner : storeOwners) {
            if (storeOwner.getEmail().equals(email)) {
                result.addAll(storeOwner.getProducts());
            }
        }
        return result;
    }

    public StoreOwner getStoreOwner(String email, String password) {
        for (StoreOwner storeOwner : storeOwners) {
            if (storeOwner.getEmail().equals(email) && storeOwner.getPassword().equals(password)) {
                return storeOwner;
            }
        }
        return null;
    }

    public StoreOwner findStoreOwnerByEmail(String email) {
        for (StoreOwner storeOwner : storeOwners) {
            if (storeOwner.getEmail().equals(email)) {
                return storeOwner;
            }
        }
        return null;
    }

    public User findUserByEmail(String userEmail) {
        for (User user : users) {
            if (user.getEmail().equals(userEmail)) {
                return user;
            }
        }
        return null;
    }


    public Provider findProviderByEmail(String email) {
        for (Provider provider : providers) {
            if (provider.getEmail().equals(email)) {
                return provider;
            }
        }
        return null;
    }

    public Object getEntityByEmail(String email) {
        User user = findUserByEmail(email);
        if (user != null) {
            return user;
        }

        Provider provider = findProviderByEmail(email);
        if (provider != null) {
            return provider;
        }

        StoreOwner storeOwner = findStoreOwnerByEmail(email);
        if (storeOwner != null) {
            return storeOwner;
        }

        return null; // No matching entity found
    }


    public boolean purchaseProduct(String userEmail, String userPassword, String productName, int quantity, String storeOwnerEmail) {
        User user = getCurrentUser(userEmail, userPassword);
        if (user == null) {
            return false; // User not found
        }
        StoreOwner storeOwner = findStoreOwnerByEmail(storeOwnerEmail);
        List <Product> p=getStoreOwnerProducts(storeOwnerEmail);
        for (Product product : p) {

            if (product.getName().equalsIgnoreCase(productName) && product.getStoreOwnerEmail().equalsIgnoreCase(storeOwnerEmail)) {
                for (int i = 0; i < quantity; i++) {
                    product.setQuantity(quantity);
                    product.recordSale(quantity);
                    user.addProductToBasket(product);
                    storeOwner.addUserPurchased(findUserByEmail(userEmail));

                }
                return true; // Purchase successful
            }
        }

        return false; // Product not found for the specified store owner
    }


    public double getUserTotalBasketPrice(String userEmail, String userPassword) {
        User user = getCurrentUser(userEmail, userPassword);
        if (user != null) {
            return user.getTotalBasketPrice();
        }
        return 0.0;
    }

    public List<Product> getUserBasket(String userEmail, String userPassword) {
        User user = getCurrentUser(userEmail, userPassword);
        if (user != null) {
            return user.getBasket();
        }
        return new ArrayList<>();
    }

    public void clearUserBasket(String userEmail, String userPassword) {
        User user = getCurrentUser(userEmail, userPassword);
        if (user != null) {
            user.clearBasket();
        }
    }


    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Provider getProvider(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) return null;

        for (Provider p : providers) {
            if (p.getEmail().equals(email) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    public List<String> getRecipeFeedback() {
        return recipeFeedback;
    }

    public boolean purchaseProduct(String userEmail, String userPassword, String productName, int quantity) {
        User user = getCurrentUser(userEmail, userPassword);
        if (user == null) {
            return false; // User not found
        }

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                for (int i = 0; i < quantity; i++) {
                    product.setQuantity(quantity);
                    product.recordSale(quantity);
                    user.addProductToBasket(product);
                }
                return true; // Purchase successful
            }
        }
        return false; // Product not found
    }


}
