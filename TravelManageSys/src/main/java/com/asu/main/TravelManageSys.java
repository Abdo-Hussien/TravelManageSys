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
import TravelManagement.Hotels;
import TravelManagement.TourGuide;
import TravelManagement.Transportation;
import TravelManagement.TravelItineraries;
import TravelManagement.Trip;
import data.fileManipulation;

/**
 *
 * @author bmood
 */
public class TravelManageSys {

    public static void main(String[] args) {
        int choice;
        Scanner in = new Scanner(System.in);
        ArrayList<Customers> allCustomers = new ArrayList<Customers>(fileManipulation.getAllCustomers());
        ArrayList<TourGuide> allTourGuides = new ArrayList<TourGuide>(fileManipulation.getAllTourGuides());
        ArrayList<Car> allCars = new ArrayList<Car>(fileManipulation.getAllCars());
        ArrayList<Trip> allTrips = new ArrayList<Trip>(fileManipulation.getAllTrips());
        ArrayList<Transportation> allTransportations = new ArrayList<Transportation>(
                fileManipulation.getAllTransportations());
        ArrayList<Hotels> allHotels = new ArrayList<Hotels>(fileManipulation.getAllHotels());
        ArrayList<BookedTravels> allBookedTravels = new ArrayList<BookedTravels>();
        ArrayList<String> TravelHistory = new ArrayList<String>();

        Admin admin = new Admin();
        TravelItineraries dashboard = new TravelItineraries();
        System.out.println("Welcome to Our Travel Agency !");
        do {
            System.out.println("1- Admin\n" + "2- Customer\n" + "3- TourGuide");
            System.out.print("Choice: ");
            choice = in.nextInt();
            in.nextLine();
            if (choice == 1) {
                System.out.println("Enter Admin username: ");
                String username = in.next();
                System.out.println("Enter Admin password: ");
                String password = in.next();
                if (username.equals("admin") && password.equals("admin")) {
                    admin.Manipulation(allCustomers, "Customers");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    admin.Manipulation(allTourGuides, "Tourguide");
                }

            } else if (choice == 2) {
                admin.userMenu(allCustomers, "Customer");
                // the main system
            } else if (choice == 3) {
                admin.userMenu(allTourGuides, "TourGuide");
                allTourGuides.get(1).showDetails(admin.index, allTourGuides, allTrips);
            } else {
                System.out.println("wrong input! please try again.");
                continue;
            }
            break;
        } while (choice != 1 || choice != 2 || choice != 3);

    }
}
