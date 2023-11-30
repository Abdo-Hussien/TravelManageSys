package AccountManagement;

import java.util.Scanner;

import data.fileManipulation;

public class AdminFunctions extends Admin {
    int index;

    public void displayAllCustomersinfo() {
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
                editCustomerInformations("null");
                break;
            case '2':
                DeleteCustomer();
                break;
            case '3':
                customerManipulation();
                break;
            default:
                System.out.println("wrong input! please try again");
                displayAllCustomersinfo();
                break;
        }

    }

    public void editCustomerInformations(String status) {
        if (status.equals("new")) {

            displayAllCustomersinfo();
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
        } else if (choice == '2') {
            System.out.println("enter old password: ");
            input = in.next();
            if (AllCustomers.get(index).password.equals(input)) {
                System.out.println("enter new password: ");
                input = in.next();
                AllCustomers.get(index).password = input;
            }
        } else if (choice == '3') {
            System.out.println("enter new firstname: ");
            input = in.next();
            AllCustomers.get(index).first_name = input;
        } else if (choice == '4') {
            System.out.println("enter new lastname: ");
            input = in.next();
            AllCustomers.get(index).last_name = input;
        } else if (choice == '5') {
            System.out.println("enter new address: ");
            in.nextLine();
            input = in.nextLine();
            AllCustomers.get(index).address = input;
        } else if (choice == '6') {
            System.out.println("enter new phone number: ");
            input = in.next();
            AllCustomers.get(index).phone_number = input;
        } else {
            System.out.println("invalid input! please try again..");

        }
        editCustomerInformations("null");
    }

    public void DeleteCustomer() {
        System.out.println("Please enter the Tour guide id you want to delete: ");
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
            DeleteCustomer();
        }

    }

    public void displayAllTourGuideinfo() {
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
                editTourguideInformations("null");
                break;
            case '2':
                DeleteTourGuide();
                break;
            case '3':
                tourGuideManipulation();
                break;
            default:
                System.out.println("wrong input! please try again");
                displayAllTourGuideinfo();
                break;
        }

    }

    public void editTourguideInformations(String status) {
        if (status.equals("new")) {

            displayAllTourGuideinfo();
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
        } else if (choice == '2') {
            System.out.println("enter old password: ");
            input = in.next();
            if (AllTourGuide.get(index).password.equals(input)) {
                System.out.println("enter new password: ");
                input = in.next();
                AllTourGuide.get(index).password = input;
            }

        } else if (choice == '3') {
            System.out.println("enter new firstname: ");
            input = in.next();
            AllTourGuide.get(index).first_name = input;

        } else if (choice == '4') {
            System.out.println("enter new lastname: ");
            input = in.next();
            AllTourGuide.get(index).last_name = input;
        } else if (choice == '5') {
            System.out.println("enter new address: ");
            in.nextLine();
            input = in.nextLine();
            AllTourGuide.get(index).address = input;
        } else if (choice == '6') {
            System.out.println("enter new phone number: ");
            input = in.next();
            AllTourGuide.get(index).phone_number = input;
        } else {
            System.out.println("invalid input! please try again..");

        }
        editTourguideInformations("null");
    }

    public void DeleteTourGuide() {
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
            DeleteTourGuide();
        }

    }
}
