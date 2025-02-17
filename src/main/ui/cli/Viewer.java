package ui.cli;

import java.util.ArrayList;

import model.City;
import model.Location;

public class Viewer {
    ArrayList<City> cities;
    ArrayList<Location> locations;

    public Viewer(ArrayList<City> cities, ArrayList<Location> locations) {
        this.cities = cities;
        this.locations = locations;
    }

    
    // EFFECTS: displays all cities one by one
    public void viewAllCities() {
        if (cities.isEmpty()) {
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("║     No cities available           ║");
            System.out.println("╚═══════════════════════════════════╝");
            return;
        }

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                     ALL CITIES                             ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");

        for (City city : cities) {
            String capitalStatus = city.getIsCapital() ? " (Capital)" : "";
            String visitedStatus = city.getVisited() ? "Yes" : "No";

            System.out.println("║  Name: " + padRight(city.getName() + capitalStatus, 52) + "║");
            System.out.println("║  House: " + padRight(city.getHouse(), 52) + "║");
            System.out.println("║  Region: " + padRight(city.getRegion(), 51) + "║");
            System.out.println("║  Population: " + padRight(String.format("%,d", city.getPopulation()), 47) + "║");
            System.out.println("║  Visited: " + padRight(visitedStatus, 50) + "║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
        }
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    // Helper method to pad strings to the right
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    // EFFECTS: displays all locations to the console
    public void viewAllLocations() {
        if (locations.isEmpty()) {
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("║     No locations available        ║");
            System.out.println("╚═══════════════════════════════════╝");
            return;
        }

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                     ALL LOCATIONS                          ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");

        for (Location location : locations) {
            displayLocationInfo(location);
        }
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    // EFFECTS: displays a single Location to the screen
    private void displayLocationInfo(Location location) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                   LOCATION INFORMATION                     ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.printf("║  Name: %-52s║%n", location.getName());
        System.out.printf("║  Region: %-50s║%n", location.getRegion());
        System.out.printf("║  Visited: %-49s║%n", location.getVisited() ? "Yes" : "No");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

}
