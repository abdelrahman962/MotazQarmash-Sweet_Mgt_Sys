package Sweet.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recipe {
    private final String name;
    private final String content;
    private final Date datePosted;
    private final String ownerEmail;
    private List<String> feedbacks;  // List to store feedback

    public Recipe(String name, String content, String ownerEmail) {
        this.name = name;
        this.content = content;
        this.datePosted = new Date();
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

    public void addFeedback(String feedbackContent) {
        if (feedbackContent != null && !feedbackContent.trim().isEmpty()) {
            feedbacks.add(feedbackContent);  // Add feedback to the list
        }
    }

    public List<String> getFeedbacks() {
        return new ArrayList<>(feedbacks);  // Return a copy of the feedback list
    }
}
