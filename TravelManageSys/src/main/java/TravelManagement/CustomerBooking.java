/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.Date;
import java.io.FileWriter;
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
    // private List<Trip> tripsList = Trip.getAllTrips();
    private int TripCounter;
    private int DiffTripCounter;
    // Filters
    private double price_start;
    private double price_end;
    private String search_text;
    private String start_date;
    private String end_date;

    // private List<Trip> filteredTrips = new ArrayList<>();

    // Return Type should be: List<Trip>
    public void getFilteredTrips(String search_filter) {
        String[] Filters = search_filter.split("/");
        setFilters(Filters);
        // Iterate through the trips and filter based on the search_text

        // Add this argument at the first when merging: trip,
        // for (Trip trip : tripsList) {
        if (tripSearch(search_text, start_date, end_date, price_start, price_end)) {
            // filteredTrips.add(trip);
            System.out.println("Trip Displayed Successfully");
        } else
            System.out.println("Trip wasn't displayed");
        // }
        // return filteredTrips;
        
        // FileWriter file = new FileWriter("TravelManageSys/src/data/customer_{id}.txt", true);

        // Checkout no cancellation - Save in a file.
        // Booking Save in runtime variables (Array List) - Not save in a file.
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
    private boolean tripSearch(String search_filter) {
        if (search_filter == null)
            return true;
        String desc = new String(
                "an Egyptian town in the Suez Governorate, lying on the western shore of the Red Sea's Gulf of Suez. Being a year-round sunshine destination with gentle waves of the clear Red Sea makes Al-Ain al-Sokhna an ideal destination to break from the city's congestion.");
        return "Al-Ain Al-Sokhna".toLowerCase().contains(search_filter.toLowerCase()) // trip.getDestination()
                || "Ain Sokhna".toLowerCase().contains(search_filter.toLowerCase()) // trip.getTripName()
                || desc.toLowerCase().contains(search_filter.toLowerCase()) // trip.getDescription()
                || "General Tour".toLowerCase().contains(search_filter.toLowerCase()); // trip.getType()
    }

    private boolean tripSearch(String search_start_date, String search_end_date) {
        if (search_start_date == null || search_end_date == null)
            return true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date start_date = dateFormat.parse(search_start_date);
            Date end_date = dateFormat.parse(search_end_date);
            Date tripStartDate = dateFormat.parse("27-11-2023"); // trip.getStartDate()
            Date tripEndDate = dateFormat.parse("29-11-2023"); // trip.getEndDate()
            // To reverse this conversion, use this method
            // String DateToString = dateFormat.format(date);
            return (tripStartDate.after(start_date) || tripStartDate.equals(start_date))
                    && (tripEndDate.before(end_date) || tripEndDate.equals(end_date));
        } catch (Exception e) {
            return false;
        }

    }

    private boolean tripSearch(double price_start, double price_end) {
        double tripPrice = 2849.99; // trip.getPrice()
        return tripPrice >= price_start && (price_end > 0 && price_end > price_start) ? tripPrice <= price_end : true;
    }

    // Add this argument at the first when merging: trip,
    private boolean tripSearch(String search_filter, String start_date, String end_date, double price_start,
            double price_end) {
        return tripSearch(price_start, price_end) && tripSearch(start_date, end_date) && tripSearch(search_filter);
    }

}
