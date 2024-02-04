
Cinema Data Manager - DSA Project
This DSA project serves as a Cinema Data Manager, providing a user-friendly interface for cinema representatives to efficiently handle customer requests. The system ensures the best customer experience by streamlining seat bookings and generating organized receipts.

Overview
As a cinema representative, you will follow a step-by-step process to assist customers in booking seats, generating receipts, and managing the overall cinema data.

1. Login Frame (Login class):
Purpose:
Authenticates users before accessing the main system.
Key Components:
Username and Password fields.
Login button with error handling for invalid input.
Functionality:
Validates user input.
Navigates to the Home Page frame upon successful login.
Efficiently handles key events for seamless navigation.
2. Home Page Frame (navigateToHomePage method in Login class):
Purpose:
Serves as the main interface for users after login.
Key Components:
Buttons for Total Seats, Book a Seat, Seats Left, and Sign Out.
Functionality:
Navigates to specific frames based on button clicks.
Displays a welcome message and options for various actions.
3. Seating Arrangement Frame (SeatingArrangementFrame class):
Purpose:
Displays the arrangement of seats in the theater.
Key Components:
Seat labels showing the arrangement.
Functionality:
Differentiates between VIP and regular seats.
Allows navigation back to the Home Page.
4. Seat Booking Frame (SeatBookingFrame class):
Purpose:
Allows users to book seats for a particular movie.
Key Components:
Input fields for customer name, movie selection, seat specification, and number of seats.
Submit button for booking with validation.
Functionality:
Checks seat availability and books seats.
Generates a receipt upon successful booking.
5. View Receipt Frame (ViewReceiptFrame class):
Purpose:
Displays and optionally saves the receipt for a seat booking.
Key Components:
Receipt information including customer name, movie, seat, seats to book, and total amount.
Functionality:
Handles the creation and display of the receipt.
Prompts the user to save the receipt to a file.
Implements a file search functionality.
6. Seats Left Frame (SeatsLeftFrame class):
Purpose:
Displays the availability of seats in the theater.
Key Components:
Labels representing individual seats.
Functionality:
Updates seat images based on availability.
Allows users to book available seats with a mouse click.
Handles VIP seat reservations and provides user feedback.
