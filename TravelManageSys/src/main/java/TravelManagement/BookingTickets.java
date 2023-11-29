package TravelManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingTickets {

    char choice;
    Scanner in = new Scanner(System.in);
    int totalTickets = 0;
    ArrayList<Ticket> ticketList = new ArrayList<Ticket>();

    public void ticketMenu(String status) {
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t           TICKETS");
        System.out.println("\t\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose Your Ticket Type:");
        System.out.println("1. Silver Ticket (Regular Ticket) ");
        System.out.println("2. Gold Ticket ");
        System.out.println("3. Platinum Ticket ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);

        if (choice == '1') {
            if (status.equals("new")) {
                ticketList.add(new Silver());
                ticketList.get(0).Add();
                ticketList.get(0).setType();

                TicketEditMenu();
            } else if (status.equals("old")) {
                for (int i = 0; i < ticketList.size(); i++) {
                    if (ticketList.get(i).getType().equals("silver")) {
                        ticketList.get(i).Add();
                        TicketEditMenu();
                    } else {
                        System.out.println("invalid Ticket Type");
                    }
                }
            }
        } else if (choice == '2') {
            if (status.equals("new")) {
                ticketList.add(new Gold());
                ticketList.get(0).Add();
            } else if (status.equals("old")) {
                for (int i = 0; i < ticketList.size(); i++) {
                    if (ticketList.get(i).getType().equals("gold")) {
                        ticketList.get(i).Add();
                        ticketList.get(0).setType();
                    }
                }
            }
        } else if (choice == '3') {
            if (status.equals("new")) {
                ticketList.add(new Platinum());
                ticketList.get(0).Add();
            } else if (status.equals("old")) {
                for (int i = 0; i < ticketList.size(); i++) {
                    if (ticketList.get(i).getType().equals("platinum")) {
                        ticketList.get(i).Add();
                        ticketList.get(0).setType();
                    }
                }
            }
        } else {
            System.out.println("invalid input! please try again");
            ticketMenu("new");
        }

    }

    public void TicketEditMenu() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Book More Tickets!");
        System.out.println("2. Delete Ticket");
        System.out.println("3. Confirm Your Tickets");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice == '1') {
            ticketMenu("old");
        } else if (choice == '2') {
            DeleteTicket();
        } else if (choice == '3') {
            confirmTicket();
        } else {
            System.out.println("Invalid Input, Please Try Again.");
            TicketEditMenu();
        }
    }

    public void DeleteTicket() {
        System.out.println("Your Cart:");
        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println(ticketList.get(i).type);
            System.out.println(ticketList.get(i).getCounter());
            System.out.println("**********");
        }
        System.out.println("what type of ticket you want to delete?");
        String ans = in.next();
        if (ans.toLowerCase().equals("silver")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("silver")) {
                    ticketList.get(i).Delete();
                    TicketEditMenu();
                }
            }
        } else if (ans.toLowerCase().equals("gold")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("gold")) {
                    ticketList.get(i).Delete();
                }
            }
        } else if (ans.toLowerCase().equals("platinum")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("platinum")) {
                    ticketList.get(i).Delete();
                }
            }
        }
    };

    public void confirmTicket() {
        System.out.println("Your Ticket(s) Has Been Confirmed Sucussfully!");
        System.out.println("You Booked:");
        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println(ticketList.get(i).getCounter() + " " + ticketList.get(i).getType() + " tickets.");
            totalTickets += ticketList.get(i).getCounter();
        }
        System.out.println("Total Tickets: " + totalTickets);
    }
}
