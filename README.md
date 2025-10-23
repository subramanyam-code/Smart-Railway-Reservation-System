***** Project Overview *****

The Smart Railway Reservation System is a simple Java-based console application that allows users to book, cancel, and view train tickets efficiently.
It simulates a real-world railway reservation process using Object-Oriented Programming (OOP) principles like classes, objects, and encapsulation.

This is the base version of the project — designed for clarity, simplicity, and easy understanding of core Java concepts such as ArrayList, HashMap, and user input handling via Scanner.

-> Features

1. View all available trains
2. Book a ticket for any available train
3. Cancel an existing ticket
4. View all current bookings
5. Simple console interface for user interaction
6. Uses clean and modular Java code

-> Algorithm

Step 1: Initialize train data

Predefine available trains with train number, name, seats, and ticket price.

Step 2: Show main menu options

Display choices like View Trains, Book Ticket, Cancel Ticket, View Bookings, Exit.

Step 3: Perform user-selected operation

View Trains: Display all trains with available seats and prices.

Book Ticket:

    Ask for passenger name and train number.

    Check seat availability → if available, book and reduce seat count.

Cancel Ticket:

    Ask for passenger name → find and remove booking → restore seat.

View Bookings: Display all passenger bookings.

Exit: End the program safely.

-> Technologies Used

Language: Java

Concepts: Object-Oriented Programming (OOP), Collections Framework

Tools:VS Code


-> How to Run

Open any Java IDE (or use terminal).

Create a file named MainSystem.java.


Compile and run:

javac MainSystem.java


Sample Output
=== SMART RAILWAY RESERVATION SYSTEM ===
1. View Trains
2. Book Ticket
3. Cancel Ticket
4. View Bookings
5. Exit
Enter your choice: 1

Available Trains:
101 - Chennai Express | Seats: 50 | Price: ₹450.00
202 - Delhi SuperFast | Seats: 60 | Price: ₹600.00
303 - Bangalore Mail  | Seats: 40 | Price: ₹350.00