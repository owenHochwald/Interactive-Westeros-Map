package ui.gui;

import javax.swing.*;

import model.City;
import model.EventLog;
import model.Location;
import model.Map;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * Represents the main page of the GUI, displaying the map and associated cities / locations
 */
public class MapGUI extends JFrame implements WindowListener {

    private Map map;
    private boolean isCityAddingMode;
    private ArrayList<CityMarker> markers;
    private MapPanel mapPanel;
    private String currentCityName;

    // EFFECTS: sets up window for the map GUI with a scrollable image and 50px
    // padding below it, and a list of squares with a toggle for the current mode.
    public MapGUI() {
        initFrame();
        MenuBar menu = new MenuBar(map, this);
        initMapComponents(menu);

        JScrollPane scrollPane = new JScrollPane(mapPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.LINE_START);

        EastDisplay eastDisplay = new EastDisplay(map, menu);
        add(eastDisplay, BorderLayout.EAST);

        ImageIcon icon = new ImageIcon("public/crown_logo.jpg");
        setIconImage(icon.getImage());
        getContentPane().setBackground(new Color(0x555555));

        setVisible(true);
        addWindowListener(this);
    }

    // MODIFES: this
    // EFFECTS: helper method to init the main frame of the applicaiton
    private void initFrame() {
        isCityAddingMode = false;
        markers = new ArrayList<>();
        init();
        setSize(1340, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // MODIFES": this
    // EFFECTS: helper method to init the map frame
    private void initMapComponents(MenuBar menu) {
        setJMenuBar(menu);
        ImageIcon mapIcon = new ImageIcon("public/westeros_map.jpg");
        mapPanel = new MapPanel(mapIcon, markers);
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isCityAddingMode) {
                    addCityMarker(e.getX(), e.getY());
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
    // EFFECTS: draws a city marker to represent the location of the city and adds
    // it to the markers list
    private void addCityMarker(int x, int y) {
        markers.add(new CityMarker(x, y, 20, currentCityName));
        mapPanel.repaint();
    }

    // EFFECTS: prints logs to console when main window is closing
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing window.");
        for (model.Event next : EventLog.getInstance()) {
            System.out.println(next.toString());
        }
    }

    // EFFECTS: plain implementation to satisify implements relationship
    public void windowIconified(WindowEvent e) {

    }

    // EFFECTS: plain implementation to satisify implements relationship
    public void windowActivated(WindowEvent e) {

    }

    // EFFECTS: plain implementation to satisify implements relationship
    public void windowOpened(WindowEvent e) {

    }

    // EFFECTS: plain implementation to satisify implements relationship
    public void windowDeiconified(WindowEvent e) {

    }

    // EFFECTS: plain implementation to satisify implements relationship
    public void windowDeactivated(WindowEvent e) {

    }

    // EFFECTS: plain implementation to satisify implements relationship
    public void windowClosed(WindowEvent e) {
    }

    // setters
    public void setCityAddingMode(boolean mode) {
        isCityAddingMode = mode;
    }

    public void setCurrentCityName(String name) {
        currentCityName = name;
    }
}
