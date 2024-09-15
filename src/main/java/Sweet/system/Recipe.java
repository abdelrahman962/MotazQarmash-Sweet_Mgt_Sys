package Sweet.system;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe {
    private final String name;
    private final String content;
    private final String ownerEmail;
    private List<String> feedbacks;  // List to store feedback
    private final Map<User, List<String>> usersProvidedFeedback;

    public Recipe(String name, String content, String ownerEmail) {
        this.name = name;
        this.content = content;
this.usersProvidedFeedback = new HashMap<>();
        this.ownerEmail = ownerEmail;
        feedbacks = new ArrayList<String>();
    }


    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }


    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void addFeedback(User user, String feedbackContent) {
        usersProvidedFeedback.putIfAbsent( user,new ArrayList<>());
      usersProvidedFeedback.get(user).add(feedbackContent);
        feedbacks.add(feedbackContent);

    }
    public Map<User, List<String>> getUsersProvidedFeedback()
    {
        return usersProvidedFeedback;
    }

    public List<String> getFeedbacks() {
        return new ArrayList<>(feedbacks);  // Return a copy of the feedback list
    }
}
