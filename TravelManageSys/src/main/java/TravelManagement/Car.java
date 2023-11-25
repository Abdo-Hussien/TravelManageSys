package TravelManagement;

public class Car {
    String made;
    String model;
    String type;
    int noSeats;
    double price=type.equals("suv") ? 0.3 : 0.2;

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
