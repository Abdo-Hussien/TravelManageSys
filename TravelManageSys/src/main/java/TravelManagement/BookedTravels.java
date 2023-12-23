package TravelManagement;

import java.util.ArrayList;
import java.util.Date;

import AccountManagement.Customers;

public class BookedTravels {
    private ChosenTrip chosenTrip;
    private ArrayList<Ticket> Bookedticket = new ArrayList<Ticket>();

    // fixed bookedTravels constructor
    public BookedTravels(ChosenTrip ChosenTrip, ArrayList<Ticket> ticket) {
        this.chosenTrip = ChosenTrip;
        this.Bookedticket = ticket;
    }

    public void setChosenTrip(String tripID, String tripName, Date startDate, Date endDate,
            double totalprice, String carID) {
        this.chosenTrip.setTripID(tripID);
        this.chosenTrip.setTripName(tripName);
        this.chosenTrip.setStartDate(startDate);
        this.chosenTrip.setEndDate(endDate);
        this.chosenTrip.setTotalPrice(totalprice);
        this.chosenTrip.setCarID(carID);
    }

    public static void displayTableBookedTrips(ArrayList<BookedTravels> trips) {
        System.out.println("All Booked Trips: " + trips.size());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-7s | %-10s | %-20s | %-15s | %-12s |\n", "Index", "Trip ID", "Trip Name", "Total Price",
                "Car Rental");
        System.out.println("-----------------------------------------------------------------------------");
        for (int i = 0; i < trips.size(); i++)
            System.out.printf("%-7s | %-10s | %-20s | %-15s | %-12s |\n",
                    i + 1,
                    trips.get(i).getTripID(),
                    trips.get(i).getTripName(),
                    "$" + trips.get(i).getTotalPrice(),
                    (trips.get(i).getCarID() != null ? "true" : "false"));
        System.out.println("-----------------------------------------------------------------------------");
    }

    public String getTripID() {
        return this.chosenTrip.getTripID();
    }

    public void setTripID(String tripID) {
        this.chosenTrip.setTripID(tripID);
    }

    public String getTripName() {
        return this.chosenTrip.getTripName();
    }

    public void setTripName(String tripName) {
        this.chosenTrip.setTripName(tripName);
    }

    public Date getStartDate() {
        return this.chosenTrip.getStartDate();
    }

    public void setStartDate(Date startDate) {
        this.chosenTrip.setStartDate(startDate);
    }

    public Date getEndDate() {
        return this.chosenTrip.getEndDate();
    }

    public void setEndDate(Date endDate) {
        this.chosenTrip.setEndDate(endDate);
    }

    public double getTotalPrice() {
        return Math.round(this.chosenTrip.getTotalPrice() * 1000.0) / 1000.0;
    }

    public void addToTotalPrice(double price_to_add) {
        this.chosenTrip.addToTotalPrice(price_to_add);
    }

    public void subtractFromTotalPrice(double price_to_subtract) {
        this.chosenTrip.addToTotalPrice(price_to_subtract);
    }

    public String getCarID() {
        return this.chosenTrip.getCarID();
    }

    public void setCarID(String carID) {
        this.chosenTrip.setCarID(carID);
    }

    public void setBookedticket(ArrayList<Ticket> Bookedticket) {
        this.Bookedticket = Bookedticket;
    }

    public static boolean appendTicket(Customers customer, ArrayList<Ticket> Bookedticket,
            ArrayList<Trip> allTrips, ChosenTrip trip) {
        int i = -1;
        for (i = 0; i < customer.getCustomerBookedTrips().size(); i++) {
            if (trip.getTripID().equals(customer.getCustomerBookedTrips().get(i).getTripID())) {
                //  Append Tickets.
                customer.getCustomerBookedTrips().get(i).Bookedticket.addAll(Bookedticket);
                //  Add prices of new added tickets.
                for (Ticket ticket : Bookedticket) {
                    double ticket_price = ticket.getCounter() * ticket.Ticket_Price(allTrips.get(Integer.parseInt(trip.getTripID()) - 1000).getInitPrice());
                    if (customer.discountActive()) {
                        trip.setTotalPrice(customer.applyDiscount(ticket_price));
                        CustomMessage("Discount applied", 1000);
                    }
                    customer.getCustomerBookedTrips().get(i).chosenTrip.addToTotalPrice(trip.getTotalPrice());
                }
                //  Replace Car ID.
                customer.getCustomerBookedTrips().get(i).chosenTrip.setCarID(trip.getCarID());
                return true;
            }
        }
        return false;
    }

    public ArrayList<Ticket> getBookedticket() {
        return Bookedticket;
    }

    public void setTripPrice(double totalPrice) {
        this.chosenTrip.setTotalPrice(totalPrice);
    }

    private static void CustomMessage(String message, int timeout) {
        try {
            System.out.println(message);
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            System.out.println("Thread error sleeping.");
        }
    }

}
