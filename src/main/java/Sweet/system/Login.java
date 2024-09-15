package Sweet.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Login {
    Mailing mail;
    String admin = "admin";
    String cUser = "user";
    static final List<User> users = new ArrayList<>();
    static final List<StoreOwner> storeOwners = new ArrayList<>();
     static final List<Provider> providers = new ArrayList<>();
    private static final List<Recipe> recipes = new ArrayList<>();
    private static final List<Product> products = new ArrayList<>();
    protected static final List<String> productFeedback = new ArrayList<>();
    protected static final List<String> recipeFeedback = new ArrayList<>();
    public static final String M2N_EMAIL="m2n@gmail.com";
    public static final String ABOOD_EMAIL = "s12112958@stu.najah.edu";
    public static final String ABOOD333_EMAIL = "abdelrahmanmasri333@gmail.com";
    public static final String USER2_EMAIL = "s12114110@stu.najah.edu";
    public static final String JANE_EMAIL = "jane.doe@example.com";
    public static final String NABLUS="Nablus";
    public static final String JENIN="Jenin";
    public static final String CHOCOLATE_CAKE="Chocolate Cake";
    public static final String BERRY_CAKE="Berry Cake";
    public static final String BERRY_CHOCOLATE_CAKE="Berry Chocolate Cake";
    public static final String MOTA12_EMAIL = "abdelrahmanmasri3@gmail.com";
    public static final String JOHN_EMAIL = "john.doe@example.com";
    public static final String PASS1="123";
    public static final String PASS2="12";

    private boolean logInStatus;

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
        User u1 = new User("as12112958@stu.najah.edu", PASS1, admin);
        User u2 = new User(M2N_EMAIL, PASS1, cUser);
        User u3 = new User(ABOOD_EMAIL, PASS1, cUser);
        User u4 = new User(JOHN_EMAIL, PASS1, cUser);
        User u5 = new User(JANE_EMAIL, PASS1, cUser);
        User u6 = new User("s1211161@stu.najah.edu", PASS1, cUser);
User u7 = new User(USER2_EMAIL, PASS1, cUser);
        StoreOwner s1 = new StoreOwner(MOTA12_EMAIL, PASS2, NABLUS);
        StoreOwner s2 = new StoreOwner("motar2@gmail.com", PASS2, JENIN);
        StoreOwner s3 = new StoreOwner("moa123@gmail.com", PASS2, NABLUS);
        Provider p1 = new Provider(ABOOD333_EMAIL, PASS1,NABLUS);
        Provider p2 = new Provider("johnnn.doe@example.com", PASS1,NABLUS);
        providers.add(p1);
        providers.add(p2);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        users.add(u7);
        storeOwners.add(s1);
        storeOwners.add(s2);
        storeOwners.add(s3);

        logInStatus = false;
    }

    public void initfeedback(){
        Message message1=new Message(JOHN_EMAIL,"as12112958@stu.najah.edu","Tried this recipe, and it turned out great!");

        addFeedbackToRecipe(JOHN_EMAIL,PASS1,CHOCOLATE_CAKE,"Tried this recipe, and it turned out great!");
        sendMessageToUser(message1.getSenderEmail(),message1.getReceiverEmail(),message1.getContent());
Message message2=new Message(JANE_EMAIL,USER2_EMAIL,"Very Tasty!");
addFeedbackToRecipe(USER2_EMAIL,PASS1,BERRY_CHOCOLATE_CAKE,"Very Tasty!");
sendMessageToUser(message2.getSenderEmail(),message2.getReceiverEmail(),message2.getContent());
    }
    public void initiateRecipe() {
        // Initialize recipes

        addRecipe(M2N_EMAIL, PASS1, CHOCOLATE_CAKE, "Delicious chocolate cake recipe.");
        addRecipe(M2N_EMAIL, PASS1, BERRY_CHOCOLATE_CAKE, "Chocolate cake with berries.");
        addRecipe(JANE_EMAIL, PASS1, CHOCOLATE_CAKE, "Delicious chocolate cake recipe.");
        addRecipe(JANE_EMAIL, PASS1, BERRY_CHOCOLATE_CAKE, "Chocolate cake with berries.");
        addRecipe("john.doe@gmail.com", PASS1,BERRY_CHOCOLATE_CAKE, "Berry cake with mixed berries.");
        addRecipe("s1211161@stu.najah.edu ",PASS1,BERRY_CAKE,"Delicious Berry cake recipe.");
        addRecipe(USER2_EMAIL,PASS1,BERRY_CHOCOLATE_CAKE,"Delicious berries");
    }


    public void initiateProduct() {
        addProduct(MOTA12_EMAIL, PASS2, NABLUS, CHOCOLATE_CAKE, 10.00, "Delicious chocolate cake with rich frosting. Gluten-free.", "gluten-free");
        addProduct(MOTA12_EMAIL, PASS2, NABLUS, BERRY_CAKE, 12.00, "Delicious Berry cake recipe.contains gluten", "contains gluten");
        addProduct("moa123@gmail.com",PASS2,JENIN,"Fruit Cake",20,"Fruit cake is a dense, rich cake filled with a variety of dried fruits and nuts.","");

        addProduct(MOTA12_EMAIL, PASS2, NABLUS, BERRY_CAKE, 12.00, "Delicious Berry cake recipe.contains gluten", "contains gluten");

    }

    public void initMessages() {
        // Sending messages from users to store owners and providers
        Message message1=new Message(ABOOD_EMAIL,MOTA12_EMAIL,"Great chocolate cake, loved it!  ");
        addFeedbackToProduct(ABOOD_EMAIL,PASS1,CHOCOLATE_CAKE,"Great chocolate cake, loved it!");
        addFeedbackToProduct(JANE_EMAIL,PASS1,CHOCOLATE_CAKE,"Tasty Chocolate Cake");
        Message message2=new Message(JANE_EMAIL,MOTA12_EMAIL,"Tasty Chocolate Cake");
        Message message3=new Message(JOHN_EMAIL,"moa123@gmail.com","Fruit Cake was delicious");
        addFeedbackToProduct(JOHN_EMAIL,PASS1,"Fruit Cake","Fruit Cake was delicious");
        addFeedbackToProduct("s1211161@stu.najah.edu ",PASS1,BERRY_CAKE,"The berry cake was delicious, highly recommend!");
        sendMessageToStoreOwner(message1.getSenderEmail(),message1.getReceiverEmail(),message1.getContent());
        sendMessageToStoreOwner(message2.getSenderEmail(),message2.getReceiverEmail(),message2.getContent());
        sendMessageToStoreOwner(message3.getSenderEmail(),message3.getReceiverEmail(),message3.getContent());
        // User: abdelrahmanmasri3@gmail.com to Store Owner: mota12@gmail.com
        sendMessageToStoreOwner(ABOOD_EMAIL, MOTA12_EMAIL,
                "Can you provide more details about the ingredients of the Chocolate Cake?");

        // User: abdelrahmanmasri3@gmail.com to Provider: abdelrahmanmasri333@gmail.com
        sendMessageToProvider(ABOOD_EMAIL, ABOOD333_EMAIL,
                "I need assistance with bulk ordering ingredients for a Berry Cake. Can you help?");

        // Responses from Store Owner and Provider to the user

        // Store Owner: mota12@gmail.com responds to User: abdelrahmanmasri3@gmail.com
        sendMessageToUser(MOTA12_EMAIL, ABOOD_EMAIL,
                "The Chocolate Cake contains flour, sugar, cocoa powder, eggs, and butter. It is gluten-free.");

        // Provider: abdelrahmanmasri333@gmail.com responds to User: abdelrahmanmasri3@gmail.com
        sendMessageToUser(ABOOD333_EMAIL, ABOOD_EMAIL,
                "Yes, we can provide bulk ingredients for Berry Cake. Please specify the quantities and delivery date.");
    }

    public String sendMessageToUser(String fromEmail, String toEmail, String content) {
        User targetUser = findUserByEmail(toEmail); // Renamed local variable

        if (targetUser != null) {
            Message message = new Message(fromEmail, toEmail, content);
            messagesToUsers.computeIfAbsent(toEmail, k -> new ArrayList<>()).add(message);
            targetUser.addMessage(message);
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
    public boolean confirmLogin(int verificationCode){
        return mail.verificationCode == verificationCode;
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
        User newUser = new User(email, password, cUser);
        users.add(newUser);
    }

    public void deleteUser(String email) {
        Object entity = getEntityByEmail(email);

        if (entity instanceof User) {
            // Delete from Users
            users.removeIf(user -> user.getEmail().equals(email));
        } else if (entity instanceof StoreOwner) {
            // Delete from StoreOwners
            storeOwners.removeIf(storeOwner -> storeOwner.getEmail().equals(email));
        } else if (entity instanceof Provider) {
            // Delete from Providers
            providers.removeIf(provider -> provider.getEmail().equals(email));
        }
    }
    public void addStoreOwner(String email, String password, String city) {
        StoreOwner newSo = new StoreOwner(email, password, city);
        storeOwners.add(newSo);
    }

    public void updateUserEmail(String oldEmail, String newEmail, String role) {
        switch (role.toLowerCase()) {
            case "user":
                updateUserEmailInList(users, oldEmail, newEmail);
                break;
            case "storeowner":
                updateStoreOwnerEmailInList(storeOwners, oldEmail, newEmail);
                break;
            case "provider":
                updateProviderEmailInList(providers, oldEmail, newEmail);
                break;
            default:

                break;
        }
    }

    private void updateUserEmailInList(List<User> list, String oldEmail, String newEmail) {
        for (User user : list) {
            if (user.getEmail().equals(oldEmail)) {
                user.setEmail(newEmail);

                break;
            }
            for(User updateUser : users) {
                if (updateUser.getEmail().equals(oldEmail)) {
                    updateUser.setEmail(newEmail);
                }
            }
        }
    }

    private void updateStoreOwnerEmailInList(List<StoreOwner> list, String oldEmail, String newEmail) {
        for (StoreOwner storeOwner : list) {
            if (storeOwner.getEmail().equals(oldEmail)) {
                storeOwner.setEmail(newEmail);
                break;
            }
            for(StoreOwner updateStoreOwner : storeOwners) {
                if (updateStoreOwner.getEmail().equals(oldEmail)) {
                    updateStoreOwner.setEmail(newEmail);
                }
            }
        }
    }

    private void updateProviderEmailInList(List<Provider> list, String oldEmail, String newEmail) {
        for (Provider provider : list) {
            if (provider.getEmail().equals(oldEmail)) {
                provider.setEmail(newEmail);
                break;
            }
            for(Provider updateProvider : providers) {
                if (updateProvider.getEmail().equals(oldEmail)) {
                    updateProvider.setEmail(newEmail);
                }
            }
        }
    }



    public void updateUserPassword(String email, String newPassword, String role) {
        switch (role.toLowerCase()) {
            case "user":
                updatePasswordForUser(email, newPassword);
                break;
            case "storeowner":
                updatePasswordForStoreOwner(email, newPassword);
                break;
            case "provider":
                updatePasswordForProvider(email, newPassword);
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    private void updatePasswordForUser(String email, String newPassword) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setPassword(newPassword);
                break;
            }
        }
    }

    private void updatePasswordForStoreOwner(String email, String newPassword) {
        for (StoreOwner storeOwner : storeOwners) {
            if (storeOwner.getEmail().equals(email)) {
                storeOwner.setPassword(newPassword);
                break;
            }
        }
    }

    private void updatePasswordForProvider(String email, String newPassword) {
        for (Provider provider : providers) {
            if (provider.getEmail().equals(email)) {
                provider.setPassword(newPassword);
                break;
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
                .toList();
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
            Recipe recipesAdd = searchRecipes(recipeName.trim()).get(0);
            if (recipesAdd !=null) {
                recipeFeedback.add(feedbackContent);
                recipesAdd.addFeedback(user, feedbackContent);

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

        return findStoreOwnerByEmail(email);// No matching entity found
    }





    public double getUserTotalBasketPrice(String userEmail, String userPassword) {
        User currentUser = getCurrentUser(userEmail, userPassword); // Renamed local variable

        if (currentUser != null) {
            return currentUser.getTotalBasketPrice();
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
