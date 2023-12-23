package TravelManagement;

public class Bus extends Transportation {
    private String busCompany;

    // Constructor
    public Bus(String pickUp, String busCompany, String transportID) {
        super(pickUp, transportID);
        this.busCompany = busCompany;
    }

    public String getBusCompany() {
        return this.busCompany;
    }
    
   
}

