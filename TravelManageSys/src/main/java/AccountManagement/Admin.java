package AccountManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import TravelManagement.TourGuide;
import data.fileManipulation;

public class Admin implements Administration {
    protected Scanner in = new Scanner(System.in);
    protected String input;
    protected char choice;
    protected boolean checked = false;
    protected ArrayList<Customers> AllCustomers = new ArrayList<>(fileManipulation.getAllCustomers());
    protected ArrayList<TourGuide> AllTourGuide = new ArrayList<>(fileManipulation.getAllTourGuides());
    protected AdminFunctions functions = new AdminFunctions();
    protected Customers addAccount = new Customers();

    @Override
    public void customerManipulation() {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t       CUSTOMERS");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        for (int i = 0; i < AllCustomers.size(); i++) {
            System.out.println(AllCustomers.get(i).account_id);
            System.out.println(AllCustomers.get(i).first_name + " " + AllCustomers.get(i).last_name);
            System.out.println(AllCustomers.get(i).username);
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
                    functions.displayAllTourGuideinfo();
                    break;
                case '2':
                    functions.editCustomerInformations("new");
                    break;
                case '3':
                    functions.DeleteCustomer();
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
    public void tourGuideManipulation() {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t       TOUR GUIDES");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        for (int i = 0; i < AllTourGuide.size(); i++) {
            System.out.println(AllTourGuide.get(i).account_id);
            System.out.println(AllTourGuide.get(i).first_name + " " + AllCustomers.get(i).last_name);
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
                    functions.displayAllCustomersinfo();
                    break;
                case '2':
                    functions.editTourguideInformations("new");
                    break;
                case '3':
                    functions.DeleteTourGuide();
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
                    tourGuideManipulation();
                    break;
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4' || choice != '5');
    }

    @Override
    public void tripsAvalability() {
        // tripsAvalability
    }

    @Override
    public void customerDiscounts() {
        // customerDiscounts
    }

}
