// === 4. Booking ===
class Booking {
    private int bookingId;
    private int userId;
    private int hallId;
    private String date;
    private String startTime;
    private String endTime;
    private String status;
    private Schedule schedule;

    public Booking(int bookingId, int userId, int hallId, String date, String startTime, String endTime, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.hallId = hallId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public int getHallId() {
        return hallId;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Booking ID: " + bookingId +
               ", Hall: " + hallId +
               ", Date: " + date +
               ", Time: " + startTime + "-" + endTime +
               ", Status: " + status;
    }
}
