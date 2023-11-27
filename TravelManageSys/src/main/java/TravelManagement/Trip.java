/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.Date;
// In need for a Date checker to check if the start and end date are after the current date for the availability of the trip.

/**
 *
 * @author bmood
 */
public abstract class Trip {
    protected String tripId;
    protected String startDate;
    protected String endDate;
    protected String tripType;
    protected String title;
    protected String Description;
    protected Hotels Hotel;
    protected double initPrice;
    protected Transportation transportation;
    protected Ticket ticket[]; // Should this attribute be array of 3 tickets? For each Trip there should be
                               // gold, silver and platinum tickets
    protected TourGuide TourGuide;
    protected static int tripCounter = 0; // static
    protected String activities[]; // array
    protected int Capacity;

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

    public void getTripDetails() {

    }

    public Trip() {
    }

    public Trip(String tripId, String title, String tripType, double initPrice, String startDate, String endDate,
            String Description, TourGuide TourGuide, int Capacity,
            String activities[], Hotels Hotel, Transportation transportation, Ticket[] ticket) {
        this.tripId = tripId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripType = tripType;
        this.title = title;
        this.Description = Description;
        this.Hotel = Hotel;
        this.initPrice = initPrice;
        this.transportation = transportation;
        this.ticket = ticket;
        this.TourGuide = TourGuide;
        tripCounter++;
        this.activities = activities;
        this.Capacity = Capacity;
    }


    public String getTripId() {
        return this.tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTripType() {
        return this.tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Hotels getHotel() {
        return this.Hotel;
    }

    public void setHotel(Hotels Hotel) {
        this.Hotel = Hotel;
    }

    public double getInitPrice() {
        return this.initPrice;
    }

    public void setInitPrice(double initPrice) {
        this.initPrice = initPrice;
    }

    public Transportation getTransportation() {
        return this.transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }


    public TourGuide getTourGuide() {
        return this.TourGuide;
    }

    public void setTourGuide(TourGuide TourGuide) {
        this.TourGuide = TourGuide;
    }


    public int getCapacity() {
        return this.Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public double TripPrice(float rate) {
        return initPrice + rate * initPrice;
    }

}