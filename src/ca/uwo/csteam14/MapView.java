package ca.uwo.csteam14;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MapView extends JPanel {
    private BufferedImage image;
    private int imageWidth, imageHeight;

    private Point centerPixel;

    public MapView(String imagePath, Point centerPixel) {
        try {
            this.image = ImageIO.read(new File(imagePath));
            this.imageWidth = image.getWidth();
            this.imageHeight = image.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.centerPixel = centerPixel;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (image != null) {
            g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(imageWidth, imageHeight);
    }


    public JScrollPane loadMapViewer() {
        JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JViewport viewport = new JViewport();
        viewport.setView(this);
        viewport.setViewPosition(centerPixel);
        scrollPane.setViewport(viewport);
        return scrollPane;
    }


        public static void main(String[] args) {

            GUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MapView panel = new MapView("./maps/MC0F.png", new Point(2900, 900));
            panel.loadMapViewer();

            GUI.frame.getContentPane().add(panel.loadMapViewer());
            GUI.frame.pack();
            GUI.frame.setLocationRelativeTo(null);
            GUI.frame.setVisible(true);
        }
    }

