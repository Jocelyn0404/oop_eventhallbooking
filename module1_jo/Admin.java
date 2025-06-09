package module1_jo;

public class Admin extends User {
    private int adminLevel;

    public Admin(String userId, String name, String email, String password, int adminLevel) {
        super(userId, name, email, password);
        this.adminLevel = adminLevel;
    }

    public void approveBooking(String bookingId) {
        System.out.println("Booking " + bookingId + " has been approved by Admin " + name + ".");
    }

    public void manageHall() {
        System.out.println("Admin " + name + " is managing halls.");
    }

    public void viewReports() {
        System.out.println("Admin " + name + " is viewing reports.");
    }

    public int getAdminLevel() {
        return adminLevel;
    }
}
