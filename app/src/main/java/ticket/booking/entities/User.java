package ticket.booking.entities;

import java.util.Date;
import java.util.List;

public class User {
    private String name;
    private String password;  // This should not be stored as plain text
    private String hashPassword; // Store the hashed password instead
    private String userId;
    private List<Ticket> ticketsBooked;
    private String source;
    private String destination;
    private Date dateOfTravel;
    private List<Train> train;

    // Constructor
    public User(String name, String userId, String hashPassword) {
        this.name = name;
        this.userId = userId;
        this.hashPassword = hashPassword;
    }

    // Getter for userId
    public String getUserId() {
        return userId;
    }

    // Getter for hashPassword
    public String getHashPassword() {
        return hashPassword;
    }
}
