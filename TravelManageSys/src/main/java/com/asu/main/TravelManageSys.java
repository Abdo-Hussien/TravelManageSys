/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.asu.main;

import java.util.ArrayList;
import java.util.Scanner;
import AccountManagement.*;
import TravelManagement.*;
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
        ArrayList<Trip> allTrips = new ArrayList<Trip>(fileManipulation.getAllTrips());
        // ArrayList<Car> allCars = new ArrayList<Car>(fileManipulation.getAllCars());
        // ArrayList<Transportation> allTransportations = new ArrayList<Transportation>(
        //         fileManipulation.getAllTransportations());
        // ArrayList<Hotels> allHotels = new ArrayList<Hotels>(fileManipulation.getAllHotels());
        // ArrayList<BookedTravels> allBookTravels = new ArrayList<BookedTravels>();
        // BookingTickets allBookTickets = new BookingTickets();

        Admin admin = new Admin();
        TravelItineraries dashboard = new TravelItineraries();
        do {
            System.out.println("Welcome to Our Travel Agency !");
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
                    admin.AdminMenu(allCustomers, allTourGuides, allTrips);
                    continue;
                } else {
                    System.out.println("wrong username or password! please try again");
                }
            } else if (choice == 2) {
                Customers currentCustomer = (Customers) admin.userMenu(allCustomers, "Customers");
                currentCustomer.UserMainMenu(allTrips, allCustomers);
                dashboard.dashboard(currentCustomer, allTrips);
                continue;
            } else if (choice == 3) {
                admin.userMenu(allTourGuides, "TourGuide");
                allTourGuides.get(0).guideMenu(Admin.index, allTourGuides, allTrips);
            } else {
                System.out.println("wrong input! please try again.");
                continue;
            }
            break;
        } while (choice != 1 || choice != 2 || choice != 3);

    }
}
