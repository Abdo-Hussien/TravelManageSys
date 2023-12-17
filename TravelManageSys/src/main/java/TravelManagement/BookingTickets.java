package TravelManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingTickets {

    int choice;
    Scanner in = new Scanner(System.in);
    int totalTickets = 0;
    ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
    boolean Typeisfound = false;
    int index;
    ArrayList<BookedTravels> bookedTravels;

    public boolean ticketMenu(ArrayList<BookedTravels> bookedTravels, ChosenTrip ChosenTrip,
            ArrayList<Trip> AllTrip) {
        double tripPrice;
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
        choice = in.nextInt();
        if (choice == 1) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("silver")) {
                    Typeisfound = true;
                    index = i;
                }
            }
            if (Typeisfound == true) {
                tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.05);
                ChosenTrip.addToTotalPrice(ticketList.get(index).Add() * tripPrice);
            } else {
                ticketList.add(new Silver());
                tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.05);
                ChosenTrip.addToTotalPrice(ticketList.get(ticketList.size() - 1).Add() * tripPrice);
                ticketList.get(ticketList.size() - 1).setType();
            }
            return TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
        } else if (choice == 2) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("gold")) {
                    Typeisfound = true;
                    index = i;
                }
            }
            if (Typeisfound == true) {
                tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.3);
                ChosenTrip.addToTotalPrice(ticketList.get(index).Add() * tripPrice);
            } else {
                ticketList.add(new Gold());
                tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.3);
                ChosenTrip.addToTotalPrice(ticketList.get(ticketList.size() - 1).Add() * tripPrice);
                ticketList.get(ticketList.size() - 1).setType();
            }
            return TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
        } else if (choice == 3) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("platinum")) {
                    Typeisfound = true;
                    index = i;
                }
            }
            if (Typeisfound == true) {
                tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.6);
                ChosenTrip.addToTotalPrice(ticketList.get(index).Add() * tripPrice);
            } else {
                ticketList.add(new Platinum());
                tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.6);
                ChosenTrip.addToTotalPrice(ticketList.get(ticketList.size() - 1).Add() * tripPrice);
                ticketList.get(ticketList.size() - 1).setType();

            }
            return TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
        } else {
            System.out.println("invalid input! please try again");
            return ticketMenu(bookedTravels, ChosenTrip, AllTrip);
        }

    }

    public boolean TicketEditMenu(ArrayList<BookedTravels> bookedTravels, ChosenTrip ChosenTrip,
            ArrayList<Trip> AllTrip) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Book More Tickets!");
        System.out.println("2. Delete Ticket");
        System.out.println("3. Confirm Your Tickets");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.nextInt();

        if (choice == 1) {
            return ticketMenu(bookedTravels, ChosenTrip, AllTrip);
        } else if (choice == 2) {
            return DeleteTicket(bookedTravels, ChosenTrip, AllTrip);
        } else if (choice == 3) {
            return confirmTicket(bookedTravels, ChosenTrip, AllTrip);
        } else {
            System.out.println("Invalid Input, Please Try Again.");
            return TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
        }
    }

    public boolean DeleteTicket(ArrayList<BookedTravels> bookedTravels, ChosenTrip ChosenTrip, ArrayList<Trip> AllTrip) {
        double tripPrice;
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
                    tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.05);
                    ChosenTrip.subtractFromTotalPrice(ticketList.get(i).Delete() * tripPrice);
                    return TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
                }
            }
        } else if (ans.toLowerCase().equals("gold")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("gold")) {
                    tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.3);
                    ChosenTrip.subtractFromTotalPrice(ticketList.get(i).Delete() * tripPrice);
                    return TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
                }
            }
        } else if (ans.toLowerCase().equals("platinum")) {
            for (int i = 0; i < ticketList.size(); i++) {
                if (ticketList.get(i).getType().equals("platinum")) {
                    tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.6);
                    ChosenTrip.subtractFromTotalPrice(ticketList.get(i).Delete() * tripPrice);
                    return TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
                }
            }
        } else if (ans.toLowerCase().equals("back")) {
            return TicketEditMenu(bookedTravels, ChosenTrip, AllTrip);
        } else {
            System.out.println("Invalid input! please try again");
            return DeleteTicket(bookedTravels, ChosenTrip, AllTrip);
        }
        return false;
    };

    public boolean confirmTicket(ArrayList<BookedTravels> bookedTravels, ChosenTrip ChosenTrip,
            ArrayList<Trip> AllTrip) {
        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println(ticketList.get(i).getCounter() + " " + ticketList.get(i).getType() + " tickets.");
            totalTickets += ticketList.get(i).getCounter();
        }
        AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).setTicketCounter(totalTickets);
        if (!CheckTripCapacity(AllTrip, ChosenTrip.getTripID())) {
            AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).setTicketCounter(-totalTickets);
            try {
                System.out.println("Booking Cancelled (REASON):\n~ Trip is full!\n~ Trip Capacity is "
                        + AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).getCapacity());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Thread error sleeping.");
            }
            return false;
        }
        System.out.println("Your Ticket(s) Has Been Confirmed Sucussfully!");
        System.out.println("You Booked:");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Enjoy your trip!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        boolean foundOldBookedTrip = false;
        if (bookedTravels != null && bookedTravels.size() != 0) {
            foundOldBookedTrip = BookedTravels.appendTicket(bookedTravels, ticketList, ChosenTrip.getTripID());
        }
        if (!foundOldBookedTrip) {
            BookedTravels oldBookedTravels = new BookedTravels(ChosenTrip.getTripID(), ChosenTrip.getTripName(),
                    ChosenTrip.getStartDate(), ChosenTrip.getEndDate(),
                    ticketList, ChosenTrip.getTotalPrice(), ChosenTrip.getCarID());
            bookedTravels.add(oldBookedTravels);
        }

        try {
            System.out.println("You successfully booked " + ChosenTrip.getTripName() + " Trip");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread error sleeping.");
        }
        ticketList = new ArrayList<Ticket>();
        totalTickets = 0;
        return true;
    }

    private boolean CheckTripCapacity(ArrayList<Trip> tripsList, String tripID) {
        if (tripsList.get(Integer.parseInt(tripID) - 1000).getCapacity() > tripsList
                .get(Integer.parseInt(tripID) - 1000).getTicketCounter()) {
            return true;
        }
        return false;
    }
}
