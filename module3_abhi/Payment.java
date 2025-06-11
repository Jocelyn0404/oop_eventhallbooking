public class Payment {
   private int paymentId;
    private int bookingId;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;

    public Payment(int paymentId, int bookingId, double amount, Date paymentDate, String paymentMethod) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public String toString() {
    return String.format("Payment ID: %d, Booking: %d, Amount: RM%.2f", paymentId, bookingId, amount);    } 
}
