package ui.gui;

/*
 * Represents a marker for a city to be displayed on the MapPanel
 */
public class CityMarker {
    private int xloc;
    private int yloc;
    private int diameter;
    private String name;

    // EFFECTS: constructs a marker with an x and y position, diameter, and a city
    // name
    public CityMarker(int x, int y, int diameter, String name) {
        this.xloc = x;
        this.yloc = y;
        this.diameter = diameter;
        this.name = name;
    }

    // setters
    public void setXloc(int x) {
        this.xloc = x;
    }

    public void setYloc(int y) {
        this.yloc = y;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void setName(String name) {
        this.name = name;
    }

    // getters
    public int getXloc() {
        return xloc;
    }

    public int getYloc() {
        return yloc;
    }

    public int getDiameter() {
        return diameter;
    }

    public String getName() {
        return name;
    }
}
