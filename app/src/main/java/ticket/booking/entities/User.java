package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import ticket.booking.utils.PasswordUtil;

import java.util.Date;
import java.util.List;

public class User {
    @JsonProperty("name")
    private String name;
    @JsonProperty("hashed_password")
    private String hashPassword; // Store the hashed password instead
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("tickets_booked")
    private List<Ticket> ticketsBooked;
    private String source;
    private String destination;
    @JsonProperty("date_of_travel")
    private Date dateOfTravel;
    private List<Train> train;

    public User() {
    }

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

    //Setter for hashPassword
    public void setHashPassword(String hashPassword){
        this.hashPassword = hashPassword;
    }


}
