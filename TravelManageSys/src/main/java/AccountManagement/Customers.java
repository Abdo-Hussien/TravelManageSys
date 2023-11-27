
package AccountManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

//contains method for genrating a random ID
class RandIDGenerator {
    private StringBuilder str;
    private int itemCount;
    private String alphaNumeric;

    public RandIDGenerator() {
        str = new StringBuilder();
        itemCount = 0;
        alphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    }

    public void generateRandID() {
        Random rand = new Random();
        for (int i = 0; i < itemCount; i++) {
            str.append(alphaNumeric.charAt(rand.nextInt(alphaNumeric.length())));
        }
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getRandID() {
        return str.toString();
    }
}

public class Customers extends Person {
    
    Scanner scanner = new Scanner(System.in);
    Person u = new Person();
    
    // keeps track of user's trip history
    private ArrayList<String> tripsHistory = new ArrayList<>();
    
    // private ArrayList<BookedTravels> BookedTravels = new ArrayList<>();
    
    
    
    
    //user address attributes
      private String streetAddress;
     private String stateAddress;
     private String zipAddress;
            
    

    public Customers(  String account_id,String first_name, String last_name, String username,String password, int age, String gender, String address, String phone_number) {
        super(first_name, last_name, username, age, phone_number, address, password, gender, account_id);
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
                u.phone_number = "+20"+phone_no;
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
            
            if(Gender.toLowerCase().equals("male")||Gender.toLowerCase().equals("female")){
                u.gender=Gender;
                break;
            }
            else{
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
        
        
        
        u.address=streetAddress+" "+"|"+" "+stateAddress+" "+"|"+" "+zipAddress;
        
        
        
        
        
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
            
                writer.println(u.first_name+" "+u.last_name);
                writer.println(u.age);
                writer.println(u.gender.toLowerCase());
                writer.println(u.phone_number);
                writer.println(u.address);
                writer.println(u.username);
                writer.println(u.password);
                writer.println(u.account_id);
                writer.println();
                

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
