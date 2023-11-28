package TravelManagement;

class Bus extends Transportation {
    private String busCompany;

    // Constructor
    public Bus(String pickUp, String destination, int maxCapacity, String busCompany) {
        super(busCompany, busCompany, maxCapacity);
        this.busCompany = busCompany;
    }

    public String getBusCompany() {
        return busCompany;
    }
    
   
}

