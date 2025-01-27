package model;
/*
 * Represent a users progress towards visiting cities with a total number of cities and 
 * number of cities the users has visited.
 */

public class Progress {

    // EFFECTS: Creates a new Progress class with no visited cities and as many total cities 
    //          as active City objects
    public Progress() {

    }


    // MODIFIES: this, city
    // EFFECTS: increases number of visited cities by 1, and mark that city as visited
    public void visitCity(City city) {

    }

    // setters
    // REQUIRES: num >= totalNumCities
    public void setTotalNumCities(int num) {
        
    }

    // getters
    public int getNumVisited() {
        return 0;
    }

    public int getTotalNumCities() {
        return 0;
    }

}
