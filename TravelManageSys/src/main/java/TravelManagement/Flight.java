package TravelManagement;

public class Flight extends Transportation {
    private String airline;

    // Constructor
    public Flight(String pickUp, int maxCapacity, String airline) {
        super(pickUp, maxCapacity);
        this.airline = airline;
    }

    public String getAirline() {
        return airline;
    }
    
}