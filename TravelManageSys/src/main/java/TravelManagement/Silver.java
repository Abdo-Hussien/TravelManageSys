/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.Scanner;

import AccountManagement.RandIDGenerator;

/**
 *
 * @author bmood
 */
public class Silver extends Ticket {
    int Scounter;

    public Silver() {
        // super.ticketExpiration=; the date the user will choose from the bookoing
        // function

        RandIDGenerator generator = new RandIDGenerator();
        generator.setItemCount(3);
        generator.generateRandID();
        ticketId = generator.getRandID();
    }
    
    @Override
    public double Ticket_Price() {
        return 0.05;
    }

    public void Add() {
        ticketTypeheader("Silver");
        System.out.println("how many sliver tickets you want?");
        addeddTicket = in.nextInt();
        if (addeddTicket > 0) {
            Scounter += addeddTicket;
            System.out.println("Done! " + addeddTicket + " sliver tickets added to your cart");
        }
        // else if(addeddTicket > tirpcapacity) {
        // System.out.println("too many tickets entered! avalibale tickerts is"+
        // avalibale);
        // }
        else {
            System.out.println("invalid number of tickets entered! Please try again.");
            Add();
        }

    }

    public void Delete() {
        System.out.println("how many sliver tickets you want to delete?");
        deletedTicket = in.nextInt();
        if (deletedTicket > 0) {
            if (deletedTicket > Scounter) {
                System.out.println("you entered more tickets than you have! please tyr again...");
                Delete();
            } else {
                Scounter -= deletedTicket;
                System.out.println("Done! " + deletedTicket + " sliver tickets deleted from your cart");
            }
        } else {

            System.out.println("invalid number of tickets entered! Please try again.");
            Delete();
        }
    }

    @Override
    public void setType() {
        this.type = "silver";
    }

    public String getType() {
        return type;
    }

    @Override
    public int getCounter() {
        return Scounter;
    }

}
