package ca.uwo.csteam14;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public class GUIForPOIs {
    protected static JFrame frame = new JFrame("BuildingBuddy *POI Editors* – Ver 1.0 –");
    private final AppMenu appMenu = new AppMenu();
    protected static Container secondary;
    protected static JLabel buildingName = new JLabel();

    protected static MapView map;

    public GUIForPOIs(Data collection) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }

            try {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setJMenuBar(appMenu.load());
                /* A split screen to show map and layer filter on the left side*/
                /* Show the correct background picture and building name */

                secondary = new Container("./images/KB_hero.png");
                buildingName.setText("Kresge Building (KB)");
                map = new MapView("./maps/"+BuildingBuddy.currentFloor_KB+".png", BuildingBuddy.getOptimumPoint("KB"));
                secondary.load(map.loadMapViewer(), 'R');

                } catch (IOException e) {
                throw new RuntimeException(e);
            }

            buildingName = new JLabel("<html><div style=\"text-align:center;\">" + "" +
                    "POI Editor" + "<br />(" + "MC" + ") ►</div></html>");
                // Set the font size and style
            Font title = new Font("Arial", Font.BOLD, 36);
            buildingName.setFont(title);

            // Set the foreground color
            Color foregroundColour = new Color(75, 250 ,0);
            Color background = new Color(0,0,0, 0.3f);
            padding(buildingName);
            buildingName.setForeground(foregroundColour);
            buildingName.setOpaque(true);
            buildingName.setBackground(background);
            secondary.load(buildingName,'L');

            new POISelector(collection);

            new Search();

            frame.setContentPane(secondary);
            frame.pack();
            frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
            frame.setVisible(true);

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
        label.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
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