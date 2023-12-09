package AccountManagement;

import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Validations {
    static Scanner in = new Scanner(System.in);
    static String NameValidation(String input_type, int low_range, int high_range) {
        while (true) {
            System.out.println("Please enter your " + input_type + ": ");
            String name = in.next();
            in.nextLine();
            if (name.length() < low_range) {
                System.out.println("Invalid!");
                continue;
            }
            if (name.length() > high_range)
                System.out.println(input_type + " is too long.");
            else
                return name;
        }
    }

    static String PhoneValidation() {
        while (true) {
            System.out.println("Enter your phone number: ");
            System.out.println("The state code (+20) is added. \n");
            String phone_no = in.next();
            in.nextLine();
            if (phone_no.length() == 10)
                return "+20" + phone_no;
            else {
                System.out.println("Invalid phone number, the phone number should be 10 numbers !");
                continue;
            }
        }
    }

    static int AgeValidation() {
        while (true) {
            System.out.println("Enter your age: ");
            int Age = in.nextInt();
            in.nextLine();
            System.out.println("");
            if (Age < 16 || Age > 90) {
                System.out.println("Invalid age, the minimum age to create an account is 16 and maximum is 90 !");
                continue;
            } else {
                return Age;
            }
        }
    }

    static String GenderValidation() {
        while (true) {
            System.out.println("Choose your gender from the following (Male/Female).");
            String Gender = in.next();
            in.nextLine();
            if (Gender.toLowerCase().equals("male") || Gender.toLowerCase().equals("female"))
                return Gender;
            else {
                System.out.println("Select from the following options only and try again.");
                continue;
            }
        }
    }

    static String[] AddressValidation() {
        while (true) {
            System.out.println("Please enter your address: \n");
            System.out.println("Example: 890 Abdelhaleem Sabry Street | Cairo | 11234  \n");
            String address = in.nextLine();
            try {
                address = address + " | ";
                String[] splittedAddress = address.split("\\s*\\|\\s*");
                int count = address.length() - address.replaceAll("\\|", "").length();
                if (count != 3) {
                    System.out.println("Invalid address format. Please follow the address format given!");
                    continue;
                }
                return splittedAddress;
            } catch (Exception e) {
                System.out.println("An error occurred while processing the address. Please try again.");
                continue;
            }
        }
    }

    static String PasswordValidation() {
        System.out.println("Note : your password should be a minimum of 8 character & maximum of 16 ");
        while (true) {
            System.out.println("Create a strong password for your account: \n");
            String pass = in.next();
            in.nextLine();
            System.out.println("");
            if (pass.length() < 8) {
                System.out.println("Weak password detected!");
                continue;
            } else if (pass.length() > 16) {
                System.out.println("Password is too long !");
                continue;
            } else {
                System.out.println("Enter your password again to confirm : ");
                System.out.println("");
                String confirm_pass = in.next();
                in.nextLine();
                System.out.println("");
                if (confirm_pass.equals(pass))
                    return pass;
                else {
                    System.out.println("Passwords don't match ,try again !");
                    continue;
                }
            }
        }
    }

}
