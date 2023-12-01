
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

/**
 *
 * @author bmood
 */
public abstract class Transportation {
    private String transportID;
    private String pickUp;
    private int maxCapacity;

    // Super Constructor
    public Transportation(String pickUp, int maxCapacity, String transportID) {
        this.pickUp = pickUp;
        this.maxCapacity = maxCapacity;
        this.transportID = transportID;
    }

    // Getter methods
    public String getPickUp() {
        return this.pickUp;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }
    public String getTransportID() {
        return this.transportID;
    }

}
