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

    public void displayCarInfo(int index) {

    }

    public void addCar(int index, ArrayList<BookedTravels> bookedTravels) {
    }

    public void displayalCars(ArrayList<Car> allCars) {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t          CAR RENTAL");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\n");

        System.out.println("All available Cars!: " + allCars.size());
        System.out.println("_________________________________________________________________\n");
        System.out.printf("%-10s | %-25s | %-15s -> (%s)\n", "Car index", "Car Name", "Car model", "Car price");
        System.out.println("_________________________________________________________________");

        for (int i = 0; i < allCars.size(); i++) {
            System.out.printf("%-10s | %-25s | %-13s -> (%d)\n",
                    i + 1,
                    allCars.get(i).getMade(),
                    allCars.get(i).getMade(),
                    allCars.get(i).getPrice());
        }
        System.out.println("Chooce car by its index: ");
        choice = in.nextInt();
        System.out.println("Want to show more information about this car?  y/n");
        ans = in.nextLine();
        if (ans.toUpperCase().endsWith("Y")) {

        }
    }
}

// added in abdelrahman bracnch