package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.City;
import model.Location;

public class VisitManager {
    ArrayList<City> cities;
    ArrayList<Location> locations;
    Scanner input;

    public VisitManager(ArrayList<City> cities, ArrayList<Location> locations, Scanner input) {
        this.cities = cities;
        this.locations = locations;
        this.input = input;
    }

    // MODIFIES: this
    // EFFECTS: allow user to change visit status one by one of each entry (location/city)
    public void changeVisitStatus() {
        displayVisitStatusMenu();

        ArrayList<Location> places = new ArrayList<>();
        places.addAll(cities);
        places.addAll(locations);
        for (Location place : places) {
            handlePlace(place);

            String choice = input.next().toLowerCase();
            if (choice.equals("m")) {
                toggleVisitStatus(place, place.getVisited());
            } else if (!choice.equals("n")) {
                System.out.println("║  Invalid choice, moving to next place...                    ║");
            }
            System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        }
    }

    // EFFECTS: displays the visit status menu
    private void displayVisitStatusMenu() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                 CHANGE VISIT STATUS                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Navigating through cities and locations                   ║");
        System.out.println("║  [m] Toggle visited status                                 ║");
        System.out.println("║  [n] Next entry                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    // EFFECTS: displays the current place being modified
    private void displayCurrentPlace(String name, boolean isVisited, String additionalInfo) {
        String visitedStatus = isVisited ? "Visited" : "Not Visited";
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.printf("║  Current Place: %-43s║%n", name + additionalInfo);
        System.out.printf("║  Status: %-49s║%n", visitedStatus);
        System.out.print("║  Enter choice: ");
    }

    // MODIFIES: this
    // EFFECTS: helper to process the places lists
    private void handlePlace(Location place) {
        String name;
        boolean isVisited;
        String additionalInfo = "";

        if (place instanceof City) {
            City city = (City) place;
            name = city.getName();
            isVisited = city.getVisited();
            additionalInfo = city.getIsCapital() ? " (Capital)" : "";
        } else {
            name = place.getName();
            isVisited = place.getVisited();
        }

        displayCurrentPlace(name, isVisited, additionalInfo);
    }

    // MODIFIES: this
    // EFFECTS: toggles the visited status of the given place
    private void toggleVisitStatus(Location place, boolean isVisited) {
        if (place instanceof City) {
            ((City) place).toggleVisited();
        } else {
            ((Location) place).toggleVisited();
        }
        String newStatus = isVisited ? "Not Visited" : "Visited";
        System.out.printf("║  Status updated to: %-39s║%n", newStatus);
    }
}
