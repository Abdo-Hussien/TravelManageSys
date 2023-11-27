/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bmood
 */
public class CustomerBooking {
    private ArrayList<Trip> tripsList = getAllTrips();
    private int TripCounter;
    private int DiffTripCounter;
    // Filters
    private double price_start;
    private double price_end;
    private String search_text;
    private String start_date;
    private String end_date;

    private ArrayList<Trip> filteredTrips = new ArrayList<>();

    public List<Trip> getFilteredTrips(String search_filter) {
        String[] Filters = search_filter.split("/");
        // Split Filters.
        setFilters(Filters);

        // Iterate through the trips and filter based on the search_text
        for (Trip trip : tripsList) {
            if (tripSearch(trip, search_text, start_date, end_date, price_start, price_end)) {
                // filteredTrips.add(trip);
                System.out.println("Trip Displayed Successfully");
            } else
                System.out.println("Trip wasn't displayed");
        }
        return filteredTrips;
        // Checkout no cancellation - Save in a file.
        // Booking Save in runtime variables (Array List) - Not save in a file.
    }

    private ArrayList<Trip> getAllTrips() {

        try {
            Path tripsFile = Path.of("TravelManageSys/src/data/trips.txt");
            Trip trip = parseTrip(Files.readString(tripsFile));
            if (trip != null)
                tripsList.add(trip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tripsList;
    }

    private Trip parseTrip(String line) {
        String[] parts = line.split("\n");

        // Invalid format, return null or handle appropriately
        // if (parts.length != {Number of Lines for each Trip}) {
        // return null;
        // }

        try {
            String id = parts[0];
            String tripName = parts[1];
            String type = parts[2];
            double price = Double.parseDouble(parts[3]);
            String startDate = parts[4];
            String endDate = parts[5];
            String description = parts[6];
            int capacity = Integer.parseInt(parts[7]);
            // Create a Trip object based on the type
            switch (type) {
                case "GeneralTour":
                    return new GeneralTours(id, tripName, type, price, startDate, endDate, description, null,
                            capacity, null, null, null, null);
                case "FamilyTour":
                    return new FamilyTours(id, tripName, type, price, startDate, endDate, description, null,
                            capacity, null, null, null, null);
                case "CoupleTour":
                    return new CoupleTours(id, tripName, type, price, startDate, endDate, description, null,
                            capacity, null, null, null, null);
                default:
                    // Handle unknown type or return null
                    return null;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., log or throw
            return null;
        }
    }

    private void setFilters(String[] Filters) {
        for (int i = 0; i < Filters.length; i++) {
            if (Filters[i].matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
                start_date = Filters[i];
                end_date = Filters[i + 1];
                // System.out.println(start_date + '\n' + end_date);
                i += 1;
                continue;
            }
            if (Filters[i].matches("\\d+(\\.\\d+)?")) {
                try {
                    price_start = Double.parseDouble(Filters[i]);
                    price_end = Double.parseDouble(Filters[i + 1]);
                    i += 1;
                    // System.out.println(price_start + "\n" + price_end);
                    continue;
                } catch (Exception e) {
                    // System.out.println(price_start + "\n" + price_end);
                    continue;
                }

            }
            search_text = Filters[i];
            // System.out.println(search_text);
        }
    }

    // Add this parameter for each overload at the first when merging: Trip trip
    private boolean tripSearch(Trip trip, String search_filter) {
        if (search_filter == null)
            return true;
        // Destination: Al-Ain
        // Al-Sokhna.toLowerCase().contains(search_filter.toLowerCase())
        // String desc = new String(
        // "an Egyptian town in the Suez Governorate, lying on the western shore of the
        // Red Sea's Gulf of Suez. Being a year-round sunshine destination with gentle
        // waves of the clear Red Sea makes Al-Ain al-Sokhna an ideal destination to
        // break from the city's congestion.");
        return trip.getTitle().toLowerCase().contains(search_filter.toLowerCase()) // "Ain Sokhna"
                || trip.getDescription().toLowerCase().contains(search_filter.toLowerCase()) // desc
                || trip.getTripType().toLowerCase().contains(search_filter.toLowerCase()); // "General Tour"
    }

    private boolean tripSearch(Trip trip, String search_start_date, String search_end_date) {
        if (search_start_date == null || search_end_date == null)
            return true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date start_date = dateFormat.parse(search_start_date);
            Date end_date = dateFormat.parse(search_end_date);
            Date tripStartDate = dateFormat.parse(trip.getStartDate()); // "27-11-2023"
            Date tripEndDate = dateFormat.parse(trip.getEndDate()); // "29-11-2023"
            // To reverse this conversion, use this method
            // String DateToString = dateFormat.format(date);
            return (tripStartDate.after(start_date) || tripStartDate.equals(start_date))
                    && (tripEndDate.before(end_date) || tripEndDate.equals(end_date));
        } catch (Exception e) {
            return false;
        }

    }

    private boolean tripSearch(Trip trip, double price_start, double price_end) {
        double tripPrice = trip.getInitPrice(); // trip.getPrice() 2849.99
        return tripPrice >= price_start && (price_end > 0 && price_end > price_start) ? tripPrice <= price_end : true;
    }

    private boolean tripSearch(Trip trip, String search_filter, String start_date, String end_date, double price_start,
            double price_end) {
        return tripSearch(trip, price_start, price_end) && tripSearch(trip, start_date, end_date)
                && tripSearch(trip, search_filter);
    }

}
