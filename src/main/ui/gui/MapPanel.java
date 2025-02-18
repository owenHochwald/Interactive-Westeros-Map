package ui.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * Class to represent the Westeros map with city markers drawn on it to represent the locations of cities
 */
public class MapPanel extends JPanel {

    private Image mapImage;
    private ArrayList<CityMarker> markers;

    // EFFECTS: constructs a new map panel with an icon and squares displayed on the page
    public MapPanel(ImageIcon mapIcon, ArrayList<CityMarker> markers) {
        this.mapImage = mapIcon.getImage();
        this.markers = markers;
        setPreferredSize(new Dimension(mapIcon.getIconWidth(), mapIcon.getIconHeight()));
    }

    // EFFECTS: overrides the default painCOmponent to display the squares representing cities
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mapImage, 0, 0, this);
        g.setColor(Color.RED);
        for (CityMarker marker : markers) {
            int radius = marker.getDiameter() / 2;
            g.fillOval(marker.getX() - radius, marker.getY() - radius, marker.getDiameter(), marker.getDiameter());
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(marker.getName());
            g.drawString(marker.getName(), marker.getX() - textWidth / 2, marker.getY() - radius - 5);
        }
    }
}
