/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import AccountManagement.Customers;
import AccountManagement.Person;
import data.fileManipulation;

/**
 *
 * @author bmood
 */
public abstract class Trip {
    private String id;
    private String name;
    private String tripType;
    private double initPrice;
    private Date[] startDates;
    private Date[] endDates;
    private String Description;
    private String tourGuideID;
    private final int Capacity;
    private int TicketCounter;
    private String[] activities;
    private String hotelName; // mandatory
    private String transportID;
    private static ArrayList<Transportation> allTransportations = new ArrayList<Transportation>(
            fileManipulation.getAllTransportations());

    public Trip() {
        Capacity = 0;
    }

    public Trip(String tripId, String title, String tripType, double initPrice, Date[] startDate, Date[] endDate,
            String Description, String tourGuideID, int Capacity, int TicketCounter,
            String activities[], String hotelName, String transportID) {
        this.id = tripId;
        this.startDates = startDate;
        this.endDates = endDate;
        this.tripType = tripType;
        this.name = title;
        this.Description = Description;
        this.hotelName = hotelName;
        this.initPrice = initPrice;
        this.tourGuideID = tourGuideID;
        this.activities = activities;
        this.Capacity = Capacity;
        this.TicketCounter = TicketCounter;
        this.transportID = transportID;
    }

    // Displays trip(s) according to a specific filter
    public static void displaySearchTrips(ArrayList<Trip> trips) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date[] startDates;
        Date[] endDates;
        System.out.println("");
        System.out.println("-------------------------------");
        for (Trip trip : trips) {
            startDates = trip.getStartDates();
            endDates = trip.getEndDates();
            System.out.println(trip.getTripID());
            System.out.println(trip.getTripName());
            System.out.println("For " + trip.getTripType() + " touring");
            System.out.println("$" + trip.TripPrice(0.05) + "/person");
            System.out.println("Available dates:");
            for (int i = 0; i < startDates.length; i++)
                System.out
                        .print("~ " + dateFormat.format(startDates[i]) + "\t" + dateFormat.format(endDates[i]) + "\n");
            System.out.println("-------------------------------");
        }
    }

    // Displays the featured Trips (Shows specific Trips only)
    public static void displayTrips(ArrayList<Trip> trips) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date[] startDates;
        Date[] endDates;
        System.out.println("\t\t\t\t\t\t\t\t\t  **********************************");
        System.out.println("\t\t\t\t\t\t\t\t\t\t   Featured Trips");
        System.out.println("\t\t\t\t\t\t\t\t\t  **********************************\n");
        for (Trip trip : trips) {
            System.out.print("\t\t\t\t\t  " + trip.getTripName());
        }
        System.out.println("");
        for (Trip trip : trips) {
            System.out.print("\t\t\t\t" + "For " + trip.getTripType() + " touring");
        }
        System.out.println("");
        for (Trip trip : trips) {
            System.out.print("\t\t\t\t " + "$" + trip.TripPrice(0.05) + "/person");
        }
        System.out.println("");
        for (Trip trip : trips) {
            startDates = trip.getStartDates();
            endDates = trip.getEndDates();
            System.out.print(
                    "\t\t\t\t" + dateFormat.format(startDates[0]) + "  " + dateFormat.format(endDates[0]));
        }
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t\t\t  ************************************************");
        System.out.println("\t\t\t\t\t\t\t\t  Book Now and Embark on an Unforgettable Journey!");
        System.out.println("\t\t\t\t\t\t\t\t  ************************************************");
    }

    // Displays full information about a specific trip
    public void displayTripDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date[] startDates;
        Date[] endDates;
        startDates = this.getStartDates();
        endDates = this.getEndDates();
        String[] Activities = this.getActivities();
        System.out.println("**********************************");
        System.out.println("Explore the Beauty of " + this.getTripName() + ".");
        System.out.println("**********************************");
        System.out.println("");
        System.out.println(this.getDescription());
        System.out.println("");
        System.out.println(this.getTripName());
        System.out.println("For " + this.getTripType() + " touring");
        System.out.println("Available dates:");
        for (int i = 0; i < startDates.length; i++)
            System.out.print("~ " + dateFormat.format(startDates[i]) + "\t" + dateFormat.format(endDates[i]) + "\n");
        System.out.println("");
        System.out.println("Activities:\n");
        for (String game : Activities) {
            System.out.println("- " + game);
        }
        System.out.println("");
        System.out.println("Staying at: " + this.getHotelName());
        Transportation transportation = this.getTransportation(transportID);
        if (transportation instanceof Bus)
            System.out.println("Bus Company: " + ((Bus) transportation).getBusCompany());
        else if (transportation instanceof Flight)
            System.out.println("Airline Company: " + ((Flight) transportation).getAirline());
        System.out.println("Car Rentals (Optional)");
        System.out.println("");
        System.out.println("Avaliable Tickets:");
        System.out.println("Silver ticket <Regular  ticket> :");
        System.out.println(" ~ One meal.\n ~ Activities not included.\n ~ Car rental with extra fees.");
        System.out.println("Price: $" + this.TripPrice(0.05) + "\n");
        System.out.println("Gold ticket :");
        System.out.println(" ~ Half board.\n ~ One choosen Activity included.\n ~ Car rental with extra fees.");
        System.out.println("Price: $" + this.TripPrice(0.3) + "\n");
        System.out.println("Platinum ticket :");
        System.out.println(" ~ Full board.\n ~ All Activities included.\n ~ Free car rental.");
        System.out.println("Price: $" + this.TripPrice(0.6) + "\n");
        System.out.println("************************************************");
        System.out.println("Book Now and Embark on an Unforgettable Journey!");
        System.out.println("************************************************");

    }

    // Gets a specific trip from a list of trips by it's ID
    public static Trip getTrip(ArrayList<Trip> Trips, String tripid) {
        for (Trip trip : Trips)
            if (trip.getTripID().equals(tripid))
                return trip;
        return null;
    }

    // Shows the available trips according to the avalability and capacity
    public static void displayAdminTrips(ArrayList<Trip> allTrips, ArrayList<Person> allCustomers2) {
        System.out.println("All available Trips!: " + allTrips.size());
        System.out.println("_________________________________________________________________\n");
        System.out.printf("%-10s | %-25s | %-15s -> (%s)\n", "Trip ID", "Trip Name", "Availability", "Remaining");
        System.out.println("_________________________________________________________________");
        for (int i = 0; i < allTrips.size(); i++) {
            System.out.printf("%-10s | %-25s | %d/%-13s -> (%d)\n",
                    allTrips.get(i).getTripID(),
                    allTrips.get(i).getTripName(),
                    allTrips.get(i).getTicketCounter(),
                    allTrips.get(i).getCapacity(),
                    allTrips.get(i).getCapacity() - allTrips.get(i).getTicketCounter());
        }
    }

    // Returns the price of a trip with the rate of a specific ticket
    public double TripPrice(double rate) {
        double GeneralPrice = initPrice + rate * initPrice;
        return Math.round(GeneralPrice * 100.0) / 100.0;
    }

    // Setters and Getters
    public String getTripID() {
        return this.id;
    }

    public int getTicketCounter() {
        return this.TicketCounter;
    }

    public void setTripID(String tripId) {
        this.id = tripId;
    }

    public String getTripName() {
        return this.name;
    }

    public void setTripName(String name) {
        this.name = name;
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

    public Date[] getStartDates() {
        return this.startDates;
    }

    public Date[] getEndDates() {
        return this.endDates;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getCapacity() {
        return this.Capacity;
    }

    public String getTourGuideID() {
        return this.tourGuideID;
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

    /*
     * Returns a transportation by the transportation ID,
     * This is for linkage of Transportation class with Trip class
     */
    public Transportation getTransportation(String transportID) {
        for (int i = 0; i < allTransportations.size(); i++)
            if (allTransportations.get(i).getTransportID().equals(transportID))
                return allTransportations.get(i);
        return null;
    }

    // Returns a Tour Guide by the TourGuide ID
    public TourGuide getTourGuide(ArrayList<TourGuide> TourGuides, String tourGuideID) {
        for (TourGuide tourGuide : TourGuides)
            if (tourGuide.getAccount_id().equals(tourGuideID))
                return tourGuide;
        return null;
    }

    public void setTicketCounter(int TicketCounter) {
        this.TicketCounter += TicketCounter;
    }

    public String getTransportID() {
        return this.transportID;
    }
}