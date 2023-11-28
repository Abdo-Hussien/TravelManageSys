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
public class Silver extends Ticket {
    int Scounter;

    public Silver() {
        // super.ticketExpiration=; the date the user will choose from the bookoing
        // function
    }

    @Override
    public double Ticket_Price() {
        return 0.05;
    }

    public void silver_welcome() {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t       SILVER TICKET");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    public void addSliver() {
        System.out.println("how many sliver tickets you want?");
        addeddTicket = in.nextInt();
        Scounter += addeddTicket;
        System.out.println("Done! " + addeddTicket + " sliver tickets added to your cart");
    }

    public void deleteSliver() {
        System.out.println("how many sliver tickets you want to delete?");
        deletedTicket = in.nextInt();
        Scounter -= deletedTicket;
        System.out.println("Done! " + deletedTicket + " sliver tickets deleted from your cart");
    }
}
