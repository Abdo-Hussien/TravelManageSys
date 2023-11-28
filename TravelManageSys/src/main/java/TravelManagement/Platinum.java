/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

/**
 *
 * @author bmood
 */
public class Platinum extends Ticket {
    int Pcounter;


    public Platinum() {
        // super.ticketExpiration=; the date the user will choose from the bookoing
        // function
    }

    @Override
    public double Ticket_Price() {
        return 0.6;
    }

    public void platinum_welcome() {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t     PLATINUM TICKET");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    public void addPlatinum() {
        System.out.println("how many Platinum tickets you want?");
        addeddTicket = in.nextInt();
        Pcounter += addeddTicket;
        System.out.println("Done! " + addeddTicket + "Platinum tickets added to your cart");
    }

    public void deletePlatinum() {
        System.out.println("how many Platinum tickets you want to delete?");
        deletedTicket = in.nextInt();
        Pcounter -= deletedTicket;
        System.out.println("Done! " + deletedTicket + "Platinum tickets deleted from your cart");
    }
}
