package ca.uwo.csteam14;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 * The GUIForPOIs class contains GUI components built specifically for POI use.
 * It provides functionality to display different types of POIs and handle user interactions with them.
 * The class contains methods to create and update the POI display on the main interface.
 * It also contains a method to add padding to the JLabel object.
 *
 * @author Jason B. Shew
 * @version 1.0.0
 * @since 2023-03-07
 */

public class GUIForPOIs {
    // Define class variables
    /**
     * A secondary canvas used for displaying additional content.
     */
    protected static Canvas secondary;

    /**
     * A label used for displaying titles.
     */
    protected static JLabel title = new JLabel();

    /**
     * A string representing the group of POIs being displayed.
     */
    protected static String POIsGroup;

    /**
     * A string representing the type of POI being displayed.
     */
    protected static String POIType;

    /**
     * The MapView object used for displaying the map.
     */
    protected static MapView mapView;


    /**
     * Constructor to create an instance of GUIForPOIs.
     * It initializes the class variables and sets up the POI display.
     *
     * @param POIsGroup the group of POIs to be displayed
     */
    public GUIForPOIs(String POIsGroup)  {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
            POI poi = null;

            // Determine which POI group to display
            switch (POIsGroup) {
                case "BMK" -> {
                    GUIForPOIs.POIType = "Bookmarks";
                    GUIForPOIs.POIsGroup = POIsGroup;
                    poi = POISelector.focus;
                    if (poi == null && Data.bookmarks.size() > 0) {
                        poi = Data.bookmarks.getFirst();
                    }
                }
                case "BIP" -> {
                    GUIForPOIs.POIType = "Developer Tool";
                    GUIForPOIs.POIsGroup = POIsGroup;
                    poi = POISelector.focus;
                    if (poi == null && Data.builtInPOIs.size() > 0)
                        poi = Data.builtInPOIs.getFirst();
                }
                case "UDP" -> {
                    GUIForPOIs.POIType = "My Locations";
                    GUIForPOIs.POIsGroup = POIsGroup;
                    poi = POISelector.focus;
                    if (poi == null && Data.userCreatedPOIs.size() > 0)
                        poi = Data.userCreatedPOIs.getFirst();
                }
                case "SRC" -> {
                    GUIForPOIs.POIType = "Search & Discovery";
                    GUIForPOIs.POIsGroup = POIsGroup;
                    poi = Search.firstResult;
                }
            }

            // Update the current POI and set up the POI display
            if (poi != null) {
                Main.updateCurrent(poi);
            }
            try {
                if (poi == null && Data.builtInPOIs.size() > 0) poi = Data.builtInPOIs.getFirst();
                else if (poi == null) poi = Main.fallbackPOI;
                assert poi != null;
                Main.updateCurrent(poi);
                secondary = new Canvas("./images/" + Main.currentBuildingCode + "_hero.png");
                mapView = new MapView(Main.currentFloor+".png", new Point(poi.positionX, poi.positionY));
                title = new JLabel("<html><div style=\"text-align:center;\">" +
                        GUIForPOIs.POIType + "<br /></div></html>");
                // Set the font size and style
                title.setFont(new Font("Arial", Font.BOLD, 26));
                // Set the foreground color
                Color foregroundColour = new Color(75, 250, 0);
                Color background = new Color(0, 0, 0, 0.3f);
                padding(title);
                title.setForeground(foregroundColour);
                title.setOpaque(true);
                title.setBackground(background);
                secondary.load(title, 'L');
                GUI.frame.setContentPane(secondary);
                new POISelector(POIsGroup);
                GUIForPOIs.POIsGroup = POIsGroup;
                new Search();
                LayerFilter.showAllLayers();
                GUI.frame.pack();
                GUI.frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
                GUI.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * Method to set padding for a JLabel
     * @param label The JLabel to set padding for
     */
    public void padding(JLabel label) {
        label.setBorder(BorderFactory.createEmptyBorder(7, 50, 7, 50));
        // Set the preferred size of the JLabel to include the padding
        Dimension size = label.getPreferredSize();
        size.width += 10;
        size.height += 5;
        label.setPreferredSize(size);
    }
}