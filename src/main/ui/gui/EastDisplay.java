package ui.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.City;
import model.Location;
import model.Map;

/*
 * A class to represent the right side panel of the GUI with actions to view / filter cities and locations
 */
public class EastDisplay extends JPanel implements ActionListener {
    private Map map;
    private MenuBar menu;
    private JButton viewCitiesButton;
    private JButton viewLocationsButton;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JPanel citiesListPanel;
    private JPanel locationsListPanel;
    private JComboBox<String> regionFilter;

    // EFFECTS: constructs a new panel that will display either cities or locations
    // and have the ability to display cities based on region
    public EastDisplay(Map map, MenuBar menu) {
        this.map = map;
        this.menu = menu;
        setSize(Integer.MAX_VALUE, 60);
        setLayout(new BorderLayout());

        initializeButtonPanel();
        initializeContentPanel();
        setupActionListeners();
    }

    // MODIFIES: this
    // EFFECTS: initializes the button panel with view cities and view locations
    // buttons
    private void initializeButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        viewCitiesButton = new JButton("View Cities");
        viewLocationsButton = new JButton("View Locations");

        String[] regions = { "Any", "Beyond the Wall", "The North", "The Vale", "The Riverlands",
                "The Crownlands", "The Westerlands", "The Reach", "The Stormlands", "Dorne", "The Iron Islands" };
        regionFilter = new JComboBox<>(regions);

        buttonPanel.add(viewCitiesButton);
        buttonPanel.add(viewLocationsButton);
        buttonPanel.add(regionFilter);
        add(buttonPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes the content panel with empty, cities, and locations
    // panels
    private void initializeContentPanel() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(createEmptyPanel("Select an option..."), "empty");
        contentPanel.add(createCitiesPanel(), "cities");
        contentPanel.add(createLocationsPanel(), "locations");
        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "empty");
    }

    // MODIFIES: this
    // EFFECTS: sets up action listeners for view cities and view locations buttons
    private void setupActionListeners() {
        viewCitiesButton.addActionListener(this);
        viewLocationsButton.addActionListener(this);
        regionFilter.addActionListener(this);
    }

    // REQUIRES: a non-empty / valid message
    // EFFECTS: creates a panel that displays a message when there are no
    // cities or locations being displayed
    private JPanel createEmptyPanel(String message) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    // MODIFES: this
    // EFFECTS: returns a new panel populated with cities
    private JPanel createCitiesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        citiesListPanel = new JPanel();
        citiesListPanel.setLayout(new BoxLayout(citiesListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(citiesListPanel);
        scrollPane.setPreferredSize(new Dimension(600, 50));
        panel.add(scrollPane);

        viewCitiesButton.addActionListener(new UpdateCitiesPanelListener(citiesListPanel));

        return panel;
    }

    // REQUIRES: a valid JPanel
    // MODIFIES: citiesListPanel
    // EFFECTS: updates the panel with new cities, creating a panel for each or
    // displays a message that none are available
    public void updateCitiesPanel(JPanel citiesListPanel) {
        citiesListPanel.removeAll();
        String selectedRegion = (String) regionFilter.getSelectedItem();

        if (map.getCities().isEmpty()) {
            citiesListPanel.add(new JLabel("No cities available."));
        } else {
            addFilteredCitiesToPanel(citiesListPanel, selectedRegion);
        }
        refreshPanel(citiesListPanel);
    }

    // MODIFIES: this
    // EFFECTS: returns a new panel for displaying locations
    private JPanel createLocationsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        locationsListPanel = new JPanel();
        locationsListPanel.setLayout(new BoxLayout(locationsListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(locationsListPanel);
        scrollPane.setSize(400, 300);
        panel.add(scrollPane);

        viewLocationsButton.addActionListener(new UpdateLocationsPanelListener(locationsListPanel));

        return panel;
    }

    // REQUIRES: a valid JPanel
    // MODIFIES: locationsListPanel
    // EFFECTS: updates the panel with new locations, creating a panel for each or
    // displays a message
    public void updateLocationsPanel(JPanel locationsListPanel) {
        locationsListPanel.removeAll();

        if (map.getLocations().isEmpty()) {
            locationsListPanel.add(new JLabel("No locations available."));
        } else {
            addLocationsToPanel(locationsListPanel);
        }

        refreshPanel(locationsListPanel);
    }

    // REQUIRES: a valid JPanel
    // MODIFIES: locationsListPanel
    // EFFECTS: adds non-city locations to the panel
    private void addLocationsToPanel(JPanel locationsListPanel) {
        for (Location location : map.getLocations()) {
            if (!(location instanceof City)) {
                JPanel locationPanel = createItemPanel(location);
                locationsListPanel.add(locationPanel);
            }
        }
    }

    // REQUIRES: a valid JPanel and region string
    // MODIFIES: citiesListPanel
    // EFFECTS: adds cities filtered by region to the panel
    private void addFilteredCitiesToPanel(JPanel citiesListPanel, String selectedRegion) {
        for (City city : map.getCities()) {
            if (selectedRegion.equals("Any") || city.getRegion().equals(selectedRegion)) {
                JPanel cityPanel = createItemPanel(city);
                citiesListPanel.add(cityPanel);
            }
        }
    }

    // REQUIRES: a valid JPanel
    // MODIFIES: panel
    // EFFECTS: refreshes the panel by calling revalidate and repaint
    private void refreshPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    // REQUIRES: a valid location
    // EFFECTS: returns a newly created panel for each entry in a list of Locations
    // / Cities has a toggle visited button and remove button.
    private JPanel createItemPanel(Location location) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setSize(Integer.MAX_VALUE, 60);

        createInfoPanel(panel, location);
        createButtonsPanel(panel, location);

        return panel;
    }

    // REQUIRES: a valid Location and JPanel
    // MODIFIES: panel, this
    // EFFECTS: creates the info panel with location details
    private void createInfoPanel(JPanel panel, Location location) {
        JLabel infoLabel = new JLabel(location.toString());
        panel.add(infoLabel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel(location.getName());
        nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.BOLD, 14));

        JPanel infoPanel = new JPanel(new BorderLayout(5, 3));
        infoPanel.setOpaque(false);

        JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        detailsPanel.setOpaque(false);

        addLocationDetails(location, detailsPanel);

        infoPanel.add(nameLabel, BorderLayout.NORTH);
        infoPanel.add(detailsPanel, BorderLayout.CENTER);

        panel.add(infoPanel, BorderLayout.CENTER);
    }

    // REQUIRES: a valid Location, detailsPanel
    // MODIFIES: detailsPanel
    // EFFECTS: adds specific details to the panel based on location type
    private void addLocationDetails(Location location, JPanel detailsPanel) {
        if (location instanceof City) {
            City city = (City) location;
            JLabel houseLabel = new JLabel(" | " + city.getHouse());
            houseLabel.setFont(new Font(houseLabel.getFont().getName(), Font.PLAIN, 12));
            detailsPanel.add(houseLabel);
        }
        if (location.getVisited()) {
            addVisitedLabel(detailsPanel);
        }
    }

    // REQUIRES: detailsPanel not null
    // MODIFIES: detailsPanel
    // EFFECTS: adds a visited label with green check mark to the details panel
    private void addVisitedLabel(JPanel detailsPanel) {
        JLabel visitedLabel = new JLabel(" ✓ Visited");
        visitedLabel.setForeground(new Color(0, 128, 0));
        visitedLabel.setFont(new Font(visitedLabel.getFont().getName(), Font.BOLD, 12));
        detailsPanel.add(visitedLabel);
    }

    // REQUIRES: detailsPanel not null
    // MODIFIES: panel
    // EFFECTS: creates the buttons panel with remove and visit buttons
    private void createButtonsPanel(JPanel panel, Location location) {
        EntryButtonPanel buttonsPanel = new EntryButtonPanel(location, panel, this, menu);
        panel.add(buttonsPanel, BorderLayout.EAST);
    }

    // handlers for components

    // MODIFIES: this
    // EFFECTS: hanldes displaying all cities from map to the panel
    public void handleViewCities() {
        cardLayout.show(contentPanel, "cities");
        updateCitiesPanel(citiesListPanel);
    }

    // MODIFIES: this
    // EFFECTS: hanldes displaying all locations from map to the panel
    public void handleViewLocations() {
        cardLayout.show(contentPanel, "locations");
        updateCitiesPanel(locationsListPanel);
    }

    // MODIFIES: this
    // EFFECTS: increases a cities population by 1000
    public void handleIncreasePopulation() {
        
    }


    // EFFECTS: Handles actions for the buttons / combo boxes for the EastDisplay
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewCitiesButton) {
            handleViewCities();
        } else if (e.getSource() == viewLocationsButton) {
            handleViewLocations();
        } else if (e.getSource() == regionFilter) {
            updateCitiesPanel(citiesListPanel);
        }
    }

    // EFFECTS updates cities panel when info is changed
    private class UpdateCitiesPanelListener implements ActionListener {
        private JPanel citiesListPanel;

        public UpdateCitiesPanelListener(JPanel citiesListPanel) {
            this.citiesListPanel = citiesListPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            updateCitiesPanel(citiesListPanel);
        }
    }

    // EFFECTS updates locations panel when info is changed
    private class UpdateLocationsPanelListener implements ActionListener {
        private JPanel locationsListPanel;

        public UpdateLocationsPanelListener(JPanel locationsListPanel) {
            this.locationsListPanel = locationsListPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            updateLocationsPanel(locationsListPanel);
        }
    }

    // getters
    public JPanel getCitiesListPanel() {
        return citiesListPanel;
    }

    public JPanel getLocationsListPanel() {
        return locationsListPanel;
    }

    public Map getMap() {
        return map;
    }
}