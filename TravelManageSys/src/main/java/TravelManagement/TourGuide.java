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

    protected double salay;
    protected  int TourGuidescounter;

public TourGuide(String first_name, String last_name, String username, int age, String phone_number, String address, String password, String gender, String account_id) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.username = username;
    this.age = age;
    this.phone_number = phone_number;
    this.address = address;
    this.password = password;
    this.gender = gender;
    this.account_id = account_id;
    TourGuidescounter++;
}

    //public double calcsalay(){}
}
