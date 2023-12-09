/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import AccountManagement.Person;
import data.fileManipulation;

/**
 *
 * @author bmood
 */
public class TourGuide extends Person {

    protected double salary;

    public TourGuide() {
    };

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

    public double CalculateSalary(ArrayList<Trip> Trips, String TourGuideID, int Month) {
        int tripsInMonth = (int) Trips.stream()
                .filter(trip -> trip.getTourGuideID().equals(TourGuideID))
                .flatMap(trip -> Arrays.stream(trip.getStartDates()))
                .filter(date -> getMonthFromDate(date) + 1 == Month)
                .count();
        /*
         * for (Trip trip : Trips) {
         * if (trip.getTourGuideID().equals(TourGuideID)) {
         * for (Date date : trip.getStartDate())
         * if (getMonthFromDate(date) + 1 == Month)
         * numoftrip++;
         * }
         * }
         */
        System.out.println(tripsInMonth);
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
        
    }
}
