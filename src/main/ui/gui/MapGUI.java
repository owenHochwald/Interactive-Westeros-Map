package ui.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MapGUI extends JFrame {

    // Constructs main window
    // EFFECTS: sets up window for the map GUI
    public MapGUI() {
        super("Westeros Map Manager");
		setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon icon = new ImageIcon("public/crown_logo.jpg");
        setIconImage(icon.getImage());

    }
}
