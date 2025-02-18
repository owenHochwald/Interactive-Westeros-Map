package ui.gui;

import javax.swing.*;

import model.City;
import model.Location;
import model.Map;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

/*
 * Represents the main page of the GUI, displaying the map and associated cities / locations
 */
public class MapGUI extends JFrame {

    private Map map;
    private boolean isCityAddingMode;
    private ArrayList<Rectangle> citySquares;
    private MapPanel mapPanel;

    // EFFECTS: sets up window for the map GUI with a scrollable image and 50px
    // padding below it, and a list of squares with a toggle for the current mode.
    public MapGUI() {
        initFrame();

        initMapComponents();

        JScrollPane scrollPane = new JScrollPane(mapPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.LINE_START);

        EastDisplay eastDisplay = new EastDisplay(map);
        add(eastDisplay, BorderLayout.EAST);

        ImageIcon icon = new ImageIcon("public/crown_logo.jpg");
        setIconImage(icon.getImage());
        getContentPane().setBackground(new Color(0x555555));

        setVisible(true);

    }

    // MODIFES: this
    // EFFECTS: helper method to init the main frame of the applicaiton
    private void initFrame() {
        isCityAddingMode = false;
        citySquares = new ArrayList<>();
        init();
        setSize(1340, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // MODIFES": this
    // EFFECTS: helper method to init the map frame
    private void initMapComponents() {
        setJMenuBar(new MenuBar(map, this));
        ImageIcon mapIcon = new ImageIcon("public/westeros_map.jpg");
        mapPanel = new MapPanel(mapIcon, citySquares);
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isCityAddingMode) {
                    addCitySquare(e.getX(), e.getY());
                    setCityAddingMode(false);
                }
            }
        });
    }

    // MODIFES: this
    // EFFECTS: inits preset locations to the map
    private void init() {
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<Location> locations = new ArrayList<>();
        initCities(cities);
        initLocations(locations);
        this.map = new Map(locations, cities);
    }

    // MODIFIES: this
    // EFFECTS: initializes preset cities
    private void initCities(ArrayList<City> cities) {
        cities.add(new City("King's Landing", 500000, "Baratheon", "Crownlands", true, false));
        cities.add(new City("Winterfell", 100000, "Stark", "The North", true, false));
        cities.add(new City("Lannisport", 50000, "Lannister", "The Westerlands", false, false));
        cities.add(new City("Oldtown", 200000, "Hightower", "The Reach", false, false));
        cities.add(new City("White Harbor", 100000, "Manderly", "The North", false, false));
        cities.add(new City("Sunspear", 50000, "Martell", "Dorne", true, false));
        cities.add(new City("Riverrun", 40000, "Tully", "The Riverlands", false, false));
    }

    // MODIFIES: this
    // EFFECTS: initializes preset locations
    private void initLocations(ArrayList<Location> locations) {
        locations.add(new Location("Gods Eye", "The Riverlands", false));
        locations.add(new Location("The Wall", "The North", false));
        locations.add(new Location("Wolf's Wood", "The North", false));
    }

    // REQUIRES: x and y are within the range of the frame
    // MODIFES: this
    // EFFECTS: draws a square to represent the location of the city and adds it to
    // the citySquares list
    private void addCitySquare(int x, int y) {
        int size = 20;
        Rectangle square = new Rectangle(x - size / 2, y - size / 2, size, size);
        citySquares.add(square);
        mapPanel.repaint();
    }

    // setters
    public void setCityAddingMode(boolean mode) {
        isCityAddingMode = mode;
    }
}
