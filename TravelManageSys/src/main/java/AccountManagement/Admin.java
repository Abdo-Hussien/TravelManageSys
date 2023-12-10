package AccountManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import javax.sound.sampled.AudioFileFormat.Type;

import TravelManagement.TourGuide;
import TravelManagement.Trip;

public class Admin implements Administration {
    protected Scanner in = new Scanner(System.in);
    protected String input;
    protected char choice;
    protected boolean checked = false;
    public int index;
    protected Customers addAccount = new Customers();

    @Override
    public <T extends Personsinterface> void Manipulation(ArrayList<T> AllUsers, String type) {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t" + type.toUpperCase());
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
                    " 1-display all information about " + type.toUpperCase() + " \n 2-edit " + type.toUpperCase()
                            + " account \n 3-delete" + type.toUpperCase() + " account \n 4-add new" + type.toUpperCase()
                            + " account \n 5-sign out");
            choice = in.next().charAt(0);
            switch (choice) {
                case '1':
                    displayinfo(AllUsers, type);
                    break;
                case '2':
                    editInformations("new", AllUsers, type);
                    break;
                case '3':
                    DeleteUsers(AllUsers, "new", type);
                    break;
                case '4':
                    // AllUsers.add((Customers) create_acc("Customer"));
                    break;
                case '5':
                    System.exit(0);
                    break;

                default:
                    System.out.println("invalid input! please try again...");
                    // customerManipulation(AllCustomers);
                    break;
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4' || choice != '5');
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
                editInformations("null", AllUsers, type);
                break;
            case '2':
                // DeleteCustomer(AllUsers, "old");
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

    public <T extends Personsinterface> void editInformations(String status, ArrayList<T> AllUsers, String type) {
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
                    System.out.println("enter new password: ");
                    input = in.next();
                    AllUsers.get(index).setPassword(input);
                    System.out.println("password updated successfully");
                } else if (choice == '3') {
                    System.out.println("enter new firstname: ");
                    input = in.next();
                    AllUsers.get(index).setFirst_name(input);
                    System.out.println("firstname updated successfully");
                } else if (choice == '4') {
                    System.out.println("enter new lastname: ");
                    input = in.next();
                    AllUsers.get(index).setLast_name(input);
                    System.out.println("lastname updated successfully");
                } else if (choice == '5') {
                    System.out.println("enter new address: ");
                    in.nextLine();
                    input = in.nextLine();
                    AllUsers.get(index).setAddress(input);
                    System.out.println("address updated successfully");
                } else if (choice == '6') {
                    System.out.println("enter new phone number: ");
                    input = in.next();
                    AllUsers.get(index).setPhone_number(input);
                    System.out.println("phone number updated successfully");
                } else {
                    System.out.println("invalid input! please try again..");
                }
            }
            do {
                System.out.println("countinue edting?  'y/n' ");
                input = in.next();
                if (input.toLowerCase().equals("y")) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    editInformations("old", AllUsers, type);
                } else if (input.toLowerCase().equals("n")) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Manipulation(AllUsers, type);

                } else {
                    System.out.println("invalid input! please try again");
                }
            } while (input != "y" || input != "n");
        }

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

    }

    @Override
    public void tripsAvalability(ArrayList<Trip> AllTrip) {
        System.out.println("All available Trips!:");
        System.out.println("*****************************************");
        for (int i = 0; i < AllTrip.size(); i++) {
            System.out.println("Trip ID: " + AllTrip.get(i).getTripId());
            System.out.println("Trip Name: " + AllTrip.get(i).getTitle());
            System.out.println(
                    "Trip availability: " + AllTrip.get(i).getTicketCounter() + "/" + AllTrip.get(i).getCapacity());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        do {
            System.out.println("*****************************************");
            System.out.println("1-To show more details about tip");
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
                        tripsAvalability(AllTrip);
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
                tripsAvalability(AllTrip);
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

    public <T extends loginable> void login(ArrayList<T> allusers) {
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

    public <T extends loginable> void userMenu(ArrayList<T> users, String Account_Type) {

        while (true) {
            System.out.println("\nChoose an action you want to perfom: ");
            System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
            System.out.println("\n1.) Create account");
            System.out.println("\n2.) Login");
            System.out.println("\n.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.\n\n");
            String userInput = in.next();
            System.out.println("");

            if (userInput.equals("1")) {
                users.add((T) create_acc(Account_Type));
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
