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

    public Hotels(String Hotelname, double rate, int numofRooms,
            boolean booking, boolean swimmingpool, boolean restaurant) {
        this.Hotelname = Hotelname;
        this.rate = rate;
        this.numofRooms = numofRooms;
        this.booking = booking;
        this.swimmingpool = swimmingpool;
        this.restaurant = restaurant;

    }

}
