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
                System.out.println("Dicsount state:True");
            } else {
                System.out.println("Dicsount state:False");
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        System.out.println("total number of CUSTOMERS: " + AllCustomers.size());
        do {
            System.out.println();
            System.out.println("chooce your operation:");
            System.out.println();
            System.out.println(
                    " 1-display all information about Customer \n 2-edit Customer account \n 3-delete Customer account \n 4-add new Customer account \n 5-sign out");
            choice = in.next().charAt(0);
            switch (choice) {
                case '1':
                    displayAllCustomersinfo(AllCustomers);
                    break;
                case '2':
                    editCustomerInformations("new", AllCustomers);
                    break;
                case '3':
                    DeleteCustomer(AllCustomers, "new");
                    break;
                case '4':
                    addAccount.create_acc("Customer", AllCustomers);
                    break;
                case '5':
                    System.exit(0);
                    break;

                default:
                    System.out.println("invalid input! please try again...");
                    customerManipulation(AllCustomers);
                    break;
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4' || choice != '5');
    }

    public void displayAllCustomersinfo(ArrayList<Customers> AllCustomers) {
        while (checked == false) {
            System.out.println("Please enter the Customer id: ");
            input = in.next();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < AllCustomers.size(); i++) {
                if (AllCustomers.get(i).account_id.equals(input)) {
                    checked = true;
                    index = i;
                }
            }
            if (checked == true) {
                System.out.println("id: " + AllCustomers.get(index).account_id);
                System.out.println("full name: " + AllCustomers.get(index).first_name + " "
                        + AllCustomers.get(index).last_name);
                System.out.println("age: " + AllCustomers.get(index).age);
                System.out.println("username: " + AllCustomers.get(index).username);
                System.out.println("password: " + AllCustomers.get(index).password);
                System.out.println("address: " + AllCustomers.get(index).address);
                System.out.println("phone number: " + AllCustomers.get(index).phone_number);
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
                editCustomerInformations("null", AllCustomers);
                break;
            case '2':
                DeleteCustomer(AllCustomers, "old");
                break;
            case '3':
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                AllCustomers.get(index).username = input;
                System.out.println("username updated successfully");
            } else if (choice == '2') {
                System.out.println("enter new password: ");
                input = in.next();
                AllCustomers.get(index).password = input;
                System.out.println("password updated successfully");
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
                editCustomerInformations("old", AllCustomers);
            } else if (input.toLowerCase().equals("n")) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customerManipulation(AllCustomers);

            } else {
                System.out.println("invalid input! please try again");
            }
        } while (input != "y" || input != "n");

    }

    public void DeleteCustomer(ArrayList<Customers> AllCustomers, String status) {
        if (status == "new") {
            System.out.println("Please enter the customer id you want to delete: ");
            input = in.next();
            for (int i = 0; i < AllCustomers.size(); i++) {
                if (AllCustomers.get(i).account_id.equals(input)) {
                    checked = true;
                    index = i;
                }
            }
            if (checked == true) {
                AllCustomers.remove(index);
                System.out.println("Account removed successfully!");
                checked = false;
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customerManipulation(AllCustomers);

            }
            if (checked == false) {
                System.out.println("Wrong input! Please try again..");
                DeleteCustomer(AllCustomers, "new");
            }
        } else {
            AllCustomers.remove(index);
            System.out.println("Account removed successfully!");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            customerManipulation(AllCustomers);
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
            System.out.println();
            System.out.println("chooce your operation:");
            System.out.println();
            System.out.println(
                    " 1-display all information about Tour guide \n 2-edit Tour guide account \n 3-delete Tour guide account \n 4-add new Tour guide account \n 5-sign out");
            choice = in.next().charAt(0);
            switch (choice) {
                case '1':
                    displayAllTourGuideinfo(AllTourGuide);
                    break;
                case '2':
                    editTourguideInformations("new", AllTourGuide);
                    break;
                case '3':
                    DeleteTourGuide(AllTourGuide, "new");
                    break;
                case '4':
                    addAccount.create_acc("TourGuide", AllTourGuide);
                    break;
                case '5':
                    System.exit(0);
                    break;

                default:
                    System.out.println("invalid input! please try again...");
                    tourGuideManipulation(AllTourGuide);
                    break;
            }
        } while (choice != '1' || choice != '2' || choice != '3' || choice != '4' || choice != '5');
    }

    public void displayAllTourGuideinfo(ArrayList<TourGuide> AllTourGuide) {
        while (checked == false) {
            System.out.println("Please enter the Tour guide id: ");
            input = in.next();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < AllTourGuide.size(); i++) {
                if (AllTourGuide.get(i).account_id.equals(input)) {
                    checked = true;
                    index = i;
                }
            }
            if (checked == true) {
                System.out.println("id: " + AllTourGuide.get(index).account_id);
                System.out.println("full name: " + AllTourGuide.get(index).first_name + " "
                        + AllTourGuide.get(index).last_name);
                System.out.println("age: " + AllTourGuide.get(index).age);
                System.out.println("username: " + AllTourGuide.get(index).username);
                System.out.println("password: " + AllTourGuide.get(index).password);
                System.out.println("address: " + AllTourGuide.get(index).address);
                System.out.println("phone number: " + AllTourGuide.get(index).phone_number);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else {
                System.out.println("invalid tour guide ID entered! please try again");
            }
        }
        checked = false;
        System.out.println("1-Edit\n" +
                "2-delete\n" +
                "3-Go back\n");
        choice = in.next().charAt(0);
        switch (choice) {
            case '1':
                editTourguideInformations("null", AllTourGuide);
                break;
            case '2':
                DeleteTourGuide(AllTourGuide, "old");
                break;
            case '3':
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                AllTourGuide.get(index).username = input;
                System.out.println("username updated successfully");
            } else if (choice == '2') {
                System.out.println("enter new password: ");
                input = in.next();
                AllTourGuide.get(index).password = input;
                System.out.println("password updated successfully");
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
                editTourguideInformations("null", AllTourGuide);
            } else if (input.toLowerCase().equals("n")) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tourGuideManipulation(AllTourGuide);

            } else {
                System.out.println("invalid input! please try again");
            }
        } while (input != "y" || input != "n");

    }

    public void DeleteTourGuide(ArrayList<TourGuide> AllTourGuide, String status) {
        if (status == "new") {
            System.out.println("Please enter the Tour guide id you want to delete: ");
            input = in.next();
            for (int i = 0; i < AllTourGuide.size(); i++) {
                if (AllTourGuide.get(i).account_id.equals(input)) {
                    checked = true;
                    index = i;
                }
            }
            if (checked == true) {
                AllTourGuide.remove(index);
                System.out.println("Account removed successfully!");
                checked = false;
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tourGuideManipulation(AllTourGuide);

            }
            if (checked == false) {
                System.out.println("Wrong input! Please try again..");
                DeleteTourGuide(AllTourGuide, "new");
            }
        } else {
            AllTourGuide.remove(index);
            System.out.println("Account removed successfully!");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tourGuideManipulation(AllTourGuide);
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

}
