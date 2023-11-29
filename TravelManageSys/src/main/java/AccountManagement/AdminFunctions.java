package AccountManagement;

import data.fileManipulation;

public class AdminFunctions extends Admin {

    public void displayAllCustomers() {
        System.out.println("Please enter the customer id: ");
        input = scan.next();
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
            }
        }

    }

    public void editInformations(){
        displayAllCustomers();
        System.out.println("");
    }


}
