package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.City;
import model.Location;
import model.Progress;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * Represents a UI to handle all of the user interaction with the console.
 * Cities must contain unique cities with unique names.
 */
public class MapApp {
    private static final String JSON_STORE = "./data/mapState.json";
    private ArrayList<City> cities;
    private ArrayList<Location> locations;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private LocationManager locationManager;
    private CityManager cityManager;
    private Viewer viewer;
    private VisitManager visitManager;

    // EFFECTS: runs the Westeros Map App
    public MapApp() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: handle user input
    private void runApp() {
        String command = null;
        System.out.println("Welcome to the Westeros Interactive Map(Console Version)!");

        init();

        while (true) {
            displayMainMenu();
            command = input.next().toLowerCase();
            if (command.equals("8")) {
                System.out.println("Thanks for playing!");
                break;
            } else {
                handleInput(command);
            }
        }
    }

    // MODIFES: this
    // EFFECTS: inits preset locations to the map
    private void init() {
        this.cities = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.input = new Scanner(System.in);
        initCities();
        initLocations();
        locationManager = new LocationManager(locations, input);
        cityManager = new CityManager(cities, input);
        viewer = new Viewer(cities, locations);
        visitManager = new VisitManager(cities, locations, input);
    }

    // MODIFIES: this
    // EFFECTS: initializes preset cities
    private void initCities() {
        cities.add(new City("King's Landing", 500000, "Baratheon", "Crownlands", true, false));
        cities.add(new City("Winterfell", 100000, "Stark", "The North", true, false));
        cities.add(new City("Lannisport", 50000, "Lannister", "The Westerlands", false, false));
        cities.add(new City("Oldtown", 200000, "Hightower", "The Reach", false, false));
        cities.add(new City("White Harbor", 100000, "Manderly", "The North", false, false));
        cities.add(new City("Sunspear", 50000, "Martell", "Dorne", true, false));
        cities.add(new City("Riverrun", 40000, "Tully", "The Riverlands", false, false));
    }

    // MODIFIES: this
    // EFFECTS: initializes preset locations
    private void initLocations() {
        locations.add(new Location("Gods Eye", "The Riverlands", false));
        locations.add(new Location("The Wall", "The North", false));
        locations.add(new Location("Wolf's Wood", "The North", false));
    }

    // EFFECTS: displays map menu options
    private void displayMainMenu() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       THE REALM OF WESTEROS            ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  1. View Cities                        ║");
        System.out.println("║  2. View Locations                     ║");
        System.out.println("║  3. Visit Places                       ║");
        System.out.println("║  4. Manage Custom Locations & Cities   ║");
        System.out.println("║  5. Load Map                           ║");
        System.out.println("║  6. Archive Your Realm                 ║");
        System.out.println("║  7. Display Progress                   ║");
        System.out.println("║  8. Abandon the Throne                 ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Your command, my lord: ");
    }

    // MODIFIES: this
    // EFFECTS: processes user keyboard input
    private void handleInput(String key) {
        switch (key) {
            case "1" -> viewer.viewAllCities();
            case "2" -> viewer.viewAllLocations();
            case "3" -> visitManager.changeVisitStatus();
            case "4" -> manageEntries();
            case "5" -> loadMap();
            case "6" -> saveMap();
            case "7" -> displayProgress();
            default -> System.out.println("Invalid selection!");
        }
    }

    // MODIFES: this, Progress
    // EFFECTS: displays city / location options for next steps
    private void manageEntries() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       Manage Locations & Cities        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  1. Manage Locations                   ║");
        System.out.println("║  2. Manage Cities                      ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Your command, my lord: ");

        String command = input.next().toLowerCase();
        if (command.equals("1")) {
            locationManager.manageLocations();
        } else if (command.equals("2")) {
            cityManager.manageCities();
        } else {
            System.out.println("I don't know what you mean, please enter something I can do...");
        }

    }

    // MODIFES: this
    // EFFECTS: loads a map from file
    private void loadMap() {
        try {
            Progress.resetCityProgress();
            Progress.resetEntryProgress();
            Progress.resetTotalCities();
            Progress.resetTotalEntry();
            locations = jsonReader.readLocations();
            cities = jsonReader.readCities();
            System.out.println("╠  ...Loaded in saved cities and locations...  ═╣");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFES: mapState
    // EFFECTS: saves the workroom to file
    private void saveMap() {
        try {
            jsonWriter.open();
            jsonWriter.write(locations, cities);
            jsonWriter.close();
            System.out.println("Saved your map to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: shows the visited city progress as a progress bar
    private void displayProgress() {
        int numVisited = Progress.getTotalNumVisitedEntries();
        int totalPlaces = Progress.getTotalNumEntries();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    VISITING PROGRESS                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        int percentage = (int) ((numVisited * 100.0) / totalPlaces);
        int barLength = 50;
        int filledLength = (int) ((numVisited * (double) barLength) / totalPlaces);
        StringBuilder bar = new StringBuilder("║  [");
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("█");
            } else {
                bar.append("░");
            }
        }
        bar.append("]  ║");
        System.out.println(bar.toString());
        System.out.printf("║  Places Visited: %d/%d (%d%%)                               ║%n",
                numVisited, totalPlaces, percentage);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

}
