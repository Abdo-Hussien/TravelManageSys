package TravelManagement;

import java.util.Date;
import java.util.Objects;

public class BookedTravels {
    protected String tripId;
    protected String tripName;
    protected Date startDate;
    protected Date enDate;
    protected int noTicket;
    protected String ticketType;

    public BookedTravels(String tripId, String tripName, Date startDate, Date enDate, int noTicket, String ticketType) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = startDate;
        this.enDate = enDate;
        this.noTicket = noTicket;
        this.ticketType = ticketType;
    }

}
