package ui.gui;

import javax.swing.*;

import model.Map;
import model.Progress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
 * Represents a menu bar that will be displayed at the top of the page.
 * Has the ability to display progress toward visiting all the entries, 
 * buttons for managing and viewing entries.
 */
public class MenuBar extends JMenuBar implements ActionListener {

    private JLabel title;
    private JProgressBar progressBar;
    private JButton updateProgressBar;
    private JButton saveButton;
    private JButton loadButton;
    private JButton addEntryButton;
    private Map map;

    // EFFECTS: creates a menu bar with title, progress, and actions
    public MenuBar(Map map) {
        this.map = map;
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        title = new JLabel("Westeros Map Manager");
        leftPanel.add(title);
        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        initRightPanel(rightPanel);
    }

    // MODIFES: this
    // inits the right panel of the menu bar
    public void initRightPanel(JPanel rightPanel) {
        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(600, 30));
        progressBar.setStringPainted(true);
        updateProgress();
        rightPanel.add(progressBar);

        JPanel fillerSpace = new JPanel();
        fillerSpace.setPreferredSize(new Dimension(120, 30));
        rightPanel.add(fillerSpace);

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        updateProgressBar = new JButton("Update Progress");
        loadButton.addActionListener(this);
        updateProgressBar.addActionListener(this);
        addEntryButton = new JButton("Add Entry");
        rightPanel.add(updateProgressBar);
        rightPanel.add(saveButton);
        rightPanel.add(loadButton);
        rightPanel.add(addEntryButton);

        add(rightPanel, BorderLayout.EAST);
    }

    // MODIFES: this
    // EFFECTS: updates progress bars based on values from the Progress class
    public void updateProgress() {
        int numEntries = Progress.getTotalNumEntries();
        int numVisitedEntries = Progress.getTotalNumVisitedEntries();
        int percentage = (int) ((numVisitedEntries * 100.0) / numEntries);
        progressBar.setValue(percentage);
        progressBar.setString("Places: " + numVisitedEntries + "/" + numEntries);
    }

    // MODIFIES: this, Progress
    // EFFECTS: implements handling when buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            try {
                map.loadMap("./data/mapState.json");
                updateProgress();
                System.out.println(map.getCities());
            } catch (IOException error) {
                System.out.println("Destination doesn't exist: " + error);
            }
        } else if (e.getSource() == saveButton) {
            try {
                map.saveMap("./data/mapState.json");
                updateProgress();
                System.out.println(map.getCities());
            } catch (IOException error) {
                System.out.println("Destination doesn't exist: " + error);
            }
        } else if (e.getSource() == updateProgressBar) {
            updateProgress();
        }
    }

    // getters
    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getAddEntryButton() {
        return addEntryButton;
    }

    public JLabel getTitle() {
        return title;
    }
}
