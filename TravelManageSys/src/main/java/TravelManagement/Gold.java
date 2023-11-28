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
        System.out.println("how many Gold tickets you want?");
        addeddTicket = in.nextInt();
        if (addeddTicket > 0) {
            Gcounter += addeddTicket;
            System.out.println("Done! " + addeddTicket + " Gold tickets added to your cart");
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

    public void deleteGold() {
        System.out.println("how many Gold tickets you want to delete?");
        deletedTicket = in.nextInt();
        if (deletedTicket > 0) {
            if (deletedTicket > Gcounter) {
                System.out.println("you entered more tickets than you have! please tyr again...");
                deleteGold();
            } else {
                Gcounter -= deletedTicket;
                System.out.println("Done! " + deletedTicket + " Gold tickets deleted from your cart");
            }
        } else {
            System.out.println("invalid number of tickets entered! Please try again.");
            deleteGold();
        }
    }
}
