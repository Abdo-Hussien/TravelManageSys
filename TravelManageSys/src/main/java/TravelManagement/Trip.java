/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
// In need for a Date checker to check if the start and end date are after the current date for the availability of the trip.

/**
 *
 * @author bmood
 */
public abstract class Trip {
    protected String tripId;
    protected String title;
    protected String tripType;
    protected double initPrice;
    protected Date startDate[];
    protected Date endDate[];
    protected String Description;
    protected String tourGuideName;
    protected int Capacity;
    protected String activities[];
    protected String hotelName; // mandatory
    protected String transportationType;

    // checks the relationship for a customer, Should be in main
    public void RelationshipChecker(String customerRelationship) {
        switch (customerRelationship.toLowerCase()) {
            case "single":
                System.out.println("All trips except couple tours using instanceOf");
                break;
            case "taken":
                System.out.println("All trips including couple tours using instanceOf");
                break;
            default:
                System.out.println("error..");
                break;
        }
    }

    public double TripPrice(double rate) {
        return initPrice + rate * initPrice;
    }

    public static void displaySearchTrips(ArrayList<Trip> trips) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date[] startDates;
        Date[] endDates;
        System.out.println("");
        System.out.println("-------------------------------");
        for (Trip trip : trips) {
            startDates = trip.getStartDate();
            endDates = trip.getEndDate();
            System.out.println(trip.getTripId());
            System.out.println(trip.getTitle());
            System.out.println("For " + trip.getTripType() + " touring");
            // Cannot retrieve rate of ticket price from Silver class
            System.out.println("$" + 1299.99 + "/person");
            System.out.println("Available dates:");
            for (int i = 0; i < startDates.length; i++)
                System.out
                        .print("~ " + dateFormat.format(startDates[i]) + "\t" + dateFormat.format(endDates[i]) + "\n");
            System.out.println("-------------------------------");
        }
    }

    public static void displayTrips(ArrayList<Trip> trips) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date[] startDates;
        Date[] endDates;
        System.out.println("\t\t\t\t\t\t\t\t\t  **********************************");
        System.out.println("\t\t\t\t\t\t\t\t\t\t   Featured Trips");
        System.out.println("\t\t\t\t\t\t\t\t\t  **********************************\n");
        for (Trip trip : trips) {
            System.out.print("\t\t\t\t\t  " + trip.getTitle());
        }
        System.out.println("");
        for (Trip trip : trips) {
            System.out.print("\t\t\t\t" + "For " + trip.getTripType() + " touring");
        }
        System.out.println("");
        for (Trip trip : trips) {
            System.out.print("\t\t\t\t " + "$" + 1299.99 + "/person");
        }
        System.out.println("");
        // System.out.println("For " + trip.getTripType() + " touring");
        // Cannot retrieve rate of ticket price from Silver class
        // System.out.println("$" + 1299.99 + "/person");
        for (Trip trip : trips) {
            startDates = trip.getStartDate();
            endDates = trip.getEndDate();
            System.out.print(
                    "\t\t\t\t" + dateFormat.format(startDates[0]) + "  " + dateFormat.format(endDates[0]));
        }
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\t\t  ************************************************");
        System.out.println("\t\t\t\t\t\t\t\t  Book Now and Embark on an Unforgettable Journey!");
        System.out.println("\t\t\t\t\t\t\t\t  ************************************************");
    }

    public static void displayTripDetails(Trip trip) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date[] startDates;
        Date[] endDates;
        startDates = trip.getStartDate();
        endDates = trip.getEndDate();
        String[] Activities = trip.getActivities();
        System.out.println("**********************************");
        System.out.println("Explore the Beauty of " + trip.getTitle() + ".");
        System.out.println("**********************************");
        System.out.println("");
        System.out.println(trip.getDescription());
        System.out.println("");
        System.out.println(trip.getTitle());
        System.out.println("For " + trip.getTripType() + " touring");
        // Cannot retrieve rate of ticket price from Silver class
        System.out.println("$" + 1299.99 + "/person");
        System.out.println("Available dates:");
        for (int i = 0; i < startDates.length; i++)
            System.out.print("~ " + dateFormat.format(startDates[i]) + "\t" + dateFormat.format(endDates[i]) + "\n");
        System.out.println("");
        System.out.println("Activities:\n");
        for (String game : Activities) {
            System.out.println("- " + game);
        }
        System.out.println("");
        System.out.println("Staying at: " + trip.getHotelName());
        System.out.println("Going by: " + trip.getTransportationType());
        System.out.println("Car Rentals (Optional)");
        System.out.println("");
        System.out.println("************************************************");
        System.out.println("Book Now and Embark on an Unforgettable Journey!");
        System.out.println("************************************************");
    }

    public Trip() {
    }

    public Trip(String tripId, String title, String tripType, double initPrice, Date[] startDate, Date[] endDate,
            String Description, String tourGuide, int Capacity,
            String activities[], String hotelName, String transportationType) {
        this.tripId = tripId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripType = tripType;
        this.title = title;
        this.Description = Description;
        this.hotelName = hotelName;
        this.initPrice = initPrice;
        this.transportationType = transportationType;
        this.tourGuideName = tourGuide;
        this.activities = activities;
        this.Capacity = Capacity;
    }

    public String getTripId() {
        return this.tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTripType() {
        return this.tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public double getInitPrice() {
        return this.initPrice;
    }

    public void setInitPrice(double initPrice) {
        this.initPrice = initPrice;
    }

    public Date[] getStartDate() {
        return this.startDate;
    }

    public Date[] getEndDate() {
        return this.endDate;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTourGuideName() {
        return this.tourGuideName;
    }

    public void setTourGuideName(String tourGuideName) {
        this.tourGuideName = tourGuideName;
    }

    public int getCapacity() {
        return this.Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public String[] getActivities() {
        return this.activities;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getTransportationType() {
        return this.transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }
}