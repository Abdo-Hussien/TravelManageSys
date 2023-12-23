/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import AccountManagement.RandIDGenerator;

/**
 *
 * @author bmood
 */
public class Silver extends Ticket {
    private int Scounter;

    public Silver(String TicketID, String TicketType, int TicketCounter) {
        ticketId = TicketID;
        type = TicketType;
        Scounter = TicketCounter;
    }

    public Silver() {
        // super.ticketExpiration=; the date the user will choose from the bookoing
        // function

        RandIDGenerator generator = new RandIDGenerator();
        generator.setItemCount(3);
        generator.generateRandID();
        ticketId = generator.getRandID();
    }

    @Override
    public double Ticket_Price(double price) {
        return (0.05 * price) + price;
    }

    public int Add() {
        ticketTypeheader("Silver");
        System.out.println("How many sliver tickets do you want?");
        addeddTicket = in.nextInt();
        in.nextLine();
        if (addeddTicket > 0) {
            Scounter += addeddTicket;
            System.out.println("Done! " + addeddTicket + " sliver tickets added to your cart!");
            return addeddTicket;
        } else {
            System.out.println("Invalid number of tickets entered! please try again...");
            return Add();
        }

    }

    public int Delete() {
        System.out.println("How many sliver tickets do you want to delete?");
        deletedTicket = in.nextInt();
        in.nextLine();
        if (deletedTicket > 0) {
            if (deletedTicket > Scounter) {
                System.out.println("You entered more tickets than you have! please try again...");
                return Delete();
            } else {
                Scounter -= deletedTicket;
                System.out.println("Done! " + deletedTicket + " sliver tickets deleted from your cart!");
                return deletedTicket;
            }
        } else {
            System.out.println("Invalid number of tickets entered! please try again...");
            return Delete();
        }
    }

    @Override
    public void setCounter(int counter) {
        this.Scounter = counter;
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
