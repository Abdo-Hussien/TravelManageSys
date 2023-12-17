/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.fileManipulation;

/**
 *
 * @author bmood
 */
public abstract class Trip {
    private String tripId;
    private String title;
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
        this.tripId = tripId;
        this.startDates = startDate;
        this.endDates = endDate;
        this.tripType = tripType;
        this.title = title;
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
            System.out.println(trip.getTripId());
            System.out.println(trip.getTitle());
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
            System.out.print("\t\t\t\t\t  " + trip.getTitle());
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
        System.out.println("Explore the Beauty of " + this.getTitle() + ".");
        System.out.println("**********************************");
        System.out.println("");
        System.out.println(this.getDescription());
        System.out.println("");
        System.out.println(this.getTitle());
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
        System.out.println("Going by: " + this.getTransportation(transportID).getPickUp());
        System.out.println("Car Rentals (Optional)");
        System.out.println("");
        System.out.println("Avaliable Ticket:\n");
        System.out.println("Silver ticket <Regular  ticket>:");
        System.out.println("1- one meal.\n 2-Activities not included\n3-car rental with extra fees.");
        System.out.println("price: $" + this.TripPrice(0.05));
        System.out.println("Gold ticket :");
        System.out.println("1- half board.\n 2-one choosen Activity included\n3-car rental with extra fees.");
        System.out.println("price: $" + this.TripPrice(0.3));
        System.out.println("Platinum ticket :\n");
        System.out.println("1-Full board.\n2-All Activities included.\n3-free car rental.");
        System.out.println("price: $" + this.TripPrice(0.6));
        System.out.println("************************************************");
        System.out.println("Book Now and Embark on an Unforgettable Journey!");
        System.out.println("************************************************");
    }

    // Gets a specific trip from a list of trips by it's ID
    public static Trip getTrip(ArrayList<Trip> Trips, String tripid) {
        for (Trip trip : Trips)
            if (trip.getTripId().equals(tripid))
                return trip;
        return null;
    }

    // Shows the available trips according to the avalability and capacity
    public static void displayAdminTrips(ArrayList<Trip> AllTrip) {
        System.out.println("All available Trips!: " + AllTrip.size());
        System.out.println("_________________________________________________________________\n");
        System.out.printf("%-10s | %-25s | %-15s -> (%s)\n", "Trip ID", "Trip Name", "Availability", "Remaining");
        System.out.println("_________________________________________________________________");

        for (int i = 0; i < AllTrip.size(); i++) {
            System.out.printf("%-10s | %-25s | %d/%-13s -> (%d)\n",
                    AllTrip.get(i).getTripId(),
                    AllTrip.get(i).getTitle(),
                    AllTrip.get(i).getTicketCounter(),
                    AllTrip.get(i).getCapacity(),
                    AllTrip.get(i).getCapacity() - AllTrip.get(i).getTicketCounter());
        }
    }

    // Returns the price of a trip with the rate of a specific ticket
    public double TripPrice(double rate) {
        Double GeneralPrice = initPrice + rate * initPrice;
        return Math.round(GeneralPrice * 100.0) / 100.0;
    }

    // Setters and Getters
    public String getTripId() {
        return this.tripId;
    }

    public int getTicketCounter() {
        return this.TicketCounter;
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

    // Each Trip has a Transportation so it gets it by trip ID
    public Transportation getTransportation(int tripId,
            ArrayList<Trip> allTrips) {
        for (int i = 0; i < allTransportations.size(); i++)
            if (allTransportations.get(i).getTransportID().equals(allTrips.get(tripId).transportID))
                return allTransportations.get(i);
        return null;
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