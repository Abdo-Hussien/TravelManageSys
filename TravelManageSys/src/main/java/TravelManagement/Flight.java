package TravelManagement;

class Flight extends Transportation {
    private String airline;
    private String flight_grade;

    // Constructor
    public Flight(String pickUp, String destination, int maxCapacity, String airline, String flight_grade) {
        super(airline, airline, maxCapacity);
        this.airline = airline;
        this.flight_grade = flight_grade;
    }

    public String getAirline() {
        return airline;
    }

    public String getflight_grade() {
        return flight_grade;
    }
}