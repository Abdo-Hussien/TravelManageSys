
package AccountManagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TravelManagement.BookedTravels;
import TravelManagement.TourGuide;

//contains method for genrating a random ID

public class Customers extends Person implements Personsinterface {

    private CustomerBooking BookingManipulations = new CustomerBooking();

    Scanner scanner = new Scanner(System.in);
    Person u;
    // keeps track of user's trip history
    public ArrayList<String> tripsHistory = new ArrayList<>();
    private ArrayList<BookedTravels> BookedTravels = new ArrayList<>();
    // user address attributes
    Matcher matcher = null;
    boolean logged_in = false; // used in edit account for user
    int index;

    public Customers(String account_id, String first_name, String last_name, String username, String password, int age,
            String gender, String address, String phone_number, ArrayList<BookedTravels> oldBookingTrips,
            ArrayList<String> tripHistory) {
        super(first_name, last_name, username, age, phone_number, address, password, gender, account_id);
        matcher = Pattern.compile("\\s*([\\s\\S]*?)\\s*\\|\\s*([\\s\\S]*?)\\s*\\|\\s*([\\s\\S]*?)\\s*\\|")
                .matcher(address);
        if (matcher.find()) {
            streetAddress = matcher.group(1);
            stateAddress = matcher.group(2);
            zipAddress = matcher.group(3);
        }
        this.BookingManipulations.CustomerBookedTrips = oldBookingTrips;
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
