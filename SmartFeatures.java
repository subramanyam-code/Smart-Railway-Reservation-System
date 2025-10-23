import java.io.*;
import java.util.*;

class Train {
    int trainNo;
    String trainName;
    int seatsAvailable;
    double basePrice;

    Train(int trainNo, String trainName, int seatsAvailable, double basePrice) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.seatsAvailable = seatsAvailable;
        this.basePrice = basePrice;
    }

    public double calculateDynamicPrice() {
        if (seatsAvailable > 40) return basePrice;
        else if (seatsAvailable > 20) return basePrice * 1.2;
        else return basePrice * 1.5;
    }

    public void display() {
        System.out.printf("%d - %s | Seats: %d | Price: ₹%.2f%n",
                trainNo, trainName, seatsAvailable, calculateDynamicPrice());
    }
}

class Booking {
    String passengerName;
    int trainNo;
    String pnr;
    double paidAmount;

    Booking(String passengerName, int trainNo, String pnr, double paidAmount) {
        this.passengerName = passengerName;
        this.trainNo = trainNo;
        this.pnr = pnr;
        this.paidAmount = paidAmount;
    }

    public void display() {
        System.out.printf("PNR: %s | Passenger: %s | Train No: %d | Paid: ₹%.2f%n",
                pnr, passengerName, trainNo, paidAmount);
    }
}

public class SmartFeatures {
    static HashMap<Integer, Train> trains = new HashMap<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        trains.put(101, new Train(101, "Chennai Express", 50, 450.0));
        trains.put(202, new Train(202, "Delhi SuperFast", 60, 600.0));
        trains.put(303, new Train(303, "Bangalore Mail", 40, 350.0));

        while (true) {
            System.out.println("\n=== SMART RAILWAY SYSTEM - ADVANCED FEATURES ===");
            System.out.println("1. View Trains");
            System.out.println("2. Book Ticket (Dynamic Pricing)");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. View All Bookings");
            System.out.println("5. Search Train");
            System.out.println("6. Predict Seat Availability");
            System.out.println("7. Export Bookings to File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> viewTrains();
                case 2 -> bookTicket();
                case 3 -> cancelTicket();
                case 4 -> viewBookings();
                case 5 -> searchTrain();
                case 6 -> predictAvailability();
                case 7 -> exportBookings();
                case 8 -> {
                    System.out.println("Thank you for using Smart Railway System!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter valid number: ");
            sc.next();
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    static void viewTrains() {
        for (Train t : trains.values()) t.display();
    }

    static void bookTicket() {
        System.out.print("Enter passenger name: ");
        String name = sc.nextLine();
        System.out.print("Enter Train Number: ");
        int no = readInt();

        Train t = trains.get(no);
        if (t == null) {
            System.out.println("❌ Train not found!");
            return;
        }

        if (t.seatsAvailable > 0) {
            double price = t.calculateDynamicPrice();
            t.seatsAvailable--;
            String pnr = "PNR" + (1000 + bookings.size() + 1);
            bookings.add(new Booking(name, no, pnr, price));
            System.out.println("✅ Ticket booked successfully! PNR: " + pnr);
        } else System.out.println("❌ No seats available!");
    }

    static void cancelTicket() {
        System.out.print("Enter PNR to cancel: ");
        String pnr = sc.nextLine();
        boolean found = false;
        Iterator<Booking> it = bookings.iterator();
        while (it.hasNext()) {
            Booking b = it.next();
            if (b.pnr.equalsIgnoreCase(pnr)) {
                Train t = trains.get(b.trainNo);
                if (t != null) t.seatsAvailable++;
                it.remove();
                System.out.println("✅ Booking cancelled successfully!");
                found = true;
                break;
            }
        }
        if (!found) System.out.println("❌ Invalid PNR!");
    }

    static void viewBookings() {
        if (bookings.isEmpty()) System.out.println("No bookings yet.");
        else bookings.forEach(Booking::display);
    }

    static void searchTrain() {
        System.out.print("Enter keyword to search train: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Train t : trains.values()) {
            if (t.trainName.toLowerCase().contains(keyword)) {
                t.display();
                found = true;
            }
        }
        if (!found) System.out.println("No train found with that keyword.");
    }

    static void predictAvailability() {
        System.out.println("🔮 Predicting seat availability trend...");
        for (Train t : trains.values()) {
            String trend = (t.seatsAvailable < 15)
                    ? "⚠️ High demand, seats running out!"
                    : (t.seatsAvailable < 35)
                    ? "📈 Moderate demand."
                    : "✅ Seats available comfortably.";
            System.out.printf("%s - %s%n", t.trainName, trend);
        }
    }

    static void exportBookings() {
        try (FileWriter fw = new FileWriter("bookings.txt")) {
            for (Booking b : bookings) {
                fw.write("PNR: " + b.pnr + " | Passenger: " + b.passengerName +
                         " | Train No: " + b.trainNo + " | Paid: ₹" + b.paidAmount + "\n");
            }
            System.out.println("📂 Bookings exported successfully to 'bookings.txt'");
        } catch (IOException e) {
            System.out.println("❌ Error exporting bookings!");
        }
    }
}

