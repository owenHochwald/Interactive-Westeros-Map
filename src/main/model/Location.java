package model;

/*
* Location to represent map locations, like natural landmarks, with a name, region,
* and a custom made boolean
*/

public class Location {

    protected String name;
    protected String region;
    protected boolean visited;
    final protected boolean customMade;

    // REQUIRES: name, region, house must not be null / empty,  population >= 10
    // MODIFIES: this, Progress
    // EFFECTS: creates a new unvisited location with given name, region,
    //          and customMade. Increases total number of locations by 1.
    public Location(String name, String region, boolean customMade) {
        this.name = name;
        this.region = region;
        this.visited = false;
        this.customMade = customMade;
        // TODO: add Progress updates
    }



    // MODIFES: this
    // EFFECTS: toggles wether a location is visited or not
    public void toggleVisited() {
        if (!visited) {
            Progress.increaseNumCitiesVisited();
        } else {
            Progress.decreaseNumCitiesVisited();
        }
        visited = !visited;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public boolean getVisited() {
        return visited;
    }

    public boolean customMade() {
        return customMade;
    }
}
    


