package TravelManagement;

public class Car {
    String made;
    String model;
    String type;
    int noSeats;
    double price= this.noSeats > 4 ? .4 : type.equals("sedan") ? 0.2  :type.equals("suv") ? 0.3: 0 ; 

    public String getMade() {
        return this.made;
    }

    public String getModel() {
        return this.model;
    }

    public String getType() {
        return this.type;
    }

    public int getNoSeats() {
        return this.noSeats;
    }

    public double getPrice() {
        return this.price;
    }

}
