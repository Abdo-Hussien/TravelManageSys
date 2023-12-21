package TravelManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Car {
    protected String carID;
    protected String made;
    protected String model;
    protected String type;
    protected int noSeats;
    protected double price;

    int choice;
    String ans;
    Scanner in = new Scanner(System.in);

    public Car(String carID, String made, String model, String type, int noSeats, double price) {
        this.carID = carID;
        this.made = made;
        this.model = model;
        this.type = type;
        this.noSeats = noSeats;
        this.price = price;
    }

    public Car() {
    }

    public String getCarID() {
        return this.carID;
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

    public void displayCarDetails() {
        System.out.println("------------------------------------");
        System.out.println("Car ID: " + this.getCarID());
        System.out.printf("Made: %-20sModel: %-20s%n", this.getMade(), this.getModel());
        System.out.printf("Type: %-20sPrice: %-20s%n", this.getType(), '$' + this.getPrice());
        System.out.println("Number of Seats: " + this.getNoSeats());
        System.out.println("------------------------------------");
    }

    public static void displayAllCars(ArrayList<Car> allCars) {
        System.out.println("All available Cars!: " + allCars.size());
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-10s | %-25s | %-10s -> (%s)\n", "Car index", "Car Name", "Car model", "Car price");
        System.out.println("-----------------------------------------------------------------------");

        for (int i = 0; i < allCars.size(); i++) {
            System.out.printf("%-10s | %-25s | %-10s -> ($%.2f)\n",
                    i + 1,
                    allCars.get(i).getMade(),
                    allCars.get(i).getModel(),
                    allCars.get(i).getPrice());
        }
    }
}

// added in abdelrahman bracnch