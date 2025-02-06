package model;

/*
 * Represents a users progress towards visiting cities with a total number of cities and 
 * number of cities the users has visited. Note, total number of cities and number of 
 * cities visited CANNOT be negative.
 */

public class Progress {

    private static int numCitiesVisited = 0;
    private static int totalNumCities = 0;
    private static int totalNumEntries = 0;
    private static int numEntriesVisited = 0;

    // MODIFIES: this
    // EFFECTS: increases the total number of cities by 1
    public static void increaseTotalCities() {
        totalNumCities++;
    }

    // MODIFIES: this
    // EFFECTS: increases the total number of entries by 1
    public static void increaseNumEntries() {
        totalNumEntries++;
    }

    // MODIFIES: this
    // EFFECTS: decreases the total number of entries by 1
    public static void decreasesNumEntries() {
        totalNumEntries--;
    }

        // MODIFIES: this
    // EFFECTS: increases the total number of visited entries by 1
    public static void increaseNumVisitedEntries() {
        numEntriesVisited++;
    }

    // MODIFIES: this
    // EFFECTS: decreases the total number of visited entries by 1
    public static void decreasesNumVisitedEntries() {
        numEntriesVisited--;
    }

    
    // MODIFIES: this
    // EFFECTS: resets number of visited entries to zero
    public static void resetEntryProgress() {
        numEntriesVisited = 0;
    }

    // MODIFIES: this
    // EFFECTS: resets number of total entries to zero
    public static void resetTotalEntry() {
        totalNumEntries = 0;
    }

    // MODIFIES: this
    // EFFECTS: resets number of total cities to zero
    public static void resetTotalCities() {
        totalNumCities = 0;
    }

    // MODIFIES: this
    // EFFECTS: increases the number of visited cities by 1
    public static void increaseNumCitiesVisited() {
        numCitiesVisited++;
    }

    // MODIFIES: this
    // EFFECTS: decrease the number of visited cities by 1
    public static void decreaseNumCitiesVisited() {
        numCitiesVisited--;
    }

    // MODIFIES: this
    // EFFECTS: resets number of visited cities to zero
    public static void resetCityProgress() {
        numCitiesVisited = 0;
    }

    // getters
    public static int getNumCitiesVisited() {
        return numCitiesVisited;
    }

    public static int getTotalNumCities() {
        return totalNumCities;
    }

    public static int getTotalNumEntries() {
        return totalNumEntries;
    }

    public static int getTotalNumVisitedEntries() {
        return numEntriesVisited;
    }

    // setters
    public static void setNumCitiesVisited(int num) {
        numCitiesVisited = num;

    }

    public static void setTotalNumCities(int num) {
        totalNumCities = num;
    }

    public static void setTotalNumEntries(int num) {
        totalNumEntries = num;

    }

    public static void setTotalNumVisited(int num) {
        numEntriesVisited = num;
    }

}
