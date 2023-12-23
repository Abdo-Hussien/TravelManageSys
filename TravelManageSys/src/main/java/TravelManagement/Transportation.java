
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
    protected String transportID;
    protected String pickUp;

    // Super Constructor
    public Transportation(String pickUp, String transportID) {
        this.pickUp = pickUp;
        this.transportID = transportID;
    }

    // Getter methods
    public String getPickUp() {
        return this.pickUp;
    }

    public String getTransportID() {
        return this.transportID;
    }

}
