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
    private JButton saveButton;
    private JButton loadButton;
    private JButton addEntryButton;
    private Map map;
    private MapGUI mainMapPanel;


    // EFFECTS: creates a menu bar with title, progress, and actions
    public MenuBar(Map map, MapGUI mainMapPanel) {
        this.mainMapPanel = mainMapPanel;

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
        addEntryButton = new JButton("Add Entry");

        saveButton.setFocusable(false);

        loadButton.addActionListener(this);
        saveButton.addActionListener(this);
        addEntryButton.addActionListener(this);

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

    // MODIFIES: Progress
    // EFFECTS: helper resets the progress in visiting all entries
    public void reset() {
        Progress.resetCityProgress();
        Progress.resetEntryProgress();
        Progress.resetTotalCities();
        Progress.resetTotalEntry();
    }

    // MODIFIES: this, Progress
    // EFFECTS: implements handling when buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            try {
                reset();
                map.loadMap("./data/mapState.json");
                updateProgress();
            } catch (IOException error) {
                System.out.println("Destination doesn't exist: " + error);
            }
        } else if (e.getSource() == saveButton) {
            try {
                map.saveMap("./data/mapState.json");
                updateProgress();
            } catch (IOException error) {
                System.out.println("Destination doesn't exist: " + error);
            }
        } else if (e.getSource() == addEntryButton) {
            new EntryWindow(map, mainMapPanel);
        }

    }
}
