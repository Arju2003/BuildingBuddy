import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI {
    JFrame frame = new JFrame("Building Buddy (Beta)");
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
                Container primary = new Container();
                primary.setBackground(ImageIO.read(new File("./images/mc_hero.jpg")));

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(primary);
                frame.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.insets = new Insets(0, 0, 20 , 0);

                ImageIcon icon = new ImageIcon("./images/bb_icon.png");
                Image image = icon.getImage(); // transform it
                Image newimg = image.getScaledInstance(200, 200,  Image.SCALE_SMOOTH); // scale it the smooth way
                icon = new ImageIcon(newimg);  // transform it back
                JLabel logo = new JLabel();
                logo.setIcon(icon);
                frame.add(logo, gbc);

                String[] buildings = {"Middlesex College", "Kresge Building", "Physics & Astronomy"};

                JComboBox<? extends String> buildingSelector = new JComboBox<>(buildings);
                buildingSelector.setBounds(450, 300, 200, 30);
                frame.add(buildingSelector, gbc);


                JButton exploreButton = new JButton("Explore");
                frame.add(exploreButton, gbc);

                frame.pack();
                frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
                frame.setVisible(true);


            } catch (IOException exp) {
                exp.printStackTrace();
            }
        });
    }
}