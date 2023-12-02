/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.asu.main;

import java.util.ArrayList;

import AccountManagement.Customers;
import TravelManagement.BookedTravels;
import TravelManagement.BookingTickets;
import TravelManagement.ChosenTrip;
import TravelManagement.FamilyTours;
import TravelManagement.TravelItineraries;
import TravelManagement.Trip;
import data.fileManipulation;

/**
 *
 * @author bmood
 */
public class TravelManageSys {

    public static void main(String[] args) {
        System.out.println("Main Class is called 'TravelManageSys' is in the 'main' package");
        ArrayList<BookedTravels> bookedTravels = new ArrayList<BookedTravels>();
        ChosenTrip chosenTrip = new ChosenTrip();
        ArrayList<Trip> trips = new ArrayList<Trip>(fileManipulation.getAllTrips());
        chosenTrip.setTripName("dahbah");
        chosenTrip.setTripId("1000");
        BookingTickets b = new BookingTickets();
        b.ticketMenu(bookedTravels, chosenTrip,);
        TravelItineraries t = new TravelItineraries();
        t.dashboard(bookedTravels, trips);

        // m7dsh ysheel el maaaaaaaaaaaiiiiiiiiiiiiiiinnnnnnnnnnnnnnnnn
    }
}