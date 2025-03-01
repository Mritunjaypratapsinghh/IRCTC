package ticket.booking.entities;

import java.util.Date;
import java.util.List;

public class Ticket {
    private String tickedId;
    private String userId;
    private String source;
    private String destination;
    private Date dateOfTravel;
    private List<Train> train;
}
