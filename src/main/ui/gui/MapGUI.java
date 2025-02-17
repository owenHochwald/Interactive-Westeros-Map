package ui.gui;

import javax.swing.*;

import model.City;
import model.Location;
import model.Map;

import java.awt.*;
import java.util.ArrayList;

/*
 * Represents the main page of the GUI, displaying the map and associated cities / locations
 */
public class MapGUI extends JFrame {

    private Map map;

    // Constructs main window
    // EFFECTS: sets up window for the map GUI with a scrollable image and 50px
    // padding below it
    public MapGUI() {
        super("Westeros Map Manager");
        init();
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        MenuBar menu = new MenuBar(map);
        setJMenuBar(menu);

        JPanel imageContainer = new JPanel();
        imageContainer.setLayout(new BoxLayout(imageContainer, BoxLayout.Y_AXIS));

        ImageIcon mapIcon = new ImageIcon("public/westeros_map.jpg");
        JLabel imageLabel = new JLabel(mapIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageContainer.add(imageLabel);

        imageContainer.add(Box.createRigidArea(new Dimension(0, 50)));

        JScrollPane scrollPane = new JScrollPane(imageContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.LINE_START);

        ImageIcon icon = new ImageIcon("public/crown_logo.jpg");
        setIconImage(icon.getImage());
        getContentPane().setBackground(new Color(0x555555));

        setVisible(true);
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
}
