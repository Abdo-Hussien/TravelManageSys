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

    public void ticketMenu(ArrayList<BookedTravels> bookedTravels, ChosenTrip ChosenTrip, ArrayList<Trip> AllTrip) {
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
            TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
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
            TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);

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
            TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
        } else {
            System.out.println("invalid input! please try again");
            ticketMenu(bookedTravels, ChosenTrip, AllTrip);
        }

    }

    public void TicketEditMenu(ArrayList<BookedTravels> bookedTravels, ChosenTrip ChosenTrip, ArrayList<Trip> AllTrip) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Book More Tickets!");
        System.out.println("2. Delete Ticket");
        System.out.println("3. Confirm Your Tickets");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice == '1') {
            ticketMenu(bookedTravels, ChosenTrip, AllTrip);
        } else if (choice == '2') {
            DeleteTicket(bookedTravels, ChosenTrip, AllTrip);
        } else if (choice == '3') {
            confirmTicket(bookedTravels, ChosenTrip, AllTrip);
            for (int i = 0; i < bookedTravels.size(); i++) {
                System.out.println(bookedTravels.get(i).tripId);
                System.out.println(bookedTravels.get(i).tripName);
                for (int j = 0; j < bookedTravels.get(i).Bookedticket.size(); j++) {
                    System.out.println(bookedTravels.get(i).Bookedticket.get(j).getCounter() + " "
                            + bookedTravels.get(i).Bookedticket.get(j).getType());
                }
            }
            int size = bookedTravels.size();
            System.out.println(size);
            ticketList = new ArrayList<Ticket>();
        } else {
            System.out.println("Invalid Input, Please Try Again.");
            TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
        }
    }

    public void DeleteTicket(ArrayList<BookedTravels> bookedTravels, ChosenTrip ChosenTrip, ArrayList<Trip> AllTrip) {
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
                    TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
                }
            }
        } else if (ans.toLowerCase().equals("gold")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("gold")) {
                    ticketList.get(i).Delete();
                    TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
                }
            }
        } else if (ans.toLowerCase().equals("platinum")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("platinum")) {
                    ticketList.get(i).Delete();
                    TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
                }
            }
        } else if (ans.toLowerCase().equals("back")) {
            TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
        } else {
            System.out.println("Invalid input! please try again");
            DeleteTicket(bookedTravels, ChosenTrip, AllTrip);
        }
    };

    public ArrayList<BookedTravels> confirmTicket(ArrayList<BookedTravels> bookedTravels, ChosenTrip ChosenTrip,
            ArrayList<Trip> AllTrip) {
        System.out.println("Your Ticket(s) Has Been Confirmed Sucussfully!");
        System.out.println("You Booked:");
        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println(ticketList.get(i).getCounter() + " " + ticketList.get(i).getType() + " tickets.");
            totalTickets += ticketList.get(i).getCounter();
        }
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Enjoy your trip!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        BookedTravels oldBookedTravels = new BookedTravels(ChosenTrip.getTripId(), ChosenTrip.getTripName(), null, null,
                ticketList);
        bookedTravels.add(oldBookedTravels);
        for (int i = 0; i < AllTrip.size(); i++) {
            if (AllTrip.get(i).getTripId().equals(ChosenTrip.getTripId())) {
                AllTrip.get(i).setTicketCounter(totalTickets);
            }
        }
        return bookedTravels;
    }
}
