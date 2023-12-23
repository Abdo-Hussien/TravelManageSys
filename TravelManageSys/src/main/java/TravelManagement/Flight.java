package TravelManagement;

public class Flight extends Transportation {
    private String airline;

    // Constructor
    public Flight(String pickUp, String airline, String transportID) {
        super(pickUp, transportID);
        this.airline = airline;
    }

    public String getAirline() {
        return this.airline;
    }
    
}