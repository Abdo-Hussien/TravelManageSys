package AccountManagement;

import java.util.ArrayList;
import java.util.Scanner;
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
                Manipulation(allCustomers, "Customers");
                break;
            } else if (choice == '2') {
                Manipulation(allTourGuides, "Tour Guide");
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
                break;
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
        String text = type.toLowerCase().equals("customers") ? "a customer"
                : "a tourguide";
        do {
            System.out.println("\nChoose your operation: \n");
            System.out.println(
                    "1- Display all information about " + text + " \n2- Edit " + text + " Account \n3- Delete "
                            + text + " account \n4- Add " + text + " account \n5-Go back");
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
        String text = type.toLowerCase().equals("customers") ? "the customer's"
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
        else if (callingfrom.equals("Customer"))
            Customers.showinfo(index, (ArrayList<Customers>) AllUsers, allTrips, null, this);
        return;
    }

    public <T extends Personsinterface> void DeleteUsers(ArrayList<T> AllUsers, String status, String type) {
        if (status == "new") {
            String text = type.toLowerCase().equals("customers") ? "a customer"
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
        System.out.println("All available Trips!:");
        System.out.println("*****************************************");
        for (int i = 0; i < AllTrip.size(); i++) {
            System.out.printf("Trip ID: %-5s | Trip Name: %-25s | Availability: %d/%d  ->(Remaining: %d)\n",
                    AllTrip.get(i).getTripId(),
                    AllTrip.get(i).getTitle(),
                    AllTrip.get(i).getTicketCounter(),
                    AllTrip.get(i).getCapacity(),
                    AllTrip.get(i).getCapacity() - AllTrip.get(i).getTicketCounter());

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
