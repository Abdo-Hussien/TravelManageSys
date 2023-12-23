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
        Admin admin = new Admin();

        do {
            System.out.println("\nWelcome to Our Travel Agency !\n");
            System.out.println("1- Admin\n" + "2- Customer\n" + "3- TourGuide\n" + "4- Leave.");
            System.out.print("Choice: ");
            choice = in.nextInt();
            in.nextLine();
            if (choice == 1) {
                System.out.println("Enter Admin username: ");
                String username = in.next();
                in.nextLine();
                System.out.println("Enter Admin password: ");
                String password = in.next();
                in.nextLine();
                if (username.equals("admin") && password.equals("admin")) {
                    admin.AdminMenu(allCustomers, allTourGuides, allTrips);
                    continue;
                } else {
                    System.out.println("Wrong username or password!");
                    continue;
                }
            } else if (choice == 2) {
                Customers currentCustomer = (Customers) admin.userMenu(allCustomers, "Customer");
                if (currentCustomer == null)
                    continue;
                RunCustomer(currentCustomer, allCustomers, allTrips);
                continue;
            } else if (choice == 3) {
                TourGuide currentTourGuide = (TourGuide) admin.userMenu(allTourGuides, "TourGuide");
                if (currentTourGuide == null)
                    continue;
                currentTourGuide.guideMenu(allTourGuides, allTrips);
                continue;
            } else if (choice == 4)
                System.exit(0);
            else {
                System.out.println("wrong input! please try again.");
                continue;
            }
        } while (choice != 1 || choice != 2 || choice != 3);

    }

    private static void RunCustomer(Customers currentCustomer, ArrayList<Customers> allCustomers,
            ArrayList<Trip> allTrips) {
        TravelItineraries dashboard = new TravelItineraries();
        if (currentCustomer.UserMainMenu(allTrips, allCustomers) == null)
            return;
        dashboard.dashboard(currentCustomer, allTrips);
        RunCustomer(currentCustomer, allCustomers, allTrips);
        return;
    }
}
