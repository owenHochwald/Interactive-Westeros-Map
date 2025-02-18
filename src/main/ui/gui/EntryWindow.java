package ui.gui;

import javax.swing.*;

import model.City;
import model.Location;
import model.Map;
import model.Progress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * Represents a window to add a new city / location to the map
 */
public class EntryWindow extends JFrame implements ActionListener {
    private Map map;
    private ArrayList<String> regions;
    private JComboBox<String> entryTypeComboBox;
    private JPanel formPanel;
    private JTextField nameField;
    private JTextField houseField;
    private JTextField populationField;
    private JTextField regionField;
    private JButton submitButton;

    // EFFECTS: constructs a new entry window to choose city / location and add
    // fields
    public EntryWindow(Map map) {
        this.map = map;
        setTitle("Add an Entry");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLayout(new BorderLayout(10, 10));
        populateRegions();

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

        formPanel.add(new JLabel("Region:"));
        regionField = new JTextField();
        formPanel.add(regionField);

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

    // MODIFIES: this
    // EFFECTS: adds all the map regions to regions
    private void populateRegions() {
        regions = new ArrayList<>();
        regions.add("Beyond the Wall");
        regions.add("The North");
        regions.add("The Vale");
        regions.add("The Riverlands");
        regions.add("The Crownlands");
        regions.add("The Westerlands");
        regions.add("The Reach");
        regions.add("The Stormlands");
        regions.add("Dorne");
        regions.add("The Iron Islands");
    }

    // EFFECTS: Handles actions for the combo box and submit button
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == entryTypeComboBox) {
            String selectedEntryType = (String) entryTypeComboBox.getSelectedItem();
            updateForm(selectedEntryType);
        } else if (source == submitButton) {
            handleSubmission();
        }
    }

    // MODIFES: this
    // EFFECTS: helper method to handle the submission in case of a city or a location
    private void handleSubmission() {
        String name = nameField.getText();
        String region = regionField.getText();
    
        if ("City".equals(entryTypeComboBox.getSelectedItem())) {
            String house = houseField.getText();
            int population = Integer.parseInt(populationField.getText());
            handleCitySubmission(name, region, house, population);
        } else {
            handleLocationSubmission(name, region);
        }
    
        clearFields();
        JOptionPane.showMessageDialog(this, "Entry added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    // MODIFIES: this, Progress
    // EFFECTS: helper method to handle and create a city
    private void handleCitySubmission(String name, String region, String house, int population) {
        City city = new City(name, population, house, region, false, false);
        map.getCities().add(city);
        Progress.increaseNumEntries();
        Progress.increaseTotalCities();
    }

    // MODIFES: this, Progress
    // EFFECTS: helper mtehod to handle and create a location
    private void handleLocationSubmission(String name, String region) {
        Location location = new Location(name, region, false);
        map.getLocations().add(location);
        Progress.increaseNumEntries();
    }

    // MODIFIES: this
    // EFFECTS: helper method to clear the form fields after submission
    private void clearFields() {
        nameField.setText("");
        regionField.setText("");

        if (houseField != null) {
            houseField.setText("");
            populationField.setText("");
        }
    }
    
}