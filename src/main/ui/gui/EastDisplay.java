package ui.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

import model.City;
import model.Location;
import model.Map;
import model.Progress;

public class EastDisplay extends JPanel {
    private ArrayList<String> regions;
    private Map map;
    private JButton viewCitiesButton;
    private JButton viewLocationsButton;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JPanel citiesListPanel;
    private JPanel locationsListPanel;

    // EFFECTS: constructs a new panel that will display either cities or locations
    // and have the ability to display cities based on region
    public EastDisplay(Map map) {
        this.map = map;
        this.regions = new ArrayList<>();
        setSize(Integer.MAX_VALUE, 60);
        populateRegions();
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

        buttonPanel.add(viewCitiesButton);
        buttonPanel.add(viewLocationsButton);
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
        viewCitiesButton.addActionListener(new ViewCitiesActionListener());
        viewLocationsButton.addActionListener(new ViewLocationsActionListener());
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
    private void updateCitiesPanel(JPanel citiesListPanel) {
        citiesListPanel.removeAll();
        if (map.getCities().isEmpty()) {
            citiesListPanel.add(new JLabel("No cities available."));
        } else {
            addCitiesToPanel(citiesListPanel);
        }
        refreshPanel(citiesListPanel);
    }

    // REQUIRES: a valid JPanel
    // MODIFIES: citiesListPanel
    // EFFECTS: adds all cities to the panel
    private void addCitiesToPanel(JPanel citiesListPanel) {
        for (City city : map.getCities()) {
            JPanel cityPanel = createItemPanel(city);
            citiesListPanel.add(cityPanel);
        }
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
    private void updateLocationsPanel(JPanel locationsListPanel) {
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
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton removeButton = new JButton("Remove");
        JButton visitButton = new JButton(location.getVisited() ? "Unmark Visited" : "Mark Visited");

        removeButton.addActionListener(new RemoveLocationListener(panel, location));
        visitButton.addActionListener(new ToggleVisitedListener(visitButton, location, panel));

        buttonsPanel.add(visitButton);
        buttonsPanel.add(removeButton);
        panel.add(buttonsPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: adds all the map regions to regions
    private void populateRegions() {
        regions.add("Beyond the Wall");
        regions.add("The North");
        regions.add("The Vale");
        regions.add("The Riverlands");
        regions.add("The Crownlands");
        regions.add("The Westerlands");
        regions.add("The Reach");
        regions.add("The Stormlands");
        regions.add("Dorne");
    }

    // ActionListener implementations, seperated out instead of a long conditional
    // chain

    // EFFECTS: handles displaying all the cities
    private class ViewCitiesActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(contentPanel, "cities");
            updateCitiesPanel(citiesListPanel);
        }
    }

    // EFFECTS: handles viewing and displaying all the locations
    private class ViewLocationsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(contentPanel, "locations");
            updateLocationsPanel(locationsListPanel);
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

    // MODIFIES: this, Progress
    // EFFECTS: handles removing a location and updating Progress class
    private class RemoveLocationListener implements ActionListener {
        private Location location;

        public RemoveLocationListener(JPanel panel, Location location) {
            this.location = location;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (location instanceof City) {
                if (location.getVisited()) {
                    Progress.decreaseNumCitiesVisited();
                    Progress.decreasesNumVisitedEntries();
                }
                map.getCities().remove(location);
                Progress.decreasesNumEntries();
                Progress.setTotalNumCities(Progress.getTotalNumCities() - 1);
                updateCitiesPanel(citiesListPanel);
            } else {
                if (location.getVisited()) {
                    Progress.decreasesNumVisitedEntries();
                }
                Progress.decreasesNumEntries();
                map.getLocations().remove(location);
                updateLocationsPanel(locationsListPanel);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: handles toggling the visit status for an entry
    private class ToggleVisitedListener implements ActionListener {
        private JButton visitButton;
        private Location location;
        private JPanel itemPanel;

        public ToggleVisitedListener(JButton visitButton, Location location, JPanel itemPanel) {
            this.visitButton = visitButton;
            this.location = location;
            this.itemPanel = itemPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            location.toggleVisited();
            updateVisitedStatus();

            // Update the respective list panel based on location type
            if (location instanceof City) {
                updateCitiesPanel(citiesListPanel);
            } else {
                updateLocationsPanel(locationsListPanel);
            }
        }

        // MODIFIES: this
        // EFFECTS: updates the visited status in the item panel
        private void updateVisitedStatus() {
            visitButton.setText(location.getVisited() ? "Unmark Visited" : "Mark Visited");

            // Find and update the info panel
            for (Component comp : itemPanel.getComponents()) {
                if (comp instanceof JPanel && comp != visitButton.getParent()) {
                    JPanel infoPanel = (JPanel) comp;
                    updateInfoPanel(infoPanel);
                    break;
                }
            }
        }

        // MODIFIES: infoPanel
        // EFFECTS: updates the info panel with the visited status
        private void updateInfoPanel(JPanel infoPanel) {
            // Find the details panel inside the info panel
            for (Component comp : infoPanel.getComponents()) {
                if (comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof BorderLayout) {
                    JPanel borderPanel = (JPanel) comp;
                    updateDetailsPanel(borderPanel);
                    break;
                }
            }
        }

        // MODIFIES: borderPanel
        // EFFECTS: updates the details panel with the visited status
        private void updateDetailsPanel(JPanel borderPanel) {
            // Find the details panel (FlowLayout panel inside BorderLayout panel)
            for (Component comp : borderPanel.getComponents()) {
                if (comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof FlowLayout) {
                    JPanel detailsPanel = (JPanel) comp;
                    updateVisitedLabel(detailsPanel);
                    break;
                }
            }
        }

        // MODIFIES: detailsPanel
        // EFFECTS: updates or adds the visited label based on location status
        private void updateVisitedLabel(JPanel detailsPanel) {
            // Remove any existing visited labels
            Component[] components = detailsPanel.getComponents();
            for (Component comp : components) {
                if (comp instanceof JLabel && ((JLabel) comp).getText().contains("Visited")) {
                    detailsPanel.remove(comp);
                }
            }

            // Add visited label if location is visited
            if (location.getVisited()) {
                addVisitedLabel(detailsPanel);
            }

            detailsPanel.revalidate();
            detailsPanel.repaint();
        }
    }
}