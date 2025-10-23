import java.util.*;

class Train {
    int trainNo;
    String trainName;
    int seatsAvailable;
    double ticketPrice;

    Train(int trainNo, String trainName, int seatsAvailable, double ticketPrice) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.seatsAvailable = seatsAvailable;
        this.ticketPrice = ticketPrice;
    }

    public void display() {
        System.out.printf("%d - %s | Seats: %d | Price: ₹%.2f%n",
                trainNo, trainName, seatsAvailable, ticketPrice);
    }
}

class Booking {
    String passengerName;
    int trainNo;
    double ticketPrice;

    Booking(String passengerName, int trainNo, double ticketPrice) {
        this.passengerName = passengerName;
        this.trainNo = trainNo;
        this.ticketPrice = ticketPrice;
    }

    public void display() {
        System.out.printf("Passenger: %s | Train No: %d | Paid: ₹%.2f%n",
                passengerName, trainNo, ticketPrice);
    }
}

public class MainSystem {
    static HashMap<Integer, Train> trains = new HashMap<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        trains.put(101, new Train(101, "Chennai Express", 50, 450.0));
        trains.put(202, new Train(202, "Delhi SuperFast", 60, 600.0));
        trains.put(303, new Train(303, "Bangalore Mail", 40, 350.0));

        while (true) {
            System.out.println("\n=== SMART RAILWAY RESERVATION SYSTEM ===");
            System.out.println("1. View Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> viewTrains();
                case 2 -> bookTicket();
                case 3 -> cancelTicket();
                case 4 -> viewBookings();
                case 5 -> {
                    System.out.println("Thank you for using Smart Railway System!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    static void viewTrains() {
        System.out.println("\nAvailable Trains:");
        for (Train t : trains.values()) {
            t.display();
        }
    }

    static void bookTicket() {
        System.out.print("Enter passenger name: ");
        String name = sc.nextLine();
        System.out.print("Enter Train Number: ");
        int no = readInt();

        Train t = trains.get(no);
        if (t == null) {
            System.out.println(" Train not found!");
            return;
        }

        if (t.seatsAvailable > 0) {
            t.seatsAvailable--;
            Booking b = new Booking(name, no, t.ticketPrice);
            bookings.add(b);
            System.out.println(" Ticket booked successfully for " + t.trainName);
        } else {
            System.out.println(" No seats available!");
        }
    }

    static void cancelTicket() {
        System.out.print("Enter passenger name to cancel booking: ");
        String name = sc.nextLine();
        boolean found = false;

        Iterator<Booking> it = bookings.iterator();
        while (it.hasNext()) {
            Booking b = it.next();
            if (b.passengerName.equalsIgnoreCase(name)) {
                Train t = trains.get(b.trainNo);
                if (t != null) t.seatsAvailable++;
                it.remove();
                found = true;
                System.out.println("Booking cancelled successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println(" No booking found for that name.");
        }
    }

    static void viewBookings() {
        System.out.println("\n=== All Current Bookings ===");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking b : bookings) {
                b.display();
            }
        }
    }
}
