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

    public void addGOLD() {
        System.out.println("how many GOLD tickets you want?");
        addeddTicket = in.nextInt();
        Gcounter += addeddTicket;
        System.out.println("Done! " + addeddTicket + "GOLD tickets added to your cart");
    }

    public void deleteGOLD() {
        System.out.println("how many GOLD tickets you want to delete?");
        deletedTicket = in.nextInt();
        Gcounter -= deletedTicket;
        System.out.println("Done! " + deletedTicket + "GOLD tickets deleted from your cart");
    }
}
