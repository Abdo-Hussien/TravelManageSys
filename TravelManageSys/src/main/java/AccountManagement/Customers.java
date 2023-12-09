
package AccountManagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TravelManagement.BookedTravels;
import TravelManagement.TourGuide;


//contains method for genrating a random ID

public class Customers extends Person {

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
    // login into account
    public void login(ArrayList<Customers> allCustomers) {

        int counter = 0;
        boolean checked = false;
        Scanner in = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("");
        System.out.println("Login");
        System.out.println("");
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("\n");
        while (counter != 3) {
            System.out.println("Enter your username : ");
            System.out.println("");
            String userName = in.next();

            System.out.println("\n");
            System.out.println("--------------------------------------");
            System.out.println("\n");

            System.out.println("Enter your password : ");
            System.out.println("");
            String pass = in.next();
            System.out.println("\n");
            System.out.println("--------------------------------------");
            System.out.println("\n");

            for (int i = 0; i < allCustomers.size(); i++) {
                if (allCustomers.get(i).username.equals(userName)) {
                    if (allCustomers.get(i).password.equals(pass)) {
                        index = i;
                        checked = true;
                    }
                }
            }
            if (checked == false) {
                counter++;
                System.out.println("you have " + counter + "/3 attempts left");
            } else if (checked == true) {
                System.out.println("login successful!");
                break;
                // main
            }
            if (counter == 3) {
                System.out.println("unfortunately you can't login...you have been timed out temporarily!");
                System.exit(0);
            }
        }
    }

    // user interface menu
    public void userMenu(ArrayList<Customers> AllCustomers) {

        while (true) {
            System.out.println("\nChoose an action you want to perfom: ");
            System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
            System.out.println("\n1.) Create account");
            System.out.println("\n2.) Login");
            System.out.println("\n3.) Edit account");
            System.out.println("\n.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.\n\n");
            String userInput = scanner.next();
            System.out.println("");

            if (userInput.equals("1")) {
                Admin admin = new Admin();
                AllCustomers.add((Customers) admin.create_acc("Customer"));
                break;
            }

            if (userInput.equals("2")) {
                login(AllCustomers);
                break;
            }

            if (userInput.equals("3")) {
                Admin admin = new Admin();
                admin.editCustomerInformations("null", AllCustomers);

                break;

            }

            else {
                System.out.println("Invalid input! please choose only from the following options.");
                continue;
            }

        }

    }

}
