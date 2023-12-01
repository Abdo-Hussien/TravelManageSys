/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.asu.main;

import java.util.ArrayList;

import AccountManagement.Customers;
import data.fileManipulation;

/**
 *
 * @author bmood
 */
public class TravelManageSys {

    public static void main(String[] args)  {
        System.out.println("Main Class is called 'TravelManageSys' is in the 'main' package");
        ArrayList<Customers> customers = new ArrayList<>();
        customers = fileManipulation.getAllCustomers();
        for (Customers customer : customers) {
            for (String trip : customer.tripsHistory) {
                System.out.println(trip);
            }
        }
    }
}