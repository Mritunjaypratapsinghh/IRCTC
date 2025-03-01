package ticket.booking.entities;

import java.util.Date;
import java.util.List;

public class User {

    private String name;
    private String password;
    private String hashedPassword;
    private String userId;
    private List<Ticket> ticketsBooked;
    private String source;
    private String destination;
    private Date dateOfTravel;
    private List<Train> train;


}
