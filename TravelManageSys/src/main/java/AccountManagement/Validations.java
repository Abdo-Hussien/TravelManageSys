package AccountManagement;

import java.util.Scanner;

public class Validations {
    static Scanner in = new Scanner(System.in);

    static String NameValidation(String input_type, int low_range, int high_range) {
        while (true) {
            System.out.println("Please enter your " + input_type + ": ");
            String name = in.next();
            in.nextLine();
            if (name.length() < low_range  ) {
                System.out.println("Invalid!");
                continue;
            }
            if (name.length() > high_range )
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
            if (phone_no.length() == 10 && !phone_no.matches(".*[^A-Za-z0-9].*")&&!phone_no.matches(".*[A-Z].*") && !phone_no.matches(".*[a-z].*"))
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
            System.out.println("Please follow this format -> 012 street  | state name | zip code");
            System.out.println("Example: 890 Abdelhaleem Sabry Street | Cairo | 11234\n");
            String address = in.nextLine();
            try {
                address = address + " | ";
                String[] splittedAddress = address.split("\\s*\\|\\s*");
                int count = address.length() - address.replaceAll("\\|", "").length();
                if (count != 3)
                    throw new Exception("Invalid address format. Please follow the address format given!");
                return splittedAddress;
            } catch (Exception e) {
                System.out.println("Error Message: " + e.getMessage() + "\n");
                continue;
            }
        }
    }

    static String PasswordValidation() {
        System.out.println("Note: Your password should be a minimum of 8 characters and a maximum of 16.");
        System.out.println( "It should contain at least one uppercase letter, one lowercase letter, one digit, and one special character.\n");
        while (true) {
            System.out.println("Create a strong password for your account: ");
            String pass = in.next();
            in.nextLine();
            System.out.println("");

            if (pass.length() < 8 || pass.length() > 16) {
                System.out.println("Invalid password length! Please enter a password between 8 and 16 characters.");
                continue;
            }

            // Check if the password contains at least one uppercase letter, one lowercase
            // letter, one digit, and one special character

            if (!pass.matches(".*[A-Z].*") || !pass.matches(".*[a-z].*") || !pass.matches(".*\\d.*")
                    || !pass.matches(".*[^A-Za-z0-9].*")) {
                System.out.println(
                        "Weak password! Make sure it includes at least one uppercase letter, one lowercase letter, one digit, and one special character.");
                continue;
            }

            System.out.println("Enter your password again to confirm: ");
            System.out.println("");
            String confirm_pass = in.next();
            in.nextLine();
            System.out.println("");

            if (confirm_pass.equals(pass)) {
                return pass;
            } else {
                System.out.println("Passwords don't match, try again!");
            }
        }
    }

}
