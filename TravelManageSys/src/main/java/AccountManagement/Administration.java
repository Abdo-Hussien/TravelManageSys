/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AccountManagement;

import java.util.ArrayList;

import TravelManagement.TourGuide;
import TravelManagement.Trip;

/**
 *
 * @author bmood
 */
public interface Administration {

    <T extends Personsinterface> void Manipulation(ArrayList<T> AllUsers, String type);

    public void tripsAvalability(ArrayList<Trip> AllTrip, ArrayList<Customers> customers,
            ArrayList<TourGuide> tourGuide);

}
