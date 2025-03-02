package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Location;
import model.City;
import model.Progress;

/*
 * Represents an entry in the EastDisplay with a removeButton and (un)mark visited button for a single city / location
 */
public class EntryButtonPanel extends JPanel implements ActionListener {
    private final Location location;
    private final JButton removeButton;
    private final JButton visitButton;
    private final JButton increasePopulationButton;
    private final JPanel parentPanel;
    private final EastDisplay eastDisplay;
    private final MenuBar menu;


    // EFFECTS: constructs a new panel with the desired buttons
    public EntryButtonPanel(Location location, JPanel parentPanel, EastDisplay eastDisplay, MenuBar menu) {
        this.location = location;
        this.parentPanel = parentPanel;
        this.eastDisplay = eastDisplay;
        this.menu = menu;

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        removeButton = new JButton("Remove");
        visitButton = new JButton(location.getVisited() ? "Unmark Visited" : "Mark Visited");
        increasePopulationButton = new JButton("Increase Population");

        setupButtons();
        add(visitButton);
        add(removeButton);
        add(increasePopulationButton);
    }

    // MODIFIES: this
    // EFFECTS: adds listeners for the remove and visit buttons
    private void setupButtons() {
        removeButton.addActionListener(this);
        visitButton.addActionListener(this);
        increasePopulationButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: takes care of removing a city / city
    private void handleRemove() {
        if (location instanceof City) {
            handleCityRemoval();
        } else {
            handleLocationRemoval();
        }
        menu.updateProgress();
    }

    // MODIFIES: this
    // EFFETS: increases the population for a city
    private void handleIncreasePopulation() {
        ((City) location).increasePopulation();
        eastDisplay.updateCitiesPanel(eastDisplay.getCitiesListPanel());
    }

    // MODIFIES: this
    // EFFECTS: helper method that actually removes a city
    private void handleCityRemoval() {
        if (location.getVisited()) {
            Progress.decreaseNumCitiesVisited();
            Progress.decreasesNumVisitedEntries();
        }
        eastDisplay.getMap().getCities().remove(location);
        Progress.decreasesNumEntries();
        Progress.setTotalNumCities(Progress.getTotalNumCities() - 1);
        eastDisplay.updateCitiesPanel(eastDisplay.getCitiesListPanel());
    }

    // MODIFIES: this
    // EFFECTS: helper method that actually removes a location
    private void handleLocationRemoval() {
        if (location.getVisited()) {
            Progress.decreasesNumVisitedEntries();
        }
        Progress.decreasesNumEntries();
        eastDisplay.getMap().getLocations().remove(location);
        eastDisplay.updateLocationsPanel(eastDisplay.getLocationsListPanel());
    }

    // MODIFIES: this
    // EFFECTS: toggles the visit status of a location and updates UI elements
    // accordingly
    private void handleVisit() {
        location.toggleVisited();
        visitButton.setText(location.getVisited() ? "Unmark Visited" : "Mark Visited");
        updateVisitedStatus(parentPanel);

        if (location instanceof City) {
            eastDisplay.updateCitiesPanel(eastDisplay.getCitiesListPanel());
        } else {
            eastDisplay.updateLocationsPanel(eastDisplay.getLocationsListPanel());
        }

        menu.updateProgress();
    }

    // MODIFIES: this
    // EFFECTS: recursively updates the visit label in relevant UI components
    private void updateVisitedStatus(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;

                updateVisitedStatus(panel);
            }
        }
    }

    // MODIFIES: this
    // EFFETS: sets behavior and action events for remove and visit buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            handleRemove();
        } else if (e.getSource() == visitButton) {
            handleVisit();
        } else if (e.getSource() == increasePopulationButton) {
            handleIncreasePopulation();
        }
    }
}