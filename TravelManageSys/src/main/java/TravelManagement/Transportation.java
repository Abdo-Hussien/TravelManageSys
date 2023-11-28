
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
    private String pickUp;
    private int maxCapacity;

    // Super Constructor
    public Transportation(String pickUp, int maxCapacity) {
        this.pickUp = pickUp;
        this.maxCapacity = maxCapacity;
    }

    // Getter methods
    public String getPickUp() {
        return pickUp;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
}









