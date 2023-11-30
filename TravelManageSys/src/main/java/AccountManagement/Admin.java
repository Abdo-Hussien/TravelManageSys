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
    protected AdminFunctions functions = new AdminFunctions();
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
                    functions.displayAllCustomersinfo(AllCustomers);
                    break;
                case '2':
                    functions.editCustomerInformations("new", AllCustomers);
                    break;
                case '3':
                    functions.DeleteCustomer(AllCustomers);
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
                    functions.displayAllTourGuideinfo(AllTourGuide);
                    break;
                case '2':
                    functions.editTourguideInformations("new", AllTourGuide);
                    break;
                case '3':
                    functions.DeleteTourGuide(AllTourGuide);
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
                    for (int i = 0; i < AllTrip.size(); i++) {
                        if (AllTrip.get(i).getTripId().equals(input)) {
                            Trip.displayTripDetails(null);
                            checked = true;
                        }
                    }
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
