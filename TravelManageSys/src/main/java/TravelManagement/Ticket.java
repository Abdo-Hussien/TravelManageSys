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
public abstract class Ticket {

    protected String ticketId;
    protected Scanner in = new Scanner(System.in);
    protected int addeddTicket, deletedTicket;
    protected String type;

    public String getType() {
        return type;
    }

    public abstract void setType();

    public void ticketTypeheader(String type) {
        System.out.printf("%-3s %s\n", "", "~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%-12s %s\n", "", type.toUpperCase());
        System.out.printf("%-3s %s\n", "", "~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public abstract int Add();

    public abstract int Delete();

    public abstract int getCounter();

    public abstract void setCounter(int counter);

    public abstract double Ticket_Price(double price);

    public String getTicketID() {
        return this.ticketId;
    }

}
