package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import TravelManagement.Trip;
import TravelManagement.CoupleTours;
import TravelManagement.FamilyTours;
import TravelManagement.GeneralTours;
import TravelManagement.TourGuide;

public class fileManipulation {
    public void getAllTrips() {
        try {
            ArrayList<Trip> AllTrips = new ArrayList<>();
            Path path = Paths.get("TravelManageSys/src/main/java/data/trips.txt");
            String val = Files.readString(path);
            String Trips[] = val.split("\\s+---\\s+");
            for (String t : Trips) {
                String[] trip = t.split(System.lineSeparator());
                if (trip[2].toLowerCase().equals("family")) {
                    AllTrips.add(new FamilyTours(trip[0], trip[1], trip[2], Double.parseDouble(trip[3]), trip[4],
                            trip[5], trip[6],
                            null, 150, null, null, null, null));
                } else if (trip[2].toLowerCase().equals("general")) {
                    AllTrips.add(new GeneralTours(trip[0], trip[1], trip[2], Double.parseDouble(trip[3]), trip[4],
                            trip[5], "",
                            null, 150, null, null, null, null));
                } else if (trip[2].toLowerCase().equals("couple")) {
                    AllTrips.add(new CoupleTours(trip[0], trip[1], trip[2], Double.parseDouble(trip[3]), trip[4],
                            trip[5], "",
                            null, 150, null, null, null, null));
                }
            }
        } catch (Exception e) {

        }

      
    }
    public void getAllTourGuides() {
            try {
                ArrayList<TourGuide> AllTourGuides = new ArrayList<>();
                Path path = Paths.get("TravelManageSys/src/main/java/data/TourGuides.txt");
                String valfortour = Files.readString(path);
                String TourGuides[] = valfortour.split("\\s+---\\s+");
                for (String t : TourGuides) {
                    String[] tourguide = t.split(System.lineSeparator());
                    String[] Fullname = tourguide[1].split(" ");
                    AllTourGuides.add(new TourGuide(tourguide[0],Fullname[0],Fullname[1],tourguide[2],tourguide[3],Integer.parseInt(tourguide[4]),tourguide[5],tourguide[6],tourguide[7]));
                   
                }
            } catch (Exception e) {

            }
    }
}
