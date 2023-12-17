/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import AccountManagement.Admin;
import AccountManagement.Person;

/**
 *
 * @author bmood
 */
public class TourGuide extends Person {

    protected double salary;

    public TourGuide() {
    };

    public TourGuide(String account_id, String first_name, String last_name, String username, String password,
            int age, String gender, String[] address, String phone_number) {
        this.account_id = account_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.streetAddress = address[0];
        this.stateAddress = address[1];
        this.zipAddress = address[2];
        setAddress(this.streetAddress, this.stateAddress, this.zipAddress);
        this.phone_number = phone_number;

    }

    public int CalcTripInMonth(ArrayList<Trip> Trips, String TourGuideID, int Month) {
        int tripsInMonth = (int) Trips.stream()
                .filter(trip -> trip.getTourGuideID().equals(TourGuideID))
                .flatMap(trip -> Arrays.stream(trip.getStartDates()))
                .filter(date -> getMonthFromDate(date) + 1 == Month)
                .count();

        return tripsInMonth;
    }

    public double CalculateSalary(ArrayList<Trip> Trips, String TourGuideID, int Month) {
        int tripsInMonth = CalcTripInMonth(Trips, TourGuideID, Month);
        /*
         * for (Trip trip : Trips) {
         * if (trip.getTourGuideID().equals(TourGuideID)) {
         * for (Date date : trip.getStartDate())
         * if (getMonthFromDate(date) + 1 == Month)
         * numoftrip++;
         * }
         * }
         */
        salary = tripsInMonth * 2173.43;
        return Math.round(salary * 100.0) / 100.0;
    }

    private static int getMonthFromDate(Date date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(java.util.Calendar.MONTH);
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void guideMenu(ArrayList<TourGuide> allTourGuides, ArrayList<Trip> allTrips) {
        Scanner in = new Scanner(System.in);
        int choice;
        System.out.println("Welcome " + this.getFirst_name() + "! What would you like to do?");
        System.out.println("1- Profile settings.\n2- Sign out.");
        choice = in.nextInt();
        in.nextLine();
        switch (choice) {
            case 1:
                showinfo(allTourGuides, allTrips);
                break;
            case 2:
                // Sign out;
                return;
            default:
                break;
        }
    }

    public void showinfo(ArrayList<TourGuide> allTourGuides, ArrayList<Trip> allTrips) {
        int choice;

        Scanner in = new Scanner(System.in);
        System.out.printf("%-20s**** Tour Guide Information ****%n", "");
        System.out.printf("ID: %s%n", this.account_id);
        System.out.printf("Full Name: %s %s%n", this.first_name,
                this.last_name);
        System.out.printf("Username: %s%n", this.username);
        System.out.printf("Address: %s%n", this.address);
        System.out.printf("Age: %d%n", this.age);
        System.out.printf("Gender: %s%n", this.gender);
        System.out.printf("Phone Number: %s%n", this.phone_number);
        System.out.printf("%-20s**********************************%n", "");
        System.out.println("1- Edit your profile details.");
        System.out.println("2- Trips you are responsible for in a specific month.");
        System.out.println("3- Go back.");
        choice = in.nextInt();
        in.nextLine();
        switch (choice) {
            case 1:
                Admin admin = new Admin();
                admin.editInformations("old", allTourGuides, "Tourguide", "Tourguide");
                break;
            case 2:
                while (!findMonth(allTourGuides, allTrips));
            case 3:
                guideMenu(allTourGuides, allTrips);
                break;
            default:
                System.out.println("Wrong input! please try again.");
                System.out.println("Press any key (followed by Enter key) to go back...");
                in.next();
                in.nextLine();
                showinfo(allTourGuides, allTrips);
                return;
        }
    }

    private boolean findMonth(ArrayList<TourGuide> allTourGuides, ArrayList<Trip> allTrips) {
        int month;
        boolean[] foundTrip = { false };
        Scanner in = new Scanner(System.in);
        System.out.print("Enter month: ");
        month = in.nextInt();
        in.nextLine();

        if (month >= 1 && month < 13) {
            allTrips.stream()
                    .filter(trip -> this.account_id.equals(trip.getTourGuideID()))
                    .forEach(trip -> {
                        Arrays.stream(trip.getStartDates()).forEach(date -> {
                            if (getMonthFromDate(date) + 1 == month) {
                                foundTrip[0] = true;
                                System.out
                                        .println(trip.getTitle() + "\n" + trip.getTripType() + " touring");
                                System.out.println(date);
                            }
                        });
                    });
            if (!foundTrip[0]) {
                System.out.println("You aren't guiding any trips in this given month.");
            } else {
                System.out.println("The total of trips: "
                        + this.CalcTripInMonth(allTrips, account_id, month));
                System.out
                        .println("Your salary in this month is: "
                                + CalculateSalary(allTrips, account_id, month));
                System.out.printf("%-20s**********************************%n", "");
            }
            System.out.println("Would you like to repeat the process on another month? (y/n)");
            char answer = in.next().toLowerCase().charAt(0);
            in.nextLine();
            if (answer == 'y')
                return false;

        } else {
            System.out.println("Invalid Month");
            System.out.println("Press any key (followed by Enter key) to go back...");
            in.next();
            in.nextLine();
            return false;
        }
        return true;
    }

    @Override
    public int getTripHistoryCounter() {
        return 0;
    }
}
