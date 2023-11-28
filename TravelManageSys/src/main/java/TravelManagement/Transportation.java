
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

/**
 *
 * @author bmood
 */
public class Transportation {
    private String pickUp;
    private String destination;
    private int maxCapacity;

    // Super Constructor
    public Transportation(String pickUp, String destination, int maxCapacity) {
        this.pickUp = pickUp;
        this.destination = destination;
        this.maxCapacity = maxCapacity;
    }

    // Getter methods
    public String getPickUp() {
        return pickUp;
    }

    public String getDestination() {
        return destination;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
    
}









