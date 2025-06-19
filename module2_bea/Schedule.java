// === 7. Schedule ===
class Schedule {
    private int scheduleId;
    private int hallId;
    private String bookingDate;
    private String timeSlot;
    private static List<String> bookedSlots = new ArrayList<>();

    public Schedule(int scheduleId, int hallId, String bookingDate, String timeSlot) {
        this.scheduleId = scheduleId;
        this.hallId = hallId;
        this.bookingDate = bookingDate;
        this.timeSlot = timeSlot;
    }

    public void addSlot() {
        String slotKey = hallId + "-" + bookingDate + "-" + timeSlot;
        if (!bookedSlots.contains(slotKey)) {
            bookedSlots.add(slotKey);
            System.out.println("Slot added: " + slotKey);
        } else {
            System.out.println("Slot already booked: " + slotKey);
        }
    }

    public void removeSlot() {
        String slotKey = hallId + "-" + bookingDate + "-" + timeSlot;
        if (bookedSlots.contains(slotKey)) {
            bookedSlots.remove(slotKey);
            System.out.println("Slot removed: " + slotKey);
        } else {
            System.out.println("Slot not found: " + slotKey);
        }
    }

    public boolean isSlotAvailable() {
        String slotKey = hallId + "-" + bookingDate + "-" + timeSlot;
        return !bookedSlots.contains(slotKey);
    }
}

