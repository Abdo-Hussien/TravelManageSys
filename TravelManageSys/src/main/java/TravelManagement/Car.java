package TravelManagement;

public class Car {
    protected String made;
    protected String model;
    protected String type;
    protected int noSeats;
    protected double price;

    public Car(String made, String model, String type, int noSeats, double price) {
        this.made = made;
        this.model = model;
        this.type = type;
        this.noSeats = noSeats;
        this.price = price;
    }

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

// added in abdelrahman bracnch