package ui.cli;

import java.util.ArrayList;
import java.util.Scanner;

import model.Location;
import model.Progress;

/*
 * A class to represent all related componenets to managing locations in the CLI
 */
public class LocationManager {
    private ArrayList<Location> locations;
    private Scanner input;

    public LocationManager(ArrayList<Location> locations, Scanner input) {
        this.locations = locations;
        this.input = input;
    }

    // MODIFES: this, Progress
    // EFFECTS: displays location management options
    // and logic to direct user to next step
    public void manageLocations() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       MANAGE Locations                 ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  [1] Add a location                    ║");
        System.out.println("║  [2] Remove a location                 ║");
        System.out.println("║  [4] Go back                           ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.print("║  Enter your choice: ");

        String command = input.next();
        handleMangeLocationInput(command);
    }

    // MODIFIES: this
    // EFFECTS: processes user keyboard input for custom location view
    private void handleMangeLocationInput(String key) {
        switch (key) {
            case "1":
                addLocation();
                break;
            case "2":
                removeLocation();
                break;
            case "3":
                break;
            default:
                System.out.println("Invalid selection!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a custom location
    private void addLocation() {
        System.out.println("\nEnter location details:");

        System.out.print("Name: ");
        String name = input.next();
        input.nextLine();

        System.out.print("Region: ");
        String region = input.nextLine();

        Location newLocation = new Location(name, region, true);
        locations.add(newLocation);

        System.out.println("Added " + name + " successfully!");
    }

    // MODIFIES: this, Progress
    // EFFECTS: removes a location if its a custom location, nothing if its not
    private void removeLocation() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ Enter a location name to remove: ");
        String locationName = input.next();
        Location locationToRemove = findLocationByName(locationName);

        if (locationToRemove.customMade()) {
            if (locationToRemove.getVisited()) {
                Progress.decreasesNumVisitedEntries();
            }
            Progress.decreasesNumEntries();
            locations.remove(locationToRemove);
        } else {
            System.out.println("We can't remove a original locations, my lord!");
        }
    }

    // REQUIRES: name in the locations names
    // EFFECTS: returns the locations with the matching name, nothing if not found
    private Location findLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

}
