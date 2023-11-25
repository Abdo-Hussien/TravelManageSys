package TravelManagement;

public class Car {
    String made;
    String model;
    String type;
    int noSeats;
    double price;
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
    public void setPrcie() {
if (this.noSeats > 4) {
    this.price =0.4;
}
else if (this.type.equals("sedan")){
    this.price =0.2;
}
else if (this.type.equals("suv")) {
    this.price =0.3;
}
    }

}
