package AccountManagement;

import java.util.ArrayList;
import java.util.Scanner;

import data.fileManipulation;

public class Admin implements Administration {
    protected Scanner scan = new Scanner(System.in);
    protected String input;
    protected char choice;
    protected ArrayList<Customers> AllCustomers = new ArrayList(fileManipulation.getAllCustomers());
    protected AdminFunctions functions = new AdminFunctions();

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
                    "1- display all information about customer \n 2-edit customer account \n 3- delete customer account \n 4-add new customer account");
            choice = scan.next().charAt(0);
            switch (choice) {
                case '1':
                    functions.displayAllCustomers();
                    break;
                case '2':
                    // edit customer information function
                    break;
                case '3':
                    // delete customer account function
                    break;
                case '4':
                    // add customer account function
                    break;

                default:
                    System.out.println("invalid input! please try again...");
                    break;
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4');

        System.out.println("Please enter the customer id: ");
        input = scan.next();

    }

    @Override
    public void tourGuideManipulation() {
        // tourGuideManipulation
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
