/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.asu.main;

import java.util.ArrayList;
import java.util.Scanner;

import AccountManagement.Admin;
import AccountManagement.Customers;
import TravelManagement.BookedTravels;
import TravelManagement.Car;
import TravelManagement.TourGuide;
import TravelManagement.Trip;

/**
 *
 * @author bmood
 */
public class TravelManageSys {

    public static void main(String[] args) {
        int choice;
        Scanner in = new Scanner(System.in);
        ArrayList<Customers> allCustomers = new ArrayList<Customers>();
        ArrayList<TourGuide> allTourGuides = new ArrayList<TourGuide>();
        ArrayList<Car> allCars = new ArrayList<Car>();
        ArrayList<Trip> allTrips = new ArrayList<Trip>();
        ArrayList<BookedTravels> allBookedTravels = new ArrayList<BookedTravels>();
        ArrayList<String> TraverHistory = new ArrayList<String>();
        Admin admin = new Admin();
        System.out.println("welcome to TravelManageSys");
        do {
            System.out.println("1-Admin\n" +
                    "2-Customer\n" +
                    "3-TourGuide\n");
            choice = in.nextInt();
            if (choice == 1) {
                System.out.println("Enter Admin username: ");
                String username = in.next();
                System.out.println("Enter Admin password: ");
                String password = in.next();
                if (username.equals("admin") && password.equals("admin")) {
                }

            } else if (choice == 2) {
                allCustomers.get(0).create_acc("Customer", allCustomers);
            } else if (choice == 3) {
                allTourGuides.get(0).create_acc("TourGuide", allTourGuides);
            } else {
                System.out.println("wrong input! please try again.");
            }
        } while (choice != 1 || choice != 2 || choice != 3);
    }
}
