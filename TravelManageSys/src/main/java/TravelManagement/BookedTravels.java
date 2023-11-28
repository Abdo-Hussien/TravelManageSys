package TravelManagement;

import java.util.Date;
import java.util.Objects;

public class BookedTravels {
    protected String tripId;
    protected Date startDate;
    protected Date enDate;
    protected int noTicket;
    protected String ticketType;


    public BookedTravels(String tripId, Date startDate, Date enDate, int noTicket, String ticketType) {
        this.tripId = tripId;
        this.startDate = startDate;
        this.enDate = enDate;
        this.noTicket = noTicket;
        this.ticketType = ticketType;
    }

}
