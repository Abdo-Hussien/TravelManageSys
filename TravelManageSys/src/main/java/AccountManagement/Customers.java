
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
import TravelManagement.TravelItineraries;
import TravelManagement.Trip;
import data.fileManipulation;

public class Customers extends Person implements Personsinterface {
    private ArrayList<Trip> tripsList;

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
        // BookingManipulations = new CustomerBooking();
        // this.CustomerBookedTrips = oldBookingTrips;
        tripsList = new ArrayList<>();
        this.CustomerBookedTickets = new BookingTickets();
        this.CustomerTravelHistory = tripHistory;
        this.CustomerBookedTrips = oldBookingTrips;
        this.tripsHistory = tripHistory;
    }

    public Customers() {
        tripsHistory = new ArrayList<>();
        // BookingManipulations = new CustomerBooking();
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
                    System.out.println(AllTrips.get(Integer.parseInt(bookedTrip.getTripId()) - 1000).getHotelName());
                });
    }

    public ArrayList<BookedTravels> mainCustomer(ArrayList<Customers> allCustomers) {
        ArrayList<Trip> FeaturedTrips = new ArrayList<>();
        tripsList = fileManipulation.getAllTrips();
        FeaturedTrips = tripsList.stream()
                .limit(3).collect(Collectors.toCollection(ArrayList::new));
        char Ans = ' ';
        Trip ChosenTrip = new GeneralTours();
        Scanner input = new Scanner(System.in);
        Trip.displayTrips(FeaturedTrips);
        System.out.println("\nA. Search for a trip(s)");
        System.out.println("B. Go Back");
        System.out.print("Choice: ");
        if (input.hasNext())
            Ans = Character.toLowerCase(input.next().charAt(0));
        switch (Ans) {
            case 'a':
                return SearchTrips(Ans, ChosenTrip, allCustomers);
            case 'b':
                UserMainMenu(tripsList, allCustomers);
                break;
            default:
                CustomMessage("Wrong Input.. Try again!", 500, allCustomers);
                break;
        }
        return null;

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
                ArrayList<BookedTravels> AddedBookedTrips = new ArrayList<BookedTravels>(
                        this.mainCustomer(allCustomers));
                if (AddedBookedTrips != null) {
                    if (!CustomerBookedTrips.containsAll(AddedBookedTrips))
                        CustomerBookedTrips.addAll(AddedBookedTrips);
                }
                // dashboard.dashboard(booking, allTrips);
                break;
            } else if (choice == 3) {
                return CustomerBookedTrips;
            } else if (choice == 4) {
                // Sign out
                break;
            } else {
                System.out.println("Invalid input! please choose only from the following options.");
                continue;
            }
        }
        return CustomerBookedTrips;
    }

    private ArrayList<BookedTravels> SearchTrips(char Ans, Trip ChosenTrip, ArrayList<Customers> allCustomers) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nExample: TripName/StartDate/EndDate...");
        System.out.print("Search for a trip: ");
        ArrayList<Trip> filteredTrips = this.getFilteredTrips(input.nextLine(), allCustomers);
        Trip.displaySearchTrips(filteredTrips);
        System.out.println("\nA. Search More Trips.");
        System.out.println("B. Book a Trip.");
        System.out.println("C. Show Trip details.");
        System.out.println("D. Go Back.");
        if (input.hasNext())
            Ans = Character.toLowerCase(input.next().charAt(0));
        switch (Ans) {
            case 'a':
                SearchTrips(Ans, ChosenTrip, allCustomers);
                break;
            case 'b':
                System.out.println("\nWhich trip do you want to book?\t(Use the ID)");
                System.out.print("Trip ID: ");
                ChosenTrip = ChosenTrip.getTrip(tripsList, input.next());
                if (ChosenTrip == null)
                    CustomMessage("No Trips Found!", 2000, allCustomers);
                int dateIndex = 0;
                if (ChosenTrip.getStartDates().length > 1) {
                    dateIndex = CustomerChooseDate(ChosenTrip);
                }
                if (dateIndex == -1) {
                    CustomMessage("No dates available with the given information!", 2000, allCustomers);
                    mainCustomer(allCustomers);
                    return null;
                }
                ChosenTrip trip = new ChosenTrip(ChosenTrip.getTripType(), ChosenTrip.getTripId(),
                        ChosenTrip.getStartDates()[dateIndex], ChosenTrip.getEndDates()[dateIndex]);
                return CustomerBookedTickets.ticketMenu(CustomerBookedTrips, trip, tripsList);
            case 'c':
                ShowTripDetails(ChosenTrip, Ans, allCustomers);
                break;
            case 'd':
                mainCustomer(allCustomers);
                break;
            default:
                CustomMessage("Wrong Input.. Try again!", 2000, allCustomers);
                break;
        }
        return null;
    }

    private void ShowTripDetails(Trip ChosenTrip, char Ans, ArrayList<Customers> allCustomers) {
        Scanner input = new Scanner(System.in);
        if (Ans != '?') {
            System.out.println("\nWhich trip do you want to book?\t(Use the ID)");
            System.out.print("Trip ID: ");
            ChosenTrip = ChosenTrip.getTrip(tripsList, input.next());
        }
        if (ChosenTrip == null)
            CustomMessage("No Trips Found!", 2000, allCustomers);
        ChosenTrip.displayTripDetails();
        System.out.println("A. Book " + ChosenTrip.getTitle() + " trip");
        System.out.println("B. Go Back");
        System.out.print("Choice: ");
        if (input.hasNext())
            Ans = Character.toLowerCase(input.next().charAt(0));
        switch (Ans) {
            case 'a':
                int dateIndex = 0;
                if (ChosenTrip.getStartDates().length > 1) {
                    dateIndex = CustomerChooseDate(ChosenTrip);
                }
                if (dateIndex == -1) {
                    CustomMessage("No dates available with the given information!", 2000, allCustomers);
                    mainCustomer(allCustomers);
                    return;
                }
                ChosenTrip trip = new ChosenTrip(ChosenTrip.getTripType(), ChosenTrip.getTripId(),
                        ChosenTrip.getStartDates()[dateIndex], ChosenTrip.getEndDates()[dateIndex]);
                CustomerBookedTickets.ticketMenu(CustomerBookedTrips, trip, tripsList);
                // addBookingTrip(ChosenTrip, dateIndex);
                // Call Ticket Functions!
                CustomMessage("You successfully booked " + ChosenTrip.getTitle() + " Trip", 500, allCustomers);
                break;
            case 'b':
                mainCustomer(allCustomers);
                break;
            default:
                CustomMessage("Wrong Input.. Try again!", 2000, allCustomers);
                break;
        }

    }

    private int CustomerChooseDate(Trip ChosenTrip) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which Date Do you want to Book?");
        for (int i = 0; i < ChosenTrip.getStartDates().length; i++)
            System.out.println(i + 1 + ". " + ChosenTrip.getStartDates()[i]);
        int index = input.nextInt() - 1;

        if (index > ChosenTrip.getStartDates().length || index < 0)
            return -1;

        return index;
    }

    private void CustomMessage(String message, int timeout, ArrayList<Customers> allCustomers) {
        try {
            System.out.println(message);
            Thread.sleep(timeout);
            mainCustomer(allCustomers);
        } catch (InterruptedException e) {
            System.out.println("Thread error sleeping.");
            // e.printStackTrace();
        }
    }

    public ArrayList<Trip> getFilteredTrips(String search_filter, ArrayList<Customers> allCustomers) {
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
                mainCustomer(allCustomers);
            }
        }
        price_start = 0;
        price_end = 0;
        search_text = null;
        start_date = null;
        end_date = null;
        if (filteredTrips.size() == 1) {
            ShowTripDetails(filteredTrips.get(0), '?', allCustomers);
        } else if (filteredTrips.isEmpty())
            CustomMessage("\nNo Trip Found with these preferences..", 3000, allCustomers);
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