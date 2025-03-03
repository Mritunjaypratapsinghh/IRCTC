package ticket.booking.entities;

import java.util.Date;
import java.util.List;

public class Ticket {
    private String tickedId;
    private String userId;
    private String source;
    private String destination;
    private String dateOfTravel;
    private List<Train> train;

    public Ticket(){}

    public Ticket(String tickedId, String userId, String source, String destination, String dateOfTravel,List<Train> train){
        this.tickedId = tickedId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }

    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", tickedId, userId, source, destination, dateOfTravel);
    }



    public String getTickedId(){
        return this.tickedId;
    }

    public void setTicketId(String tickedId){
        this.tickedId = tickedId;
    }

    public String getSource(){
        return this.source;
    }

    public void setSource(String source){
        this.source = source;
    }

    public String getDestination(){
        return this.destination;
    }

    public void setDestination(){
        this.destination = destination;
    }

    public String getDateOfTravel(){
        return this.dateOfTravel;
    }

    public void setDateOfTravel(String dateOfTravel){
        this.dateOfTravel = dateOfTravel;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(){
        this.userId = userId;
    }

    public List<Train> getTrain(){
        return this.train;
    }

    public void setTrain(List<Train> train){
        this.train = train;
    }




}
