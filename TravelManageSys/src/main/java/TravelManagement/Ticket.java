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

    protected int ticketId;
    protected String ticketExpiration; // Ticket expiration from mohamed booking function
    protected Scanner in = new Scanner(System.in);
    protected int addeddTicket,deletedTicket;
    public double Ticket_Price() {
        return 0;
    }


}
