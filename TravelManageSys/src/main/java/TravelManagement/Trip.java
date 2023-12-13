/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
            // Cannot retrieve rate of ticket price from Silver class
            System.out.println("$" + trip.TripPrice(0.05) + "/person");
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
            System.out.print("\t\t\t\t " + "$" + trip.TripPrice(0.05) + "/person");
        }
        System.out.println("");
        // System.out.println("For " + trip.getTripType() + " touring");
        // Cannot retrieve rate of ticket price from Silver class
        // System.out.println("$" + 1299.99 + "/person");
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

    public void displayTripDetails(Trip trip) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date[] startDates;
        Date[] endDates;
        ArrayList<Transportation> allTranports = new ArrayList<>();
        startDates = trip.getStartDates();
        endDates = trip.getEndDates();
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
        System.out.println("$" + trip.TripPrice(0.05) + "/person");
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
        System.out.println("Going by: " + trip.getTransportation(allTranports, transportID));
        System.out.println("Car Rentals (Optional)");
        System.out.println("");
        System.out.println("************************************************");
        System.out.println("Book Now and Embark on an Unforgettable Journey!");
        System.out.println("************************************************");
    }

    public Trip getTrip(ArrayList<Trip> tripsList, String id) {
        for (Trip bookedTravels : tripsList) {
            if (bookedTravels.getTripId().equals(id)) {
                return bookedTravels;
            }
        }
        return null;
    }

    public static void displayAdminTrips(ArrayList<Trip> AllTrip) {
        System.out.println("All available Trips!:");
        System.out.println("*****************************************");
        AllTrip.stream().forEach(trip -> {
            System.out.printf("Trip ID: %-5s | Trip Name: %-25s | Availability: %d/%d -> (Remaining: %d)%n",
                    trip.getTripId(),
                    trip.getTitle(),
                    trip.getTicketCounter(),
                    trip.getCapacity(),
                    trip.getCapacity() - trip.getTicketCounter());
        });
    }

    public double TripPrice(double rate) {
        Double GeneralPrice = initPrice + rate * initPrice;
        return Math.round(GeneralPrice * 100.0) / 100.0;
    }

    public String getTripId() {
        return this.tripId;
    }

    public int getTicketCounter() {
        return this.TicketCounter;
    }

    public void getTicketCounter(int TicketCounter) {
        this.TicketCounter = TicketCounter;
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

    public Transportation getTransportation(ArrayList<Transportation> transportations, String transportID) {
        for (Transportation transportation : transportations)
            if (transportation.getTransportID().equals(transportID))
                return transportation;
        return null;
    }

    public TourGuide getTourGuide(ArrayList<TourGuide> TourGuides, String tourGuideID) {
        for (TourGuide tourGuide : TourGuides)
            if (tourGuide.getAccount_id().equals(tourGuideID))
                return tourGuide;
        return null;
    }

    public void setTicketCounter(int TicketCounter) {
        this.TicketCounter += TicketCounter;
    }
}