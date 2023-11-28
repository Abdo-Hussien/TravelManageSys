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
        if (addeddTicket > 0) {
            Pcounter += addeddTicket;
            System.out.println("Done! " + addeddTicket + " Platinum tickets added to your cart");
        }
        // else if(addeddTicket > tirpcapacity) {
        // System.out.println("too many tickets entered! avalibale tickerts is"+
        // avalibale);
        // }
        else {
            System.out.println("invalid number of tickets entered! Please try again.");
            addPlatinum();
        }

    }

    public void deletePlatinum() {
        System.out.println("how many Platinum tickets you want to delete?");
        deletedTicket = in.nextInt();
        if (deletedTicket > 0) {
            Pcounter -= deletedTicket;
            System.out.println("Done! " + deletedTicket + " Platinum tickets deleted from your cart");
        } else if (deletedTicket > Pcounter) {
            System.out.println("you entered more tickets than you have! please tyr again...");
            deletePlatinum();
        } else {
            System.out.println("invalid number of tickets entered! Please try again.");
            deletePlatinum();
        }
    }
}
