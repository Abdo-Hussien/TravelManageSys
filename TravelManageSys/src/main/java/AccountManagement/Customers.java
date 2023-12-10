
package AccountManagement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TravelManagement.BookedTravels;

public class Customers extends Person implements Personsinterface {

    private CustomerBooking BookingManipulations = new CustomerBooking();
    private ArrayList<String> tripsHistory;

    public Customers(String account_id, String first_name, String last_name, String username, String password, int age,
            String gender, String address, String phone_number, ArrayList<BookedTravels> oldBookingTrips,
            ArrayList<String> tripHistory) {
        super(first_name, last_name, username, age, phone_number, address, password, gender, account_id);
        Matcher matcher = Pattern.compile("\\s*([\\s\\S\\dA-Za-z]*?)\\s*\\|\\s*")
                .matcher(address);
        if (matcher.find())
            streetAddress = matcher.group(1);
        if (matcher.find())
            stateAddress = matcher.group(1);
        if (matcher.find())
            zipAddress = matcher.group(1);
        this.BookingManipulations.CustomerBookedTrips = oldBookingTrips;
        this.tripsHistory = tripHistory;
    }

    public Customers() {
    }

    public int getTripHistoryCounter() {
        return tripsHistory.size();
    }

    public void settripHistory(ArrayList<BookedTravels> bookedTravels) {
        for (int i = 0; i < bookedTravels.size(); i++) {
            tripsHistory.add(bookedTravels.get(i).getTripId());
        }
    }

}
