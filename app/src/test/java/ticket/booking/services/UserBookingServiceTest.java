package ticket.booking.services;

public class UserBookingServiceTest {
    public static void main(String[] args) {
        try {
            UserBookingService service = new UserBookingService();

            System.out.println("Sign Up: " + service.signUp("John", "john123", "password"));
            System.out.println("Sign In (correct password): " + service.signIn("john123", "password"));
            System.out.println("Sign In (wrong password): " + service.signIn("john123", "wrongpassword"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
