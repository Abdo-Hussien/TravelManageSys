package TravelManagement;

import java.util.Date;

public class ChosenTrip {
    private String tripName;
    private String tripId;
    private Date starDate;
    private Date endDate;

    public ChosenTrip() {
    }

    public ChosenTrip(String tripType, String tripId, Date starDate, Date endDate) {
        this.tripName = tripType;
        this.tripId = tripId;
        this.starDate = starDate;
        this.endDate = endDate;
    }

    public String getTripName() {
        return this.tripName;
    }

    public void setTripName(String tripType) {
        this.tripName = tripType;
    }

    public String getTripId() {
        return this.tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public Date getStarDate() {
        return this.starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
