/**
 * @author Jason
 * GUIForPOIs Class
 * GUI componenets built specifically for POI use
 */

package ca.uwo.csteam14;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class GUIForPOIs {
    protected static Canvas secondary;
    protected static JLabel title = new JLabel();

    protected static String POIsGroup;

    protected static String POIType;

    protected static MapView mapView;

    /**
     * @param POIsGroup
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

            switch (POIsGroup) {
                case "BMK" -> {
                    GUIForPOIs.POIType = "Bookmarks";
                    if (Data.bookmarks.size() > 0) {
                        poi = Data.bookmarks.getFirst();
                    }
                }
                case "BIP" -> {
                    GUIForPOIs.POIType = "Developer Tool";
                    if (Data.builtInPOIs.size() > 0)
                        poi = Data.builtInPOIs.getFirst();
                }
                case "UDP" -> {
                    GUIForPOIs.POIType = "My Locations";
                    if (Data.userCreatedPOIs.size() > 0)
                        poi = Data.userCreatedPOIs.getFirst();
                }
                case "SRC" -> {
                    GUIForPOIs.POIType = "Search & Discovery";
                    poi = Search.firstResult;
                }
            }

            if (poi != null) {
                Main.currentBuildingCode = poi.map.replaceAll("\\dF.png","");
                Main.currentFloor = poi.map.replaceAll(".png","");
            }
            try {
                if (poi == null && Data.builtInPOIs.size() > 0) poi = Data.builtInPOIs.getFirst();
                else if (poi == null) poi = Main.fallbackPOI;
                assert poi != null;
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * @param label
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