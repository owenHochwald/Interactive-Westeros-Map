package ui.gui;

/*
 * Represents a marker for a city to be displayed on the MapPanel
 */
public class CityMarker {
    private int x;
    private int y;
    private int diameter;
    private String name;

    // EFFECTS: constructs a marker with an x and y position, diameter, and a city
    // name
    public CityMarker(int x, int y, int diameter, String name) {
        this.x = x;
        this.y= y;
        this.diameter = diameter;
        this.name = name;
    }

    // setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void setName(String name) {
        this.name = name;
    }

    // getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public String getName() {
        return name;
    }
}
