package module1_jo;

public class Feedback {
    private String feedbackId;
    private String userId;
    private String message;
    private int rating;

    public Feedback(String feedbackId, String userId, String message, int rating) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.message = message;
        this.rating = rating;
    }

    public void submitFeedback() {
        System.out.println("Feedback submitted by user " + userId + ": " + message + " (Rating: " + rating + ")");
    }

    public void viewFeedback() {
        System.out.println("Feedback ID: " + feedbackId + "\nMessage: " + message + "\nRating: " + rating);
    }
}
