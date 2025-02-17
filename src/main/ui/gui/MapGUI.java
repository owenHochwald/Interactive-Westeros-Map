package ui.gui;

import javax.swing.*;
import java.awt.*;


/*
 * Represents the main page of the GUI, displaying the map and associated cities / locations
 */
public class MapGUI extends JFrame {

    // Constructs main window
    // EFFECTS: sets up window for the map GUI with a scrollable image and 50px padding below it
    public MapGUI() {
        super("Westeros Map Manager");
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        MenuBar menu = new MenuBar();
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
}
