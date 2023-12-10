/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import AccountManagement.Person;

/**
 *
 * @author bmood
 */
public class TourGuide extends Person {

    protected double salary;
    protected String answer;
    protected int month;

    protected Scanner in = new Scanner(System.in);

    public TourGuide(String account_id, String first_name, String last_name, String username, String password,
            int age, String gender, String address, String phone_number) {
        this.account_id = account_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone_number = phone_number;

    }

    public int Calculatetripinmouth(ArrayList<Trip> Trips, String TourGuideID, int Month) {
        int tripsInMonth = (int) Trips.stream()
                .filter(trip -> trip.getTourGuideID().equals(TourGuideID))
                .flatMap(trip -> Arrays.stream(trip.getStartDates()))
                .filter(date -> getMonthFromDate(date) + 1 == Month)
                .count();

        return tripsInMonth;
    }

    public double CalculateSalary(ArrayList<Trip> Trips, String TourGuideID, int Month) {
        int tripsInMonth = Calculatetripinmouth(Trips, TourGuideID, Month);
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
        return salary;
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

    public void showDetails(int index, ArrayList<TourGuide> allTourGuides, ArrayList<Trip> allTrips) {

        System.out.println("\t\t\t\t\t\t\t\t\t  **** Tour guide information****");

        System.out.println("id" + allTourGuides.get(index).account_id);
        System.out.println("full name: " + allTourGuides.get(index).first_name + " "
                + allTourGuides.get(index).last_name);
        System.out.println("Username" + allTourGuides.get(index).username);
        System.out.println("Address" + allTourGuides.get(index).address);
        System.out.println("Age" + allTourGuides.get(index).age);
        System.out.println("Gender" + allTourGuides.get(index).gender);
        System.out.println("PhoneNumber" + allTourGuides.get(index).phone_number);
        System.out.println("\t\t\t\t\t\t\t\t\t  **********************************");
        System.out.println("Want to know the  trips you are responsible for in month? y/n");
        answer = in.next();
        if (answer == "y") {

            System.out.println("Enter month:");
            month = in.nextInt();
            if (month >= 1 || month < 13) {
                System.out.println("the total of trips:"
                        + allTourGuides.get(index).Calculatetripinmouth(allTrips, account_id, month));
                System.out.println("These are all the trips you have:");
                for (int i = 0; i < allTrips.size(); i++) {
                    if (allTourGuides.get(index).account_id.equals(allTrips.get(i).getTourGuideID())) {
                        for (Date date : allTrips.get(i).getStartDates()) {
                            if (getMonthFromDate(date) + 1 == month) {
                                System.out.println(allTrips.get(i).getTitle());
                                System.out.println("For " + allTrips.get(i).getTripType() + " touring");
                                System.out.println("It is start at:" + allTrips.get(i).getStartDates());
                                System.out.println("It is End at:" + allTrips.get(i).getEndDates());
                            } else {
                                System.out.println("you have zero trips in this month.");
                            }
                        }
                    }
                }
            } else {
                System.out.println("wrong input! please try again.");
            }
        } else if (answer == "n") {

        }

        else {
            System.out.println("wrong input! please try again.");

        }

        System.out.println("");
        System.out.println("\t\t\t\t\t\t\t\t\t  **********************************");

    }
}
