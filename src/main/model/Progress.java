package model;

/*
 * Represents a users progress towards visiting cities with a total number of cities and 
 * number of cities the users has visited. Note, total number of cities and number of 
 * cities visited CANNOT be negative.
 */

public class Progress {

    private static int numVisited = 0;
    private static int totalNumCities = 0;

    // MODIFIES: this
    // EFFECTS: increases the total number of cities by 1
    public static void increaseTotalCities() {
        totalNumCities++;
    }

    // MODIFIES: this
    // EFFECTS: increases the total number of entries by 1
    public static void increaseNumEntries() {
    }

    // MODIFIES: this
    // EFFECTS: decreases the total number of entries by 1
    public static void decreasesNumEntries() {
    }

    
    // MODIFIES: this
    // EFFECTS: resets number of visited entries to zero
    public static void resetEntryProgress() {
        numVisited = 0;
    }

    // MODIFIES: this
    // EFFECTS: increases the number of visited cities by 1
    public static void increaseNumCitiesVisited() {
        numVisited++;
    }

    // MODIFIES: this
    // EFFECTS: decrease the number of visited cities by 1
    public static void decreaseNumCitiesVisited() {
        numVisited--;
    }

    // MODIFIES: this
    // EFFECTS: resets number of visited cities to zero
    public static void resetCityProgress() {
        numVisited = 0;
    }

    // getters
    public static int getNumCitiesVisited() {
        return numVisited;
    }

    public static int getTotalNumCities() {
        return totalNumCities;
    }

    public static int getTotalNumEntries() {
        return 0;
    }

    public static int getTotalNumVisitedEntries() {
        return 0;
    }

    // setters
    public static void setNumCitiesVisited(int num) {
        numVisited = num;

    }

    public static void setTotalNumCities(int num) {
        totalNumCities = num;
    }

    public static void setTotalNumEntries(int num) {

    }

    public static void setTotalNumVisited(int num) {
        
    }

}
