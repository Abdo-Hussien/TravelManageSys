package AccountManagement;

import java.util.ArrayList;
import java.util.Scanner;

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
            System.out.println("1- Customer Management\n2- Tour guide Management\n3- Trip Management\n4- Sign out");
            System.out.print("Enter your operation: ");
            choice = in.next().charAt(0);
            if (choice == '1') {
                Manipulation(allCustomers, "Customer");
                break;
            } else if (choice == '2') {
                Manipulation(allTourGuides, "TourGuide");
                break;
            } else if (choice == '3') {
                tripsAvalability(allTrips, allCustomers, allTourGuides);
                break;
            } else if (choice == '4') {
                System.out.println("Sign out successfully");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            } else {
                System.out.println("Invalid input! please choose only from the following options.");
                continue;
            }
        }
    }

    @Override
    public <T extends Personsinterface> void Manipulation(ArrayList<T> AllUsers, String type) {
        System.out.println("\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t" + type.toUpperCase());
        System.out.println("\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        Person.DisplayAllUsers(AllUsers, type);
        String text = type.toLowerCase().equals("customer") ? "a customer"
                : "a tourguide";
        do {
            System.out.println("\nChoose your operation: \n");
            System.out.println(
                    "1- Display all information about " + text + " \n2- Edit " + text + " Account \n3- Delete "
                            + text + " account \n4- Add " + text + " account \n5- Go back");
            choice = in.next().charAt(0);
            if (choice == '1') {
                displayinfo(AllUsers, type);
                return;
            } else if (choice == '2') {
                editInformations("new", AllUsers, type, "Admin");
                return;
            } else if (choice == '3') {
                DeleteUsers(AllUsers, "new", type);
                return;
            } else if (choice == '4') {
                AllUsers.add((T) create_acc(type));
                Manipulation(AllUsers, type);
                return;
            } else if (choice == '5') {
                AdminMenu(allCustomers, allTourGuides, allTrips);
                return;
            } else {
                System.out.println("invalid input! please try again...");
                Manipulation(AllUsers, type);
                return;
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4' ||
                choice != '5');

    }

    public <T extends Personsinterface> void displayinfo(ArrayList<T> AllUsers, String type) {
        String text = type.toLowerCase().equals("customer") ? "the customer's"
                : "the tourguide's";
        while (true) {
            System.out.print("Use the index to display " + text + " details: ");
            input = in.next();
            in.nextLine();
            try {
                index = Integer.parseInt(input) - 1;
                Person.DisplayUserDetails(AllUsers.get(index));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid Customer ID entered! please try again");
                continue;
            }
            break;
        }
        System.out.println("1- Edit\n" +
                "2- Delete\n" +
                "3- Go back\n");
        choice = in.next().charAt(0);
        switch (choice) {
            case '1':
                editInformations("null", AllUsers, type, "Admin");
                break;
            case '2':
                DeleteUsers(AllUsers, "old", "Customer");
                break;
            case '3':
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Manipulation(AllUsers, "Customer");
                break;
            default:
                System.out.println("Wrong input! please try again");
                displayinfo(AllUsers, type);
                break;
        }
    }

    public <T extends Personsinterface> void editInformations(String status, ArrayList<T> AllUsers, String type,
            String callingfrom) {

        do {
            if (status.equals("new")) {
                displayinfo(AllUsers, type);
            } else {
                System.out.println("Choose what field to edit: \n" +
                        "1- Username\n" +
                        "2- Password\n" +
                        "3- First Name\n" +
                        "4- Last Name\n" +
                        "5- Address\n" +
                        "6- Phone number\n");
                choice = in.next().charAt(0);
                if (choice == '1') {
                    System.out.println("Enter new username: ");
                    AllUsers.get(index).setUsername(Validations.NameValidation("new Username", 8, 22));
                    System.out.println("Username updated successfully");
                } else if (choice == '2') {
                    AllUsers.get(index).setPassword(Validations.PasswordValidation());
                    System.out.println("Password updated successfully");
                } else if (choice == '3') {
                    AllUsers.get(index).setFirst_name(Validations.NameValidation("new Firstname", 3, 14));
                    System.out.println("First Name updated successfully");
                } else if (choice == '4') {
                    AllUsers.get(index).setLast_name(Validations.NameValidation("new Lastname", 3, 14));
                    System.out.println("Last Name updated successfully");
                } else if (choice == '5') {
                    String[] arrAddress = Validations.AddressValidation();
                    AllUsers.get(index).setStreetAddress(arrAddress[0]);
                    AllUsers.get(index).setStateAddress(arrAddress[1]);
                    AllUsers.get(index).setZipAddress(arrAddress[2]);
                    AllUsers.get(index).setAddress(arrAddress[0], arrAddress[1], arrAddress[2]);
                    System.out.println("Address updated successfully");
                } else if (choice == '6') {
                    AllUsers.get(index).setPhone_number(Validations.PhoneValidation());
                    System.out.println("Phone number updated successfully");
                } else {
                    System.out.println("Invalid input! please try again..");
                }
            }
            System.out.println("Countinue edting? 'y/n' ");
            input = in.next();
            in.nextLine();
        } while (input.equalsIgnoreCase("y"));
        if (callingfrom.equals("Admin"))
            Manipulation(AllUsers, type);
        else if (callingfrom.equals("Customer")) {
            Customers current_customer = (Customers) AllUsers.get(0);
            current_customer.showinfo(allCustomers, allTrips);
        }
        return;
    }

    public <T extends Personsinterface> void DeleteUsers(ArrayList<T> AllUsers, String status, String type) {
        if (status == "new") {
            String text = type.toLowerCase().equals("customer") ? "a customer"
                    : "a tourguide";
            while (true) {
                System.out.print("Use the index to delete " + text + ": ");
                input = in.next();
                in.nextLine();
                try {
                    index = Integer.parseInt(input) - 1;
                    AllUsers.remove(index);
                    System.out.println("Account removed successfully!");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Manipulation(AllUsers, type);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Invalid Customer ID entered! please try again");
                    continue;
                }
                break;
            }
        } else {
            AllUsers.remove(index);
            System.out.println("Account removed successfully!");
            try {
                Thread.sleep(300);
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
        Trip.displayAdminTrips(AllTrip);
        System.out.println("*****************************************");
        System.out.println("1-To show more details about trip");
        System.out.println("2-To get back");
        choice = in.next().charAt(0);
        Trip trip;
        int tripindex = 0;
        if (choice == '1') {
            System.out.println("Enter The trip ID:");
            input = in.next();
            in.nextLine();
            try {
                tripindex = Integer.parseInt(input) - 1000;
                if (tripindex > AllTrip.size() || tripindex < 0)
                    throw new IndexOutOfBoundsException("Invalid Trip ID! please try again..");
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                tripsAvalability(AllTrip, customers, tourGuide);
            }
            trip = AllTrip.get(tripindex);
            trip.displayTripDetails();
            System.out.println("Press any key (followed by Enter key) to go back...");
            input = in.next();
            in.nextLine();
            tripsAvalability(AllTrip, customers, tourGuide);
        } else if (choice == '2') {
            AdminMenu(customers, tourGuide, AllTrip);
            return;
        } else {
            System.out.println("invaild input! please try again");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tripsAvalability(AllTrip, customers, tourGuide);
        }
    }

    public Person create_acc(String Account_Type) {
        Person person = null;
        if (Account_Type.equalsIgnoreCase("TourGuide"))
            person = new TourGuide();
        else if (Account_Type.equalsIgnoreCase("Customer"))
            person = new Customers();
        person.CustomerBookedTickets = new BookingTickets();
        person.CustomerTravelHistory = new ArrayList<String>();
        person.CustomerBookedTrips = new ArrayList<BookedTravels>();
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

    public <T extends Personsinterface> T login(ArrayList<T> allusers) {
        int counter = 0;
        T user = null;
        System.out.println("\n~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("\t\tLogin");
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\n");
        while (counter != 3) {
            System.out.print("Enter your username: ");
            String userName = in.next();
            in.nextLine();
            System.out.println("--------------------------------------");
            System.out.print("Enter your password: ");
            String pass = in.next();
            in.nextLine();
            user = CheckCredentials(allusers, userName, pass);
            if (user == null) {
                counter++;
                System.out.println("You have " + counter + "/3 attempts left...");
            } else
                return user;
            System.out.println("--------------------------------------");

            if (counter == 3) {
                System.out.println("Unfortunately you can't login... you have been timed out temporarily!");
                System.exit(0);
                return null;
            }
        }
        return null;
    }

    public <T extends Personsinterface> T CheckCredentials(ArrayList<T> allusers, String username, String password) {
        T user = null;
        for (int userindex = 0; userindex < allusers.size(); userindex++) {
            user = allusers.get(userindex);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                index = userindex;
                break;
            } else
                index = -1;
        }
        if (index == -1) {
            return null;
        } else {
            System.out.println("Login successful!");
            return user;
        }
    }

    public <T extends Personsinterface> T userMenu(ArrayList<T> users, String Account_Type) {
        System.out.println("Choose an action you want to perfom.");
        System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
        System.out.println("1.) Create account.");
        System.out.println("2.) Login.");
        System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
        String userInput = in.next();
        in.nextLine();
        if (userInput.equals("1")) {
            users.add((T) create_acc(Account_Type));
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return login(users);
        } else if (userInput.equals("2"))
            return login(users);
        else {
            System.out.println("Invalid input! please choose only from the following options.");
            return userMenu(users, Account_Type);
        }
    }
}
