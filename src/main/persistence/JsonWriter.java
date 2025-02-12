package persistence;
import model.Location;
import model.City;


import java.io.*;
import java.util.ArrayList;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {


    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of locations and cities to file
    public void write(ArrayList <Location> locations, ArrayList<City> cities) {
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of city to file
    public void writeCity(City city) {
    }

        // MODIFIES: this
    // EFFECTS: writes JSON representation of city to file
    public void writeLocation(Location location) {
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
    }
}
