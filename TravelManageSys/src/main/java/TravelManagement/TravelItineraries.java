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

    public TravelItineraries() {
    }

    public void dashboard(Customers customer, ArrayList<Trip> AllTrip) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Dashboard");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (customer.getCustomerBookedTrips().size() == 0) {
            System.out.println("Your cart is empty!");
            System.out.println();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            System.out.println();
        } else {
            System.out.println("you booked: ");
            for (int i = 0; i < customer.getCustomerBookedTrips().size(); i++) {
                System.out.println("Trip name: " + customer.getCustomerBookedTrips().get(i).getTripName());
                System.out.println("Trip ID: " + customer.getCustomerBookedTrips().get(i).getTripId());
                System.out.println("Trip will start at: " +
                        customer.getCustomerBookedTrips().get(i).getStartDate());
                System.out.println("Trip will end at : " + customer.getCustomerBookedTrips().get(i).getEndDate());
                System.out.println("your tickets:");
                for (int j = 0; j < customer.getCustomerBookedTrips().get(i).Bookedticket.size(); j++) {
                    System.out.println(customer.getCustomerBookedTrips().get(i).Bookedticket.get(j).getCounter() + " "
                            + customer.getCustomerBookedTrips().get(i).Bookedticket.get(j).getType());
                }
                System.out.println("total price for tickets: " + customer.getCustomerBookedTrips().get(i).getTotalPrice());
                // System.out.println(
                //         "Transportation by: " + AllTrip.get((Integer.parseInt(customer.getCustomerBookedTrips().get(i).getTripId()) - 1000))
                //                 .getTransportation(null, customer.getCustomerBookedTrips().get(i).getTripId()));
            }
            do {
                System.out.println("******************************");
                System.out.println("1-Check out\n" +
                        "2-Show details\n" +
                        "3-Reschedul trip\n" +
                        "4-Cancel trip\n" +
                        "5-Go back\n");
                choice = in.next().charAt(0);
                if (choice == '1') {
                    // checkOut(customer.getCustomerBookedTrips(), null);
                    break;
                } else if (choice == '2') {
                    showDetails(AllTrip);
                } else if (choice == '3') {
                    reschedule(customer, AllTrip);
                } else if (choice == '4') {
                    cancelTrip(customer);
                } else if (choice == '5') {
                    
                    break;
                } else {
                    System.out.println("wrong input! please try again");
                }
            } while (choice != '1' || choice != '2' || choice != '3' || choice != '4');

        }
    }

    public void checkOut(Customers customer) {
        customer.settripHistory(customer.getCustomerBookedTrips());
        // write in file
    }

    public void showDetails(ArrayList<Trip> AllTrip) {
        System.out.println("Enter the trip ID you want to show details about");
        input = in.next();
        Trip trip = AllTrip.get(0).getTrip(AllTrip, input);
        trip.displayTripDetails();
    }

    public void reschedule(Customers customer, ArrayList<Trip> AllTrip) {
        System.out.println("Enter the trip ID you want to reschedul it: ");
        input = in.next();
        for (int i = 0; i < AllTrip.size(); i++) {
            if (AllTrip.get(i).getTripId().equals(input)) {
                checked = true;
                index = i;
            }
        }
        for (int i = 0; i < customer.getCustomerBookedTrips().size(); i++) {
            for (int j = 0; j < AllTrip.size(); j++) {
                if (customer.getCustomerBookedTrips().get(i).getTripId().equals(AllTrip.get(j).getTripId())) {
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
            customer.getCustomerBookedTrips().get(BookedTravelsindex).setStartDate(null);
            System.out.println("Enter End date: ");
            input = in.next();
            customer.getCustomerBookedTrips().get(BookedTravelsindex).setEnDate(null);
        }
        if (checked == false) {
            System.out.println("invalid ID enterd.");
        }
    }

    public void cancelTrip(Customers customer) {
        System.out.println("Enter the trip ID you want to cancel it: ");
        input = in.next();
        for (int i = 0; i < customer.getCustomerBookedTrips().size(); i++) {
            if (customer.getCustomerBookedTrips().get(i).getTripId().equals(input)) {
                checked = true;
                index = i;
            }
        }
        if (checked) {
            customer.getCustomerBookedTrips().remove(index);
            System.out.println("Trip cancelled successfully.");
            // main
        } else {
            System.out.println("invalid ID enterd.");
        }
    }

}
