package ticket.booking;

import ticket.booking.utils.PasswordUtil;

public class PasswordUtilTest {
    public static void main(String[] args) {
        // Step 1: Define a sample password
        String rawPassword = "MySecurePass123";

        // Step 2: Hash the password
        String hashedPassword = PasswordUtil.hashPassword(rawPassword);
        System.out.println("Hashed Password: " + hashedPassword);

        // Step 3: Verify the password (Correct input)
        boolean isMatch = PasswordUtil.verifyPassword(rawPassword, hashedPassword);
        System.out.println("Password Verification (Correct): " + isMatch);

        // Step 4: Verify with an incorrect password
        boolean isWrongMatch = PasswordUtil.verifyPassword("WrongPassword", hashedPassword);
        System.out.println("Password Verification (Incorrect): " + isWrongMatch);
    }
}
