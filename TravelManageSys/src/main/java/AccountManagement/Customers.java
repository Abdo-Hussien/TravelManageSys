
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

    // create an account
    public Person create_acc(String object, ArrayList ArrayList) {
        System.out.println("\n");
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("");
        System.out.println("Create Account ");
        System.out.println("");
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("\n");
        if (object.equals("TourGuide")) {
            u = new TourGuide();
        } else if (object.equals("Customer")) {
            u = new Customers();
        }

        while (true) {

            System.out.println("Please enter your first name : ");
            System.out.println("");
            String f_name = scanner.next();
            System.out.println("");
            if (f_name.length() < 3) {
                System.out.println("Invalid !");
                continue;
            }

            if (f_name.length() > 14) {
                System.out.println("First name is too long.");
            }

            else {
                u.first_name = f_name;
                break;
            }

        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Please enter your last name : ");
            System.out.println("");
            String l_name = scanner.next();
            System.out.println("");
            if (l_name.length() < 3) {
                System.out.println("Invalid !");
                continue;
            }

            if (l_name.length() > 14) {
                System.out.println("Last name is too long.");
            }

            else {
                u.last_name = l_name;
                break;
            }
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Please enter a username for your account : ");
            System.out.println("");
            String u_name = scanner.next();
            System.out.println("");
            if (u_name.length() < 7) {
                System.out.println("Username is too short , it must be a minimum of 7 characters.");
                continue;
            }

            if (u_name.length() > 14) {
                System.out.println("Username is too long , it must be a maximum of 14 characters.");
            }

            else {
                u.username = u_name;
                break;
            }
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Enter your mobile phone number : ");
            System.out.println("the state code (+20) is added. ");
            System.out.println("");
            String phone_no = scanner.next();
            System.out.println("");
            if (phone_no.length() == 10) {
                u.phone_number = "+20" + phone_no;
                break;
            } else {
                System.out.println("Invalid phone number, the phone number should be 10 numbers !");
                continue;

            }
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Enter your age : ");
            System.out.println("");
            Integer Age = scanner.nextInt();
            System.out.println("");
            if (Age < 16) {
                System.out.println("Invalid age, the minimum age to create an account is 16 !");
                continue;
            } else {
                u.age = Age;
                break;
            }
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Choose your gender from the following (Male/Female) . ");
            System.out.println("");
            String Gender = scanner.next();
            System.out.println("");

            if (Gender.toLowerCase().equals("male") || Gender.toLowerCase().equals("female")) {
                u.gender = Gender;
                break;
            } else {
                System.out.println("Select from the following options only and try again.");
                continue;
            }

        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");
        scanner.nextLine();

        while (true) {
            System.out.println("Please enter your street address : ");
            System.out.println("");
            streetAddress = scanner.nextLine();
            System.out.println("");
            break;
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Please enter your state address : ");
            System.out.println("");
            stateAddress = scanner.nextLine();
            System.out.println("");
            break;
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Please enter your ZIP code address : ");
            System.out.println("");
            zipAddress = scanner.nextLine();
            System.out.println("");
            break;
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        u.address = streetAddress + " " + "|" + " " + stateAddress + " " + "|" + " " + zipAddress;

        System.out.println("Note : your password should be a minimum of 8 character & maximum of 16 ");
        while (true) {

            System.out.println("Create a strong password for your account : ");
            System.out.println("");
            String pass = scanner.next();
            System.out.println("");
            if (pass.length() < 8) {
                System.out.println("Weak password detected!");
                continue;
            }

            else if (pass.length() > 16) {
                System.out.println("Password is too long !");
                continue;
            } else {
                u.password = pass;
                System.out.println("Enter your password again to confirm : ");
                System.out.println("");
                String confirm_pass = scanner.next();
                System.out.println("");
                if (confirm_pass.equals(pass)) {
                    System.out.println("");
                    System.out.println("---------------------------------------");
                    System.out.println("Created account successfully.");
                    System.out.println("---------------------------------------");
                    System.out.println("");
                    break;
                }

                else {
                    System.out.println("Passwords don't match ,try again !");
                    continue;
                }

            }

        }

        // generates random ID for Customer
        RandIDGenerator generator = new RandIDGenerator();
        generator.setItemCount(5);
        generator.generateRandID();
        u.account_id = generator.getRandID();

        System.out.println("Successfully created the account : " + u.username);
        userMenu(ArrayList);
        return u;

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

            System.out.println(" ");
            System.out.println("Choose an action you want to perfom : ");
            System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
            System.out.println(" ");
            System.out.println("1.) Create account");
            System.out.println(" ");
            System.out.println("2.) Login");
            System.out.println(" ");
            System.out.println("3.) Edit account");
            System.out.println(" ");
            System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
            System.out.println("\n");
            String userInput = scanner.next();
            System.out.println("");

            if (userInput.equals("1")) {
                AllCustomers.add((Customers) create_acc("Customer", AllCustomers));
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
