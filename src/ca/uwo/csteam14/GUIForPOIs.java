package ca.uwo.csteam14;
import org.w3c.dom.html.HTMLImageElement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.*;

public class GUIForPOIs {
   
    protected AppMenu appMenu = new AppMenu("user");
    protected static Container secondary;
    protected static JLabel title = new JLabel();

    protected static MapView map;

    public GUIForPOIs(LinkedList<POI> collection, String listTitle) {
        EventQueue.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }

            try {
                GUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GUI.frame.setJMenuBar(appMenu.load());
                /* A split screen to show map and layer filter on the left side*/
                /* Show the correct background picture and building name */

                secondary = new Container("./images/"+BuildingBuddy.currentBuildingCode+"_hero.png");
                title.setText(listTitle);
                map = new MapView(BuildingBuddy.currentFloor+".png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                secondary.load(map.loadMapViewer(), 'R');

                } catch (IOException e) {
                throw new RuntimeException(e);
            }

            title = new JLabel("<html><div style=\"text-align:center;\">" + "" +
                    listTitle + "<br /></div></html>");
                // Set the font size and style
            Font title = new Font("Arial", Font.BOLD, 26);
            GUIForPOIs.title.setFont(title);

            // Set the foreground color
            Color foregroundColour = new Color(75, 250 ,0);
            Color background = new Color(0,0,0, 0.3f);
            padding(GUIForPOIs.title);
            GUIForPOIs.title.setForeground(foregroundColour);
            GUIForPOIs.title.setOpaque(true);
            GUIForPOIs.title.setBackground(background);
            secondary.load(GUIForPOIs.title,'L');

            new POISelector(collection);

            new Search();

            GUI.frame.setContentPane(secondary);
            GUI.frame.pack();
            GUI.frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
            GUI.frame.setVisible(true);

        });
    }

    public static String getBuildingName(String currentBuilding) {
        switch (currentBuilding) {
            case ("MC") -> {
                return "Middlesex College";
            }
            case ("KB") -> {
                return "Kresge Building";
            }
            case ("PAB") -> {
                return "Physics & Astronomy Building";
            }
        }
        return "";
    }

    public void padding(JLabel label) {
        label.setBorder(BorderFactory.createEmptyBorder(7, 50, 7, 50));
        // Set the preferred size of the JLabel to include the padding
        Dimension size = label.getPreferredSize();
        size.width += 10;
        size.height += 5;
        label.setPreferredSize(size);
    }

    public static MapView getMap() {
        return map;
    }
    public static BufferedImage getBufferedMap() {
        return map.getMapImage();
    }

}