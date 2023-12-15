package TravelManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class BookedTravels {
    protected String tripId;
    protected String tripName;
    protected Date startDate;
    protected Date endDate;
    protected Double totalPrice;
    protected String model;
    ArrayList<Ticket> Bookedticket = new ArrayList<Ticket>();

    public BookedTravels(String tripId, String tripName, Date startDate, Date endDate, ArrayList<Ticket> ticket,
            Double price) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Bookedticket = ticket;
        this.totalPrice = price;
    }

    public BookedTravels() {
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

    public Date getEndDate() {
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

    public Double getTotalPrice() {
        return this.totalPrice;
    }

}
