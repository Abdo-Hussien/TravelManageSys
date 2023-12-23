package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import AccountManagement.Customers;
import AccountManagement.Person;
import AccountManagement.Personsinterface;
import TravelManagement.*;

public class fileManipulation {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    // Function to get all Trips from the file
    public static ArrayList<Trip> getAllTrips() {
        try {
            ArrayList<Trip> AllTrips = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/Trips.txt");
            String fileContent = Files.readString(path);
            String Trips[] = fileContent.split("\\s*---\\s*");
            for (String tripString : Trips) {
                Trip trip = parseTrip(tripString);
                if (trip != null)
                    AllTrips.add(trip);
            }
            return AllTrips;
        } catch (Exception e) {
            System.out.println("Trips Reading failed");
            return null;
        }
    }

    // Function to convert String to Trip
    private static Trip parseTrip(String tripString) {
        String[] trip = tripString.split(System.lineSeparator());
        Date[] start_date = Arrays.stream(trip[4].split("\\s*\\|\\s*")).map(date_str -> {
            try {
                return dateFormat.parse(date_str);
            } catch (ParseException e) {
                return null;
            }
        }).toArray(Date[]::new);
        Date[] end_date = Arrays.stream(trip[5].split("\\s*\\|\\s*")).map(date_str -> {
            try {
                return dateFormat.parse(date_str);
            } catch (ParseException e) {
                return null;
            }
        }).toArray(Date[]::new);
        if (trip[2].toLowerCase().equals("family")) {
            return new FamilyTours(trip[0], trip[1], trip[2], Double.parseDouble(trip[3]),
                    start_date, end_date, trip[6], trip[7], Integer.parseInt(trip[8]), Integer.parseInt(trip[9]),
                    trip[10].split("\\s*\\|\\s*"), trip[11], trip[12]);
        } else if (trip[2].toLowerCase().equals("general")) {
            return new GeneralTours(trip[0], trip[1], trip[2], Double.parseDouble(trip[3]),
                    start_date, end_date, trip[6], trip[7], Integer.parseInt(trip[8]), Integer.parseInt(trip[9]),
                    trip[10].split("\\s*\\|\\s*"), trip[11], trip[12]);
        } else if (trip[2].toLowerCase().equals("couple")) {
            return new CoupleTours(trip[0], trip[1], trip[2], Double.parseDouble(trip[3]),
                    start_date, end_date, trip[6], trip[7], Integer.parseInt(trip[8]), Integer.parseInt(trip[9]),
                    trip[10].split("\\s*\\|\\s*"), trip[11], trip[12]);
        } else
            return null;
    }

    // Function to get all Customers from the file
    public static ArrayList<Customers> getAllCustomers() {
        try {
            ArrayList<Customers> AllCustomers = new ArrayList<>();
            ArrayList<BookedTravels> CustomerBookedTrips;
            ArrayList<String> TripHistory;
            Path path = Paths.get("TravelManageSys/src/main/java/data/Customers.txt");
            String fileContent = Files.readString(path);
            String Customers[] = fileContent.split("\\s*---\\s*");
            for (String c : Customers) {
                String[] customer = c.split(System.lineSeparator());
                String[] Fullname = customer[1].split(" ");
                Boolean bookedTripsChecker = customer[8].equalsIgnoreCase("No Booked Trips");
                if (bookedTripsChecker)
                    TripHistory = handleTripHistory(customer[9]);
                else
                    TripHistory = handleTripHistory(customer[17]);
                String[] BookedTripsLine = Arrays.copyOfRange(customer, 8, 17);
                CustomerBookedTrips = handleBookedTravels(BookedTripsLine, bookedTripsChecker);
                AllCustomers.add(new Customers(customer[0], Fullname[0], Fullname[1], customer[2], customer[3],
                        Integer.parseInt(customer[4]), customer[5], customer[6], customer[7], CustomerBookedTrips,
                        TripHistory));
            }
            return AllCustomers;
        } catch (Exception e) {
            System.out.println("Customers Reading failed");
            return null;
        }
    }

    public static ArrayList<String> handleTripHistory(String TripHistoryLine) {
        if (!TripHistoryLine.equalsIgnoreCase("No Trips History")) {
            String[] strTripHistory = TripHistoryLine.split("\\s*\\|\\s*");
            return new ArrayList<>(Arrays.asList(strTripHistory));
        }
        return new ArrayList<String>();
    }

    public static ArrayList<BookedTravels> handleBookedTravels(String[] BookedTripsLines, boolean bookedTripsChecker)
            throws ParseException {
        if (bookedTripsChecker)
            return new ArrayList<BookedTravels>();
        return parseBookedTrip(BookedTripsLines);
    }

    // Function to convert String to BookedTravels
    public static ArrayList<BookedTravels> parseBookedTrip(String[] BookedTripsLine) throws ParseException {
        ArrayList<BookedTravels> CustomerBookedTrips = new ArrayList<>();
        String[] IDs = BookedTripsLine[0].split("\\s*\\|\\s*");
        String[] Names = BookedTripsLine[1].split("\\s*\\|\\s*");
        String[] TicketExpDates = BookedTripsLine[2].split("\\s*\\|\\s*");
        String[] totalPrices = BookedTripsLine[3].split("\\s*\\|\\s*");
        String[] carIDs = BookedTripsLine[4].split("\\s*\\|\\s*");
        String[] TicketIDs = BookedTripsLine[5].split("\\s*\\|\\s*");
        String[] StartDates = BookedTripsLine[6].split("\\s*\\|\\s*");
        String[] TicketTypes = BookedTripsLine[7].split("\\s*\\|\\s*");
        String[] TicketCounter = BookedTripsLine[8].split("\\s*\\|\\s*");
        for (int i = 0; i < IDs.length; i++) {
            ArrayList<Ticket> CustomerTickets = new ArrayList<>();
            String[] TripTicketIDs = TicketIDs[i].split("\\s*,\\s*");
            String[] TripTicketTypes = TicketTypes[i].split("\\s*,\\s*");
            String[] TripTicketCounter = TicketCounter[i].split("\\s*,\\s*");
            for (int j = 0; j < TripTicketTypes.length; j++) {
                if (TripTicketTypes[j].equalsIgnoreCase("silver"))
                    CustomerTickets.add(new Silver(TripTicketIDs[j],
                            TripTicketTypes[j], Integer.parseInt(TripTicketCounter[j])));
                else if (TripTicketTypes[j].equalsIgnoreCase("gold"))
                    CustomerTickets.add(new Gold(TripTicketIDs[j],
                            TripTicketTypes[j], Integer.parseInt(TripTicketCounter[j])));
                else if (TripTicketTypes[j].equalsIgnoreCase("platinum"))
                    CustomerTickets.add(new Platinum(TripTicketIDs[j],
                            TripTicketTypes[j], Integer.parseInt(TripTicketCounter[j])));
            }
            ChosenTrip chosenTrip = new ChosenTrip(IDs[i], Names[i], dateFormat.parse(StartDates[i]),
                    dateFormat.parse(TicketExpDates[i]), Double.parseDouble(totalPrices[i]), carIDs[i]);
            CustomerBookedTrips.add(new BookedTravels(chosenTrip, CustomerTickets));
        }
        return CustomerBookedTrips;
    }

    // Function to get all TourGuides from the file
    public static ArrayList<TourGuide> getAllTourGuides() {
        try {
            ArrayList<TourGuide> AllTourGuides = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/Tourguides.txt");
            String fileContent = Files.readString(path);
            String TourGuides[] = fileContent.split("\\s*---\\s*");
            for (String t : TourGuides) {
                String[] tourguide = t.split(System.lineSeparator());
                String[] Fullname = tourguide[1].split(" ");
                AllTourGuides.add(new TourGuide(tourguide[0], Fullname[0], Fullname[1], tourguide[2], tourguide[3],
                        Integer.parseInt(tourguide[4]), tourguide[5], tourguide[6].split("\\s*\\|\\s*"), tourguide[7]));

            }
            return AllTourGuides;
        } catch (Exception e) {
            System.out.println("Tourguides Reading failed");
            return null;
        }
    }

    // Function to get all Hotels from the file
    public static ArrayList<Hotels> getAllHotels() {
        try {
            ArrayList<Hotels> AllHotels = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/Hotels.txt");
            String fileContent = Files.readString(path);
            String Hotels[] = fileContent.split("\\s*---\\s*");
            for (String t : Hotels) {
                String[] hotel = t.split(System.lineSeparator());
                AllHotels.add(new Hotels(hotel[0], Double.parseDouble(hotel[1]), Integer.parseInt(hotel[2]),
                        Boolean.parseBoolean(hotel[3]), Boolean.parseBoolean(hotel[4]),
                        Boolean.parseBoolean(hotel[5])));
            }
            return AllHotels;
        } catch (Exception e) {
            System.out.println("Hotels Reading failed");
            return null;
        }
    }

    // Function to get all Cars from the file
    public static ArrayList<Car> getAllCars() {
        try {
            ArrayList<Car> AllCars = new ArrayList<>();
            Path path = Paths.get("TravelManageSys\\src\\main\\java\\data\\Cars.txt");
            String fileContent = Files.readString(path);
            String Cars[] = fileContent.split("\\s*---\\s*");
            for (String c : Cars) {
                String[] Car = c.split(System.lineSeparator());
                AllCars.add(
                        new Car(Car[0], Car[1], Car[2], Car[3], Integer.parseInt(Car[4]), Double.parseDouble(Car[5])));
            }
            return AllCars;
        } catch (Exception e) {
            System.out.println("Cars Reading failed");
            return null;
        }
    }

    // Function to get all Transportation from the file
    public static ArrayList<Transportation> getAllTransportations() {
        try {
            ArrayList<Transportation> AllTransportations = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/Transportations.txt");
            String fileContent = Files.readString(path);
            String Transportations[] = fileContent.split("\\s*---\\s*");
            for (String t_str : Transportations) {
                String[] Transportation = t_str.split(System.lineSeparator());
                if (Transportation[0].toLowerCase().equals("flight")) {
                    AllTransportations
                            .add(new Flight(Transportation[1], Transportation[2],
                                    Transportation[3]));
                } else if (Transportation[0].toLowerCase().equals("bus")) {
                    AllTransportations
                            .add(new Bus(Transportation[1], Transportation[2],
                                    Transportation[3]));
                }
            }
            return AllTransportations;
        } catch (Exception e) {
            System.out.println("Transportations Reading failed");
            return null;
        }
    }

    public static void writePersonalInfo(BufferedWriter writer, Person user, String path) throws IOException {
        writer.write(user.getAccount_id());
        writer.write(System.lineSeparator()); // Use '\n' as a delimiter

        writer.write(user.getFirst_name() + " " + user.getLast_name());
        writer.write(System.lineSeparator());

        writer.write(user.getUsername());
        writer.write(System.lineSeparator());

        writer.write(user.getPassword());
        writer.write(System.lineSeparator());

        writer.write(String.valueOf(user.getAge()));
        writer.write(System.lineSeparator());

        writer.write(user.getGender());
        writer.write(System.lineSeparator());

        String address = user.getStreetAddress() + " | " + user.getStateAddress() + " | "
                + user.getZipAddress();
        writer.write(address + " | ");
        writer.write(System.lineSeparator());

        writer.write(user.getPhone_number());
        writer.write(System.lineSeparator());
    }

    public static void writeTourguides(ArrayList<TourGuide> Tourguides) {
        String filePath = "TravelManageSys/src/main/java/data/Tourguides.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (TourGuide tourGuide : Tourguides) {
                writePersonalInfo(writer, tourGuide, filePath);
                writer.write("---"); // Delimiter between TourGuide objects
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            System.out.println("Tourguides Writing failed");
        }
    }

    public static void writeCustomers(ArrayList<Customers> Customers) {
        String filePath = "TravelManageSys/src/main/java/data/Customers.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Customers customer : Customers) {
                writePersonalInfo(writer, customer, filePath);
                if (customer.getCustomerBookedTrips().isEmpty())
                    writer.write("No Booked Trips");
                else {
                    // Writing additional customer details
                    for (BookedTravels bookedtrips : customer.getCustomerBookedTrips())
                        writer.write(bookedtrips.getTripID() + " | ");
                    writer.write(System.lineSeparator());

                    for (BookedTravels bookedtrips : customer.getCustomerBookedTrips())
                        writer.write(bookedtrips.getTripName() + " | ");
                    writer.write(System.lineSeparator());

                    for (BookedTravels bookedtrips : customer.getCustomerBookedTrips())
                        writer.write(dateFormat.format(bookedtrips.getEndDate()) + " | ");
                    writer.write(System.lineSeparator());

                    for (BookedTravels bookedtrips : customer.getCustomerBookedTrips())
                        writer.write(bookedtrips.getTotalPrice() + " | ");
                    writer.write(System.lineSeparator());

                    for (BookedTravels bookedtrips : customer.getCustomerBookedTrips()) {
                        if (bookedtrips.getCarID() == null)
                            writer.write("No car" + " | ");
                        else
                            writer.write(bookedtrips.getCarID() + " | ");
                    }
                    writer.write(System.lineSeparator());
                    for (BookedTravels bookedtrip : customer.getCustomerBookedTrips()) {
                        for (Ticket ticket : bookedtrip.getBookedticket())
                            writer.write(ticket.getTicketID() + " , ");
                        writer.write("| ");
                    }
                    writer.write(System.lineSeparator());
                    for (BookedTravels bookedtrip : customer.getCustomerBookedTrips()) {
                        for (int i = 0; i < bookedtrip.getBookedticket().size(); i++)
                            writer.write(dateFormat.format(bookedtrip.getStartDate()) + " , ");
                        writer.write("| ");
                    }
                    writer.write(System.lineSeparator());
                    for (BookedTravels bookedtrip : customer.getCustomerBookedTrips()) {
                        for (Ticket ticket : bookedtrip.getBookedticket())
                            writer.write(ticket.getType() + " , ");
                        writer.write("| ");
                    }
                    writer.write(System.lineSeparator());
                    for (BookedTravels bookedtrip : customer.getCustomerBookedTrips()) {
                        for (Ticket ticket : bookedtrip.getBookedticket())
                            writer.write(ticket.getCounter() + " , ");
                        writer.write("| ");
                    }
                }
                writer.write(System.lineSeparator());
                if (customer.getCustomerTravelHistory().isEmpty())
                    writer.write("No Trips History");
                else {
                    for (String tripID : customer.getCustomerTravelHistory())
                        writer.write(tripID + " | ");
                }
                writer.write(System.lineSeparator());
                writer.write("---"); // Delimiter between Customer objects
                writer.write(System.lineSeparator());
            }

        } catch (Exception e) {
            System.out.println("Customers Writing failed");
        }
    }

    public static void writeTrips(ArrayList<Trip> Trips) {
        String filePath = "TravelManageSys/src/main/java/data/Trips.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Trip trip : Trips) {
                writer.write(trip.getTripID());
                writer.write(System.lineSeparator());

                writer.write(trip.getTripName());
                writer.write(System.lineSeparator());

                writer.write(trip.getTripType());
                writer.write(System.lineSeparator());

                writer.write(String.valueOf(trip.getInitPrice()));
                writer.write(System.lineSeparator());

                for (Date date : trip.getStartDates())
                    writer.write(dateFormat.format(date) + " | ");
                writer.write(System.lineSeparator());

                for (Date date : trip.getEndDates())
                    writer.write(dateFormat.format(date) + " | ");
                writer.write(System.lineSeparator());

                writer.write(trip.getDescription());
                writer.write(System.lineSeparator());

                writer.write(trip.getTourGuideID());
                writer.write(System.lineSeparator());

                writer.write(String.valueOf(trip.getCapacity()));
                writer.write(System.lineSeparator());

                writer.write(String.valueOf(trip.getTicketCounter()));
                writer.write(System.lineSeparator());

                for (String activity : trip.getActivities())
                    writer.write(activity + " | ");
                writer.write(System.lineSeparator());

                writer.write(trip.getHotelName());
                writer.write(System.lineSeparator());

                writer.write(trip.getTransportID());
                writer.write(System.lineSeparator());

                writer.write("---"); // Delimiter between Trips objects
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            System.out.println("Customers Writing failed");
        }
    }

}