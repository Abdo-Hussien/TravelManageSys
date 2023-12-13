package AccountManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import javax.sound.sampled.AudioFileFormat.Type;

import TravelManagement.BookedTravels;
import TravelManagement.BookingTickets;
import TravelManagement.TourGuide;
import TravelManagement.Trip;

public class Admin implements Administration {
    protected Scanner in = new Scanner(System.in);
    protected String input;
    protected char choice;
    protected boolean checked = false;
    static public int index;
    protected Customers addAccount = new Customers();
    ArrayList<Customers> allCustomers = new ArrayList<Customers>();
    ArrayList<TourGuide> allTourGuides = new ArrayList<TourGuide>();
    ArrayList<Trip> allTrips = new ArrayList<Trip>();

    public void AdminMenu(ArrayList<Customers> allCustomers, ArrayList<TourGuide> allTourGuides,
            ArrayList<Trip> allTrips) {
        this.allCustomers = allCustomers;
        this.allTourGuides = allTourGuides;
        this.allTrips = allTrips;
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t          ADMIN");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        while (true) {
            System.out.println("1-Customer Management\n2-Tour guide Management\n3-Trip Management\n4-sign out");
            System.out.println("Enter your operation");
            choice = in.next().charAt(0);
            if (choice == '1') {
                Manipulation(allCustomers, "Customers");
                break;
            } else if (choice == '2') {
                Manipulation(allTourGuides, "Tour Guide");
                break;
            } else if (choice == '3') {
                tripsAvalability(allTrips, allCustomers, allTourGuides);
                break;
            } else if (choice == '4') {
                System.out.println("sign out successfully");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                System.out.println("Invalid input! please choose only from the following options.");
                continue;
            }
        }
    }

    @Override
    public <T extends Personsinterface> void Manipulation(ArrayList<T> AllUsers, String type) {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t\t" + type.toUpperCase());
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        for (int i = 0; i < AllUsers.size(); i++) {
            System.out.println(AllUsers.get(i).getAccount_id());
            System.out.println(AllUsers.get(i).getFirst_name() + " " + AllUsers.get(i).getLast_name());
            System.out.println(AllUsers.get(i).getUsername());
            if (type.equals("Customers")) {
                if (AllUsers.get(i).getTripHistoryCounter() > 2) {
                    System.out.println("Dicsount state:True");
                } else {
                    System.out.println("Dicsount state:False");
                }
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        System.out.println("total number of " + type.toUpperCase() + ":" + AllUsers.size());

        do {
            System.out.println();
            System.out.println("chooce your operation:");
            System.out.println();
            System.out.println(
                    " 1-display all information about " + type.toUpperCase() + " \n 2-edit " +
                            type.toUpperCase()
                            + " account \n 3-delete" + type.toUpperCase() + " account \n 4-add new" +
                            type.toUpperCase()
                            + " account \n 5-Go back");
            choice = in.next().charAt(0);
            if (choice == '1') {
                displayinfo(AllUsers, type);
            } else if (choice == '2') {
                editInformations("new", AllUsers, type, "Admin");
            } else if (choice == '3') {
                DeleteUsers(AllUsers, "new", type);
            } else if (choice == '4') {
                AllUsers.add((T) create_acc(type));
            } else if (choice == '5') {
                AdminMenu(allCustomers, allTourGuides, allTrips);
                return;
            } else {
                System.out.println("invalid input! please try again...");
                Manipulation(AllUsers, type);
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4' ||
                choice != '5');
    }

    public <T extends Personsinterface> void displayinfo(ArrayList<T> AllUsers, String type) {
        while (checked == false) {
            System.out.println("Please enter the " + type.toUpperCase() + " id: ");
            input = in.next();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < AllUsers.size(); i++) {
                if (AllUsers.get(i).getAccount_id().equals(input)) {
                    checked = true;
                    index = i;
                }
            }
            if (checked == true) {
                System.out.println("id: " + AllUsers.get(index).getAccount_id());
                System.out.println("full name: " + AllUsers.get(index).getFirst_name() + " "
                        + AllUsers.get(index).getLast_name());
                System.out.println("age: " + AllUsers.get(index).getAge());
                System.out.println("username: " + AllUsers.get(index).getUsername());
                System.out.println("password: " + AllUsers.get(index).getPassword());
                System.out.println("address: " + AllUsers.get(index).getAddress());
                System.out.println("phone number: " + AllUsers.get(index).getPhone_number());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else {
                System.out.println("invalid Customer ID entered! please try again");
            }
        }
        checked = false;
        System.out.println("1-Edit\n" +
                "2-delete\n" +
                "3-Go back\n");
        choice = in.next().charAt(0);
        switch (choice) {
            case '1':
                editInformations("null", AllUsers, type, "Admin");
                break;
            case '2':
                DeleteUsers(AllUsers, "old", "Customers");
                break;
            case '3':
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Manipulation(AllUsers, "Customers");
                break;
            default:
                System.out.println("wrong input! please try again");
                displayinfo(AllUsers, type);
                break;
        }
    }

    public <T extends Personsinterface> void editInformations(String status, ArrayList<T> AllUsers, String type,
            String callingfrom) {
        {
            if (status.equals("new")) {
                displayinfo(AllUsers, type);
            } else {
                System.out.println("what filed you want to edit: \n" +
                        "1-username\n" +
                        "2-password\n" +
                        "3-firstname\n" +
                        "4-lastname\n" +
                        "5-address\n" +
                        "6-phone number\n");
                choice = in.next().charAt(0);
                if (choice == '1') {
                    System.out.println("enter new username: ");
                    input = in.next();
                    AllUsers.get(index).setUsername(input);
                    System.out.println("username updated successfully");
                } else if (choice == '2') {
                    AllUsers.get(index).setPassword(Validations.PasswordValidation());
                    System.out.println("password updated successfully");
                } else if (choice == '3') {
                    AllUsers.get(index).setFirst_name(Validations.NameValidation(" new Firstname", 3, 14));
                    System.out.println("firstname updated successfully");
                } else if (choice == '4') {
                    AllUsers.get(index).setLast_name(Validations.NameValidation(" new Lastname", 3, 14));
                    System.out.println("lastname updated successfully");
                } else if (choice == '5') {
                    // AllUsers.get(index).setAddress(Validations.AddressValidation());
                    System.out.println("address updated successfully");
                } else if (choice == '6') {
                    AllUsers.get(index).setPhone_number(Validations.PhoneValidation());
                    System.out.println("phone number updated successfully");
                } else {
                    System.out.println("invalid input! please try again..");
                }
            }
            do {
                System.out.println("countinue edting? 'y/n' ");
                input = in.next();
                if (input.toLowerCase().equals("y")) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (callingfrom.equals("Admin")) {
                        editInformations("old", AllUsers, type, "Admin");
                    } else if (callingfrom.equals("Customer")) {
                        editInformations("old", AllUsers, type, "Customer");
                    }
                } else if (input.toLowerCase().equals("n")) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (callingfrom.equals("Admin")) {
                        Manipulation(AllUsers, type);
                    } else if (callingfrom.equals("Customer")) {
                        Customers.showinfo(index, (ArrayList<Customers>) AllUsers, allTrips, null, this);
                    }

                } else {
                    System.out.println("invalid input! please try again");
                }
            } while (input != "y" || input != "n");
        }
        return;

    }

    public <T extends Personsinterface> void DeleteUsers(ArrayList<T> AllUsers, String status, String type) {
        if (status == "new") {
            System.out.println("Please enter the " + type.toUpperCase() + " id you want to delete: ");
            input = in.next();
            for (int i = 0; i < AllUsers.size(); i++) {
                if (AllUsers.get(i).getAccount_id().equals(input)) {
                    checked = true;
                    index = i;
                }
            }
            if (checked == true) {
                AllUsers.remove(index);
                System.out.println("Account removed successfully!");
                checked = false;
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Manipulation(AllUsers, type);

            }
            if (checked == false) {
                System.out.println("Wrong input! Please try again..");
                DeleteUsers(AllUsers, "new", type);
            }
        } else {
            AllUsers.remove(index);
            System.out.println("Account removed successfully!");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Manipulation(AllUsers, type);
        }
        return;

    }

    @Override
    public void tripsAvalability(ArrayList<Trip> AllTrip, ArrayList<Customers> customers,
            ArrayList<TourGuide> tourGuide) {
        System.out.println("All available Trips!:");
        System.out.println("*****************************************");
        for (int i = 0; i < AllTrip.size(); i++) {
            System.out.println("Trip ID: " + AllTrip.get(i).getTripId());
            System.out.println("Trip Name: " + AllTrip.get(i).getTitle());
            System.out.println(
                    "Trip availability: " + AllTrip.get(i).getTicketCounter() + "/" + AllTrip.get(i).getCapacity());
            System.out.println("available: " + (AllTrip.get(i).getCapacity() - AllTrip.get(i).getTicketCounter()));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        do {
            System.out.println("*****************************************");
            System.out.println("1-To show more details about trip");
            System.out.println("2-To get back");
            choice = in.next().charAt(0);
            if (choice == '1') {
                // do {
                System.out.println("Enter The trip ID:");
                input = in.next();
                Trip trip = AllTrip.get(0).getTrip(AllTrip, input);
                trip.displayTripDetails(trip);
                do {
                    System.out.println("Go back y/n");
                    input = in.next();
                    if (input.toLowerCase().equals("y")) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tripsAvalability(AllTrip, customers, tourGuide);
                    } else if (input.toLowerCase().equals("n")) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        trip.displayTripDetails(trip);

                    } else {
                        System.out.println("wrong input!please try agin.");
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } while (!input.toLowerCase().equals("y") || !input.toLowerCase().equals("n"));
                // if (checked == false) {
                // System.out.println("invalid input! please try again");
                // }
                // } while (checked == true);
            } else if (choice == '2') {
                AdminMenu(customers, tourGuide, AllTrip);
                break;
            } else {
                System.out.println("invaild input! please try again");
            }
        } while (choice != '1' || choice != '2');
    }

    public Person create_acc(String Account_Type) {
        Person person = null;
        if (Account_Type.equals("TourGuide"))
            person = new TourGuide();
        else if (Account_Type.equals("Customer"))
            person = new Customers();
        System.out.println("\n~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("\t\tCreate Account ");
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\n");
        person.first_name = Validations.NameValidation("First Name", 3, 14);
        System.out.println("--------------------------------------");
        person.last_name = Validations.NameValidation("Last Name", 3, 14);
        System.out.println("--------------------------------------");
        person.username = Validations.NameValidation("Username", 7, 14);
        System.out.println("--------------------------------------");
        person.phone_number = Validations.PhoneValidation();
        System.out.println("--------------------------------------");
        person.age = Validations.AgeValidation();
        System.out.println("--------------------------------------");
        person.gender = Validations.GenderValidation();
        System.out.println("--------------------------------------");
        String[] address = Validations.AddressValidation();
        person.streetAddress = address[0];
        person.stateAddress = address[1];
        person.zipAddress = address[2];
        System.out.println("--------------------------------------");
        person.password = Validations.PasswordValidation();
        System.out.println("--------------------------------------");
        RandIDGenerator generator = new RandIDGenerator();
        generator.setItemCount(5);
        generator.generateRandID();
        person.account_id = generator.getRandID();
        System.out.println("Successfully created the account: " + person.username);
        return person;
    }

    public <T extends Personsinterface> void login(ArrayList<T> allusers) {
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

            for (int i = 0; i < allusers.size(); i++) {
                T customer = allusers.get(i);
                if (customer.getUsername().equals(userName)) {
                    if (customer.getPassword().equals(pass)) {
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

    public <T extends Personsinterface> void userMenu(ArrayList<T> users, String Account_Type) {

        while (true) {
            System.out.println("\nChoose an action you want to perfom: ");
            System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
            System.out.println("\n1.) Create account.");
            System.out.println("\n2.) Login.");
            System.out.println("\n.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.\n\n");
            String userInput = in.next();
            System.out.println("");

            if (userInput.equals("1")) {
                users.add((T) create_acc(Account_Type));
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                login(users);
                break;
            }

            else if (userInput.equals("2")) {
                login(users);
                break;
            }

            else {
                System.out.println("Invalid input! please choose only from the following options.");
                continue;
            }

        }

    }

}
