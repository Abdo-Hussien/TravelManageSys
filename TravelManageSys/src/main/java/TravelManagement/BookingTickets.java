package TravelManagement;

import java.util.ArrayList;
import java.util.Scanner;

import AccountManagement.Customers;

public class BookingTickets {

    int choice;
    Scanner in = new Scanner(System.in);
    int totalTickets = 0;
    ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
    boolean Typeisfound = false;
    int index;

    public boolean ticketMenu(Customers customer, ChosenTrip ChosenTrip,
            ArrayList<Trip> AllTrip, ArrayList<Car> allCars) {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.printf("%-3s %s\n", "", "~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%-12s %s\n", "", "TICKETS");
        System.out.printf("%-3s %s\n", "", "~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose Your Ticket Type:");
        System.out.println("1. Silver Ticket (Regular Ticket) ");
        System.out.println("2. Gold Ticket ");
        System.out.println("3. Platinum Ticket ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Choice: ");
        choice = in.nextInt();
        in.nextLine();
        switch (choice) {
            case 1:
                AddTicket(ChosenTrip, AllTrip, Silver.class, new Silver());
                break;
            case 2:
                AddTicket(ChosenTrip, AllTrip, Gold.class, new Gold());
                break;
            case 3:
                AddTicket(ChosenTrip, AllTrip, Platinum.class, new Platinum());
                break;
            default:
                System.out.println("Invalid input! please try again...");
                return ticketMenu(customer, ChosenTrip, AllTrip, allCars);
        }
        return TicketEditMenu(customer, ChosenTrip, AllTrip, allCars);
    }

    public void AddTicket(ChosenTrip ChosenTrip,
            ArrayList<Trip> AllTrip, Class<? extends Ticket> ticketType, Ticket newTicket) {
        double tripPrice = 0;
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketType.isInstance(ticketList.get(i))) {
                Typeisfound = true;
                index = i;
            }
        }
        if (Typeisfound == true) {
            tripPrice = calcTicketPrice(ChosenTrip, AllTrip, index);
            ChosenTrip.addToTotalPrice(ticketList.get(index).Add() * tripPrice);
        } else {
            ticketList.add(newTicket);
            tripPrice = calcTicketPrice(ChosenTrip, AllTrip, ticketList.size() - 1);
            ChosenTrip.addToTotalPrice(ticketList.get(ticketList.size() - 1).Add() * tripPrice);
            ticketList.get(ticketList.size() - 1).setType(); /////
        }
    }

    private double calcTicketPrice(ChosenTrip ChosenTrip,
            ArrayList<Trip> AllTrip, int index_of_ticket) {
        double tripPrice = 0;
        if (ticketList.get(index_of_ticket) instanceof Silver)
            tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.05);
        if (ticketList.get(index_of_ticket) instanceof Gold)
            tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.3);
        if (ticketList.get(index_of_ticket) instanceof Platinum)
            tripPrice = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(0.6);
        return tripPrice;
    }

    public boolean TicketEditMenu(Customers customer, ChosenTrip ChosenTrip,
            ArrayList<Trip> AllTrip, ArrayList<Car> allCars) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Book More Tickets!");
        System.out.println("2. Delete Ticket");
        System.out.println("3. Confirm Your Tickets");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.nextInt();
        in.nextLine();
        if (choice == 1) {
            return ticketMenu(customer, ChosenTrip, AllTrip, allCars);
        } else if (choice == 2) {
            boolean isDeleted = ViewCart(customer, ChosenTrip, AllTrip, allCars);
            if (isDeleted) // If Not Deleted then Error
                return isDeleted;
            CustomMessage("No tickets Deleted: You chose a ticket that isn't added to your cart...", 500);
            return TicketEditMenu(customer, ChosenTrip, AllTrip, allCars);
        } else if (choice == 3) {
            return confirmTicket(customer, ChosenTrip, AllTrip, allCars);
        } else {
            System.out.println("Invalid Input, Please Try Again.");
            return TicketEditMenu(customer, ChosenTrip, AllTrip, allCars);
        }
    }

    public boolean ViewCart(Customers customer, ChosenTrip ChosenTrip,
            ArrayList<Trip> allTrips, ArrayList<Car> allCars) {
        int Counter = 0;
        for (Ticket ticket : ticketList)
            Counter += ticket.getCounter();
        if (Counter == 0) {
            CustomMessage("You have 0 tickets!", 500);
            return TicketEditMenu(customer, ChosenTrip, allTrips, allCars);
        }
        System.out.println("Your Cart:");
        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println(ticketList.get(i).getCounter() + " " + ticketList.get(i).type + " tickets");
            System.out.println("**********");
        }
        return DeleteTicket(customer, ChosenTrip, allTrips, allCars);
    }

    public boolean DeleteTicket(Customers customer, ChosenTrip ChosenTrip,
            ArrayList<Trip> allTrips, ArrayList<Car> allCars) {
        System.out.println("What type of ticket you want to delete?" + "   \'Enter Ticket type\' ");
        System.out.println("Example: Silver, Regular, Gold, ..."); // silver hya hya regular
        System.out.println("Back to return to ticket editing menu!");
        String ans = in.next();
        in.nextLine();
        if (ans.length() < 3) { // remove if
            System.out.println("Invalid input! please try again");
            return DeleteTicket(customer, ChosenTrip, allTrips, allCars);
        }
        if ("silver".contains(ans.toLowerCase()) || "regular".contains(ans.toLowerCase())) {
            return checkTicketDeletion(customer, allTrips, ChosenTrip, "silver", 0.05, allCars);
        } else if ("gold".contains(ans.toLowerCase())) {
            return checkTicketDeletion(customer, allTrips, ChosenTrip, "gold", 0.3, allCars);
        } else if ("platinum".contains(ans.toLowerCase())) {
            return checkTicketDeletion(customer, allTrips, ChosenTrip, "platinum", 0.6, allCars);
        } else if ("back".contains(ans.toLowerCase()))
            return TicketEditMenu(customer, ChosenTrip, allTrips, allCars);
        else {
            System.out.println("Invalid input! please try again");
            return DeleteTicket(customer, ChosenTrip, allTrips, allCars); // elmafrod ttl3 3nd el ticket type
        }
    }

    private boolean checkTicketDeletion(Customers customer, ArrayList<Trip> allTrips,
            ChosenTrip ChosenTrip, String ticket_type, double rate, ArrayList<Car> allCars) { /////
        double tripPrice;
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i).getType().equalsIgnoreCase(ticket_type)) {
                tripPrice = allTrips.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000).TripPrice(rate);
                ChosenTrip.subtractFromTotalPrice(ticketList.get(i).Delete() * tripPrice);
                return TicketEditMenu(customer, ChosenTrip, allTrips, allCars);
            }
        }
        return false;
    }

    public boolean confirmTicket(Customers customer, ChosenTrip ChosenTrip,
            ArrayList<Trip> AllTrip, ArrayList<Car> allCars) {
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i).getCounter() == 0) {
                ticketList.remove(i);
                i--;
                continue;
            }
            totalTickets += ticketList.get(i).getCounter();
            if (ticketList.get(i) instanceof Platinum && ChosenTrip.getCarID() != null) {
                double carPrice = allCars.get(Integer.parseInt(ChosenTrip.getCarID()) - 2000).getPrice();
                ChosenTrip.subtractFromTotalPrice(carPrice);
                CustomMessage("Car rental for free!", 500);
            }
        }
        Trip trip = AllTrip.get(Integer.parseInt(ChosenTrip.getTripID()) - 1000);
        int alltickets = trip.getTicketCounter() + totalTickets;
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        if (!CheckTripCapacity(AllTrip, ChosenTrip.getTripID(), alltickets)) {
            CustomMessage("Booking Cancelled (REASON):\n ~ Added tickets exceeds trip capacity.\n\n ~ Available seats: "
                    + (trip.getCapacity() - trip.getTicketCounter()), 2000);
            return false;
        }
        System.out.println("Your Ticket(s) Has Been Confirmed Sucussfully!");
        System.out.println("You Booked:");
        for (Ticket ticket : ticketList)
            System.out.println(ticket.getCounter() + " " + ticket.getType() + " tickets.");
        System.out.println("Total Tickets: " + totalTickets);
        boolean foundOldBookedTrip = false;
        if (customer.getCustomerBookedTrips() != null && customer.getCustomerBookedTrips().size() != 0) {
            foundOldBookedTrip = BookedTravels.appendTicket(customer, ticketList,
                    AllTrip, ChosenTrip);
        }

        if (!foundOldBookedTrip) {
            if (customer.discountActive()) {
                ChosenTrip.setTotalPrice(customer.applyDiscount(ChosenTrip.getTotalPrice()));
                CustomMessage("Discount applied", 1000);
            }
            BookedTravels oldBookedTravels = new BookedTravels(ChosenTrip, ticketList);
            customer.getCustomerBookedTrips().add(oldBookedTravels);
        }
        CustomMessage("You successfully booked " + ChosenTrip.getTripName() + " Trip", 300);
        ticketList = new ArrayList<Ticket>();
        totalTickets = 0;
        return true;
    }

    private boolean CheckTripCapacity(ArrayList<Trip> tripsList, String tripID, int tickets) {
        if (tripsList.get(Integer.parseInt(tripID) - 1000).getCapacity() >= tickets) {
            return true;
        }
        return false;
    }

    private void CustomMessage(String message, int timeout) {
        try {
            System.out.println(message);
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            System.out.println("Thread error sleeping.");
        }
    }

}
