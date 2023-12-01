package TravelManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingTickets {

    char choice;
    Scanner in = new Scanner(System.in);
    int totalTickets = 0;
    ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
    boolean Typeisfound = false;
    int index;
    ArrayList<BookedTravels> bookedTravels;

    public void ticketMenu(ArrayList<BookedTravels> bookedTravels, Trip ChosenTrip) {
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
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("silver")) {
                    Typeisfound = true;
                    index = i;
                }
            }
            if (Typeisfound == true) {
                ticketList.get(index).Add();
            } else {
                ticketList.add(new Silver());
                ticketList.get(ticketList.size() - 1).Add();
                ticketList.get(ticketList.size() - 1).setType();

            }
            TicketEditMenu(bookedTravels, ChosenTrip);
        } else if (choice == '2') {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("gold")) {
                    Typeisfound = true;
                    index = i;
                }
            }
            if (Typeisfound == true) {
                ticketList.get(index).Add();
            } else {
                ticketList.add(new Gold());
                ticketList.get(ticketList.size() - 1).Add();
                ticketList.get(ticketList.size() - 1).setType();
            }
            TicketEditMenu(bookedTravels, ChosenTrip);

        } else if (choice == '3') {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("platinum")) {
                    Typeisfound = true;
                    index = i;
                }
            }
            if (Typeisfound == true) {
                ticketList.get(index).Add();
            } else {
                ticketList.add(new Platinum());
                ticketList.get(ticketList.size() - 1).Add();
                ticketList.get(ticketList.size() - 1).setType();

            }
            TicketEditMenu(bookedTravels, ChosenTrip);
        } else {
            System.out.println("invalid input! please try again");
            ticketMenu(bookedTravels, ChosenTrip);
        }

    }

    public void TicketEditMenu(ArrayList<BookedTravels> bookedTravels, Trip ChosenTrip) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Book More Tickets!");
        System.out.println("2. Delete Ticket");
        System.out.println("3. Confirm Your Tickets");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice == '1') {
            ticketMenu(bookedTravels, ChosenTrip);
        } else if (choice == '2') {
            DeleteTicket(bookedTravels, ChosenTrip);
        } else if (choice == '3') {
            confirmTicket(bookedTravels, ChosenTrip);
        } else {
            System.out.println("Invalid Input, Please Try Again.");
            TicketEditMenu(bookedTravels, ChosenTrip);
        }
    }

    public void DeleteTicket(ArrayList<BookedTravels> bookedTravels, Trip ChosenTrip) {
        System.out.println("Your Cart:");
        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println(ticketList.get(i).getCounter() + " " + ticketList.get(i).type + " tickets");
            System.out.println("**********");
        }
        System.out.println("what type of ticket you want to delete?" + "\"enter ticketType\" ");
        System.out.println("back to return to main menu");
        String ans = in.next();
        if (ans.toLowerCase().equals("silver")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("silver")) {
                    ticketList.get(i).Delete();
                    TicketEditMenu(bookedTravels, ChosenTrip);
                }
            }
        } else if (ans.toLowerCase().equals("gold")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("gold")) {
                    ticketList.get(i).Delete();
                    TicketEditMenu(bookedTravels, ChosenTrip);
                }
            }
        } else if (ans.toLowerCase().equals("platinum")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("platinum")) {
                    ticketList.get(i).Delete();
                    TicketEditMenu(bookedTravels, ChosenTrip);
                }
            }
        } else if (ans.toLowerCase().equals("back")) {
            TicketEditMenu(bookedTravels, ChosenTrip);
        } else {
            System.out.println("Invalid input! please try again");
            DeleteTicket(bookedTravels, ChosenTrip);
        }
    };

    public ArrayList<Ticket> confirmTicket(ArrayList<BookedTravels> bookedTravels, Trip ChosenTrip) {
        System.out.println("Your Ticket(s) Has Been Confirmed Sucussfully!");
        System.out.println("You Booked:");
        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println(ticketList.get(i).getCounter() + " " + ticketList.get(i).getType() + " tickets.");
            totalTickets += ticketList.get(i).getCounter();
        }
        bookedTravels.get(bookedTravels.size() - 1).Bookedticket = ticketList;
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Enjoy your trip!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        // bookedTravels.get(index).Bookedticket = ticketList;
        return ticketList;
    }
}
