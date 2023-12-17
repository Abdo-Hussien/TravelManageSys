/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
    int ans;

    public TravelItineraries() {
    }

    public void dashboard(Customers customer, ArrayList<Trip> allTrips) {
        System.out.printf("%s\n",
                "~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~~-~-~-~-~-~-~-~-~-~-~-~");
        System.out.printf("%-40sDashboard\n", "");
        System.out.printf("%s\n",
                "~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~~-~-~-~-~-~-~-~-~-~-~-~");
        if (customer.getCustomerBookedTripsCount() == 0) {
            System.out.println("Your cart is empty!");
            System.out.println();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            System.out.println();
        } else {
            customer.displayBookedTrips(allTrips);
            do {
                System.out.println("1- Check out.\n" +
                        "2- Show details.\n" +
                        "3- reschedule trip.\n" +
                        "4- Cancel trip.\n" +
                        "5- Go back.\n");
                choice = in.next().charAt(0);
                in.nextLine();
                if (choice == '1') {
                    checkOut(customer);
                    break;
                } else if (choice == '2') {
                    showDetails(getCustomerBookedTripsDetails(allTrips, customer));
                } else if (choice == '3') {
                    if (reschedule(customer, allTrips))
                        dashboard(customer, allTrips);
                    break;
                } else if (choice == '4') {
                    cancelTrip(allTrips, customer);
                    dashboard(customer, allTrips);
                    break;
                } else if (choice == '5') {
                    return;
                } else {
                    System.out.println("wrong input! please try again");
                }
            } while (choice != '1' || choice != '2' || choice != '3' || choice != '4'||choice != '5');

        }
    }

    public void checkOut(Customers customer) {
        customer.setTripHistory(customer.getCustomerBookedTrips());
        customer.setCustomerBookedTrips(new ArrayList<>());
        customer.getCustomerBookedTrips().stream().forEach(bookedtrip -> {
            bookedtrip.getBookedticket().stream().forEach(ticket -> ticket.setCounter());
        });
        // write in file
        System.out.println(
                "Congratulations! You've successfully booked your trips. Get ready for unforgettable adventures!");
        pause(1200);
    }

    public void showDetails(ArrayList<Trip> AllTrip) {
        System.out.println("Enter the trip ID you want to show details about");
        input = in.next();
        in.nextLine();
        Trip trip = Trip.getTrip(AllTrip, input);
        if (trip == null) {
            System.out.println("Invalid Trip ID!\n");
            return;
        }
        trip.displayTripDetails();
    }

    private ArrayList<Trip> getCustomerBookedTripsDetails(ArrayList<Trip> allTrips, Customers customer) {
        ArrayList<Trip> customerBookedTripsDetails = new ArrayList<>();
        customer.getCustomerBookedTrips().stream().forEach(bookedtrip -> {
            customerBookedTripsDetails.add(Trip.getTrip(allTrips, bookedtrip.getTripID()));
        });
        return customerBookedTripsDetails;
    }

    public boolean reschedule(Customers customer, ArrayList<Trip> AllTrip) {
        System.out.print("Enter the trip ID you want to reschedule: ");
        input = in.next();
        in.nextLine();
        Trip trip = Trip.getTrip(getCustomerBookedTripsDetails(AllTrip, customer), input);
        if (trip == null) {
            System.out.println("Invalid Trip ID!\n");
            return false;
        }
        index = AllTrip.indexOf(trip);
        for (int i = 0; i < customer.getCustomerBookedTrips().size(); i++)
            if (customer.getCustomerBookedTrips().get(i).getTripID().equals(trip.getTripId()))
                BookedTravelsindex = i;
        return assignNewDate(trip, customer);
    }

    private boolean assignNewDate(Trip trip, Customers customer) {
        Date[] AvailableStartDates = trip.getStartDates();
        Date[] AvailableEndDates = trip.getEndDates();
        if (AvailableStartDates.length < 2) {
            System.out.println("There is no more available dates that the one you chose!");
            pause(1000);
            return true;
        }
        System.out.println(trip.getTitle());
        System.out.println("Available dates:\n");
        System.out.printf("%-6s| %-29s| %-12s%n", "Index", "Start Dates", "End Dates");
        System.out.println("------|------------------------------|-----------------------------");
        for (int key = 0; key < AvailableStartDates.length; key++) {
            System.out.printf("%-6d| %-12s | %-12s%n", key + 1, AvailableStartDates[key], AvailableEndDates[key]);
        }
        System.out.print("Choose which date: ");
        ans = in.nextInt();
        in.nextLine();
        if (ans >= AvailableStartDates.length) {
            System.out.println("Input isn't included in the choices, please choose from the following dates\n");
            pause(1000);
            assignNewDate(trip, customer);
            return true;
        }
        if (AvailableStartDates[ans - 1]
                .equals(customer.getCustomerBookedTrips().get(BookedTravelsindex).getStartDate()))
            System.out.println("You already have this date active!");
        else {
            customer.getCustomerBookedTrips().get(BookedTravelsindex).setStartDate(AvailableStartDates[ans - 1]);
            customer.getCustomerBookedTrips().get(BookedTravelsindex).setEndDate(AvailableEndDates[ans - 1]);
            System.out.println("Trip rescheduled successfully!");
        }
        pause(1000);
        return true;
    }

    public void pause(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public void cancelTrip(ArrayList<Trip> allTrips, Customers customer) {
        System.out.println("Enter the trip ID you want to cancel it: ");
        input = in.next();
        in.nextLine();
        int[] TicketsCounter = { 0 };
        Trip trip = Trip.getTrip(getCustomerBookedTripsDetails(allTrips, customer), input);
        if (trip == null) {
            System.out.println("Invalid Trip ID!\n");
            return;
        }
        int tripindex = allTrips.indexOf(trip);

        for (int i = 0; i < customer.getCustomerBookedTrips().size(); i++) {
            if (customer.getCustomerBookedTrips().get(i).getTripID().equals(input)) {
                checked = true;
                customer.getCustomerBookedTrips().get(i).getBookedticket().forEach(ticket -> {
                    TicketsCounter[0] += ticket.getCounter();
                });
                index = i;
            }
        }
        if (checked) {
            allTrips.get(tripindex).setTicketCounter(-TicketsCounter[0]);
            customer.getCustomerBookedTrips().remove(index);
            System.out.println("Trip cancelled successfully.");

            return;
        } else {
            System.out.println("Invalid Trip ID.");
            pause(1000);
        }
    }

}
