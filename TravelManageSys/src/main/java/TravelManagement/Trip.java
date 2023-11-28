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
    protected String carRentalType[];

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

    public double TripPrice(float rate) {
        return initPrice + rate * initPrice;
    }

    public static void displayTrips(ArrayList<Trip> trips) {
        int i = 0;
        for (Trip trip : trips) {
            if (i % 3 == 0)
                System.out.println("");
            System.out.print((i + 1) + ". " + trip.getTitle() + " " + trip.getTripId() + "\t");
            i++;
        }
    }

    public Trip() {
    }

    public Trip(String tripId, String title, String tripType, double initPrice, Date[] startDate, Date[] endDate,
            String Description, String tourGuide, int Capacity,
            String activities[], String hotelName, String transportationType, String[] carRentalType) {
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
        this.carRentalType = carRentalType;
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

    public String[] getCarRentalType() {

        return this.carRentalType;
    }

}