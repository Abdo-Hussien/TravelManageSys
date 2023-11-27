/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

/**
 *
 * @author bmood
 */
public class CoupleTours extends Trip{

    public CoupleTours() {
        super();
    }

    public CoupleTours(String tripId, String title, String tripType, double initPrice, String startDate,
            String endDate,
            String Description, TourGuide TourGuide, int Capacity,
            String activities[], Hotels Hotel, Transportation transportation, Ticket[] ticket) {
        super(tripId, title, tripType, initPrice, startDate, endDate, Description, TourGuide, Capacity, activities,
                Hotel, transportation, ticket);
    } 
    
}
