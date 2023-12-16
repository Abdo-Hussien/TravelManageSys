package TravelManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class BookedTravels {
    protected String tripId;
    protected String tripName;
    protected Date startDate;
    protected Date endDate;
    protected double totalPrice;
    protected String carID;
    ArrayList<Ticket> Bookedticket = new ArrayList<Ticket>();

    public BookedTravels(String tripId, String tripName, Date startDate, Date endDate, ArrayList<Ticket> ticket,
            double totalprice, String carID) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Bookedticket = ticket;
        this.totalPrice = totalprice;
        this.carID = carID;
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

    public ArrayList<Ticket> getBookedticket() {
        return Bookedticket;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

}
