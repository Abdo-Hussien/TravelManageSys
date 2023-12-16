
package AccountManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import TravelManagement.BookedTravels;
import TravelManagement.BookingTickets;
import TravelManagement.ChosenTrip;
import TravelManagement.GeneralTours;
import TravelManagement.Transportation;
import TravelManagement.Trip;

public class Customers extends Person implements Personsinterface {
    // Filter Search Preferences
    private double price_start;
    private double price_end;
    private String search_text;
    private String start_date;
    private String end_date;
    // private CustomerBooking BookingManipulations;
    private ArrayList<String> tripsHistory;
    static int choice;
    protected static Scanner in = new Scanner(System.in);

    public Customers(String account_id, String first_name, String last_name, String username, String password, int age,
            String gender, String address, String phone_number, ArrayList<BookedTravels> oldBookingTrips,
            ArrayList<String> tripHistory) {
        super(first_name, last_name, username, age, phone_number, address, password, gender, account_id);
        Matcher matcher = Pattern.compile("\\s*([\\s\\S\\dA-Za-z]*?)\\s*\\|\\s*")
                .matcher(address);
        if (matcher.find())
            streetAddress = matcher.group(1);
        if (matcher.find())
            stateAddress = matcher.group(1);
        if (matcher.find())
            zipAddress = matcher.group(1);
        this.CustomerBookedTickets = new BookingTickets();
        this.CustomerTravelHistory = tripHistory;
        this.CustomerBookedTrips = oldBookingTrips;
        this.tripsHistory = tripHistory;
    }

    public Customers() {
        this.CustomerBookedTickets = new BookingTickets();
        this.CustomerTravelHistory = new ArrayList<>();
        this.CustomerBookedTrips = new ArrayList<>();
        tripsHistory = new ArrayList<>();
    }

    public int getTripHistoryCounter() {
        return tripsHistory.isEmpty() ? 0 : tripsHistory.size();
    }

    public void settripHistory(ArrayList<BookedTravels> bookedTravels) {
        for (int i = 0; i < bookedTravels.size(); i++) {
            tripsHistory.add(bookedTravels.get(i).getTripId());
        }
    }

    public void showinfo(ArrayList<Customers> allCustomers, ArrayList<Trip> allTrips) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Your information:");
        System.out.println("ID: " + this.getAccount_id());
        System.out.println("Full name: " + this.getFirst_name() + " "
                + this.getLast_name());
        System.out.println("Age: " + this.getAge());
        System.out.println("Username: " + this.getUsername());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Phone number: " + this.getPhone_number());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (true) {

            System.out.println("1- Edit your information\n2- Go back");
            int choice = in.next().charAt(0);
            switch (choice) {
                case '1':
                    Admin admin = new Admin();
                    admin.editInformations("old", allCustomers, "Customers", "Customer");
                    break;
                case '2':
                    UserMainMenu(allTrips, allCustomers);
                    break;

                default:
                    System.out.println("Wrong input! Please try again..");
                    continue;
            }
        }
    }

    public void displayBookedTrips(ArrayList<Trip> AllTrips) {
        this.CustomerBookedTrips.stream()
                .forEach(bookedTrip -> {
                    int[] TicketsCounter = { 0 };
                    Transportation transportation = AllTrips.get(Integer.parseInt(bookedTrip.getTripId()) - 1000)
                            .getTransportation(
                                    AllTrips.get(Integer.parseInt(bookedTrip.getTripId()) - 1000).getTransportID());
                    bookedTrip.getBookedticket().forEach(ticket -> {
                        TicketsCounter[0] += ticket.getCounter();
                    });
                    System.out.printf("%s\n",
                            "~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~~-~-~-~-~-~-~-~-~-~-~-~");
                    System.out.printf("%-40s%s\n\n",
                            "", AllTrips.get(Integer.parseInt(bookedTrip.getTripId()) - 1000).getTitle());
                    System.out.printf("Tour Guide ID : %-10s%n",
                            AllTrips.get(Integer.parseInt(bookedTrip.getTripId()) - 1000).getTourGuideID());
                    System.out.printf("Type : %-30sTotal Price : %-30sTrip ID : %-10s%n",
                            AllTrips.get(Integer.parseInt(bookedTrip.getTripId()) - 1000).getTripType(),
                            bookedTrip.getTotalPrice(), bookedTrip.getTripId());
                    System.out.printf("Trip Capacity : %-51sNumber of tickets bought : %-10s%n",
                            AllTrips.get(Integer.parseInt(bookedTrip.getTripId()) - 1000).getCapacity(),
                            TicketsCounter[0]);
                    System.out.printf("Start Date : %-42sEnd Date : %-20s\n\n", bookedTrip.getStartDate(),
                            bookedTrip.getEndDate());
                    System.out.printf("| %-40s | %-38s | %-7s |%n", "Ticket ID", "Type", "Counter");
                    System.out.println(
                            "|------------------------------------------|----------------------------------------|---------|");

                    bookedTrip.getBookedticket().forEach(ticket -> {
                        System.out.printf("| %-40s | %-38s | %-7s |%n", ticket.getTicketID(), ticket.getType(),
                                ticket.getCounter());
                    });

                    System.out.println(
                            "|------------------------------------------|----------------------------------------|---------|");
                    System.out.printf("Pickup : %-30s%n", transportation.getPickUp());
                    System.out.printf("Staying At : %-30s%n",
                            AllTrips.get(Integer.parseInt(bookedTrip.getTripId()) - 1000).getHotelName());
                    String[] activities = AllTrips.get(Integer.parseInt(bookedTrip.getTripId()) - 1000).getActivities();
                    System.out.printf("Activities :%n");
                    for (String activity : activities)
                        System.out.printf("%-3s- %s%n", "", activity);
                    System.out.printf("%s\n",
                            "~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~~-~-~-~-~-~-~-~-~-~-~-~");
                });

    }

    public void mainCustomer(ArrayList<Customers> allCustomers, ArrayList<Trip> tripsList) {
        ArrayList<Trip> FeaturedTrips = new ArrayList<>();
        FeaturedTrips = tripsList.stream()
                .limit(3).collect(Collectors.toCollection(ArrayList::new));
        char Ans = ' ';
        Trip ChosenTrip = new GeneralTours();
        Scanner input = new Scanner(System.in);
        Trip.displayTrips(FeaturedTrips);
        System.out.println("\nA. Search for a trip(s)");
        System.out.println("B. Go Back");
        System.out.print("Choice: ");
        Ans = Character.toLowerCase(input.next().charAt(0));
        input.nextLine();
        switch (Ans) {
            case 'a':
                SearchTrips(Ans, ChosenTrip, allCustomers, tripsList);
                break;
            case 'b':
                UserMainMenu(tripsList, allCustomers);
                break;
            default:
                CustomMessage("Wrong Input.. Try again!", 500, allCustomers, tripsList);
                break;
        }
    }

    public ArrayList<BookedTravels> UserMainMenu(ArrayList<Trip> allTrips, ArrayList<Customers> allCustomers) {
        Scanner in = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Welcome..!!");
            System.out.println("1- Profile settings.\n2- Trip.\n3- Cart.\n4- Sign out.");
            choice = in.nextInt();
            in.nextLine();
            if (choice == 1) {
                this.showinfo(allCustomers, allTrips);
                break;
            } else if (choice == 2) {
                this.mainCustomer(allCustomers, allTrips);
                break;
            } else if (choice == 3) {
                break;
            } else if (choice == 4) {
                return null;
            } else {
                System.out.println("Invalid input! please choose only from the following options.");
                continue;
            }
        }
        return CustomerBookedTrips;
    }

    private void SearchTrips(char Ans, Trip ChosenTrip, ArrayList<Customers> allCustomers, ArrayList<Trip> tripsList) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nExample: TripName/StartDate/EndDate...");
        System.out.print("Search for a trip: ");
        ArrayList<Trip> filteredTrips = this.getFilteredTrips(input.nextLine(), allCustomers, tripsList);
        if (filteredTrips.size() == 1) {
            ShowTripDetails(filteredTrips.get(0), '?', allCustomers, tripsList);
            return;
        } else if (filteredTrips.isEmpty()) {
            CustomMessage("\nNo Trip Found with these preferences..", 3000, allCustomers, tripsList);
            return;
        }
        Trip.displaySearchTrips(filteredTrips);
        System.out.println("\nA. Search More Trips.");
        System.out.println("B. Book a Trip.");
        System.out.println("C. Show Trip details.");
        System.out.println("D. Go Back.");
        Ans = Character.toLowerCase(input.next().charAt(0));
        input.nextLine();
        switch (Ans) {
            case 'a':
                SearchTrips(Ans, ChosenTrip, allCustomers, tripsList);
                break;
            case 'b':
                System.out.println("\nWhich trip do you want to book?\t(Use the ID)");
                System.out.print("Trip ID: ");
                String tripID = input.next();
                input.nextLine();
                ChosenTrip = ChosenTrip.getTrip(tripsList, tripID);
                if (ChosenTrip == null)
                    CustomMessage("No Trips Found!", 2000, allCustomers, tripsList);
                int dateIndex = 0;
                if (ChosenTrip.getStartDates().length > 1) {
                    dateIndex = CustomerChooseDate(ChosenTrip);
                }
                if (dateIndex == -1) {
                    CustomMessage("No dates available with the given information!", 2000, allCustomers, tripsList);
                    mainCustomer(allCustomers, tripsList);
                    return;
                }
                ChosenTrip trip = new ChosenTrip(ChosenTrip.getTripType(), ChosenTrip.getTripId(),
                        ChosenTrip.getStartDates()[dateIndex], ChosenTrip.getEndDates()[dateIndex]);
                CustomerBookedTickets.ticketMenu(CustomerBookedTrips, trip, tripsList);
            case 'c':
                ShowTripDetails(ChosenTrip, Ans, allCustomers, tripsList);
                break;
            case 'd':
                mainCustomer(allCustomers, tripsList);
                break;
            default:
                CustomMessage("Wrong Input.. Try again!", 2000, allCustomers, tripsList);
                break;
        }
        return;
    }

    private void ShowTripDetails(Trip ChosenTrip, char Ans, ArrayList<Customers> allCustomers,
            ArrayList<Trip> tripsList) {
        Scanner input = new Scanner(System.in);
        if (Ans != '?') {
            System.out.println("\nWhich trip do you want to book?\t(Use the ID)");
            System.out.print("Trip ID: ");
            String tripID = input.next();
            input.nextLine();
            ChosenTrip = ChosenTrip.getTrip(tripsList, tripID);
        }
        if (ChosenTrip == null)
            CustomMessage("No Trips Found!", 2000, allCustomers, tripsList);
        ChosenTrip.displayTripDetails();
        System.out.println("A. Book " + ChosenTrip.getTitle() + " trip");
        System.out.println("B. Go Back");
        System.out.print("Choice: ");
        Ans = Character.toLowerCase(input.next().charAt(0));
        input.nextLine();
        switch (Ans) {
            case 'a':
                int dateIndex = 0;
                if (ChosenTrip.getStartDates().length > 1) {
                    dateIndex = CustomerChooseDate(ChosenTrip);
                }
                if (dateIndex == -1) {
                    CustomMessage("No dates available with the given information!", 2000, allCustomers, tripsList);
                    mainCustomer(allCustomers, tripsList);
                    return;
                }
                ChosenTrip trip = new ChosenTrip(ChosenTrip.getTripType(), ChosenTrip.getTripId(),
                        ChosenTrip.getStartDates()[dateIndex], ChosenTrip.getEndDates()[dateIndex]);
                CustomerBookedTickets.ticketMenu(CustomerBookedTrips, trip, tripsList);
                break;
            case 'b':
                mainCustomer(allCustomers, tripsList);
                break;
            default:
                CustomMessage("Wrong Input.. Try again!", 2000, allCustomers, tripsList);
                break;
        }
        return;
    }

    private int CustomerChooseDate(Trip ChosenTrip) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which Date Do you want to Book?");
        for (int i = 0; i < ChosenTrip.getStartDates().length; i++)
            System.out.println(i + 1 + ". " + ChosenTrip.getStartDates()[i]);
        int index = input.nextInt() - 1;
        input.nextLine();

        if (index > ChosenTrip.getStartDates().length || index < 0)
            return -1;

        return index;
    }

    private void CustomMessage(String message, int timeout, ArrayList<Customers> allCustomers,
            ArrayList<Trip> tripsList) {
        try {
            System.out.println(message);
            Thread.sleep(timeout);
            mainCustomer(allCustomers, tripsList);
        } catch (InterruptedException e) {
            System.out.println("Thread error sleeping.");
            // e.printStackTrace();
        }
    }

    public ArrayList<Trip> getFilteredTrips(String search_filter, ArrayList<Customers> allCustomers,
            ArrayList<Trip> tripsList) {
        ArrayList<Trip> filteredTrips = new ArrayList<>();
        String[] Filters = search_filter.split("/");
        // Split Filters.
        setFilters(Filters);

        // Iterate through the trips and filter based on the search_text
        for (Trip trip : tripsList) {
            try {
                if (tripSearch(trip, search_text, start_date, end_date, price_start, price_end)) {
                    filteredTrips.add(trip);
                }
            } catch (ParseException e) {
                System.out.println(
                        "Error in filtering..\nCaused by Invalid Date Parsing.\nProper Date Format: dd-mm-yyyy\n\n");
                mainCustomer(allCustomers, tripsList);
            }
        }
        price_start = 0;
        price_end = 0;
        search_text = null;
        start_date = null;
        end_date = null;
        return filteredTrips;
    }

    private void setFilters(String[] Filters) {
        for (int i = 0; i < Filters.length; i++) {
            if (Filters[i].matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
                start_date = Filters[i];
                end_date = i + 1 == Filters.length ? "01-12-3000" : Filters[i + 1];
                i += 1;
                continue;
            }
            if (Filters[i].matches("\\d+(\\.\\d+)?")) {
                try {
                    price_start = Double.parseDouble(Filters[i]);
                    price_end = Double.parseDouble(i + 1 == Filters.length ? "99999999" : Filters[i + 1]);
                    i += 1;
                    continue;
                } catch (Exception e) {
                    System.out.println("Process <Parsing> -> Error(s)\nParsing 'price_start', 'price_end'");
                    continue;
                }
            }
            search_text = Filters[i];
        }
    }

    private boolean tripSearch(Trip trip, String search_filter) {
        if (search_filter == null)
            return true;
        return trip.getTitle().toLowerCase().contains(search_filter.toLowerCase())
                || trip.getDescription().toLowerCase().contains(search_filter.toLowerCase())
                || trip.getTripType().toLowerCase().contains(search_filter.toLowerCase());
    }

    private boolean tripSearch(Trip trip, String search_start_date, String search_end_date) throws ParseException {
        if (search_start_date == null && search_end_date == null)
            return true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // 05-11-2023
        Date start_date = null;
        Date end_date = null;
        if (search_start_date != null)
            start_date = dateFormat.parse(search_start_date);
        if (search_end_date != null)
            end_date = dateFormat.parse(search_end_date);
        Date tripStartDates[] = trip.getStartDates();
        Date tripEndDates[] = trip.getEndDates();
        Boolean FoundStartDate = false;
        Boolean FoundEndDate = false;

        for (Date s_date : tripStartDates) {
            FoundStartDate = s_date.after(start_date) || s_date.equals(start_date);
            if (FoundStartDate)
                break;
        }
        for (Date e_date : tripEndDates) {
            FoundEndDate = e_date.after(end_date) || e_date.equals(end_date);
            if (FoundEndDate)
                break;
        }
        return FoundStartDate || FoundEndDate;
    }

    private boolean tripSearch(Trip trip, double price_start, double price_end) {
        double tripPrice = trip.getInitPrice();
        return (tripPrice >= price_start)
                && ((price_end > 0 && price_end > price_start) ? tripPrice <= price_end : true);
    }

    private boolean tripSearch(Trip trip, String search_filter, String start_date, String end_date, double price_start,
            double price_end) throws ParseException {
        return tripSearch(trip, price_start, price_end) && tripSearch(trip, start_date, end_date)
                && tripSearch(trip, search_filter);
    }

    public ArrayList<BookedTravels> getCustomerBookedTrips() {
        return CustomerBookedTrips;
    }

    public int getCustomerBookedTripsCount() {
        return CustomerBookedTrips.isEmpty() ? 0 : CustomerBookedTrips.size();
    }
}