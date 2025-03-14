package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import ticket.booking.utils.PasswordUtil;

import java.util.Date;
import java.util.List;

public class User {
    @JsonProperty("name")
    private String name;
    private String password;
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
    public User(String name, String password, String hashPassword,List<Ticket>ticketsBooked,String userId) {
        this.name = name;
        this.password = password;
        this.hashPassword = hashPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
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


    public List<Ticket> getTicketsBooked(){
        return ticketsBooked;
    }

    public void printTickets(){
        for( int i =0; i<ticketsBooked.size(); i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

    public void setName(String name){
        this.name = name;

    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked){
        this.ticketsBooked = ticketsBooked;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }
}
