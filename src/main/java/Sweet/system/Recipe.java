package Sweet.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recipe {
    private final String name;
    private final String content;
    private final String ownerEmail;
    private List<String> feedbacks;  // List to store feedback
    private List<User> usersProvidedFeedback = new ArrayList<>();

    public Recipe(String name, String content, String ownerEmail) {
        this.name = name;
        this.content = content;
        Date datePosted = new Date();
        this.ownerEmail = ownerEmail;
        feedbacks = new ArrayList<String>();
    }


    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    /*public Date getDatePosted() {
        return datePosted;
    }
*/
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void addFeedback(User user, String feedbackContent) {
        if (feedbacks == null) {
            feedbacks = new ArrayList<>();
        }
        feedbacks.add(feedbackContent);
        usersProvidedFeedback.add(user);
    }
    public List<User> getUsersProvidedFeedback() {
        return new ArrayList<>(usersProvidedFeedback);
    }
    public List<String> getFeedbacks() {
        return new ArrayList<>(feedbacks);  // Return a copy of the feedback list
    }
}
