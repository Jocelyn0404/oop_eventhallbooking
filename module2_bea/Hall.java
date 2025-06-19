// === 3. Hall ===
class Hall {
    private int hallId;
    private String name;
    private String location;
    private int capacity;
    public double pricePerHour;

    public Hall(int hallId, String name, String location, int capacity, double pricePerHour) {
        this.hallId = hallId;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.pricePerHour = pricePerHour;
    }

    public int getHallId() {
        return hallId;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public String toString() {
        return hallId + ": " + name + " - " + location + " (" + capacity + " people), RM" + pricePerHour + "/hour";
    }
}
