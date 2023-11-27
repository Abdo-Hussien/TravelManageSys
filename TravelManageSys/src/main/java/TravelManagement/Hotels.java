/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

/**
 *
 * @author bmood
 */
public class Hotels {

public String Hotelname;
boolean booking;
double rate;
boolean swimmingpool;
boolean restaurant;
int numofRooms;

    public Hotels(String Hotelname, boolean booking, double rate, boolean swimmingpool, boolean restaurant, int numofRooms) {
        this.Hotelname = Hotelname;
        this.booking = booking;
        this.rate = rate;
        this.swimmingpool = swimmingpool;
        this.restaurant = restaurant;
        this.numofRooms = numofRooms;
    }


    
}
