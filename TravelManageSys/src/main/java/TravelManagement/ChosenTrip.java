package TravelManagement;

import java.util.Date;

public class ChosenTrip {
    private String tripID;
    private String tripName;
    private Date startDate;
    private Date endDate;
    private double totalPrice;
    private String carID;

    public ChosenTrip() {
    }

    public ChosenTrip(String tripID, String tripName, Date startDate, Date endDate, double totalPrice, String carID) {
        this.tripName = tripName;
        this.tripID = tripID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.carID = carID;
    }

    public ChosenTrip(ChosenTrip chosenTrip) {
        this.tripName = chosenTrip.getTripName();
        this.tripID = chosenTrip.getTripID();
        this.startDate = chosenTrip.getStartDate();
        this.endDate = chosenTrip.getEndDate();
        this.totalPrice = chosenTrip.getTotalPrice();
        this.carID = chosenTrip.getCarID();
    }

    public String getTripID() {
        return this.tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getTripName() {
        return this.tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addToTotalPrice(double price_to_add) {
        this.totalPrice += price_to_add;
    }

    public void subtractFromTotalPrice(double price_to_subtract) {
        this.totalPrice -= price_to_subtract;
    }

    public String getCarID() {
        return this.carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

}
