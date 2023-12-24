package AccountManagement;

import java.util.ArrayList;
import java.util.Scanner;

import TravelManagement.BookedTravels;
import TravelManagement.BookingTickets;
import TravelManagement.TourGuide;
import TravelManagement.Trip;
import data.fileManipulation;

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
                fileManipulation.writeTourguides(allTourGuides);
                fileManipulation.writeCustomers(allCustomers);
                fileManipulation.writeTrips(allTrips);
                System.out.println("Sign out successfully");
                pause(300);
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
                if (DeleteUsers(AllUsers, "new", type))
                    return;
                else
                    continue;
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
                if (try_again())
                    continue;
                else {
                    Manipulation(AllUsers, type);
                    return;
                }
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
                if (DeleteUsers(AllUsers, "old", "Customer"))
                    break;
                else
                    Manipulation(AllUsers, type);
            case '3':
                pause(300);
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
                } else
                    System.out.println("Invalid input! please try again..");
            }
            if (!try_again())
                break;
        } while (true);
        if (callingfrom.equals("Admin"))
            Manipulation(AllUsers, type);
        else if (callingfrom.equals("Customer")) {
            Customers current_customer = (Customers) AllUsers.get(index);
            current_customer.showinfo((ArrayList<Customers>) AllUsers, allTrips);
        } else if (callingfrom.equals("Tourguide")) {
            TourGuide current_TourGuide = (TourGuide) AllUsers.get(index);
            current_TourGuide.showinfo((ArrayList<TourGuide>) AllUsers, allTrips);
        }
    }

    public <T extends Personsinterface> boolean DeleteUsers(ArrayList<T> AllUsers, String status, String type) {
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
                    pause(300);
                    Manipulation(AllUsers, type);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Invalid Customer ID entered!");
                    if (try_again())
                        continue;
                    else
                        return false;
                }
                break;
            }
        } else {
            AllUsers.remove(index);
            System.out.println("Account removed successfully!");
            pause(300);
            Manipulation(AllUsers, type);
        }
        return true;
    }

    @Override
    public void tripsAvalability(ArrayList<Trip> AllTrip, ArrayList<Customers> customers,
            ArrayList<TourGuide> tourGuide) {
        Trip.displayAdminTrips(AllTrip, customers);
        System.out.println("*****************************************");
        System.out.println("1- Show more details about trip.");
        System.out.println("2- Go back.");
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
                pause(500);
                tripsAvalability(AllTrip, customers, tourGuide);
                return;
            }
            trip = AllTrip.get(tripindex);
            trip.displayTripDetails();
            System.out.println("Press any key (followed by Enter key) to go back...");
            input = in.next();
            in.nextLine();
        } else if (choice == '2') {
            AdminMenu(customers, tourGuide, AllTrip);
            return;
        } else {
            System.out.println("invaild input! please try again");
            pause(300);
        }
        tripsAvalability(AllTrip, customers, tourGuide);
    }

    public Person create_acc(String Account_Type) {
        Person person = null;
        // checks if the user is a customer or a tour guide
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
        int attempts = 5;
        T user = null;
        System.out.println("\n~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("\t\tLogin");
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\n");
        while (attempts != 0) {
            System.out.print("Enter your username: ");
            String userName = in.next();
            in.nextLine();
            System.out.println("--------------------------------------");
            System.out.print("Enter your password: ");
            String pass = in.next();
            in.nextLine();
            System.out.println("");
            user = CheckCredentials(allusers, userName, pass);
            if (user == null) {
                attempts--;
                System.out.println("You have " + attempts + " attempts left...");
                if (attempts != 0) {
                    if (!try_again())
                        return null;
                }
            } else
                return user;
            System.out.println("--------------------------------------");

            if (attempts == 0) {
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
        Person current_person = null;
        System.out.println("Choose an action you want to perfom.");
        System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
        System.out.println("1.) Create account.");
        System.out.println("2.) Login.");
        System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
        String userInput = in.next();
        in.nextLine();
        if (userInput.equals("1")) {
            current_person = (Person) create_acc(Account_Type);
            index = users.indexOf(current_person);
            users.add((T) current_person);
            pause(300);
            return checkLogin(current_person, users, Account_Type);
        } else if (userInput.equals("2")) {
            return checkLogin(current_person, users, Account_Type);
        } else {
            System.out.println("Invalid input! please choose only from the following options.");
            if (try_again())
                return userMenu(users, Account_Type);
            else
                return null;
        }
    }

    public <T extends Personsinterface> T checkLogin(Person current_person, ArrayList<T> users, String Account_Type) {
        current_person = (Person) login(users);
        if (current_person == null)
            return userMenu(users, Account_Type);
        index = users.indexOf(current_person);
        return (T) current_person;
    }

    private boolean try_again() {
        System.out.println("Do you want to try again? (y/n)");
        choice = in.next().toLowerCase().charAt(0);
        switch (choice) {
            case 'y':
                return true;
            case 'n':
                return false;
            default:
                System.out.println("Invalid Input..");
                pause(500);
                return try_again();
        }
    }

    public void pause(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

}
