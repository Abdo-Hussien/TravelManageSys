package AccountManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import TravelManagement.TourGuide;
import TravelManagement.Trip;

public class Admin implements Administration {
    protected Scanner in = new Scanner(System.in);
    protected String input;
    protected char choice;
    protected boolean checked = false;
    int index;
    protected Customers addAccount = new Customers();

    @Override
    public void customerManipulation(ArrayList<Customers> AllCustomers) {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t       CUSTOMERS");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        for (int i = 0; i < AllCustomers.size(); i++) {
            System.out.println(AllCustomers.get(i).account_id);
            System.out.println(AllCustomers.get(i).first_name + " " + AllCustomers.get(i).last_name);
            System.out.println(AllCustomers.get(i).username);
            if (AllCustomers.get(i).getTripHistoryCounter() > 2) {
                System.out.println("Discount status: True");
            } else {
                System.out.println("Discount status: False");
            }

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        System.out.println("total number of customers: " + AllCustomers.size());
        do {
            System.out.println("chooce your operation:");
            System.out.println(
                    "1- display all information about customer \n 2-edit customer account \n 3- delete customer account \n 4-add new customer account \n 5-sing out");
            choice = in.next().charAt(0);
            switch (choice) {
                case '1':
                    displayAllCustomersinfo(AllCustomers);
                    break;
                case '2':
                    editCustomerInformations("new", AllCustomers);
                    break;
                case '3':
                    DeleteCustomer(AllCustomers);
                    break;
                case '4':
                    try {
                        addAccount.create_acc();
                    } catch (IOException e) {
                        System.out.println("invaild path");
                    }
                    break;
                case '5':
                    // sign out
                    break;

                default:
                    System.out.println("invalid input! please try again...");
                    break;
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4' || choice != '5');

        System.out.println("Please enter the customer id: ");
        input = in.next();

    }

    public void displayAllCustomersinfo(ArrayList<Customers> AllCustomers) {
        System.out.println("Please enter the customer id: ");
        input = in.next();
        for (int i = 0; i < AllCustomers.size(); i++) {
            if (AllCustomers.get(i).account_id.equals(input)) {
                System.out.println("id: " + AllCustomers.get(i).account_id);
                System.out.println("full name: " + AllCustomers.get(i).first_name + " "
                        + AllCustomers.get(i).last_name);
                System.out.println("age: " + AllCustomers.get(i).age);
                System.out.println("username: " + AllCustomers.get(i).username);
                System.out.println("password: " + AllCustomers.get(i).password);
                System.out.println("address: " + AllCustomers.get(i).address);
                System.out.println("phone number: " + AllCustomers.get(i).phone_number);
                index = i;
            }
        }
        System.out.println("1-Edit" +
                "2-delete" +
                "3-Go back");
        choice = in.next().charAt(0);
        switch (choice) {
            case '1':
                editCustomerInformations("null", AllCustomers);
                break;
            case '2':
                DeleteCustomer(AllCustomers);
                break;
            case '3':
                customerManipulation(AllCustomers);
                break;
            default:
                System.out.println("wrong input! please try again");
                displayAllCustomersinfo(AllCustomers);
                break;
        }

    }

    public void editCustomerInformations(String status, ArrayList<Customers> AllCustomers) {
        if (status.equals("new")) {

            displayAllCustomersinfo(AllCustomers);
        }

        System.out.println("what filed you want to edit: " +
                "1-username" +
                "2-password" +
                "3-firstname" +
                "4-lastname" +
                "5-address" +
                "6-phone number");
        choice = in.next().charAt(0);
        if (choice == '1') {
            System.out.println("enter new username: ");
            input = in.next();
            AllCustomers.get(index).username = input;
            System.out.println("username updated successfully");
        } else if (choice == '2') {
            System.out.println("enter old password: ");
            input = in.next();
            if (AllCustomers.get(index).password.equals(input)) {
                System.out.println("enter new password: ");
                input = in.next();
                AllCustomers.get(index).password = input;
                System.out.println("password updated successfully");
            } else {
                System.out.println("wrong password please try again");
            }
        } else if (choice == '3') {
            System.out.println("enter new firstname: ");
            input = in.next();
            AllCustomers.get(index).first_name = input;
            System.out.println("firstname updated successfully");
        } else if (choice == '4') {
            System.out.println("enter new lastname: ");
            input = in.next();
            AllCustomers.get(index).last_name = input;
            System.out.println("lastname updated successfully");
        } else if (choice == '5') {
            System.out.println("enter new address: ");
            in.nextLine();
            input = in.nextLine();
            AllCustomers.get(index).address = input;
            System.out.println("address updated successfully");
        } else if (choice == '6') {
            System.out.println("enter new phone number: ");
            input = in.next();
            AllCustomers.get(index).phone_number = input;
            System.out.println("phone number updated successfully");
        } else {
            System.out.println("invalid input! please try again..");

        }
        editCustomerInformations("null", AllCustomers);
    }

    public void DeleteCustomer(ArrayList<Customers> AllCustomers) {
        System.out.println("Please enter the customer id you want to delete: ");
        input = in.next();
        for (int i = 0; i < AllCustomers.size(); i++) {
            if (AllCustomers.get(i).account_id.equals(input)) {
                checked = true;
                index = i;
            }
        }
        if (checked) {
            AllCustomers.remove(index);
            System.out.println("Account removed successfully!");
        }
        if (checked == false) {
            System.out.println("Wrong input! Please try again..");
            DeleteCustomer(AllCustomers);
        }

    }

    @Override
    public void tourGuideManipulation(ArrayList<TourGuide> AllTourGuide) {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t       TOUR GUIDES");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        for (int i = 0; i < AllTourGuide.size(); i++) {
            System.out.println(AllTourGuide.get(i).account_id);
            System.out.println(AllTourGuide.get(i).first_name + " " + AllTourGuide.get(i).last_name);
            System.out.println(AllTourGuide.get(i).username);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        System.out.println("total number of TOUR GUIDES: " + AllTourGuide.size());
        do {
            System.out.println("chooce your operation:");
            System.out.println(
                    "1- display all information about Tour guide \n 2-edit Tour guide account \n 3- delete Tour guide account \n 4-add new Tour guide account \n 5-sign out");
            choice = in.next().charAt(0);
            switch (choice) {
                case '1':
                    displayAllTourGuideinfo(AllTourGuide);
                    break;
                case '2':
                    editTourguideInformations("new", AllTourGuide);
                    break;
                case '3':
                    DeleteTourGuide(AllTourGuide);
                    break;
                case '4':
                    try {
                        addAccount.create_acc();
                    } catch (IOException e) {
                        System.out.println("invaild path");
                    }
                    break;
                case '5':
                    // sign out
                    break;

                default:
                    System.out.println("invalid input! please try again...");
                    tourGuideManipulation(AllTourGuide);
                    break;
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4' || choice != '5');
    }

    public void displayAllTourGuideinfo(ArrayList<TourGuide> AllTourGuide) {
        System.out.println("Please enter the Tour guide id: ");
        input = in.next();
        for (int i = 0; i < AllTourGuide.size(); i++) {
            if (AllTourGuide.get(i).account_id.equals(input)) {
                System.out.println("id: " + AllTourGuide.get(i).account_id);
                System.out.println("full name: " + AllTourGuide.get(i).first_name + " "
                        + AllTourGuide.get(i).last_name);
                System.out.println("age: " + AllTourGuide.get(i).age);
                System.out.println("username: " + AllTourGuide.get(i).username);
                System.out.println("password: " + AllTourGuide.get(i).password);
                System.out.println("address: " + AllTourGuide.get(i).address);
                System.out.println("phone number: " + AllTourGuide.get(i).phone_number);
                index = i;
            }
        }
        System.out.println("1-Edit" +
                "2-delete" +
                "3-Go back");
        choice = in.next().charAt(0);
        switch (choice) {
            case '1':
                editTourguideInformations("null", AllTourGuide);
                break;
            case '2':
                DeleteTourGuide(AllTourGuide);
                break;
            case '3':
                tourGuideManipulation(AllTourGuide);
                break;
            default:
                System.out.println("wrong input! please try again");
                displayAllTourGuideinfo(AllTourGuide);
                break;
        }
    }

    public void editTourguideInformations(String status, ArrayList<TourGuide> AllTourGuide) {
        if (status.equals("new")) {

            displayAllTourGuideinfo(AllTourGuide);
        }

        System.out.println("what filed you want to edit: " +
                "1-username" +
                "2-password" +
                "3-firstname" +
                "4-lastname" +
                "5-address" +
                "6-phone number");
        choice = in.next().charAt(0);
        if (choice == '1') {
            System.out.println("enter new username: ");
            input = in.next();
            AllTourGuide.get(index).username = input;
            System.out.println("username updated successfully");
        } else if (choice == '2') {
            System.out.println("enter old password: ");
            input = in.next();
            if (AllTourGuide.get(index).password.equals(input)) {
                System.out.println("enter new password: ");
                input = in.next();
                AllTourGuide.get(index).password = input;
                System.out.println("password updated successfully");
            }
        } else if (choice == '3') {
            System.out.println("enter new firstname: ");
            input = in.next();
            AllTourGuide.get(index).first_name = input;
            System.out.println("firstname updated successfully");
        } else if (choice == '4') {
            System.out.println("enter new lastname: ");
            input = in.next();
            AllTourGuide.get(index).last_name = input;
            System.out.println("lastname updated successfully");
        } else if (choice == '5') {
            System.out.println("enter new address: ");
            in.nextLine();
            input = in.nextLine();
            AllTourGuide.get(index).address = input;
            System.out.println("address updated successfully");
        } else if (choice == '6') {
            System.out.println("enter new phone number: ");
            input = in.next();
            AllTourGuide.get(index).phone_number = input;
            System.out.println("phone number updated successfully");
        } else {
            System.out.println("invalid input! please try again..");
        }
        editTourguideInformations("null", AllTourGuide);
    }

    public void DeleteTourGuide(ArrayList<TourGuide> AllTourGuide) {
        System.out.println("Please enter the Tour guide id you want to delete: ");
        input = in.next();
        for (int i = 0; i < AllTourGuide.size(); i++) {
            if (AllTourGuide.get(i).account_id.equals(input)) {
                checked = true;
                index = i;
            }
        }
        if (checked) {
            AllTourGuide.remove(index);
            System.out.println("Account removed successfully!");
        }
        if (checked == false) {
            System.out.println("Wrong input! Please try again..");
            DeleteTourGuide(AllTourGuide);
        }

    }

    @Override
    public void tripsAvalability(ArrayList<Trip> AllTrip) {
        System.out.println("All available Trips!:");
        System.out.println("*****************************************");
        for (int i = 0; i < AllTrip.size(); i++) {
            System.out.println("Trip ID: " + AllTrip.get(i).getTripId());
            System.out.println("Trip Name: " + AllTrip.get(i).getTitle());
            System.out.println("Trip capacity: " + AllTrip.get(i).getCapacity());
            // availbaltiy ticket variable here
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        while (choice != '1' || choice != '2') {
            System.out.println("*****************************************");
            System.out.println("1-To show more details about tip");
            System.out.println("2-To get back");
            choice = in.next().charAt(0);
            if (choice == '1') {
                while (checked == true) {
                    System.out.println("Enter The trip ID:");
                    input = in.next();
                    Trip trip = AllTrip.get(0).getTrip(AllTrip, input);
                    trip.displayTripDetails(trip);
                    if (checked == false) {
                        System.out.println("invalid input! please try again");
                    }
                }
            } else if (choice == '2') {
                // main menu
            } else {
                System.out.println("invaild input! please try again");
            }
        }
    }
}
