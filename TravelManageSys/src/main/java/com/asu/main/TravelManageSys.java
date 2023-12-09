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
        ArrayList<Customers> allCustomers = new ArrayList<Customers>();
        ArrayList<TourGuide> allTourGuides = new ArrayList<TourGuide>();
        ArrayList<Car> allCars = new ArrayList<Car>();
        ArrayList<Trip> allTrips = new ArrayList<Trip>();
        ArrayList<Transportation> allTransportations = new ArrayList<Transportation>();
        ArrayList<Hotels> allHotels = new ArrayList<Hotels>();
        ArrayList<BookedTravels> allBookedTravels = new ArrayList<BookedTravels>();
        ArrayList<String> TravelHistory = new ArrayList<String>();
        allCustomers = fileManipulation.getAllCustomers();
        allTourGuides = fileManipulation.getAllTourGuides();
        allCars = fileManipulation.getAllCars();
        allTrips = fileManipulation.getAllTrips();
        allTransportations = fileManipulation.getAllTransportations();
        allHotels = fileManipulation.getAllHotels();

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
                    // Admin
                }

            } else if (choice == 2) {
                allCustomers.add((Customers) admin.create_acc("Customer"));
            } else if (choice == 3) {
                allTourGuides.add((TourGuide) admin.create_acc("TourGuide"));
            } else {
                System.out.println("wrong input! please try again.");
                continue;
            }
            break;
        } while (choice != 1 || choice != 2 || choice != 3);

    }
}
