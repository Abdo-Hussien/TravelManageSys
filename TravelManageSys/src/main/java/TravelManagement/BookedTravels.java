package TravelManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class BookedTravels {
    protected String tripId;
    protected String tripName;
    protected Date startDate;
    protected Date endDate;
    ArrayList<Ticket> Bookedticket = new ArrayList<Ticket>();

    public BookedTravels(String tripId, String tripName, Date startDate, Date endDate, ArrayList<Ticket> ticket) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Bookedticket = ticket;
    }

    public String getTripId() {
        return this.tripId;
    }

    public String getTripName() {
        return this.tripName;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEnDate() {
        return this.endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEnDate(Date EndDate) {
        this.startDate = EndDate;
    }

    public void getBookedticket() {
        for (int i = 0; i < Bookedticket.size(); i++) {
            System.out.println(Bookedticket.get(i).getCounter() + " " + Bookedticket.get(i).getType());
        }
    }
}
