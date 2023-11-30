
package AccountManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

import data.fileManipulation;

import TravelManagement.BookedTravels;
import TravelManagement.Trip;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

//contains method for genrating a random ID

public class Customers extends Person {

    private CustomerBooking BookingManipulations = new CustomerBooking();

    Scanner scanner = new Scanner(System.in);
    Person u = new Person();
    // keeps track of user's trip history
    private ArrayList<String> tripsHistory = new ArrayList<>();
    // private ArrayList<BookedTravels> BookedTravels = new ArrayList<>();
    // user address attributes
    private String streetAddress;
    private String stateAddress;
    private String zipAddress;
    boolean logged_in = false; // used in edit account for user
    private ArrayList<Customers> allcustomer1 = new ArrayList<Customers>();

    public Customers(String account_id, String first_name, String last_name, String username, String password, int age,
            String gender, String address, String phone_number, ArrayList<BookedTravels> oldBookingTrips,
            String[] tripHistory) {
        super(first_name, last_name, username, age, phone_number, address, password, gender, account_id);
        this.BookingManipulations.CustomerBookedTrips = oldBookingTrips;
    }

    public Customers() {
    }

    public int getTripHistoryCounter() {
        return tripsHistory.size();
    }

    // create an account
    public void create_acc() throws IOException {
        System.out.println("\n");
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("");
        System.out.println("Create Account ");
        System.out.println("");
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("\n");

        while (true) {

            System.out.println("Please enter your first name : ");
            System.out.println("");
            String f_name = scanner.next();
            System.out.println("");
            if (f_name.length() < 3) {
                System.out.println("Invalid !");
                continue;
            }

            if (f_name.length() > 14) {
                System.out.println("First name is too long.");
            }

            else {
                u.first_name = f_name;
                break;
            }

        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Please enter your last name : ");
            System.out.println("");
            String l_name = scanner.next();
            System.out.println("");
            if (l_name.length() < 3) {
                System.out.println("Invalid !");
                continue;
            }

            if (l_name.length() > 14) {
                System.out.println("Last name is too long.");
            }

            else {
                u.last_name = l_name;
                break;
            }
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Please enter a username for your account : ");
            System.out.println("");
            String u_name = scanner.next();
            System.out.println("");
            if (u_name.length() < 7) {
                System.out.println("Username is too short , it must be a minimum of 7 characters.");
                continue;
            }

            if (u_name.length() > 14) {
                System.out.println("Username is too long , it must be a maximum of 14 characters.");
            }

            else {
                u.username = u_name;
                break;
            }
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Enter your mobile phone number : ");
            System.out.println("the state code (+20) is added. ");
            System.out.println("");
            String phone_no = scanner.next();
            System.out.println("");
            if (phone_no.length() == 10) {
                u.phone_number = "+20" + phone_no;
                break;
            } else {
                System.out.println("Invalid phone number, the phone number should be 10 numbers !");
                continue;

            }
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Enter your age : ");
            System.out.println("");
            Integer Age = scanner.nextInt();
            System.out.println("");
            if (Age < 16) {
                System.out.println("Invalid age, the minimum age to create an account is 16 !");
                continue;
            } else {
                u.age = Age;
                break;
            }
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Choose your gender from the following (Male/Female) . ");
            System.out.println("");
            String Gender = scanner.next();
            System.out.println("");

            if (Gender.toLowerCase().equals("male") || Gender.toLowerCase().equals("female")) {
                u.gender = Gender;
                break;
            } else {
                System.out.println("Select from the following options only and try again.");
                continue;
            }

        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");
        scanner.nextLine();

        while (true) {
            System.out.println("Please enter your street address : ");
            System.out.println("");
            streetAddress = scanner.nextLine();
            System.out.println("");
            break;
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Please enter your state address : ");
            System.out.println("");
            stateAddress = scanner.nextLine();
            System.out.println("");
            break;
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        while (true) {
            System.out.println("Please enter your ZIP code address : ");
            System.out.println("");
            zipAddress = scanner.nextLine();
            System.out.println("");
            break;
        }

        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("\n");

        u.address = streetAddress + " " + "|" + " " + stateAddress + " " + "|" + " " + zipAddress;

        System.out.println("Note : your password should be a minimum of 8 character & maximum of 16 ");
        while (true) {

            System.out.println("Create a strong password for your account : ");
            System.out.println("");
            String pass = scanner.next();
            System.out.println("");
            if (pass.length() < 8) {
                System.out.println("Weak password detected!");
                continue;
            }

            else if (pass.length() > 16) {
                System.out.println("Password is too long !");
                continue;
            } else {
                u.password = pass;
                System.out.println("Enter your password again to confirm : ");
                System.out.println("");
                String confirm_pass = scanner.next();
                System.out.println("");
                if (confirm_pass.equals(pass)) {
                    System.out.println("");
                    System.out.println("---------------------------------------");
                    System.out.println("Created account successfully.");
                    System.out.println("---------------------------------------");
                    System.out.println("");
                    break;
                }

                else {
                    System.out.println("Passwords don't match ,try again !");
                    continue;
                }

            }

        }

        // generates random ID for Customer
        RandIDGenerator generator = new RandIDGenerator();
        generator.setItemCount(5);
        generator.generateRandID();
        u.account_id = generator.getRandID();

        // input data into text file
        String filePath = "TravelManageSys\\TravelManageSys\\src\\main\\java\\data\\customers.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true), true)) {

            writer.println(u.first_name + " " + u.last_name);
            writer.println(u.age);
            writer.println(u.gender.toLowerCase());
            writer.println(u.phone_number);
            writer.println(u.address);
            writer.println(u.username);
            writer.println(u.password);
            writer.println(u.account_id);
            writer.println();

        }

        System.out.println("Successfully created the account : " + u.username);
        System.out.println("Would you wish to create an another account? (yes/no) ");
        String choice = scanner.next();
        if (choice.toLowerCase().equals("yes") || choice.toLowerCase().equals("y")) {
            create_acc();
        }

        else {

            userMenu();
        }

    }

    // login into account
    public void login(ArrayList<Customers> allCustomers) throws FileNotFoundException, IOException {

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

            for (int i = 0; i < allCustomers.size(); i++) {
                if (allCustomers.get(i).username.equals(userName)) {
                    if (allCustomers.get(i).password.equals(pass)) {
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

    // user can edit his account information
    public void edit_acc() throws FileNotFoundException, IOException {

        String line;
        File filePath = new File("TravelManageSys//TravelManageSys//src//main//java//data//customers.txt");
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        StringBuilder content = new StringBuilder();

        String new_phoneNumber = "";
        String prev_phoneNumber = "";
        String prev_userName = "";
        String new_userName = "";
        String prev_password = "";
        String new_password = "";
        String userInput;

        if (1 == 0/* logged_in == false */) {
            System.out.println("");
            System.out.println("---------------------------------------------");
            System.out.println("You have to logged in to edit your account.");
            System.out.println("---------------------------------------------");

            login(allcustomer1);
        }

        else {

            System.out.println("What field would you wish to edit ? ");
            System.out.println("");
            System.out.println("------------------------------------------------- ");
            System.out.println("1) Phone number");
            System.out.println("2) Username ");
            System.out.println("3) Password");
            System.out.println("4) Go back to menu");
            System.out.println("------------------------------------------------- ");

            userInput = scanner.next();
            System.out.println("");

            if (userInput.equals("1")) {

                while (true) {
                    System.out.println("Enter your previous phone number : ");
                    System.out.println("the state code (+20) is added.");
                    prev_phoneNumber = scanner.next();

                    System.out.println("");
                    if (prev_phoneNumber.length() == 10) {
                        prev_phoneNumber = "+20" + prev_phoneNumber;
                        break;
                    } else {
                        System.out.println("Invalid phone number, the phone number should be 10 numbers !");
                        continue;

                    }
                }

                while (true) {
                    System.out.println("Enter the phone number you want to be added : ");
                    System.out.println("the state code (+20) is added.");
                    new_phoneNumber = scanner.next();

                    System.out.println("");
                    if (new_phoneNumber.length() == 10) {
                        new_phoneNumber = "+20" + new_phoneNumber;
                        break;
                    } else {
                        System.out.println("Invalid phone number, the phone number should be 10 numbers !");
                        continue;

                    }
                }

                while ((line = reader.readLine()) != null) {
                    // Replace the old value with the new value
                    line = line.replace(prev_phoneNumber, new_phoneNumber);
                    content.append(line).append("\n");
                }
                reader.close();

                // Write the updated content back to the file

                System.out.println("Successfully changed phone number to : " + new_phoneNumber);
                writer.write(content.toString());
                writer.close();

            }

            // change username
            if (userInput.equals("2")) {

                while (true) {
                    System.out.println("Enter your previous username : ");
                    prev_userName = scanner.next();

                    System.out.println("");
                    if (prev_userName.length() < 7) {
                        System.out.println("Your username should be a minimum of 7 characters.");
                        continue;
                    }
                    if (prev_userName.length() > 14) {
                        System.out.println("Your username should be a minimum of 7 characters.");
                        continue;
                    } else {
                        break;
                    }

                }

                while (true) {
                    System.out.println("Enter your new username : ");
                    new_userName = scanner.next();

                    System.out.println("");
                    if (new_userName.length() < 7) {
                        System.out.println("Your username should be a minimum of 7 characters.");
                        continue;
                    }
                    if (new_userName.length() > 14) {
                        System.out.println("Your username should be a minimum of 7 characters.");
                        continue;
                    } else {
                        break;
                    }

                }

                while ((line = reader.readLine()) != null) {
                    line = line.replace(prev_userName, new_userName);
                    content.append(line).append("\n");
                }
                reader.close();

                System.out.println("Successfully changed  your username to :" + new_userName);
                writer.write(content.toString());
                writer.close();
            }

            // change password
            if (userInput.equals("3")) {

                while (true) {
                    System.out.println("Enter your previous username : ");
                    prev_userName = scanner.next();

                    System.out.println("");
                    if (prev_userName.length() < 7) {
                        System.out.println("Your username should be a minimum of 7 characters.");
                        continue;
                    }
                    if (prev_userName.length() > 14) {
                        System.out.println("Your username should be a minimum of 7 characters.");
                        continue;
                    } else {
                        break;
                    }

                }

                while (true) {
                    System.out.println("Enter your new username : ");
                    new_userName = scanner.next();

                    System.out.println("");
                    if (new_userName.length() < 7) {
                        System.out.println("Your username should be a minimum of 7 characters.");
                        continue;
                    }
                    if (new_userName.length() > 14) {
                        System.out.println("Your username should be a minimum of 7 characters.");
                        continue;
                    } else {
                        break;
                    }

                }

                while ((line = reader.readLine()) != null) {
                    line = line.replace(prev_userName, new_userName);
                    content.append(line).append("\n");
                }
                reader.close();

                System.out.println("Successfully changed  your username to :" + new_userName);
                writer.write(content.toString());
                writer.close();
            }

            // return back to usermenu
            if (userInput.equals("4")) {
                userMenu();
            }

        } // else

    } // func

    // user interface menu
    public void userMenu() throws IOException {

        while (true) {

            System.out.println(" ");
            System.out.println("Choose an action you want to perfom : ");
            System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
            System.out.println(" ");
            System.out.println("1.) Create account");
            System.out.println(" ");
            System.out.println("2.) Login");
            System.out.println(" ");
            System.out.println("3.) Edit account");
            System.out.println(" ");
            System.out.println(".~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.~~~~~~~~~~~.");
            System.out.println("\n");
            String userInput = scanner.next();
            System.out.println("");

            if (userInput.equals("1")) {
                create_acc();
                break;
            }

            if (userInput.equals("2")) {
                login(allcustomer1);
                break;
            }

            if (userInput.equals("3")) {

                edit_acc();
                break;

            }

            else {
                System.out.println("Invalid input! please choose only from the following options.");
                continue;
            }

        }

    }

    // diplay all users accounts and their information (for admin usage only)
    public void display_all_users() throws FileNotFoundException, IOException {
        String filePath = "TravelManageSys\\TravelManageSys\\src\\main\\java\\data\\customers.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line from the file until the end of the file is reached
            System.out.println("\n");
            System.out.println("Users accounts : ");
            System.out.println("==============================");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }

        }
    }

}
