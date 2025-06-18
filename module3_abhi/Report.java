public class Report {
    public void displayBookingSummary(List<Booking> bookings) {
        int totalBookings = bookings.size();
        int approvedBookings = 0;
        double totalRevenue = 0;

        for (Booking b : bookings) {
            if (b.getStatus().equalsIgnoreCase("Approved")) {
                approvedBookings++;

                Hall hall = getHallById(b.getHallId());
                if (hall != null) {
                    int startHour = Integer.parseInt(b.getStartTime().split(":")[0]);
                    int endHour = Integer.parseInt(b.getEndTime().split(":")[0]);
                    int duration = endHour - startHour;
                    if (duration > 0) {
                        totalRevenue += hall.getPricePerHour() * duration;
                    }
                }
            }
        }

        System.out.println("\n====== Booking Report ======");
        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Approved Bookings: " + approvedBookings);
        System.out.println("Estimated Revenue: RM" + Math.round(totalRevenue * 100.0) / 100.0);
        System.out.println("=============================");
    }

    private Hall getHallById(int hallId) {
        for (Hall h : EventHallBookingSystem.halls) {
            if (h.getHallId() == hallId) {
                return h;
            }
        }
        return null;
    }
}
