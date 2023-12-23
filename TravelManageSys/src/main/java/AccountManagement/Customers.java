
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
import TravelManagement.Car;
import TravelManagement.ChosenTrip;
import TravelManagement.GeneralTours;
import TravelManagement.Hotels;
import TravelManagement.Transportation;
import TravelManagement.Trip;
import data.fileManipulation;

public class Customers extends Person {
    // Filter Search Preferences
    private double price_start;
    private double price_end;
    private String search_text;
    private String start_date;
    private String end_date;

    private static ArrayList<Car> allCars = new ArrayList<Car>(fileManipulation.getAllCars());
    private double discount;
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
        this.discount = 0.0;
    }

    public Customers() {
        this.CustomerBookedTickets = new BookingTickets();
        this.CustomerTravelHistory = new ArrayList<>();
        this.CustomerBookedTrips = new ArrayList<>();
        CustomerTravelHistory = new ArrayList<>();
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
        while (true) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1- Edit your information.\n2- Trip history.\n3- Go back.");
            int choice = in.next().charAt(0);
            switch (choice) {
                case '1':
                    Admin admin = new Admin();
                    admin.editInformations("old", allCustomers, "Customer", "Customer");
                    return;
                case '2':
                    displayTripHistory(allTrips);
                    break;
                case '3':
                    UserMainMenu(allTrips, allCustomers);
                    return;
                default:
                    System.out.println("Wrong input! Please try again..");
                    break;
            }
        }
    }

    public void displayTripHistory(ArrayList<Trip> allTrips) {
        if (this.getCustomerTravelHistory().isEmpty()) {
            CustomMessage(
                    "You don't have any trips in your history with us!\nYour journey awaits, and the story of your adventures is ready to unfold.",
                    2000);
            return;
        }
        System.out.println("Embark on the memories of your travels:\n");
        System.out.println("|------------------------------------------|----------------------------------------|");
        System.out.printf("| %-40s | %-38s |%n", "Trip IDs", "Trip Names");
        System.out.println("|------------------------------------------|----------------------------------------|");

        // Display Trip IDs and Titles in a single loop
        for (String id : this.getCustomerTravelHistory()) {
            String tripId = allTrips.get(Integer.parseInt(id) - 1000).getTripID();
            String title = allTrips.get(Integer.parseInt(id) - 1000).getTripName();
            System.out.printf("| %-40s | %-38s |%n", tripId, title);
        }

        // Closing the table
        System.out.println("|------------------------------------------|----------------------------------------|");
    }

    public void displayBookedTripsDetails(ArrayList<Trip> AllTrips) {
        int[] TicketsCounter = { 0 };
        boolean[] foundPlatinumTicket = { false };
        BookedTravels customerBookedTrips;
        Transportation transportation;
        Hotels hotel;

        System.out.print("Please use the index to display booking details of a trip: ");
        choice = in.nextInt();
        in.nextLine();
        try {
            customerBookedTrips = this.CustomerBookedTrips.get(choice - 1);
        } catch (IndexOutOfBoundsException e) {
            CustomMessage("Invalid index.. please write an index of one of the previous booked trips!", 300);
            displayBookedTripsDetails(AllTrips);
            return;
        }
        transportation = AllTrips.get(Integer.parseInt(customerBookedTrips.getTripID()) - 1000)
                .getTransportation(
                        AllTrips.get(Integer.parseInt(customerBookedTrips.getTripID()) - 1000).getTransportID());
        customerBookedTrips.getBookedticket().forEach(ticket -> {
            TicketsCounter[0] += ticket.getCounter();

        });
        hotel = AllTrips.get(Integer.parseInt(customerBookedTrips.getTripID()) - 1000)
                .getHotel(
                        AllTrips.get(Integer.parseInt(customerBookedTrips.getTripID()) - 1000).getHotelName());
        System.out.printf("\n%s\n",
                "************************************************************************************************");
        System.out.printf("%-40s%s\n\n",
                "", AllTrips.get(Integer.parseInt(customerBookedTrips.getTripID()) - 1000).getTripName());
        System.out.printf("Tour Guide ID : %-20sTotal Price : %-30sTrip ID : %-10s%n",
                AllTrips.get(Integer.parseInt(customerBookedTrips.getTripID()) - 1000).getTourGuideID(),
                "$" + customerBookedTrips.getTotalPrice(), customerBookedTrips.getTripID());
        System.out.printf("Start Date : %-42sEnd Date : %-20s\n", customerBookedTrips.getStartDate(),
                customerBookedTrips.getEndDate());
        System.out.printf("Number of tickets bought : %-10s\n\n", TicketsCounter[0]);
        System.out.printf("| %-40s | %-38s | %-7s |\n", "Ticket ID", "Type", "Counter");
        System.out.println(
                "|------------------------------------------|----------------------------------------|---------|");

        customerBookedTrips.getBookedticket().forEach(ticket -> {
            System.out.printf("| %-40s | %-38s | %-7s |%n", ticket.getTicketID(), ticket.getType(),
                    ticket.getCounter());
            if (ticket.getType().equalsIgnoreCase("Platinum"))
                foundPlatinumTicket[0] = true;
        });

        System.out.println(
                "|------------------------------------------|----------------------------------------|---------|");
        System.out.printf("Pickup : %-50sStaying At : %-30s%n", transportation.getPickUp(),
                hotel.getHotelname());
        System.out.println("Hotel rating: " + hotel.getRate());
        boolean isCarRented = customerBookedTrips.getCarID() != null;
        System.out.printf("Car Rental : %-60s%n", isCarRented);
        if (isCarRented) {
            Car rentedCar = allCars.get(Integer.parseInt(customerBookedTrips.getCarID()) - 2000);
            System.out.printf("Car ID : %-20s", customerBookedTrips.getCarID());
            if (foundPlatinumTicket[0])
                System.out.printf("Price : %-20s%n", "Free");
            else
                System.out.printf("Price : %-20s%n", "$" + rentedCar.getPrice());
            System.out.printf("Made : %-20sModel : %-20s%n", rentedCar.getMade(), rentedCar.getModel());
        }
        System.out.printf("\n%s\n",
                "************************************************************************************************");
    }

    public void mainCustomer(ArrayList<Customers> allCustomers, ArrayList<Trip> tripsList) {
        ArrayList<Trip> FeaturedTrips = new ArrayList<>();
        FeaturedTrips = tripsList.stream()
                .limit(3).collect(Collectors.toCollection(ArrayList::new));
        char Ans = ' ';
        Trip ChosenTrip = new GeneralTours();
        Scanner in = new Scanner(System.in);
        Trip.displayTrips(FeaturedTrips);
        System.out.println("\nA. Search for a trip(s)");
        System.out.println("B. Go Back");
        System.out.print("Choice: ");
        Ans = Character.toLowerCase(in.next().charAt(0));
        in.nextLine();
        switch (Ans) {
            case 'a':
                SearchTrips(Ans, ChosenTrip, allCustomers, tripsList);
                break;
            case 'b':
                UserMainMenu(tripsList, allCustomers);
                break;
            default:
                CustomMessage("Wrong Input.. Try again!", 500);
                mainCustomer(allCustomers, tripsList);
                break;
        }
    }

    public ArrayList<BookedTravels> UserMainMenu(ArrayList<Trip> allTrips, ArrayList<Customers> allCustomers) {
        int choice;
        while (true) {
            System.out.println("\nWelcome " + this.getFirst_name() + "! What would you like to do?");
            if (this.getCustomerTravelHistory().size() > 2 && this.getCustomerTravelHistory().size() < 5)
                setDiscount(0.05);
            if (this.getCustomerTravelHistory().size() > 4 && this.getCustomerTravelHistory().size() < 8)
                setDiscount(0.1);
            if (this.getCustomerTravelHistory().size() > 7)
                setDiscount(0.2);
            if (this.discount > 0.0)
                System.out.println("~ You have an active " + (int) (this.discount * 100) + "% discount!!");
            System.out.println("\n1- Profile settings.\n2- Trip.\n3- Cart.\n4- Sign out.");
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
                fileManipulation.writeCustomers(allCustomers);
                fileManipulation.writeTrips(allTrips);
                CustomMessage("Sign out successfully", 2000);
                return null;
            } else {
                System.out.println("Invalid input! please choose only from the following options.");
                continue;
            }
        }
        return CustomerBookedTrips;
    }

    private void SearchTrips(char Ans, Trip ChosenTrip, ArrayList<Customers> allCustomers, ArrayList<Trip> tripsList) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nExample: TripName/StartDate/EndDate...");
        System.out.print("Search for a trip: ");
        ArrayList<Trip> filteredTrips = this.getFilteredTrips(in.nextLine(), allCustomers, tripsList);
        if (filteredTrips.size() == 1) {
            ShowTripDetails(filteredTrips.get(0), '?', allCustomers, tripsList);
            return;
        } else if (filteredTrips.isEmpty()) {
            CustomMessage("\nNo Trip Found with these preferences..", 2000);
            mainCustomer(allCustomers, tripsList);
            return;
        }
        Trip.displaySearchTrips(filteredTrips);
        System.out.println("\nA. Search More Trips.");
        System.out.println("B. Book a Trip.");
        System.out.println("C. Show Trip details.");
        System.out.println("D. Go Back.");
        Ans = Character.toLowerCase(in.next().charAt(0));
        in.nextLine();
        switch (Ans) {
            case 'a':
                SearchTrips(Ans, ChosenTrip, allCustomers, tripsList);
                break;
            case 'b':
                System.out.println("\nWhich trip do you want to book?\t(Use the ID)");
                System.out.print("Trip ID: ");
                String tripID = in.next();
                in.nextLine();
                ChosenTrip = Trip.getTrip(tripsList, tripID);
                if (ChosenTrip == null) {
                    CustomMessage("No Trips Found!", 1500);
                    mainCustomer(allCustomers, tripsList);
                    return;
                }
                if (bookATrip(ChosenTrip, tripsList))
                    return;
                mainCustomer(allCustomers, tripsList);
                break;
            case 'c':
                ShowTripDetails(ChosenTrip, Ans, allCustomers, tripsList);
                break;
            case 'd':
                mainCustomer(allCustomers, tripsList);
                break;
            default:
                CustomMessage("Wrong Input.. Try again!", 1500);
                mainCustomer(allCustomers, tripsList);
                break;
        }
        return;
    }

    private void ShowTripDetails(Trip ChosenTrip, char Ans, ArrayList<Customers> allCustomers,
            ArrayList<Trip> tripsList) {
        Scanner in = new Scanner(System.in);
        if (Ans != '?') {
            System.out.println("\nWhich trip do you want to book?\t(Use the ID)");
            System.out.print("Trip ID: ");
            String tripID = in.next();
            in.nextLine();
            ChosenTrip = Trip.getTrip(tripsList, tripID);
        }
        if (ChosenTrip == null) {
            CustomMessage("No Trips Found!", 1500);
            mainCustomer(allCustomers, tripsList);
            return;
        }
        ChosenTrip.displayTripDetails();
        System.out.println("A. Book " + ChosenTrip.getTripName() + " trip");
        System.out.println("B. Go Back");
        System.out.print("Choice: ");
        Ans = Character.toLowerCase(in.next().charAt(0));
        in.nextLine();
        switch (Ans) {
            case 'a':
                if (bookATrip(ChosenTrip, tripsList))
                    return;
            case 'b':
                mainCustomer(allCustomers, tripsList);
                break;
            default:
                CustomMessage("Wrong Input.. Try again!", 1500);
                mainCustomer(allCustomers, tripsList);
                break;
        }
        return;
    }

    private boolean bookATrip(Trip ChosenTrip, ArrayList<Trip> tripsList) {
        int dateIndex = 0;
        if (ChosenTrip.getStartDates().length > 1) {
            dateIndex = CustomerChooseDate(ChosenTrip);
        }
        if (dateIndex == -1) {
            CustomMessage("No dates available with the given information!", 1500);
            return false;
        }
        ChosenTrip trip = new ChosenTrip(ChosenTrip.getTripID(), ChosenTrip.getTripName(),
                ChosenTrip.getStartDates()[dateIndex], ChosenTrip.getEndDates()[dateIndex], 0.0, null);
        String ChosenCarID = rentACar();
        trip.setCarID(ChosenCarID);
        if (ChosenCarID != null) {
            trip.addToTotalPrice(allCars.get(Integer.parseInt(ChosenCarID) - 2000).getPrice());
            CustomMessage("You successfully rented " + allCars.get(Integer.parseInt(ChosenCarID) - 2000).getMade() + " "
                    + allCars.get(Integer.parseInt(ChosenCarID) - 2000).getModel(), 1000);
        }
        CustomerBookedTickets.ticketMenu(this, trip, tripsList, allCars);
        return true;
    }

    private String rentACar() {
        char ans;
        System.out.println("Do you want to rent a car for this trip? (y/n)");
        ans = in.next().toLowerCase().charAt(0);
        in.nextLine();
        if (ans == 'y') {
            Car.displayAllCars(allCars);
            return ChooseCar();
        } else if (ans == 'n') {
            return null;
        } else {
            System.out.println("Wrong input, please choose 'y' -> YES or 'n' -> NO");
            return rentACar();
        }
    }

    private String ChooseCar() {
        Car chosenCar;
        char ans;
        System.out.print("Choose car by its index: ");
        choice = in.nextInt();
        in.nextLine();
        try {
            chosenCar = allCars.get(choice - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No car found with this index, please choose from the following cars!");
            return rentACar();
        }
        System.out.println("1- Show more details about " + chosenCar.getModel() + ".");
        System.out.println("2- Rent " + chosenCar.getModel() + ".");
        System.out.print("Choice: ");
        choice = in.nextInt();
        in.nextLine();
        if (choice == 1) {
            chosenCar.displayCarDetails();
            System.out.println("1- Rent " + chosenCar.getModel() + ".");
            System.out.println("2- Find another car " + chosenCar.getModel() + ".");
            System.out.print("Choice: ");
            choice = in.nextInt();
            in.nextLine();
            if (choice == 1) {
                return chosenCar.getCarID();
            } else if (choice == 2) {
                Car.displayAllCars(allCars);
                return ChooseCar();
            } else {
                System.out.println("Invalid choice! Please enter 1 or 2.");
                Car.displayAllCars(allCars);
                return ChooseCar();
            }
        } else if (choice == 2) {
            return chosenCar.getCarID();
        } else {
            System.out.println("Wrong input, please choose 'y' -> YES or 'n' -> NO");
            return ChooseCar();
        }
    }

    private int CustomerChooseDate(Trip ChosenTrip) {
        Scanner in = new Scanner(System.in);
        System.out.println("Which Date Do you want to Book?");
        for (int i = 0; i < ChosenTrip.getStartDates().length; i++)
            System.out.println(i + 1 + ". " + ChosenTrip.getStartDates()[i]);
        int index = in.nextInt() - 1;
        in.nextLine();

        if (index > ChosenTrip.getStartDates().length || index < 0)
            return -1;

        return index;
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
                end_date = i + 1 == Filters.length ? "01-12-2000" : Filters[i + 1];
                i += 1;
                continue;
            }
            if (Filters[i].matches("\\d+(\\.\\d+)?")) {
                try {
                    price_start = Double.parseDouble(Filters[i]);
                    price_end = Double.parseDouble(i + 1 == Filters.length ? "99999999.99" : Filters[i + 1]);
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
        return trip.getTripName().toLowerCase().contains(search_filter.toLowerCase())
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

    public int getTripHistoryCounter() {
        return CustomerTravelHistory.isEmpty() ? 0 : CustomerTravelHistory.size();
    }

    public void setTripHistory(ArrayList<BookedTravels> bookedTravels) {
        for (int i = 0; i < bookedTravels.size(); i++)
            if (!CustomerTravelHistory.contains(bookedTravels.get(i).getTripID()))
                CustomerTravelHistory.add(bookedTravels.get(i).getTripID());
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean discountActive() {
        if (this.discount != 0) {
            return true;
        }
        return false;
    }

    public double getDiscount() {
        return discount;
    }

    public double applyDiscount(double originalPrice) {
        return originalPrice - (originalPrice * discount);
    }

    private void CustomMessage(String message, int timeout) {
        try {
            System.out.println(message);
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            System.out.println("Thread error sleeping.");
            // e.printStackTrace();
        }
    }
}