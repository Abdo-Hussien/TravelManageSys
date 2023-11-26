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
    protected int tripId;
    protected String startDate;
    protected String endDate;
    protected String tripType;
    protected String title;
    protected String Description;
    protected Hotels Hotel;
    protected int initPrice;
    protected Transportation transportation;
    protected Ticket ticket[];                //Should this attribute be array of 3 tickets? For each Trip there should be gold, silver and platinum tickets
    protected TourGuide TourGuide;
    protected static int tripCounter = 0;   //static
    protected String activities;           //array
    protected int Capacity;
    
    public void RelationshipChecker(String customerRelationship){
        switch (customerRelationship) {
            case "Single":
            case "single":
                System.out.println("All trips except couple tours using tripType attribute");
                break;
            case "Taken":
            case "taken":
                System.out.println("All trips including couple tours using tripType attribute");
                break;
            default:
                System.out.println("error..");
                break;
        }
    }
    
    public Trip(int tripId, String startDate, String endDate, String tripType, String title, String Description, Hotels Hotel, int initPrice, Transportation transportation, Ticket[] ticket, TourGuide TourGuide, String activities, int Capacity) {
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

    public int getCapacity() {
        return Capacity;
    }

//    public int getTripId() {
//        return tripId;
//    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTripType() {
        return tripType;
    }

    public String getTitle() {
        return title;
    }

    // Hotel name included
    
    public String getDescription() {
        return Description;
    }

    public int getInitPrice() {
        return initPrice;
    }

    //transportation type (flight or bus) included

    //ticket type & price included

    //tour Guide name included

    public static int getTripCounter() {
        return tripCounter;
    }

    public String getActivities() {
        return activities;
    }
    
    
    
}