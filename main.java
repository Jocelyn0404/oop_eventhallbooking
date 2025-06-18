public class EventHallBookingSystem {
    static Scanner scanner = new Scanner(System.in);
    static User currentUser = null;
    static Admin adminUser = new Admin(0, "AdminUser", "admin@example.com", "admin123", 1);
    static List<Hall> halls = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static List<Payment> payments = new ArrayList<>();
    static List<Notification> notifications = new ArrayList<>();
    static List<Feedback> feedbackList = new ArrayList<>();
    static Authentication auth = new Authentication();
    static Report report = new Report();

    public static void main(String[] args) {
        initData();
        int mainChoice;
        do {
            System.out.println("\n====== MAIN MENU ======");
            System.out.println("1. Customer Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
           try {
                mainChoice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                mainChoice = -1;
            }

            switch (mainChoice) {
                case 1: customerLogin(); break;
                case 2: adminLogin(); break;
                case 3: System.out.println("Exiting... Goodbye!"); break;
                default: System.out.println("Invalid option.");
            }
        } while (mainChoice != 3);
    }

    static void initData() {
        halls.add(new Hall(1, "Grand Ballroom", "Downtown", 500, 1000));
        halls.add(new Hall(2, "Conference Room", "City Center", 100, 300));
    }

    static void customerLogin() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        if (auth.login(name, email, password)) {
            currentUser = new User(1, name, email, password);
            System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
            customerMenu();
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }
        static void customerMenu() {
        int choice;
        do {
            System.out.println("\n====== CUSTOMER MENU ======");
            System.out.println("1. View Available Halls");
            System.out.println("2. Book a Hall");
            System.out.println("3. Make Payment");
            System.out.println("4. Submit feedback");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                choice = -1;
            }
            switch (choice) {
                case 1: viewHalls(); break;
                case 2: bookingMenu(); break;
                case 3: paymentMenu(); break;
                case 4: feedbackMebu();break;
                case 5: currentUser.logout(); currentUser = null; break;
                default: System.out.println("Invalid option.");
            }
        } while (choice != 5);
    }

    static void adminLogin() {
        System.out.print("Enter Admin Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();
        
        if (adminUser.getEmail().equals(email) && adminUser.getPassword().equals(password)) {
            System.out.println("Admin login successful. Welcome Admin!");
            
            int choice;
            do {
                System.out.println("\n====== ADMIN MENU ======");
                System.out.println("1. View All Bookings");
                System.out.println("2. Approve/Reject Booking");
                System.out.println("3. view Feedback");
                System.out.println("4. view Report");
                System.out.println("5. Logout");
                System.out.print("Choose an option: ");
                
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Please enter a number: ");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1: viewAllBookings(); break;
                    case 2: approveOrRejectBooking(); break;
                    case 3: viewFeedbacks(); break;
                    case 4: report.displayBookingSummary(bookings);break;
                    case 5: System.out.println("Logged out from Admin."); break;
                    default: System.out.println("Invalid option.Please choose between 1 and 5.");
                }
            } while (choice != 5);
        } else {
            System.out.println("Admin login failed.Invalid admin credentials.");
        }
    }

    static void viewHalls() {
        System.out.println("\nList of Available Halls:");
        for (Hall h : halls) System.out.println(" - " + h);
    }

    static void bookingMenu() {
        System.out.print("Enter Hall ID to book: ");
        int hallId = scanner.nextInt(); 
        scanner.nextLine();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.print("Enter Start Time (HH:mm): ");
        String start = scanner.nextLine();
        System.out.print("Enter End Time (HH:mm): ");
        String end = scanner.nextLine();
        
        for (Booking b : bookings) {
            if (b.getHallId() == hallId && b.getDate().equals(date) && timeOverlaps(b.getStartTime(), b.getEndTime(), start, end)) {
                System.out.println("The hall is already booked during this time.");
                return;
            }
        }
        Booking booking = new Booking(bookings.size() + 1, currentUser.userId, hallId, date, start, end, "Pending");
        bookings.add(booking);
        System.out.println("Booking created successfully! " + booking);
    }

    static boolean timeOverlaps(String start1, String end1, String start2, String end2) {
    int s1 = Integer.parseInt(start1.replace(":", ""));
    int e1 = Integer.parseInt(end1.replace(":", ""));
    int s2 = Integer.parseInt(start2.replace(":", ""));
    int e2 = Integer.parseInt(end2.replace(":", ""));
    return s1 < e2 && s2 < e1;
}

static void paymentMenu() {
    System.out.print("Enter Booking ID to pay: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    Booking b = null;
    for (Booking bk : bookings) {
        if (bk.getBookingId() == id) {
            b = bk;
            break;
        }
    }

    if (b == null) {
        System.out.println("Booking not found.");
        return;
    }

    if (!b.getStatus().equalsIgnoreCase("Approved")) {
        System.out.println("Booking is not approved yet. You can only make payment for approved bookings.");
        return;
    }

    Hall hall = null;
    for (Hall h : halls) {
        if (h.getHallId() == b.getHallId()) {
            hall = h;
            break;
        }
    }

    if (hall == null) {
        System.out.println("Hall data not found.");
        return;
    }

    int startHour = Integer.parseInt(b.getStartTime().split(":")[0]);
    int endHour = Integer.parseInt(b.getEndTime().split(":")[0]);
    int duration = endHour - startHour;

    if (duration <= 0) {
        System.out.println("Invalid time range.");
        return;
    }

    double total = hall.getPricePerHour() * duration;
    total = Math.round(total * 100.0) / 100.0; // round to 2 decimal places

    System.out.println("Total amount to pay: RM" + total);
    System.out.print("Enter payment method (Card/FPX): ");
    String method = scanner.nextLine();
    
    Payment payment = new Payment(payments.size() + 1, b.getBookingId(), total, new Date(), method);
    payments.add(payment);

    System.out.println("Payment successful!");
    System.out.println(payment);
}
    static void viewAllBookings() {
        for (Booking b : bookings) System.out.println(b);
    }

   static void approveOrRejectBooking() {
    System.out.print("Enter Booking ID: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    Booking selectedBooking = null;
    for (Booking b : bookings) {
        if (b.getBookingId() == id) {
            selectedBooking = b;
            break;
        }
    }

    if (selectedBooking == null) {
        System.out.println("Booking not found.");
        return;
    }

    System.out.print("Approve or Reject (A/R): ");
    String choice = scanner.nextLine().toUpperCase();

    if (choice.equals("A")) {
        boolean conflict = false;

        for (Booking other : bookings) {
            if (
                other.getBookingId() != selectedBooking.getBookingId() &&
                other.getHallId() == selectedBooking.getHallId() &&
                other.getDate().equals(selectedBooking.getDate()) &&
                other.getStatus().equalsIgnoreCase("Approved") &&
                timeOverlaps(
                    selectedBooking.getStartTime(), selectedBooking.getEndTime(),
                    other.getStartTime(), other.getEndTime()
                )
            ) {
                conflict = true;
                break;
            }
        }

        if (conflict) {
            System.out.println("Cannot approve. Booking conflicts with an already approved booking.");
        } else {
            selectedBooking.setStatus("Approved");
            System.out.println("Approved booking ID: " + selectedBooking.getBookingId());
        }

    } else if (choice.equals("R")) {
        selectedBooking.setStatus("Rejected");
        System.out.println("Booking rejected.");
    } else {
        System.out.println("Invalid choice.");
    }
}

}
static void feedbackMenu() {
        System.out.println("Enter your feedback message: ");
        String message = scanner.nextLine();
        System.out.println("Enter rating (1 to 5): ");
        int rating = 0;

        try {
            rating = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Rating set to 0.");
            scanner.nextLine();
        }

        Feedback fb = new Feedback(generateFeedbackId(), currentUser.getUserId(), message, rating);
        feedbackList.add(fb);
        fb.submitFeedback();
    }

    static int generateFeedbackId() {
        return feedbackList.size() + 1;
    }

    static void viewFeedbacks() {
        if (feedbackList.isEmpty()) {
            System.out.println("No feedback submitted yet.");
        } else {
            System.out.println("\n====== All Feedback ======");
            for (Feedback fb : feedbackList) {
                System.out.println("User ID: " + fb.getUserId());
                System.out.println("Rating: " + fb.getRating());
                System.out.println("Message: " + fb.getMessage());
                System.out.println("---------------------------");
            }
        }
    }
}


