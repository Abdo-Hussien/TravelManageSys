package TravelManagement;

public class Bus extends Transportation {
    private String busCompany;

    // Constructor
    public Bus(String pickUp, int maxCapacity, String busCompany) {
        super(pickUp, maxCapacity);
        this.busCompany = busCompany;
    }

    public String getBusCompany() {
        return busCompany;
    }
    
   
}

