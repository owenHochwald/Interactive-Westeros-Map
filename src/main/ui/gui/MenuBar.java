package ui.gui;


import javax.swing.*;

import model.Progress;

import java.awt.*;

/*
 * Represents a menu bar that will be displayed at the top of the page.
 * Has the ability to display the two seperate progress bars (cities and entries) 
 * for the users progress in exploring, manage entries and view entries.
 */
public class MenuBar extends JMenuBar {

    private JLabel title;
    private JProgressBar progressBar;
    private JButton saveButton;
    private JButton loadButton;
    private JButton viewCitiesButton;
    private JButton viewLocationsButton;
    private JButton addEntryButton;

    // EFFECTS: creates a menu bar with title, progress, and actions
    public MenuBar() {
        setLayout(new BorderLayout());
        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        title = new JLabel("Westeros Map Manager");
        leftPanel.add(title);
        add(leftPanel, BorderLayout.WEST);

        // Create a panel for the right side (progress bar and buttons)
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);

        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(150, 20));
        progressBar.setStringPainted(true);
        rightPanel.add(progressBar);

        rightPanel.add(Box.createHorizontalStrut(20));

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        viewCitiesButton = new JButton("View All Cities");
        viewLocationsButton = new JButton("View All Locations");
        addEntryButton = new JButton("Add Entry");

        rightPanel.add(saveButton);
        rightPanel.add(loadButton);
        rightPanel.add(viewCitiesButton);
        rightPanel.add(viewLocationsButton);
        rightPanel.add(addEntryButton);

        add(rightPanel, BorderLayout.EAST);
        setVisible(true);
    }

    // MODIFES: this
    // EFFECTS: updates progress bars based on values from the Progress class
    public void updateProgress() {

    }


    // getters
    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getViewCitiesButton() {
        return viewCitiesButton;
    }

    public JButton getViewLocationsButton() {
        return viewLocationsButton;
    }

    public JButton getAddEntryButton() {
        return addEntryButton;
    }

    public JLabel getTitle() {
        return title;
    }

}
