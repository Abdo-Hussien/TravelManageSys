package TravelManagement;

public class Flight extends Transportation {
    private String airline;

    // Constructor
    public Flight(String pickUp, int maxCapacity, String airline, String transportID) {
        super(pickUp, maxCapacity, transportID);
        this.airline = airline;
    }

    public String getAirline() {
        return this.airline;
    }
    
}