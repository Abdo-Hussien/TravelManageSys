/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

/**
 *
 * @author bmood
 */
public class Gold extends Ticket {
    int Gcounter;

    public Gold() {
        // super.ticketExpiration=; the date the user will choose from the bookoing
        // function
    }

    @Override
    public double Ticket_Price() {
        return 0.3;
    }

    public void gold_welcome() {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t\tGOLD TICKET");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    public void addGold() {
        System.out.println("how many sliver tickets you want?");
        addeddTicket = in.nextInt();
        if (addeddTicket > 0) {
            Gcounter += addeddTicket;
            System.out.println("Done! " + addeddTicket + " sliver tickets added to your cart");
        }
        // else if(addeddTicket > tirpcapacity) {
        // System.out.println("too many tickets entered! avalibale tickerts is"+
        // avalibale);
        // }
        else {
            System.out.println("invalid number of tickets entered! Please try again.");
            addGold();
        }

    }

    public void deleteGOLD() {
        System.out.println("how many sliver tickets you want to delete?");
        deletedTicket = in.nextInt();
        if (deletedTicket > 0) {
            Gcounter -= deletedTicket;
            System.out.println("Done! " + deletedTicket + " sliver tickets deleted from your cart");
        } else if (deletedTicket > Gcounter) {
            System.out.println("you entered more tickets than you have! please tyr again...");
            deleteGOLD();
        } else {
            System.out.println("invalid number of tickets entered! Please try again.");
            deleteGOLD();
        }
    }
}
