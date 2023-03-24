package ca.uwo.csteam14;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public class GUI {
    protected static JFrame frame = new JFrame("BuildingBuddy – Ver 1.0 –");
    private final AppMenu appMenu = new AppMenu("user");
    protected static Container primary;
    protected static JLabel buildingName = new JLabel();

    protected static MapView map;

    public GUI(String buildingCode) {
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
                switch (buildingCode) {
                    case "KB" -> {
                        primary = new Container("./images/KB_hero.png");
                        buildingName.setText("Kresge Building (KB)");
                        map = new MapView(BuildingBuddy.currentFloor_KB+".png", BuildingBuddy.getOptimumPoint(buildingCode));
                        primary.load(map.loadMapViewer(), 'R');

                    }
                    case "MC" -> {
                        primary = new Container("./images/MC_hero.png");
                        buildingName.setText("Middlesex College (MC)");
                        map = new MapView(BuildingBuddy.currentFloor_MC+".png", BuildingBuddy.getOptimumPoint(buildingCode));
                        primary.load(map.loadMapViewer(), 'R');
                    }
                    case "PAB" -> {
                        primary = new Container("./images/PAB_hero.png");
                        buildingName.setText("Physics & Astronomy Building (PAB)");
                        map = new MapView(BuildingBuddy.currentFloor_PAB+".png", BuildingBuddy.getOptimumPoint(buildingCode));
                        primary.load(map.loadMapViewer(), 'R');

                    }
                }

                buildingName = new JLabel("<html><div style=\"text-align:center;\">" +getBuildingName(buildingCode) + "<br />(" + buildingCode + ") ►</div></html>");
                // Set the font size and style
                Font title = new Font("Arial", Font.BOLD, 26);
                buildingName.setFont(title);

                // Set the foreground color
                Color foregroundColour = new Color(75, 250 ,0);
                Color background = new Color(0,0,0, 0.3f);
                padding(buildingName);
                buildingName.setForeground(foregroundColour);
                buildingName.setOpaque(true);
                buildingName.setBackground(background);
                primary.load(buildingName,'L');

                new FloorSelector();

                new LayerFilter();

                new Search();

                frame.setContentPane(primary);
                frame.pack();
                frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
                frame.setVisible(true);

            } catch (IOException exp) {
                exp.printStackTrace();
            }

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