package AccountManagement;

import java.util.ArrayList;
import java.util.Objects;

import TravelManagement.TourGuide;

public class Person {

    protected String first_name;
    protected String last_name;
    protected String username;
    protected int age;
    protected String phone_number;
    protected String address;
    protected String streetAddress;
    protected String stateAddress;
    protected String zipAddress;
    protected String password;
    protected String gender;
    protected String account_id; // given to user by a random method

    public Person(String first_name, String last_name, String username, int age, String phone_number, String address,
            String password, String gender, String account_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.age = age;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
        this.gender = gender;
        this.account_id = account_id;
    }

    public Person() {
    }

    public static <T extends Personsinterface> void DisplayUserDetails(T user) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("ID: " + user.getAccount_id());
        System.out.println("Full Name: " + user.getFirst_name() + " " + user.getLast_name());
        System.out.println("Age: " + user.getAge());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Phone Number: " + user.getPhone_number());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static <T extends Personsinterface> void DisplayAllUsers(ArrayList<T> AllUsers, String type) {
        if (type.toLowerCase().equals("customer"))
            System.out.printf("%-5s | %-15s | %-25s | %-25s | %-20s |%n", "Index", "Account ID", "Full Name",
                    "Username", "Discount Status");
        else
            System.out.printf("%-5s | %-15s | %-25s | %-25s |%n", "Index", "Account ID", "Full Name", "Username");
        System.out.println("-----------------------------------------------------------------------"
                + (type.toLowerCase().equals("customer") ? "---------------------" : ""));
        int[] count = { 1 };
        AllUsers.forEach(user -> {

            String fullName = user.getFirst_name() + " " + user.getLast_name();
            if (type.toLowerCase().equals("customer")) {
                String discountStatus = (user.getTripHistoryCounter() > 2) ? "True" : "False";
                System.out.printf("%-5s | %-15s | %-25s | %-25s | %-20s |%n",
                        count[0], user.getAccount_id(), fullName, user.getUsername(), discountStatus);
            } else
                System.out.printf("%-5s | %-15s | %-25s | %-25s |%n",
                        count[0], user.getAccount_id(), fullName, user.getUsername());
            count[0]++;
        });
        System.out.println("-----------------------------------------------------------------------"
                + (type.toLowerCase().equals("customer") ? "---------------------" : ""));
        System.out.println("Total number of " + type.toLowerCase() + ": " + AllUsers.size());
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public String getUsername() {
        return this.username;
    }

    public int getAge() {
        return this.age;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public String getAddress() {
        return this.streetAddress + ", " + this.stateAddress + ", " + this.zipAddress;
    }

    public String getPassword() {
        return this.password;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAccount_id() {
        return this.account_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String street, String state, String zip) {
        this.address = street + ", " + state + ", " + zip;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStateAddress() {
        return this.stateAddress;
    }

    public void setStateAddress(String stateAddress) {
        this.stateAddress = stateAddress;
    }

    public String getZipAddress() {
        return this.zipAddress;
    }

    public void setZipAddress(String zipAddress) {
        this.zipAddress = zipAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
