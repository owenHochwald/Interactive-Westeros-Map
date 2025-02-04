package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.City;

public class MapApp {
    private ArrayList<City> cities;
    private Scanner input;

    // EFFECTS: runs the Westeros Map App
    public MapApp() {
        runApp();

    }


    // MODIFIES: this
    // EFFECTS: handle user input
    private void runApp() {
        String command = null;

        System.out.println("Welcome to the Westeros Interactive Map(Console Version)!");

        init();

        while(true) {
            displayMenu();

            command = input.next().toLowerCase();

            if (command.equals("q")) {
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
        input = new Scanner(System.in);

        City kingsLanding = new City("King's Landing", 500000, "Baratheon", "Crownlands", true, false);
        City winterfell = new City("Winterfell", 100000, "Stark", "The North", true, false);
        City lannisport = new City("Lannisport", 50000, "Lannister", "The Westerlands", false, false);
        City oldtown = new City("Oldtown", 200000, "Hightower", "The Reach", false, false);
        City whiteHarbor = new City("White Harbor", 100000, "Manderly", "The North", false, false);
        City sunspear = new City("Sunspear", 50000, "Martell", "Dorne", true, false);
        City riverrun = new City("Riverrun", 40000, "Tully", "The Riverlands", false, false);
        cities.add(kingsLanding);
        cities.add(winterfell);
        cities.add(lannisport);
        cities.add(oldtown);
        cities.add(whiteHarbor);
        cities.add(sunspear);
        cities.add(riverrun);
        
    }

    // EFFECTS: displays map menu options
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tView all cities-> va");
        System.out.println("\tView only custom-made cities-> vc");
        System.out.println("\tDisplay your progress toward visiting cities-> p");
        System.out.println("\tQuit-> q");

    }

    // MODIFIES: this
    // EFFECTS: processes user keyboard input
    private void handleInput(String key) {
        switch (key) {
            case "va":
                viewAllCities();
                break;
            case "vc":
                changeCustomCities();
                break;
            case "p":
                displayProgress();
                break;
            default:
                System.out.println("Invalid selection!");
        }

    }

    // EFFECTS: displays all cities one by one with ability to mark as visited
    private void viewAllCities() {

    }

    // MODIFIES: this
    // EFFECTS displays custom cities one by one with ability to change their attributes
    private void changeCustomCities() {

    }


    // MODIFIES: this
    // EFFECTS: adds a custom city
    private void addCity() {

    }

    // MODIFIES: this
    // EFFECTS: removes a custom city
    private void removeCity() {

    }

    // MODIFIES: this
    // EFFECTS: marks a city as visited
    private void markVisited() {

    }

    // REQUIRES: both cities are in cities
    // MODIFIES: this
    // EFFECTS: establishes an alliance between two cities in the map
    private void establishAlliance(City c1, City c2) {

    }

    // EFFETS: shows the visited city progress as a progress bar
    private void displayProgress() {

    }


}
