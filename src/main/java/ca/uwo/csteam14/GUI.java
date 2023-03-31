/**
 * @author Jason, Daniel, Arjuna, Bobby, Josh
 * GUI Class
 * Construct the GUI for the building buddy application
 */

package ca.uwo.csteam14;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class GUI {
    protected static JFrame frame = new JFrame("BuildingBuddy – Ver 1.0 –");
    protected static Canvas canvas;

    protected static MapView mapView;


    /**
     * @param buildingCode
     */
    public GUI(String buildingCode) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }

            try {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                AppMenu appMenu = new AppMenu();
                frame.setJMenuBar(appMenu.load());
                JLabel buildingName = new JLabel();
                switch (buildingCode) {
                    case "KB" -> {
                        canvas = new Canvas("./images/KB_hero.png");
                        buildingName.setText("Kresge Building (KB)");
                        mapView = new MapView(Main.currentFloor_KB+".png", Main.getOptimumPoint(buildingCode));
                    }
                    case "MC" -> {
                        canvas = new Canvas("./images/MC_hero.png");
                        buildingName.setText("Middlesex College (MC)");
                        mapView = new MapView(Main.currentFloor_MC+".png", Main.getOptimumPoint(buildingCode));
                    }
                    case "PAB" -> {
                        canvas = new Canvas("./images/PAB_hero.png");
                        buildingName.setText("Physics & Astronomy Building (PAB)");
                        mapView = new MapView(Main.currentFloor_PAB+".png", Main.getOptimumPoint(buildingCode));
                    }
                }
                frame.setContentPane(canvas);
                buildingName = new JLabel("<html><div style=\"text-align:center;\">" + Main.getBuildingFullName(buildingCode) + "<br />(" + buildingCode + ") ►</div></html>");
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
                canvas.load(buildingName,'L');

                new FloorSelector();
                new LayerFilter(); // this invokes the MapView object
                new Search();
                frame.pack();
                frame.setResizable(false);
                frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
                frame.setVisible(true);

            } catch (IOException exp) {
                exp.printStackTrace();
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