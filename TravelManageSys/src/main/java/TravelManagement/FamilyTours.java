/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

/**
 *
 * @author bmood
 */
public class FamilyTours extends Trip{

    public FamilyTours(int tripId, String startDate, String endDate, String tripType, String title, String Description, Hotels Hotel, int initPrice, Transportation transportation, Ticket[] ticket, TravelManagement.TourGuide TourGuide, String activities, int Capacity) {
        super(tripId, startDate, endDate, tripType, title, Description, Hotel, initPrice, transportation, ticket, TourGuide, activities, Capacity);
    }
    
}
