package model;

/*
* Location to represent map locations, like natural landmarks, with a name, region,
* and a custom made boolean
*/

public class Location {

    // REQUIRES: name, region, house must not be null / empty,  population >= 10
    // MODIFIES: this, Progress
    // EFFECTS: creates a new unvisited location with given name, region,
    //          and customMade. Increases total number of locations by 1.
    public Location(String name, String region, boolean visited, boolean customMade) {
    }



    // MODIFES: this
    // EFFECTS: toggles wether a location is visited or not
    public void toggleVisited() {

    }

    // getters
    public String getName() {
        return "";
    }

    public String getRegion() {
        return "";
    }

    public boolean getVisited() {
        return false;
    }

    public boolean customMade() {
        return false;
    }
}
    


