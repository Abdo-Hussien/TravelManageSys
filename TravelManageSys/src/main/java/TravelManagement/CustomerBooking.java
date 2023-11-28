/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import AccountManagement.Customers;
import AccountManagement.Person;
import data.fileManipulation;

import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bmood
 */
public class CustomerBooking {
    private ArrayList<Trip> tripsList = new ArrayList<>();
    private ArrayList<Trip> filteredTrips = new ArrayList<>();

    private BookedTravels[] BookedTrips; // [ tripID, tripName, tripDate, noTickets, typeTic ]
    private String[] TravelHistory; // Only Save ID's of TravelHistory

    // Filter Search Preferences
    private double price_start;
    private double price_end;
    private String search_text;
    private String start_date;
    private String end_date;

    public void mainCustomer() {
        Scanner input = new Scanner(System.in);
        tripsList = fileManipulation.getAllTrips();
        Trip.displayTrips(tripsList);
        char Ans;
        System.out.println("\n\nA. Search for a trip(s)");
        System.out.println("B. Book a trip");
        System.out.println("C. See details of a trip");
        Ans = input.next().charAt(0);
        Ans = Character.toLowerCase(Ans);
        if (Ans == 'a') {
            System.out.println("\nExample: TripName/StartDate/EndDate...");
            System.out.print("Search for a trip: ");
            this.getFilteredTrips(input.nextLine());
        } else if (Ans == 'b') {
            System.out.println("\nWhich trip do you want to book?\t(Use the ID)");
            System.out.print("Trip ID: ");
            int tripID = input.nextInt();
        }
        input.close();
    }

    public ArrayList<Trip> getFilteredTrips(String search_filter) {
        String[] Filters = search_filter.split("/");
        // Split Filters.
        setFilters(Filters);

        // Iterate through the trips and filter based on the search_text
        for (Trip trip : tripsList) {
            try {
                if (tripSearch(trip, search_text, start_date, end_date, price_start, price_end)) {
                    filteredTrips.add(trip);
                } else {
                    System.out.println(trip.getTitle() + " Trip wasn't displayed");
                }
            } catch (ParseException e) {
                System.out.println(
                        "Error in filtering..\nCaused by Invalid Date Parsing.\nProper Date Format: dd-mm-yyyy\n\n");
                mainCustomer();
            }
        }
        return filteredTrips;
        // Checkout no cancellation - Save in a file.
        // Booking Save in runtime variables (Array List) - Not save in a file.
    }

    private void setFilters(String[] Filters) {
        for (int i = 0; i < Filters.length; i++) {
            if (Filters[i].matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
                start_date = Filters[i];
                end_date = Filters[i + 1];
                i += 1;
                continue;
            }
            if (Filters[i].matches("\\d+(\\.\\d+)?")) {
                try {
                    price_start = Double.parseDouble(Filters[i]);
                    price_end = Double.parseDouble(Filters[i + 1]);
                    i += 1;
                    continue;
                } catch (Exception e) {
                    System.out.println("Process <Parsing> -> Error(s)\nParsing 'price_start', 'price_end'");
                    continue;
                }
            }
            search_text = Filters[i];
        }
    }

    private boolean tripSearch(Trip trip, String search_filter) {
        if (search_filter == null)
            return true;
        return trip.getTitle().toLowerCase().contains(search_filter.toLowerCase())
                || trip.getDescription().toLowerCase().contains(search_filter.toLowerCase())
                || trip.getTripType().toLowerCase().contains(search_filter.toLowerCase());
    }

    private boolean tripSearch(Trip trip, String search_start_date, String search_end_date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date start_date = dateFormat.parse(search_start_date);
        Date end_date = dateFormat.parse(search_end_date);
        Date tripStartDates[] = trip.getStartDate();
        Date tripEndDates[] = trip.getEndDate();
        Boolean FoundDate = false;

        if (search_start_date == null || search_end_date == null)
            return true;
        for (Date s_date : tripStartDates) {
            FoundDate = s_date.after(start_date) || s_date.equals(start_date);
        }
        for (Date e_date : tripEndDates) {
            FoundDate = e_date.after(end_date) || e_date.equals(end_date);
        }
        return FoundDate;
    }

    private boolean tripSearch(Trip trip, double price_start, double price_end) {
        double tripPrice = trip.getInitPrice();
        return tripPrice >= price_start && (price_end > 0 && price_end > price_start) ? tripPrice <= price_end : true;
    }

    private boolean tripSearch(Trip trip, String search_filter, String start_date, String end_date, double price_start,
            double price_end) throws ParseException {
        return tripSearch(trip, price_start, price_end) && tripSearch(trip, start_date, end_date)
                && tripSearch(trip, search_filter);
    }

}
