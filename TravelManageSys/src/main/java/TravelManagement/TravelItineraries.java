/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.Attr;

import AccountManagement.Customers;

/**
 *
 * @author bmood
 */
public class TravelItineraries {
    int index;
    int BookedTravelsindex;
    protected Scanner in = new Scanner(System.in);
    protected String input;
    protected char choice;
    protected boolean checked = false;

    public void dashboard(ArrayList<BookedTravels> bookedTravel, ArrayList<Trip> AllTrip) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Dashboard");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("you booked: ");
        for (int i = 0; i < bookedTravel.size(); i++) {
            System.out.println("Trip name: " + bookedTravel.get(i).getTripName());
            System.out.println("Trip ID: " + bookedTravel.get(i).getTripId());
            // System.out.println("Trip will start at: " +
            // bookedTravel.get(i).getStartDate());
            // System.out.println("Trip will end at : " + bookedTravel.get(i).getEnDate());
            System.out.println("your tickets:");
            for (int j = 0; j < bookedTravel.get(i).Bookedticket.size(); j++) {
                System.out.println(bookedTravel.get(i).Bookedticket.get(j).getCounter() + " "
                        + bookedTravel.get(i).Bookedticket.get(j).getType());
            }
        }
        do {
            System.out.println("******************************");
            System.out.println("1-check out" +
                    "2-show details" +
                    "3-reschedul trip" +
                    "4-cancel trip");
            choice = in.next().charAt(0);
            if (choice == '1') {
                checkOut(bookedTravel, null);
            } else if (choice == '2') {
                showDetails(AllTrip);
            } else if (choice == '3') {
                reschedul(bookedTravel, AllTrip);
            } else if (choice == '4') {
                DelteTrip(bookedTravel);
            } else {
                System.out.println("wrong input! please try again");
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4');

    }

    public void checkOut(ArrayList<BookedTravels> bookedTravel, ArrayList<Customers> AllCustomers) {
        AllCustomers.get(index).settripHistory(bookedTravel);
        // write in file
    }

    public void showDetails(ArrayList<Trip> AllTrip) {
        System.out.println("Enter the trip ID you want to show details about");
        input = in.next();
        Trip trip = AllTrip.get(0).getTrip(AllTrip, input);
        trip.displayTripDetails(trip);
    }

    public void reschedul(ArrayList<BookedTravels> bookedTravel, ArrayList<Trip> AllTrip) {
        System.out.println("Enter the trip ID you want to reschedul it: ");
        input = in.next();
        for (int i = 0; i < AllTrip.size(); i++) {
            if (AllTrip.get(i).getTripId().equals(input)) {
                checked = true;
                index = i;
            }
        }
        for (int i = 0; i < bookedTravel.size(); i++) {
            for (int j = 0; j < AllTrip.size(); j++) {
                if (bookedTravel.get(i).getTripId().equals(AllTrip.get(j).getTripId())) {
                    BookedTravelsindex = i;
                }
            }
        }
        if (checked == true) {
            System.out.println(AllTrip.get(index).getTitle());
            System.out.println("Available dates:");
            System.out.println("Start dates: " + AllTrip.get(index).getStartDates());
            System.out.println("End dates: " + AllTrip.get(index).getEndDates());
            System.out.println("Enter start date: ");
            input = in.next();
            bookedTravel.get(BookedTravelsindex).setStartDate(null);
            System.out.println("Enter End date: ");
            input = in.next();
            bookedTravel.get(BookedTravelsindex).setEnDate(null);
        }
        if (checked == false) {
            System.out.println("invalid ID enterd.");
        }
    }

    public void DelteTrip(ArrayList<BookedTravels> bookedTravel) {
        System.out.println("Enter the trip ID you want to reschedul it: ");
        input = in.next();
        for (int i = 0; i < bookedTravel.size(); i++) {
            if (bookedTravel.get(i).getTripId().equals(input)) {
                checked = true;
                index = i;
            }
        }
        if (checked) {
            bookedTravel.remove(index);
            System.out.println("Trip cancelled successfully.");
            // main
        } else {
            System.out.println("invalid ID enterd.");
        }
    }

}
