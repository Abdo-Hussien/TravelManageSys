/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import AccountManagement.Person;

/**
 *
 * @author bmood
 */
public class TourGuide extends Person {

    protected double salary;
    protected static int TourGuidescounter;

     public TourGuide( String account_id,String first_name, String last_name,String username, String password,
     int age,String gender, String address,String phone_number) {
        this.account_id = account_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone_number = phone_number;
        TourGuidescounter++;
    }
    // public double calcsalay(){}
}
