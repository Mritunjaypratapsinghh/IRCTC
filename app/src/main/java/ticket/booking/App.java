package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.utils.PasswordUtil;

import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        User loggedInUser = null;

        try {
            userBookingService = new UserBookingService();
        } catch (IOException e) {
            System.out.println("Error initializing the system: " + e.getMessage());
            return;
        }

        while (option != 7) {
            System.out.println("Choose Option:");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Booking");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");

            option = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            switch (option) {
                case 1:
                    System.out.print("Enter your Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your password to Sign Up: ");
                    String password = scanner.nextLine();
                    User newUser = new User(name, password, PasswordUtil.hashPassword(password), new ArrayList<>(), UUID.randomUUID().toString());
                    if (userBookingService.signUp(newUser)) {
                        System.out.println("Sign-up successful!");
                    } else {
                        System.out.println("Sign-up failed.");
                    }
                    break;

                case 2:
                    System.out.print("Enter your username to Login: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String loginPassword = scanner.nextLine();
                    loggedInUser = new User(username, loginPassword, PasswordUtil.hashPassword(loginPassword), new ArrayList<>(), UUID.randomUUID().toString());

                    if (userBookingService.signIn(loggedInUser)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid credentials. Try again.");
                        loggedInUser = null;
                    }
                    break;

                case 3:
                    if (loggedInUser != null) {
                        userBookingService.fetchBookings(loggedInUser);
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 4:
                    System.out.print("Type your source station: ");
                    String source = scanner.nextLine();
                    System.out.print("Type your destination station: ");
                    String destination = scanner.nextLine();
                    List<Train> trains = userBookingService.getTrain(source, destination);

                    if (trains == null || trains.isEmpty()) {
                        System.out.println("No trains found for the given route.");
                    } else {
                        int index = 1;
                        for (Train t : trains) {
                            System.out.println(index + " Train id: " + t.getTrainId());
                            for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                                System.out.println("Station " + entry.getKey() + " Time: " + entry.getValue());
                            }
                            index++;
                        }
                    }
                    break;

                case 5:
                    if (loggedInUser == null) {
                        System.out.println("Please log in first.");
                        break;
                    }
                    System.out.print("Enter train ID: ");
                    int trainId = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Enter row: ");
                    int row = scanner.nextInt();
                    System.out.print("Enter column: ");
                    int col = scanner.nextInt();

//                    if (userBookingService.bookTrainSeat(trainId, row, col)) {
//                        System.out.println("Seat booked successfully!");
//                    } else {
//                        System.out.println("Seat booking failed. Please try another seat.");
//                    }
                    break;

                case 6:
                    if (loggedInUser == null) {
                        System.out.println("Please log in first.");
                        break;
                    }
                    System.out.print("Enter ticket ID to cancel: ");
                    String ticketId = scanner.nextLine();
                    if (userBookingService.cancelBooking(loggedInUser, ticketId)) {
                        System.out.println("Ticket canceled successfully.");
                    } else {
                        System.out.println("Invalid ticket ID or cancellation failed.");
                    }
                    break;

                case 7:
                    System.out.println("Exiting the application.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}