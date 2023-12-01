/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.Date;

/**
 *
 * @author bmood
 */
public class CoupleTours extends Trip {

    public CoupleTours() {
        super();
    }

    public CoupleTours(String tripId, String title, String tripType, double initPrice, Date[] startDate, Date[] endDate,
            String Description, String tourGuide, int Capacity, String activities[], String hotelName,
            String transportID) {
        super(tripId, title, tripType, initPrice, startDate, endDate, Description, tourGuide, Capacity, activities,
                hotelName, transportID);
    }

}
