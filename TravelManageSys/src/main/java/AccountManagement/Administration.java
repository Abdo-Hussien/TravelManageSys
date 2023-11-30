/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AccountManagement;

import java.util.ArrayList;

import TravelManagement.TourGuide;

/**
 *
 * @author bmood
 */
public interface Administration {

    void customerManipulation(ArrayList<Customers> AllCustomers);
    void tourGuideManipulation(ArrayList<TourGuide> AllTourGuide);
    void tripsAvalability();
    void customerDiscounts();
    
}
