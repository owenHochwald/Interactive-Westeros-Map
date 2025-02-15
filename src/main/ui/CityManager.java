package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.City;
import model.Progress;

public class CityManager {
    private ArrayList<City> cities;
    private Scanner input;

    public CityManager(ArrayList<City> cities, Scanner input) {
        this.cities = cities;
        this.input = input;
    }

    // MODIFES: this, Progress
    // EFFECTS: displays city management options
    // and logic to direct user to next step
    public void manageCities() {
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

    // MODIFIES: this
    // EFFECTS: adds a custom city
    private void addCity() {
        input.nextLine();
        System.out.println("\nEnter city details:");
        System.out.print("Name: ");
        String name = input.nextLine();
        int population = getValidatedInt("Population: ");
        System.out.print("House (ruling family): ");
        String house = input.nextLine();
        System.out.print("Region: ");
        String region = input.nextLine();
        boolean isCapital = getValidatedBoolean("Is this city a capital? (true/false): ");

        City newCity = new City(name, population, house, region, isCapital, true);
        cities.add(newCity);
        System.out.println("Added " + name + " successfully!");
    }

    // EFFECTS: prompts the user for an integer input and validates it
    private int getValidatedInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            // try {
            String line = input.nextLine();
            int value = Integer.parseInt(line);
            if (value >= 0) {
                return value;
            } else {
                System.out.println("Please enter a non-negative number.");
            }
            // } catch (NumberFormatException e) {
            //     System.out.println("Invalid input. Please enter a valid number.");
            // }
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

    // MODIFIES: this, Progress
    // EFFECTS: removes a city if its a custom city, nothing if its not
    private void removeCity() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ Enter a city name to remove: ");
        String cityName = input.next();
        City cityToRemove = findCityByName(cityName);

        if (cityToRemove.customMade()) {
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

    // REQUIRES: name in the cities names
    // EFFECTS: helper method that returns the city with the matching name
    private City findCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
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
}
