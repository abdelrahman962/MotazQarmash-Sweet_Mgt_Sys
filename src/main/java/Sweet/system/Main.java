package Sweet.system;


import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static final int STORE_OWNER = 2;
    private static final int PROVIDER = 3;
    private static final String INVALID_CHOICE_MESSAGE = "Invalid choice! Please try again.";
    static User currentUser;
    public static final int NOT_VALID = 0;
    static Scanner scanner = new Scanner(System.in);
    static Login login = new Login();

    public static final int USER_TYPE = 1;
    private static int whichType = 0;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static StoreOwner currentStoreOwner;
    private static Provider provider;
    private static Provider currentProvider;

    public static void main(String[] args) {
        login.initializeUsers();
        manageUserRegistration();
    }

    public static void manageUserRegistration() {
        login.setLogInStatus(false);
        int choice;
        while (true) {
            LOGGER.info("""
                    Enter your choice:
                    1. Sign Up
                    2. Login
                    3. Exit""");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1 -> signUp();
                case 2 -> signIn();
                case 3 -> {
                    System.exit(0);
                    return;
                }
                default -> LOGGER.info("Invalid choice! Please try again.");
            }

            if (login.isLoggedIn()) {
                determineScreenAfterLogin();            }
        }
    }
    private static void determineScreenAfterLogin(){
        switch (whichType) {
            case USER_TYPE:
                switch (currentUser.getType()) {
                    case 0:
                        adminScreen();
                        break;
                    case USER_TYPE:
                        userScreen();
                        break;
                    default:
                        manageUserRegistration();
                        break;
                }
                break;
            case STORE_OWNER:
                storeOwnerScreen();
                break;
            case PROVIDER:
                providerScreen();
                break;
            default:
                manageUserRegistration();
                break;
        }
    }

    private static void providerScreen() {
        LOGGER.info("Provider Screen");
        LOGGER.info("""
                Please enter your choice:
               
                3. Logout
                4. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {

            case 3 -> manageUserRegistration();
            case 4 -> {
                System.exit(0);
                return;
            }
            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
        providerScreen();
    }

    private static void storeOwnerScreen() {
        LOGGER.info("Store Owner Screen");
        LOGGER.info("""
                Please enter your choice:
                1. Add products
                2. Remove products
                3. Update products
                4. View products
                5. Communicate with users
                6. Receive feedback
                7. Monitor sales and profits.
                8. Identify best-selling products in each store.
                9. Implement dynamic discount features.
                10. Account Management
                11. Order Management
                12. Logout
                13. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1-> addProducts();
            case 2-> deleteProduct();
            case 3-> updateProduct();
            case 4-> viewProducts();
            case 5 -> communicateWithUsers();
            case 6-> recieveFeedbacks();
            case 7-> monitorSalesAndProfits();
            case 8 -> identifyBestSellingProducts();
            case 9->implementDynamicDiscountFeatures();
            case 10-> updateAccount();
            case 11 -> {
                LOGGER.info("Order Management:");

                // Call the Order_Management method to get the list of users
                List<User> usersWithOrders = currentStoreOwner.Order_Management(currentStoreOwner.getEmail());

                // Check if any users were found
                if (usersWithOrders.isEmpty()) {
                    LOGGER.info("No users have ordered products from this store.");
                } else {
                    // Iterate over the list and display relevant information
                    LOGGER.info("Users who have ordered products from this store:");
                    for (User user : usersWithOrders) {
                        StringBuilder basketItems = new StringBuilder();

                        user.getBasket().forEach(product ->
                                basketItems.append("- ").append(product.getName())
                                        .append(" (Quantity: ").append(product.getProductQuantity()).append("), ")
                        );

                        // Remove the trailing comma and space
                        if (basketItems.length() > 0) {
                            basketItems.setLength(basketItems.length() - 2);
                        }

                        LOGGER.info("User Email: " + user.getEmail() +
                                " | Total Price: " + user.total_price(currentStoreOwner) +
                                " | Basket items: " + basketItems);
                    }
                }
            }


            case 12 -> manageUserRegistration();
            case 13 -> {
                System.exit(0);
                return;
            }
            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
        storeOwnerScreen();
    }

    private static void implementDynamicDiscountFeatures() {
        LOGGER.info("Implementing dynamic discount features.");

        // Prompt for the product name
        LOGGER.info("Product name: ");
        String productName = scanner.nextLine();

        // Find the product by name in the current store owner's inventory
        Product foundProduct = currentStoreOwner.findProductByName(productName);

        // Check if the product exists
        if (foundProduct != null) {
            // Prompt for the discount percentage
            LOGGER.info("Enter discount percentage: ");
            double discountPercentage = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline

            // Check if the discount percentage is valid
            if (discountPercentage >= 0) {
                // Apply the discount to the product
                foundProduct.applyDiscount(discountPercentage);
                LOGGER.info("Discount of " + discountPercentage + "% has been applied to the product: " + productName);
            } else {
                LOGGER.info("Invalid discount percentage. It must be a non-negative value.");
            }
        } else {
            LOGGER.info("Product not found: " + productName);
        }
    }


    private static void identifyBestSellingProducts() {
        LOGGER.info("Identify Best Selling Product: ");
        List<Product> best=currentStoreOwner.getBestSellingProducts();
        for (Product p : best) {
            LOGGER.info("Product name: " + p.getName()+"\n"+"Product quantity: "
                    +p.getProductQuantity()+"\n"+"Product price: "+p.getPrice());
        }
    }

    private static void monitorSalesAndProfits() {
        LOGGER.info("Monitor Sales and Profits");
        List <Product>products=currentStoreOwner.getProducts();
        double getTotalSale=0;

        for (Product p : products) {
            double sale = p.getPrice()*p.getProductQuantity();
            getTotalSale+=sale;
            LOGGER.info("Product name: " + p.getName()+"\n"+"Product quantity: "
                    +p.getProductQuantity()+"\n"+"Product price: "+p.getPrice()+"\n"+"Total Sale: "+sale+"\n");

        }

        LOGGER.info("Total profit: "+getTotalSale);
    }

    private static void viewProducts() {
        LOGGER.info("View Products");
        List<Product> products = currentStoreOwner.getProducts();
        for (Product product : products) {
            LOGGER.info("Product name: " + product.getName()+"\n"+"Product content: " + product.getDescription()+"\n"+"Product Price: "+product.getPrice()+"\n"+"Product dietary need: "+product.getDietaryNeeds());

        }
    }

    private static void updateProduct() {
        LOGGER.info("Update Product");
        LOGGER.info("Product name: ");
        String name = scanner.nextLine();

        Product productToUpdate = login.searchProducts(name).getFirst();

        if (productToUpdate != null) {
            if (currentStoreOwner.getProducts().contains(productToUpdate)) {
                LOGGER.info("Product found: " + productToUpdate.getName());

                while (true) {
                    LOGGER.info("""
                    Choose the attribute to update:
                    1. Product Name
                    2. Product Price
                    3. Product Description
                    4. Product Dietary Need
                    5. Finish updating""");

                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline

                    switch (choice) {
                        case 1:
                            LOGGER.info("Enter new product name: ");
                            String newName = scanner.nextLine();
                            productToUpdate.setName(newName);
                            LOGGER.info("Product name updated successfully.");
                            break;
                        case 2:
                            LOGGER.info("Enter new product price: ");
                            double newPrice = scanner.nextDouble();
                            scanner.nextLine();  // Consume the newline
                            productToUpdate.setPrice(newPrice);
                            LOGGER.info("Product price updated successfully.");
                            break;
                        case 3:
                            LOGGER.info("Enter new product description: ");
                            String newDescription = scanner.nextLine();
                            productToUpdate.setDescription(newDescription);
                            LOGGER.info("Product description updated successfully.");
                            break;
                        case 4:
                            LOGGER.info("Finished updating product.");
                            return;  // Exit the method after updating

                        default:
                            LOGGER.info("Invalid choice. Please try again.");
                            break;
                    }

                    LOGGER.info("Do you want to update another attribute? (yes/no): ");
                    String continueChoice = scanner.nextLine();

                    if (!continueChoice.equalsIgnoreCase("yes")) {
                        LOGGER.info("Finished updating product.");
                        break;
                    }
                }
            } else {
                LOGGER.info("Product does not belong to the current store owner.");
            }
        } else {
            LOGGER.info("Product not found: " + name);
        }
    }


    private static void deleteProduct() {
        LOGGER.info("Delete Product");
        LOGGER.info("Product name: ");
        String name = scanner.nextLine();

        // Search for the product by name
        Product productToDelete = login.searchProducts(name).getFirst();

        if (productToDelete != null) {
            // Check if the product belongs to the current store owner
            if (currentStoreOwner.getProducts().contains(productToDelete)) {
                currentStoreOwner.getProducts().remove(productToDelete); // Remove from the store owner's product list
                login.getAllProducts().remove(productToDelete); // Remove from the global product list
                LOGGER.info("Product deleted successfully: " + name);
            } else {
                LOGGER.info("Product does not belong to the current store owner.");
            }
        } else {
            LOGGER.info("Product not found: " + name);
        }
    }


    private static void recieveFeedbacks() {
    }

    private static void communicateWithUsers() {
        LOGGER.info("Communicate with Users");
        List<Message> receivedMessages =login.getMessagesForStoreOwner(currentStoreOwner.getEmail());
        if (receivedMessages.isEmpty()) {
            LOGGER.info("No messages found.");
            return;
        }

        LOGGER.info("Received Messages:");
        for (int i = 0; i < receivedMessages.size(); i++) {
            Message message = receivedMessages.get(i);
            LOGGER.info("Message " + (i + 1) + ": From " + message.getSenderEmail() + " - " + message.getContent());
        }

        // Allow the store owner to choose a message to respond to
        LOGGER.info("Enter the number of the message you want to respond to (or 0 to exit):");
        int messageNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (messageNumber < 1 || messageNumber > receivedMessages.size()) {
            LOGGER.info("Invalid choice. Exiting.");
            return;
        }

        Message selectedMessage = receivedMessages.get(messageNumber - 1);

        // Get response content from the store owner
        LOGGER.info("Enter your response:");
        String responseContent = scanner.nextLine();

        // Respond to the selected message
        currentStoreOwner.respondToMessage(login, selectedMessage.getSenderEmail(), responseContent);

        LOGGER.info("Response sent successfully.");


    }

    private static void addProducts() {

        LOGGER.info("Add products");
        LOGGER.info("Product name to add: ");
        String productName = scanner.nextLine();
        LOGGER.info("Product description: ");
        String productDescription = scanner.nextLine();
        LOGGER.info("Product price: ");
        double productPrice = scanner.nextDouble();
        LOGGER.info("Dietary need: ");
        String dietaryNeed = scanner.nextLine();
        login.addProduct(currentStoreOwner.getEmail(),currentStoreOwner.getPassword(),currentStoreOwner.getCity(),productName,productPrice,productDescription,dietaryNeed);
    }

    private static void userScreen() {
        LOGGER.info("User Screen");
        LOGGER.info("""
                Please enter your choice:
                1. Post and share personal dessert recipes.
                2. Search for dessert recipes
                3. Filter recipes based on dietary needs or food allergies.
                4. View All recipes
                5. Purchase Products
                6. Communication and Feedback
                7. Logout
                8. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> postAndShareRecipe() ;
            case 2 -> searchUserRecipes();
            case 3->filterRecipesBasedOnDietaryNeed();
            case 4-> viewRecipes();
            case 5->purchaseProducts();
            case 6->communicationAndFeedback();
            case 7->manageUserRegistration();
            case 8 -> {
                System.exit(0);
                return;
            }
            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
        userScreen();
    }

    private static void communicationAndFeedback() {
        LOGGER.info("Communication and Feedback");
        LOGGER.info("""
            Please enter your choice:
            1. User Communicate with store owner
            2. User provides feedback for a store owner's product
            3. User provides feedback for a user's recipe
            4. User can see feedbacks on a chosen product
            5. User can see feedbacks on a chosen recipe
            6. User receives messages from store owner
            7. Return to user screen
            """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Product p;
        Recipe r;
        switch (choice) {
            case 1 -> {
                LOGGER.info("Store owner email: ");
                String storeOwnerEmail = scanner.nextLine();
                LOGGER.info("Message content: ");
                String messageFromUser = scanner.nextLine();
                LOGGER.info("Sending message to store owner...");
                login.sendMessageToStoreOwner(currentUser.getEmail(), storeOwnerEmail, messageFromUser);
            }
            case 2 -> {
                LOGGER.info("Provide Feedback for product");
                LOGGER.info("Store owner email: ");
                String storeOwnerEmail = scanner.nextLine();
                LOGGER.info("Product Name: ");
                String productName = scanner.nextLine();
                LOGGER.info("Feedback content: ");
                String feedbackContent = scanner.nextLine();
                LOGGER.info("Providing feedback on product...");
                p=login.searchProducts(productName).getFirst();
                p.addFeedback(currentUser,feedbackContent);
                login.addFeedbackToProduct(currentUser.getEmail(), storeOwnerEmail, productName, feedbackContent);
            }
            case 3 -> {
                LOGGER.info("Provide Feedback for recipe");
                LOGGER.info("Recipe Name: ");
                String recipeName = scanner.nextLine();
                LOGGER.info("Feedback content: ");
                String feedbackContent = scanner.nextLine();
                LOGGER.info("Providing feedback on recipe...");
                r=login.searchRecipes(recipeName).getFirst();
                r.addFeedback(currentUser,feedbackContent);
                login.addFeedbackToRecipe(currentUser.getEmail(), currentUser.getPassword(), recipeName, feedbackContent);
            }
            case 4 -> {
                LOGGER.info("View feedback on a product");
                LOGGER.info("Product Name: ");
                String productName = scanner.nextLine();

                List<Product> products = login.searchProducts(productName);
                Map<User, List<String>> feedbackMap = new HashMap<>();

                for (Product product : products) {
                    if (product.getName().equalsIgnoreCase(productName)) {
                        feedbackMap.putAll(product.getUserProvidedFeedback()); // Correct method to get user feedbacks
                    }
                }

                if (feedbackMap.isEmpty()) {
                    LOGGER.info("No feedback found for product: " + productName);
                } else {
                    LOGGER.info("Feedback for product: " + productName);
                    for (Map.Entry<User, List<String>> entry : feedbackMap.entrySet()) {
                        User user = entry.getKey();
                        List<String> feedbacks = entry.getValue();
                        String userEmail = user != null ? user.getEmail() : "Unknown User";
                        for (String feedback : feedbacks) {
                            LOGGER.info("User: " + userEmail + " Feedback: " + feedback);
                        }
                    }
                }
            }

            case 5 -> {
                LOGGER.info("View feedback on a recipe");
                LOGGER.info("Recipe Name: ");
                String recipeName = scanner.nextLine();

                List<Recipe> recipes = login.searchRecipes(recipeName);
                if (recipes.isEmpty()) {
                    LOGGER.info("No recipe found with the name: " + recipeName);
                    break;
                }

                // Retrieve feedbacks via login method
                List<String> f = login.getRecipeFeedback();

                List<String> feedbacks = new ArrayList<>();
                List<String> providedFeedbackUserEmail = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    if (recipe.getName().equalsIgnoreCase(recipeName)) {
                        feedbacks = recipe.getFeedbacks();  // Fetch feedbacks for the recipe

                        // Combine feedbacks from the recipe with the feedbacks retrieved via login.getRecipeFeedback()
                        feedbacks.addAll(f);

                        List<User> feedbackUsers = recipe.getUsersProvidedFeedback();  // Fetch list of users who provided feedback

                        // Populate the list of user emails
                        for (User user : feedbackUsers) {
                            providedFeedbackUserEmail.add(user != null ? user.getEmail() : "Unknown User");
                        }

                        // If login.getRecipeFeedback() returns associated emails, add them here
                        for (int i = 0; i < f.size(); i++) {
                            providedFeedbackUserEmail.add(currentUser.getEmail());  // Example placeholder, replace with actual logic if needed
                        }
                    }
                }

                if (feedbacks.isEmpty()) {
                    LOGGER.info("No feedback found for recipe: " + recipeName);
                } else {
                    LOGGER.info("Feedback for recipe: " + recipeName);
                    for (int i = 0; i < feedbacks.size(); i++) {
                        String userEmail = i < providedFeedbackUserEmail.size() ? providedFeedbackUserEmail.get(i) : "Unknown User";
                        LOGGER.info("User: " + userEmail + " Feedback: " + feedbacks.get(i));
                    }


                }
            }
            case 6->{
                LOGGER.info("User receives messages from store owner");
                List<Message> receivedMessages =login.getMessagesForUser(currentUser.getEmail());
                if (receivedMessages.isEmpty()) {
                    LOGGER.info("No messages found.");
                    return;
                }

                LOGGER.info("Received Messages:");
                for (int i = 0; i < receivedMessages.size(); i++) {
                    Message message = receivedMessages.get(i);
                    LOGGER.info("Message " + (i + 1) + ": From " + message.getSenderEmail() + " - " + message.getContent());
                }

                // Allow the store owner to choose a message to respond to
                LOGGER.info("Enter the number of the message you want to respond to (or 0 to exit):");
                int messageNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (messageNumber < 1 || messageNumber > receivedMessages.size()) {
                    LOGGER.info("Invalid choice. Exiting.");
                    return;
                }

                Message selectedMessage = receivedMessages.get(messageNumber - 1);

                // Get response content from the store owner
                LOGGER.info("Enter your response:");
                String responseContent = scanner.nextLine();

                // Respond to the selected message
                currentUser.respondToMessage(login, selectedMessage.getSenderEmail(), responseContent);

                LOGGER.info("Response sent successfully.");



            }
            case 7->userScreen();
            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);

        }
        communicationAndFeedback();
    }


    private static void filterRecipesBasedOnDietaryNeed() {

        LOGGER.info("Filter Recipes based on Dietary Need:");
        LOGGER.info("""
            Please enter your choice:
            1. Filter Recipes from specific Store
            2. Filter Recipes from all Stores
            """);

        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the newline left by nextInt()

        LOGGER.info("Dietary need or food allergies: ");
        String dietaryNeed = scanner.nextLine();

        List<Product> products = new ArrayList<>();

        switch (choice) {
            case 1 -> {
                LOGGER.info("Store Owner Email: ");
                String storeOwnerEmail = scanner.nextLine();
                products = login.searchProductsByDietaryNeed(dietaryNeed, storeOwnerEmail);
                LOGGER.info("Products from store owned by " + storeOwnerEmail + ":");
            }
            case 2 -> {
                List<Product> allProducts = login.getAllProducts();
                for (Product p : allProducts) {
                    if (p.getDescription().toLowerCase().contains(dietaryNeed.toLowerCase())) {
                        products.add(p);
                    }
                }

                LOGGER.info("Products from all stores:");
            }
            default -> {
                LOGGER.warning("Invalid choice. Please select either 1 or 2.");
                return;
            }
        }

        if (products.isEmpty()) {
            LOGGER.info("No products found matching the dietary need: " + dietaryNeed);
        } else {
            for (Product product : products) {
                LOGGER.info("Product name: " + product.getName() + "\nProduct description: " + product.getDescription());
            }
        }
    }


    private static void purchaseProducts() {
        LOGGER.info("Purchase Products");
        List<Product> products = login.getAllProducts();
        LOGGER.info("Store owner email: ");
        String storeOwnerEmail = scanner.nextLine(); // Get store owner email from the user

        double totalPrice = 0.0; // Variable to keep track of total price

        while (true) {
            LOGGER.info("Product name: ");
            String name = scanner.nextLine(); // Get product name from the user

            // Find the product in the list belonging to the specified store owner
            Product foundProduct = null;
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(name) && product.getStoreOwnerEmail().equalsIgnoreCase(storeOwnerEmail)) {
                    foundProduct = product;
                    break;
                }
            }

            // If the product is found, prompt for quantity
            if (foundProduct != null) {
                LOGGER.info("Product found: " + foundProduct.getName() + "\nProduct price: " + foundProduct.getPrice());

                LOGGER.info("Product quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                boolean productAdded = login.purchaseProduct(currentUser.getEmail(), currentUser.getPassword(), name, quantity, storeOwnerEmail);

                if (productAdded) {
                    double price = foundProduct.getPrice() * quantity; // Calculate price for this product
                    totalPrice += price; // Add to total price
                    LOGGER.info("Product added to basket: " + name + " - Quantity: " + quantity);
                } else {
                    LOGGER.info("Failed to add product: " + name);
                }
            } else {
                LOGGER.info("Product not found in the specified store: " + name);
            }

            LOGGER.info("Do you want to add another product? (yes/no): ");
            String continueChoice = scanner.nextLine();

            if (!continueChoice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        // Display the total price before exiting
        LOGGER.info("Total price of all products in basket: " + totalPrice);
    }




    private static void searchUserRecipes() {
        LOGGER.info("Search for dessert recipes");
        LOGGER.info("Recipe name: ");
        String name = scanner.nextLine();
        List<Recipe>recipes=login.searchRecipes(name);
        for(Recipe r: recipes){
            LOGGER.info("Recipe name: " + r.getName()+"\n"+"Recipe content: " + r.getContent()+"\n"+"User owner: "+r.getOwnerEmail());

        }

    }

    private static void viewRecipes() {


        LOGGER.info("View Recipes");
        List<Recipe> allRecipes=login.getAllRecipes();



        for(Recipe r:allRecipes){

            LOGGER.info("Recipe name: " + r.getName()+"\n"+"Recipe content: " + r.getContent()+"\n"+"User owner: "+r.getOwnerEmail());

        }
    }

    private static void postAndShareRecipe() {
        LOGGER.info("Post and Share Recipes");
        LOGGER.info("Recipe details");

        LOGGER.info("Recipe Name: ");
        String name = scanner.nextLine();
        LOGGER.info("Recipe Description: ");
        String description = scanner.nextLine();
        String userEmail=currentUser.getEmail();
        Recipe recipe = new Recipe(name, description, userEmail);
        currentUser.addPost(recipe);

        login.addRecipe(currentUser.getEmail(), currentUser.getPassword(),name, description);

    }

    private static void adminScreen() {
        LOGGER.info("Admin screen");
        LOGGER.info("""
                Please enter your choice:
                1. Manage User Accounts
                2. Delete Account
                3. Update Account
                4. Monitor profits and generate financial reports
                5. Identify best-selling products in each store.
                6. Gather and display statistics on registered users by City (Nablus/Jenin etc...)
                7. Logout
                8. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1-> manageUserAccounts();
            case 2 -> deleteAccount();
            case 3->updateAccount();

            case 7 -> manageUserRegistration();
            case 8 -> System.exit(0);
            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
    }

    private static void updateAccount() {
        LOGGER.info("Update Account");

        LOGGER.info("Enter the email of the account to update:");
        String email = scanner.nextLine();

        Object userType = login.getEntityByEmail(email);

        if (userType == null) {
            LOGGER.info("No account found with the provided email.");
            return;
        }
        LOGGER.info("""
                What would you like to update?
                1. Email
                2. Password""");


        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                LOGGER.info("Enter new email:");
                String newEmail = scanner.nextLine();
                if (userType instanceof User) {
                    ((User) userType).setEmail(newEmail);
                    LOGGER.info("User email updated successfully.");
                } else if (userType instanceof StoreOwner) {
                    ((StoreOwner) userType).setEmail(newEmail);
                    LOGGER.info("Store owner email updated successfully.");
                } else if (userType instanceof Provider) {
                    ((Provider) userType).setEmail(newEmail);
                    LOGGER.info("Provider email updated successfully.");
                }
                break;
            case "2":
                LOGGER.info("Enter new password:");
                String newPassword = scanner.nextLine();
                if (userType instanceof User) {
                    ((User) userType).setPassword(newPassword);
                    LOGGER.info("User password updated successfully.");
                } else if (userType instanceof StoreOwner) {
                    ((StoreOwner) userType).setPassword(newPassword);
                    LOGGER.info("Store owner password updated successfully.");
                } else if (userType instanceof Provider) {
                    ((Provider) userType).setPassword(newPassword);
                    LOGGER.info("Provider password updated successfully.");
                }
                break;
            default:
                LOGGER.info("Invalid choice. No changes made.");
                break;
        }
    }


    private static void manageUserAccounts() {
        LOGGER.info("Manage User Accounts");
        for(User u: login.users){
            LOGGER.info("User Email: " + u.getEmail()+", User Password: " + u.getPassword());
        }
        for(StoreOwner s: login.storeOwners){
            LOGGER.info("Store Owner Email: " + s.getEmail()+", Store Owner Password: " + s.getPassword());
        }

    }

    private static void deleteAccount() {
        String emailDelete;
        LOGGER.info("Enter your email address to delete account:");
        emailDelete = scanner.nextLine();
        LOGGER.info("Delete account: " + emailDelete);
        deleteUserAccounts(emailDelete);
        login.deleteUser(emailDelete);
        adminScreen();
    }
    public static void deleteUserAccounts(String email) {

    }



    private static void signUp() {
        LOGGER.info("Enter email you want to sign up with: ");
        String email = scanner.nextLine();
        if (login.emailExists(email)) {
            LOGGER.info("Email exists");
            signUp();
        }
        LOGGER.info("Create password: ");
        String password = scanner.nextLine();
        login.addUser(email, password);
    }

    private static void signIn() {
        whichType = NOT_VALID;
        LOGGER.info("Enter your email: ");
        String email = scanner.nextLine();
        LOGGER.info("Enter your password: ");
        String password = scanner.nextLine();
        whichType = login.getTypeNumber(email, password);
        switch (whichType) {
            case NOT_VALID:
                LOGGER.info("Your account doesn't exist. Please proceed to sign up.");
                manageUserRegistration();
                break;
            case USER_TYPE:
                currentUser = login.getCurrentUser(email, password);
                currentStoreOwner = null;
                currentProvider=null;

                login.setLogInStatus(true);
                break;
            case STORE_OWNER:
                currentStoreOwner = login.getStoreOwner(email, password);
                currentUser = null;
                currentProvider=null;

                login.setLogInStatus(true);
                break;
            case PROVIDER:
                currentProvider=login.getProvider(email,password);
                currentUser = null;
                currentStoreOwner=null;

                login.setLogInStatus(true);

            default:
                LOGGER.info("Error signing in!");
                manageUserRegistration();
                break;
        }
    }


}


