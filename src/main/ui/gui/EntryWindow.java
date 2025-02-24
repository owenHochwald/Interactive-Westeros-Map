package ui.gui;

import javax.swing.*;

import model.City;
import model.Location;
import model.Map;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Represents a window to add a new city / location to the map
 */
public class EntryWindow extends JFrame implements ActionListener {
    private Map map;
    private JComboBox<String> entryTypeComboBox;
    private JComboBox<String> regionTypeComboBox;
    private JPanel formPanel;
    private JTextField nameField;
    private JTextField houseField;
    private JTextField populationField;
    private JButton submitButton;
    private MapGUI mainMapPanel;
    private MenuBar menu;

    // EFFECTS: constructs a new entry window to choose city / location and add
    // fields
    public EntryWindow(Map map, MapGUI mainMapPanel, MenuBar menu) {
        this.mainMapPanel = mainMapPanel;
        this.map = map;
        this.menu = menu;
        setTitle("Add an Entry");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLayout(new BorderLayout(10, 10));

        String[] entryTypes = { "City", "Location" };
        entryTypeComboBox = new JComboBox<>(entryTypes);
        entryTypeComboBox.addActionListener(this);
        add(entryTypeComboBox, BorderLayout.NORTH);

        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 10, 10));
        add(formPanel, BorderLayout.CENTER);

        updateForm("City");

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // EFFECTS: Updates the form based on the selected entry type (City or Location)
    private void updateForm(String entryType) {
        formPanel.removeAll();

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        String[] regionTypes = { "Beyond the Wall", "The North", "The Vale", "The Riverlands",
                "The Crownlands", "The Westerlands", "The Reach", "The Stormlands", "Dorne", "The Iron Islands" };

        // formPanel.add(new JLabel("Region:"));
        // regionField = new JTextField();
        // formPanel.add(regionField);

        regionTypeComboBox = new JComboBox<>(regionTypes);
        formPanel.add(new JLabel("Region:"));
        formPanel.add(regionTypeComboBox);

        if (entryType.equals("City")) {
            formPanel.add(new JLabel("House:"));
            houseField = new JTextField();
            formPanel.add(houseField);

            formPanel.add(new JLabel("Population (>=10):"));
            populationField = new JTextField();
            formPanel.add(populationField);
        }
        formPanel.revalidate();
        formPanel.repaint();
    }

    // EFFECTS: Handles actions for the combo box and submit button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == entryTypeComboBox) {
            String selectedEntryType = (String) entryTypeComboBox.getSelectedItem();
            updateForm(selectedEntryType);
        } else if (e.getSource() == submitButton) {
            handleSubmission();
        }
    }

    // MODIFES: this
    // EFFECTS: helper method to handle the submission in case of a city or a
    // location
    private void handleSubmission() {
        String name = nameField.getText();
        String region = (String) regionTypeComboBox.getSelectedItem();

        if ("City".equals(entryTypeComboBox.getSelectedItem())) {
            String house = houseField.getText();
            int population = Integer.parseInt(populationField.getText());
            handleCitySubmission(name, region, house, population);
        } else {
            handleLocationSubmission(name, region);
        }

        clearFields();
        dispose();
        menu.updateProgress();
    }

    // MODIFIES: this, Progress
    // EFFECTS: helper method to handle and create a city
    private void handleCitySubmission(String name, String region, String house, int population) {
        City city = new City(name, population, house, region, false, false);
        map.getCities().add(city);

        mainMapPanel.setCurrentCityName(name);
        mainMapPanel.setCityAddingMode(true);
    }

    // MODIFES: this, Progress
    // EFFECTS: helper mtehod to handle and create a location
    private void handleLocationSubmission(String name, String region) {
        Location location = new Location(name, region, false);
        map.getLocations().add(location);
    }

    // MODIFIES: this
    // EFFECTS: helper method to clear the form fields after submission
    private void clearFields() {
        nameField.setText("");

        if (houseField != null) {
            houseField.setText("");
            populationField.setText("");
        }
    }

}