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
 * 
 */

public class MapApp {
    private static final String JSON_STORE = "./data/mapState.json";
    private ArrayList<City> cities;
    private ArrayList<City> customCities;
    private ArrayList<Location> locations;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

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
        this.customCities = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.input = new Scanner(System.in);
        initCities();
        initLocations();
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

    // // EFFECTS: displays map menu options
    // private void displayMainMenu() {
    //     System.out.println("╔════════════════════════════════════════╗");
    //     System.out.println("║       GAME OF THRONES MAP MENU         ║");
    //     System.out.println("╠════════════════════════════════════════╣");
    //     System.out.println("║  [va] View all cities                  ║");
    //     System.out.println("║  [vc] View only custom-made cities     ║");
    //     System.out.println("║  [l]  View all locations               ║");
    //     System.out.println("║  [lc] View only custom-made locations  ║");
    //     System.out.println("║  [v]  Visit places                     ║");
    //     System.out.println("║  [al] Add a custom location            ║");
    //     System.out.println("║  [ac] Add a custom city                ║");
    //     System.out.println("║  [p]  Display your progress            ║");
    //     System.out.println("║  [ls] Load saved map                   ║");
    //     System.out.println("║  [sm] Save map                         ║");
    //     System.out.println("║  [q]  Quit                             ║");
    //     System.out.println("╠════════════════════════════════════════╣");
    //     System.out.print("║  Enter your choice: ");
    // }

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

    // // MODIFIES: this
    // // EFFECTS: processes user keyboard input
    // private void handleInput(String key) {
    //     switch (key) {
    //         case "va" -> viewAllCities();
    //         case "v" -> changeVisitStatus();
    //         case "vc" -> changeCustomCities();
    //         case "ac" -> addCity();
    //         case "al" -> addLocation();
    //         case "p" -> displayProgress();
    //         case "l" -> viewAllLocations();
    //         case "lc" -> viewCustomLocations();
    //         case "ls" -> loadMap();
    //         case "sm" -> saveMap();
    //         default -> System.out.println("Invalid selection!");
    //     }
    // }

    // MODIFIES: this
    // EFFECTS: processes user keyboard input
    private void handleInput(String key) {
        switch (key) {
            case "1" -> viewAllCities();
            case "2" -> viewAllLocations();
            case "3" -> changeVisitStatus();
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
            manageLocations();
        } else if (command.equals("2")) {
            manageCities();
        } else {
            System.out.println("I don't know what you mean, please enter something I can do...");
        }

    }

    // MODIFES: this, Progress
    // EFFECTS: displays city management options
    //          and logic to direct user to next step
    private void manageCities() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       MANAGE CITIES                    ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  [1] Add a city                        ║");
        System.out.println("║  [2] Remove a city                     ║");
        System.out.println("║  [3] Establish an alliance             ║");
        System.out.println("║  [4] Go back                           ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.print("║  Enter your choice: ");
        
        String command = input.next();
        handleMangeCityInput(command);
    }

    // MODIFIES: this
    // EFFECTS: processes user keyboard input for custom cities view
    private void handleMangeCityInput(String key) {
        switch (key) {
            case "1":
                addCity();
                break;
            case "2":
                removeCity();
                break;
            case "3":
                establishAlliance();
                break;
            case "4":
                break;
            default:
                System.out.println("Invalid selection!");
        }

    }
    
    // MODIFES: this, Progress
    // EFFECTS: displays location management options
    //          and logic to direct user to next step
    private void manageLocations() {
        
    }


    // EFFECTS: displays all cities one by one
    private void viewAllCities() {
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



    // Helper method to pad strings to the right
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    // helper for the changeVisitStatus method
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
    // EFFECTS: allow user to change visit status one by one of each city
    private void changeVisitStatus() {
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

    // EFFECTS: displays options menu for custom cities
    private void customCitiesMenu() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       CUSTOM CITIES MANAGEMENT         ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  [r] Remove a city                     ║");
        System.out.println("║  [a] Establish an alliance             ║");
        System.out.println("║  [v] Toggle visited status             ║");
        System.out.println("║  [n] Go to the next city               ║");
        System.out.println("║  [q] Quit                              ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.print("║  Enter your choice: ");
    }

    // // MODIFIES: this
    // // EFFECTS: processes user keyboard input for custom cities view
    // private void handleInputCustomCityView(String key, City city) {
    //     switch (key) {
    //         case "r":
    //             removeCity(city);
    //             break;
    //         case "a":
    //             establishAlliance(city);
    //             break;
    //         case "v":
    //             city.toggleVisited();
    //             break;
    //         case "n":
    //             break;
    //         default:
    //             System.out.println("Invalid selection!");
    //     }

    // }

    // // MODIFIES: this
    // // EFFECTS displays custom cities one by one with ability to change their
    // // settings
    // private void changeCustomCities() {
    //     ArrayList<City> customCities = getCustomCities();
    //     System.out.println();
    //     if (customCities.isEmpty()) {
    //         System.out.println("╔═══════════════════════════════════╗");
    //         System.out.println("║     No custom cities created :(   ║");
    //         System.out.println("╚═══════════════════════════════════╝");
    //     } else {
    //         for (City city : customCities) {
    //             displayCityInfo(city);
    //             customCitiesMenu();
    //             String choice = input.next().toLowerCase();

    //             if (choice.equals("q")) {
    //                 break;
    //             }
    //             handleInputCustomCityView(choice, city);
    //         }
    //     }
    // }

    private void displayCityInfo(City city) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                     CITY INFORMATION                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.printf("║  Name: %-52s║%n", city.getName());
        System.out.printf("║  Population: %-47d║%n", city.getPopulation());
        System.out.printf("║  House: %-52s║%n", city.getHouse());
        System.out.printf("║  Region: %-51s║%n", city.getRegion());
        System.out.printf("║  Capital City: %-45s║%n", city.getIsCapital() ? "Yes" : "No");
        System.out.printf("║  Visited: %-50s║%n", city.getVisited() ? "Yes" : "No");
        System.out.printf("║  Alliances: %-48s║%n",city.getAlliances());
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    private void viewCustomLocations() {
        ArrayList<Location> customLocations = getCustomLocations();

        if (customLocations.isEmpty()) {
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("║   No custom locations created :(  ║");
            System.out.println("╚═══════════════════════════════════╝");
            return;
        }

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                   CUSTOM LOCATIONS                         ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");

        for (Location location : customLocations) {
            displayLocationInfo(location);
        }
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    // Helper method to get custom cities
    private ArrayList<City> getCustomCities() {
        ArrayList<City> customCities = new ArrayList<>();
        for (City city : cities) {
            if (city.customMade()) {
                customCities.add(city);
            }
        }
        return customCities;
    }

    // Helper method to get custom locations
    private ArrayList<Location> getCustomLocations() {
        ArrayList<Location> customLocations = new ArrayList<>();
        for (Location location : locations) {
            if (location.customMade()) {
                customLocations.add(location);
            }
        }
        return customLocations;
    }

    // EFFECTS: displays all locations to the console
    private void viewAllLocations() {
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

    // Helper method for viewAllLocations
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

    // MODIFIES: this
    // EFFECTS: adds a custom city
    private void addCity() {
        input.nextLine();
        System.out.println("\nEnter city details:");
        String name = getStringInput("Name: ");
        int population = getValidatedInt("Population: ");
        String house = getStringInput("House (ruling family): ");
        String region = getStringInput("Region: ");
        boolean isCapital = getValidatedBoolean("Is this city a capital? (true/false): ");

        City newCity = new City(name, population, house, region, isCapital, true);
        cities.add(newCity);
        customCities.add(newCity);
        System.out.println("Added " + name + " successfully!");
    }

    // EFFECTS: prompts the user for a string input and returns it
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine();  
    }

    // EFFECTS: prompts the user for an integer input and validates it
    private int getValidatedInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = input.nextLine();
                int value = Integer.parseInt(line);
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // EFFECTS: prompts the user for a boolean input and validates it
    private boolean getValidatedBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = input.nextLine().toLowerCase(); 
            if (response.equals("true") || response.equals("false")) {
                return Boolean.parseBoolean(response);
            } else {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a city if its a custom city, nothing if its not
    private void removeCity() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ Enter a city name to remove: ");
        String cityName = input.next();
        City cityToRemove = findCityByName(cityName);

        if(cityToRemove.customMade()) {
            if (cityToRemove.getVisited()) {
                Progress.decreaseNumCitiesVisited();
                Progress.decreasesNumVisitedEntries();
            }
            Progress.decreasesNumEntries();
            Progress.setTotalNumCities(Progress.getTotalNumCities() - 1);
            cities.remove(cityToRemove);
        } else {
            System.out.println("We can't remove a original cities, my lord!");
        }
    }

    // MODIFIES: this
    // EFFECTS: establishes an alliance between two cities in the map
    private void establishAlliance() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ Entry the first city: ");
        String cityName = input.next();
        City firstCity = findCityByName(cityName);
        System.out.println("║ Entry the second city: ");
        cityName = input.next();
        City secondCity = findCityByName(cityName);

        firstCity.addAlliance(secondCity.getName());
        secondCity.addAlliance(firstCity.getName());
        System.out.println("║ Succesfully created the alliance!");
    }

    // Helper method for establishAlliance
    // REQUIRES: name in the cities names
    // EFFECTS: returns the city with the matching name
    private City findCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
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
