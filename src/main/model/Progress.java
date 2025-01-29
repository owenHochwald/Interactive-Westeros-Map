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
    // EFFECTS: increases the number of visited cities by 1
    public static void increaseNumVisited() {
        numVisited++;
    }

    // MODIFIES: this
    // EFFECTS: decrease the number of visited cities by 1
    public static void decreaseNumVisited() {
        numVisited--;
    }

    // MODIFIES: this
    // EFFECTS: resets number of visited cities to zero
    public static void resetProgress() {
        numVisited = 0;
    }

    // getters
    public static int getNumVisited() {
        return numVisited;
    }

    public static int getTotalNumCities() {
        return totalNumCities;
    }

    // setters
    public static void setNumVisited(int num) {
        numVisited = num;

    }

    public static void setTotalNumCities(int num) {
        totalNumCities = num;
    }

}
