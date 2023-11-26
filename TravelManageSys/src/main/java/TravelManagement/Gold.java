/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.Scanner;

/**
 *
 * @author bmood
 */
public class Gold extends Ticket{
    Scanner in = new Scanner(System.in);
    // @Override
    // public float Ticket_Price(){
    //    return (price el intial trip) + 500;
    // }

    public void gold_welcome(){
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t\tGOLD TICKET");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    @Override
    public void setNo_Of_Tickets() {
        super.setNo_Of_Tickets();
    }

    public void ticket_detalis(){
        System.out.println(this.No_Of_Tickets +" Gold Tickets");
    }
}
