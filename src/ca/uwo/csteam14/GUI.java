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
                Splash startScreen = new Splash("./images/mc_hero.jpg");
                ImageIcon icon = new ImageIcon("./images/bb_icon.png");
                Image image = icon.getImage(); // transform it
                Image newimg = image.getScaledInstance(200, 200,  Image.SCALE_SMOOTH); // scale it the smooth way
                icon = new ImageIcon(newimg);  // transform it back
                JLabel logo = new JLabel();
                logo.setIcon(icon);
                startScreen.load(logo);
                String[] buildings = {"Middlesex College", "Kresge Building", "Physics & Astronomy"};

                JComboBox<? extends String> buildingSelector = new JComboBox<>(buildings);
                buildingSelector.setBounds(450, 300, 200, 30);
                startScreen.load(buildingSelector);

                JButton exploreButton = new JButton("Explore");
                startScreen.load(exploreButton);

                /* A split screen to show map and map layers */

                primary = new Container("./images/mc_hero.jpg");
                String[] POIList = {"🏊 Bookmarks", "🏫 Classrooms","🧪 Labs",
                        "🍴 Restaurants","🚽 Washrooms","💻 Accessibility", "\uD83D\uDCCD My Locations"};

                DataView myLocations = new DataView("Layer Selector", POIList);

                myLocations.setPreferredSize(new Dimension(500, 500));
                myLocations.setFont(new Font("Arial", Font.PLAIN, 36)); // Set font to a large size
                myLocations.setBounds(100, 100, 300, 50); // Set bounds to a larger size

                MapView map = new MapView("./maps/middlesex/MC-BF-1.png");
                map.setPreferredSize(new Dimension(500, 500));
                primary.load(myLocations.load(), 'L');
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