package TravelManagement;

public class Bus extends Transportation {
    private String busCompany;

    // Constructor
    public Bus(String pickUp, int maxCapacity, String busCompany, String transportID) {
        super(pickUp, maxCapacity, transportID);
        this.busCompany = busCompany;
    }

    public String getBusCompany() {
        return this.busCompany;
    }
    
   
}

