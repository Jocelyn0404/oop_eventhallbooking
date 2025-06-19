// === 6. Notification ===
class Notification {
    private int notificationId;
    private int userId;
    private String message;
    private Date date;

    public Notification(int notificationId, int userId, String message) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.date = new Date();
    }

    public void send() {
        System.out.println("Notification sent to user " + userId + ": " + message);
    }
}
