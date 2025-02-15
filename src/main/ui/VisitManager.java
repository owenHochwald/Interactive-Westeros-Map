package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.City;
import model.Location;

public class VisitManager {
    private static final String TOP_BORDER    = "╔══════════════════════════════════════════════════════════════╗";
    private static final String BOTTOM_BORDER = "╚══════════════════════════════════════════════════════════════╝";
    private static final String MIDDLE_BORDER = "╠══════════════════════════════════════════════════════════════╣";
    private static final String VERTICAL_BAR  = "║";
    private static final int LINE_WIDTH = 60;

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
            }  else if (choice.equals("n")) {
                System.out.println("║  Status unchanged...                                          ║");
            } else if (!choice.equals("n")) {
                System.out.println("║ By the Old Gods and the New, that command is not recognized ║");
            }
            System.out.println(BOTTOM_BORDER);
        }
        displayDepartureMessage();
    }

    // EFFECTS: displays the visit status menu
    private void displayVisitStatusMenu() {
        System.out.println(TOP_BORDER);
        System.out.println("              THE MAESTER'S TRAVEL RECORDS");
        System.out.println(MIDDLE_BORDER);
        System.out.println("              By order of the Hand of the King");
        System.out.println(VERTICAL_BAR + formatLine("Royal Decrees:"));
        System.out.println(VERTICAL_BAR + formatLine("[M] Mark your passage through these lands"));
        System.out.println(VERTICAL_BAR + formatLine("[N] Proceed to the next holding"));
        System.out.println(VERTICAL_BAR + formatLine("[Q] Return to the Small Council"));
        System.out.println(BOTTOM_BORDER + "\n");
    }

    // EFFECTS: returns a line of the specified length wrapped with vertical bars
    private String formatLine(String text) {
        return String.format(" %-" + (LINE_WIDTH + 1) + "s" + VERTICAL_BAR, text);
    }


    // EFFECTS: displays the current place being modified
    private void displayCurrentPlace(String name, boolean isVisited, String additionalInfo) {
        String visitedStatus = isVisited ? "Visited" : "Not Visited";
        System.out.println(TOP_BORDER);

        System.out.printf("║  %-60s║%n", name + additionalInfo);
        System.out.printf("║  Status: %-52s║%n", visitedStatus);
        System.out.println(MIDDLE_BORDER);
        System.out.print("║  Your command, my lord: ");
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
            additionalInfo += ", House " + city.getHouse(); 
            additionalInfo += city.getIsCapital() ? " (Capital)" : "";
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
        System.out.printf("║  Status updated to: %-41s║%n", newStatus);
    }

    private void displayDepartureMessage() {
        System.out.println(TOP_BORDER);
        System.out.println("              The Records Have Been Updated");
        System.out.println("              Returning to the Small Council");
        System.out.println("              May the Seven guide your path!");
        System.out.println(BOTTOM_BORDER);
    }
}
