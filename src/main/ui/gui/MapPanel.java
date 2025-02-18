package ui.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * Class to represent the Westeros map with squares drawn on it to represent cities
 */
public class MapPanel extends JPanel {

    private Image mapImage;
    private ArrayList<Rectangle> squares;

    // EFFECTS: constructs a new map panel with an icon and squares displayed on the page
    public MapPanel(ImageIcon mapIcon, ArrayList<Rectangle> squares) {
        this.mapImage = mapIcon.getImage();
        this.squares = squares;
        setPreferredSize(new Dimension(mapIcon.getIconWidth(), mapIcon.getIconHeight()));
    }

    // EFFECTS: overrides the default painCOmponent to display the squares representing cities
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the map image.
        g.drawImage(mapImage, 0, 0, this);
        // Draw city squares.
        g.setColor(Color.RED);
        for (Rectangle square : squares) {
            g.fillRect(square.x, square.y, square.width, square.height);
        }
    }
}
