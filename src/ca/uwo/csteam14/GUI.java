package ca.uwo.csteam14;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class GUI {
    protected static JFrame frame = new JFrame("BuildingBuddy (Beta)");
    private final AppMenu appMenu = new AppMenu();
    protected static Container primary;

    public GUI() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }

            try {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setJMenuBar(appMenu.load());
                Splash startScreen = new Splash("./images/mc_hero.png");
                startScreen.build();

                /* A split screen to show map and layer filter */

                primary = new Container("./images/kb_hero.png");
                /* Shows building name */
                JLabel buildingName = new JLabel("Kresge Building (KB)");
                JLabel floorName = new JLabel("Ground Floor");
                // Set the font size and style
                Font font = new Font("Arial", Font.BOLD, 24);
                buildingName.setFont(font);
                floorName.setFont(font);
                // Set the foreground color
                Color color = new Color(255, 255, 0);
                buildingName.setForeground(color);
                floorName.setForeground(color);
                primary.load(buildingName,'L');
                primary.load(floorName,'L');

                String[] layerList = {"🏊 Bookmarks", "🏫 Classrooms","🧪 Labs",
                        "🍴 Restaurants","🚽 Washrooms","💻 Accessibility", "\uD83D\uDCCD My Locations"};
                LayerFilter filter = new LayerFilter("", layerList);
                primary.load(filter.load(), 'L');
                String[] floors = {"Ground Floor", "First Floor", "Second Floor", "Third Floor"};
                JComboBox<? extends String> floorSelector = new JComboBox<>(floors);
                floorSelector.setBounds(450, 300, 200, 30);
                primary.load(floorSelector, 'L');
                JButton goToButton = new JButton("Take Me There");
                primary.load(goToButton, 'L');
                MapView map = new MapView("./maps/KB0F.png", new Point(3200,350));
                primary.load(map.loadMapViewer(),'R');

                frame.setContentPane(primary);
                frame.pack();
                frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
                frame.setVisible(true);

            } catch (IOException exp) {
                exp.printStackTrace();
            }

        });
    }


}