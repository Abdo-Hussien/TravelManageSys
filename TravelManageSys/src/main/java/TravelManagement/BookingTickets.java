package TravelManagement;

import java.util.Scanner;

public class BookingTickets {

    char choice;
    Scanner in = new Scanner(System.in);
    Silver silver = new Silver();
    Gold gold = new Gold();
    Platinum platinum = new Platinum();
    int totalTickets = 0;

    public void ticketTypeMenu() {
        ticketDisplayMenu("add");
    }

    

    public void TicketMainMenu() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Book More Tickets!");
        System.out.println("2. Delete Ticket");
        System.out.println("3. Confirm Your Tickets");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice == '1') {
            ticketDisplayMenu("add");
        } else if (choice == '2') {
            ticketDisplayMenu("delete");

        } else if (choice == '3') {
            System.out.println("Your Ticket(s) Has Been Confirmed Sucussfully!");
            System.out.println("You Booked:");
            System.out.println(silver.Scounter + " Silver Tickets(Regular)");
            System.out.println(gold.Gcounter + " Gold Tickets");
            System.out.println(platinum.Pcounter + " Platinum Tickets");
            totalTickets = silver.Scounter + gold.Gcounter + platinum.Pcounter;
            System.out.println("Total Tickets: " + totalTickets);
        } else {
            System.out.println("Invalid Input, Please Try Again.");
            TicketMainMenu();
        }
    }

    public void ticketDisplayMenu(String s) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose Your Ticket Type:");
        System.out.println("1. Silver Ticket (Regular Ticket) ");
        System.out.println("2. Gold Ticket ");
        System.out.println("3. Platinum Ticket ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice == '1') {
            if (s.equals("add")) {
                silver.silver_welcome();
                silver.addSliver();
            } else if (s.equals("delete")) {
                silver.silver_welcome();
                silver.deleteSliver();
            }
            System.out.println("go to main menu");
            choice = in.next().charAt(0);
            if (choice == 'y') {
                TicketMainMenu();
            }

        } else if (choice == '2') {
            if (s.equals("add")) {
                gold.gold_welcome();
                gold.addGold();
            } else if (s.equals("delete")) {
                gold.gold_welcome();
                gold.deleteGold();
            }
            System.out.println("go to main menu");
            choice = in.next().charAt(0);
            if (choice == 'y') {
                TicketMainMenu();
            }
        } else if (choice == '3') {
            if (s.equals("add")) {
                platinum.platinum_welcome();
                platinum.addPlatinum();
            } else if (s.equals("delete")) {
                platinum.platinum_welcome();
                platinum.deletePlatinum();
            }
                        System.out.println("go to main menu");
            choice = in.next().charAt(0);
            if (choice == 'y') {
                TicketMainMenu();
            }
        } else {
            System.out.println("Invalid Number, Please Try Again.");
            ticketTypeMenu();
        }
    }
}