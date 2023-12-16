package data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import AccountManagement.Customers;
import TravelManagement.*;

public class fileManipulation {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    // Function to get all Trips from the file
    public static ArrayList<Trip> getAllTrips() {
        try {
            ArrayList<Trip> AllTrips = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/trips.txt");
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
            ArrayList<BookedTravels> CustomerBookedTrips = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/customers.txt");
            String fileContent = Files.readString(path);
            String Customers[] = fileContent.split("\\s*---\\s*");
            for (String c : Customers) {
                String[] customer = c.split(System.lineSeparator());
                String[] Fullname = customer[1].split(" ");
                ArrayList<String> TripHistory = new ArrayList<>();
                if (customer[8].equalsIgnoreCase("No Booked Trips")) {
                    if (!customer[9].equalsIgnoreCase("No Trips History")) {
                        String[] strTripHistory = customer[9].split("\\s*\\|\\s*");
                        TripHistory = new ArrayList<>(Arrays.asList(strTripHistory));
                    }
                } else {
                    if (!customer[15].equalsIgnoreCase("No Trips History")) {
                        String[] strTripHistory = customer[15].split("\\s*\\|\\s*");
                        TripHistory = new ArrayList<>(Arrays.asList(strTripHistory));
                    }
                }
                String[] BookedTripsLine = Arrays.copyOfRange(customer, 8, 17);
                CustomerBookedTrips = customer[8].equalsIgnoreCase("No Booked Trips") ? new ArrayList<BookedTravels>()
                        : parseBookedTrip(BookedTripsLine);
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
            CustomerBookedTrips
                    .add(new BookedTravels(IDs[i], Names[i], dateFormat.parse(StartDates[i]),
                            dateFormat.parse(TicketExpDates[i]), CustomerTickets,
                            Double.parseDouble(totalPrices[i]),
                            carIDs[i]));
        }
        return CustomerBookedTrips;
    }

    // Function to get all TourGuides from the file
    public static ArrayList<TourGuide> getAllTourGuides() {
        try {
            ArrayList<TourGuide> AllTourGuides = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/TourGuides.txt");
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
            Path path = Paths.get("TravelManageSys/src/main/java/data/Transportation.txt");
            String fileContent = Files.readString(path);
            String Transportations[] = fileContent.split("\\s*---\\s*");
            for (String t_str : Transportations) {
                String[] Transportation = t_str.split(System.lineSeparator());
                if (Transportation[0].toLowerCase().equals("flight")) {
                    AllTransportations
                            .add(new Flight(Transportation[1], Integer.parseInt(Transportation[2]), Transportation[3],
                                    Transportation[4]));
                } else if (Transportation[0].toLowerCase().equals("bus")) {
                    AllTransportations
                            .add(new Bus(Transportation[1], Integer.parseInt(Transportation[2]), Transportation[3],
                                    Transportation[4]));
                }
            }
            return AllTransportations;
        } catch (Exception e) {
            System.out.println("Transportations Reading failed");
            return null;
        }
    }

}
