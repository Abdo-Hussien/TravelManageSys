package data;

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
import TravelManagement.Trip;
import TravelManagement.Car;
import TravelManagement.CoupleTours;
import TravelManagement.FamilyTours;
import TravelManagement.GeneralTours;
import TravelManagement.Hotels;
import TravelManagement.TourGuide;

public class fileManipulation {

    // function to get all trips from the file
    public ArrayList<Trip> getAllTrips() {
        try {
            ArrayList<Trip> AllTrips = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/trips.txt");
            String val = Files.readString(path);
            String Trips[] = val.split("\\s+---\\s+");
            for (String tripString : Trips) {
                Trip trip = parseTrip(tripString);
                if (trip != null)
                    AllTrips.add(trip);
            }
            return AllTrips;
        } catch (Exception e) {
            return null;
        }

    }

    private Trip parseTrip(String tripString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String[] trip = tripString.split(System.lineSeparator());
        Date[] start_date = Arrays.stream(trip[4].split("\\s+|\\s+")).map(date_str -> {
            try {
                return dateFormat.parse(date_str);
            } catch (ParseException e) {
                return null;
            }
        }).toArray(Date[]::new);
        Date[] end_date = Arrays.stream(trip[5].split("\\s+|\\s+")).map(date_str -> {
            try {
                return dateFormat.parse(date_str);
            } catch (ParseException e) {
                return null;
            }
        }).toArray(Date[]::new);
        if (trip[2].toLowerCase().equals("family")) {
            return new FamilyTours(trip[0], trip[1], trip[2], Double.parseDouble(trip[3]),
                    start_date, end_date, trip[6], trip[7], Integer.parseInt(trip[8]), trip[9].split("\\s+|\\s+"),
                    trip[10], trip[11], trip[12].split("\\s+|\\s+"));
        } else if (trip[2].toLowerCase().equals("general")) {
            return new GeneralTours(trip[0], trip[1], trip[2], Double.parseDouble(trip[3]),
                    start_date, end_date, trip[6], trip[7], Integer.parseInt(trip[8]), trip[9].split("\\s+|\\s+"),
                    trip[10], trip[11], trip[12].split("\\s+|\\s+"));
        } else if (trip[2].toLowerCase().equals("couple")) {
            return new CoupleTours(trip[0], trip[1], trip[2], Double.parseDouble(trip[3]),
                    start_date, end_date, trip[6], trip[7], Integer.parseInt(trip[8]), trip[9].split("\\s+|\\s+"),
                    trip[10], trip[11], trip[12].split("\\s+|\\s+"));
        } else
            return null;
    }

    // function to get all customers from the file
    public static void getAllCustomers() {
        try {
            ArrayList<Customers> AllCustomers = new ArrayList<>();
            Path path = Paths.get("TravelManageSys\\TravelManageSys\\src\\main\\java\\data\\customers.txt");
            String val = Files.readString(path);
            String Customers[] = val.split("\\s+---\\s+");
            for (String c : Customers) {
                String[] customer = c.split(System.lineSeparator());
                String[] Fullname = customer[1].split(" ");
                AllCustomers.add(new Customers(customer[0], Fullname[0], Fullname[1], customer[2], customer[3],
                        Integer.parseInt(customer[4]), customer[5], customer[6], customer[7]));

            }
        } catch (Exception e) {

        }

    }

    // function to get all tour guides from the file
    public void getAllTourGuides() {
        try {
            ArrayList<TourGuide> AllTourGuides = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/TourGuides.txt");
            String valfortour = Files.readString(path);
            String TourGuides[] = valfortour.split("\\s+---\\s+");
            for (String t : TourGuides) {
                String[] tourguide = t.split(System.lineSeparator());
                String[] Fullname = tourguide[1].split(" ");
                AllTourGuides.add(new TourGuide(tourguide[0], Fullname[0], Fullname[1], tourguide[2], tourguide[3],
                        Integer.parseInt(tourguide[4]), tourguide[5], tourguide[6], tourguide[7]));

            }
        } catch (Exception e) {

        }
    }

    public void getAllHotels() {
        try {
            ArrayList<Hotels> AllHotels = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/Hotels.txt");
            String valforhotels = Files.readString(path);
            String Hotels[] = valforhotels.split("\\s+---\\s+");
            for (String t : Hotels) {
                String[] hotel = t.split(System.lineSeparator());
                AllHotels.add(new Hotels(hotel[0], Double.parseDouble(hotel[1]), Integer.parseInt(hotel[2]),
                        Boolean.parseBoolean(hotel[3]), Boolean.parseBoolean(hotel[4]),
                        Boolean.parseBoolean(hotel[5])));

            }
        } catch (Exception e) {

        }
    }

    // function to get all tour guides from the file

    public static ArrayList<Car> getAllCars() throws IOException {

        ArrayList<Car> AllCars = new ArrayList<>();
        Path path = Paths.get("D://Java oop//TravelManageSys//TravelManageSys//src//main//java//data//Cars.txt");
        String valfortour = Files.readString(path);
        String Cars[] = valfortour.split("\\s+---\\s+");
        for (String c : Cars) {
            String[] Car = c.split(System.lineSeparator());
            AllCars.add(new Car(Car[0], Car[1], Car[2], Integer.parseInt(Car[3]), Double.parseDouble(Car[4])));

        }
        return AllCars;
    }

}
