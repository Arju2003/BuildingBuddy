import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI {
    protected static JFrame frame = new JFrame("BuildingBuddy (Beta)");
    private final AppMenu appMenu = new AppMenu();

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
                Container primary = new Container("./images/mc_hero.jpg");
                ImageIcon icon = new ImageIcon("./images/bb_icon.png");
                Image image = icon.getImage(); // transform it
                Image newimg = image.getScaledInstance(200, 200,  Image.SCALE_SMOOTH); // scale it the smooth way
                icon = new ImageIcon(newimg);  // transform it back
                JLabel logo = new JLabel();
                logo.setIcon(icon);
                primary.load(logo);
                String[] buildings = {"Middlesex College", "Kresge Building", "Physics & Astronomy"};

                JComboBox<? extends String> buildingSelector = new JComboBox<>(buildings);
                buildingSelector.setBounds(450, 300, 200, 30);
                primary.load(buildingSelector);

                JButton exploreButton = new JButton("Explore");
                primary.load(exploreButton);

                frame.pack();
                frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
                frame.setVisible(true);

            } catch (IOException exp) {
                exp.printStackTrace();
            }


        });

    }
}