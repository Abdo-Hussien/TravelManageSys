/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

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

    public void displayTrips(ArrayList<Trip> trips) {
        for (Trip trip : trips) {
            System.out.print("****************************************************");
            System.out.print("\t Explore the Beauty of Ain Sokhna");
            System.out.print("****************************************************");
            System.out.println("\t\t  " + this.getTitle());
            System.out.println("\t     " + "For" + this.getTripType() + "touring");
            System.out.println("\t       "+ "$" + 1299.99 + "/person"); //Cannot retrieve rate of ticket price from Silver class
            System.out.println("\t\t "+ "$" + this.getStartDate());
            System.out.println("\t\t "+ "$" + this.getEndDate());
            System.out.println("\t      " + "View details (y/n)");
            System.out.println("\t        " + "Book now?(book)");
            System.out.print("****************************************************");
            System.out.print("  Book Now and Embark on an Unforgettable Journey!");
            System.out.print("****************************************************");
        }
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