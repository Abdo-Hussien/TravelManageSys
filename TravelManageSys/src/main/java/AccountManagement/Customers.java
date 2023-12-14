
package AccountManagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TravelManagement.BookedTravels;
import TravelManagement.BookingTickets;
import TravelManagement.TravelItineraries;
import TravelManagement.Trip;

public class Customers extends Person implements Personsinterface {

    private static CustomerBooking BookingManipulations;
    private ArrayList<String> tripsHistory;
    static int choice;
    protected static Scanner in = new Scanner(System.in);

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
        BookingManipulations = new CustomerBooking();
        Customers.BookingManipulations.CustomerBookedTrips = oldBookingTrips;
        this.tripsHistory = tripHistory;
    }

    public Customers() {
        tripsHistory = new ArrayList<>();
        BookingManipulations = new CustomerBooking();
    }

    public int getTripHistoryCounter() {
        return tripsHistory.isEmpty() ? 0 : tripsHistory.size();
    }

    public void settripHistory(ArrayList<BookedTravels> bookedTravels) {
        for (int i = 0; i < bookedTravels.size(); i++) {
            tripsHistory.add(bookedTravels.get(i).getTripId());
        }
    }

    public static void showinfo(int index, ArrayList<Customers> allCustomers, ArrayList<Trip> allTrips,
            BookingTickets bookingTickets, Admin edit) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Your information:");
        System.out.println("id: " + allCustomers.get(index).getAccount_id());
        System.out.println("full name: " + allCustomers.get(index).getFirst_name() + " "
                + allCustomers.get(index).getLast_name());
        System.out.println("age: " + allCustomers.get(index).getAge());
        System.out.println("username: " + allCustomers.get(index).getUsername());
        System.out.println("password: " + allCustomers.get(index).getPassword());
        System.out.println("address: " + allCustomers.get(index).getAddress());
        System.out.println("phone number: " + allCustomers.get(index).getPhone_number());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (true) {

            System.out.println("1-Edit your information\n2-Go back");
            int choice = in.next().charAt(0);
            switch (choice) {
                case '1':
                    Admin admin = new Admin();
                    admin.editInformations("old", allCustomers, "Customers", "Customer");
                    break;
                case '2':
                    UserMainMenu(allTrips, allCustomers, bookingTickets, edit, null, null);
                    break;

                default:
                    System.out.println("wrong input! please try again..");
                    continue;
            }
        }
    }

    public static void UserMainMenu(ArrayList<Trip> allTrips, ArrayList<Customers> allCustomers,
            BookingTickets bookingTickets, Admin edit, TravelItineraries dashboard, ArrayList<BookedTravels> booking) {
        while (true) {
            System.out.println("Welcome..!!");
            System.out.println("1- Profile settings.\n2- Trip.\n3- Cart.\n4- Sign out.");
            choice = in.nextInt();
            in.nextLine();
            if (choice == 1) {
                showinfo(Admin.index, allCustomers, allTrips, bookingTickets, edit);
            } else if (choice == 2) {
                booking = BookingManipulations.mainCustomer(allCustomers);
                dashboard.dashboard(booking, allTrips);
            } else if (choice == 3) {
                dashboard.dashboard(booking, allTrips);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                continue;
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid input! please choose only from the following options.");
                continue;
            }
        }
    }
}