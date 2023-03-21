package ca.uwo.csteam14;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapView extends JPanel {
    private BufferedImage mapImage;
    private int imageWidth, imageHeight;
    private final Point focalPoint;



    public MapView(String mapName, Point focalPoint) {

        try {
            mapImage = ImageIO.read(new File(mapName));
            imageWidth = mapImage.getWidth();
            imageHeight = mapImage.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.focalPoint = focalPoint;
    }

    public MapView(BufferedImage bufferedMap, Point focalPoint) {
        mapImage = bufferedMap;
        try {
            imageWidth = mapImage.getWidth();
            imageHeight = mapImage.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.focalPoint = focalPoint;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (mapImage != null) {
            g2d.drawImage(mapImage, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(imageWidth, imageHeight);
    }


    public JScrollPane loadMapViewer() {
        JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JViewport viewport = new JViewport();
        viewport.setView(this);
        // Get the size of the viewport
        Dimension viewportSize = viewport.getViewSize();
        // Calculate the position to display in the center of the viewport
        int x = focalPoint.x - viewportSize.width / 8;
        int y = focalPoint.y - viewportSize.height / 4;
        viewport.setViewPosition(new Point(x, y));
        scrollPane.setViewport(viewport);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if the click was inside the oval shape
                if (e.getX() >= focalPoint.x && e.getX() <= focalPoint.x + 80
                        && e.getY() >= focalPoint.y && e.getY() <= focalPoint.y + 80) {
                    // The user clicked on the oval shape
                    System.out.println("This is a highlighted POI!");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Check if the click was inside the oval shape
                if (e.getX() >= focalPoint.x && e.getX() <= focalPoint.x + 80
                        && e.getY() >= focalPoint.y && e.getY() <= focalPoint.y + 80) {
                    // The user clicked on the oval shape
                    System.out.println("This is a highlighted POI!");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse has moved away!");
            }
        });
        return scrollPane;
    }

    public BufferedImage getMapImage() {
        return mapImage;
    }

    public BufferedImage highlight(POI poi) throws IOException {

        BufferedImage basemap = ImageIO.read(new File("./maps/" + BuildingBuddy.currentFloor + ".png"));

        // create a new image with the same dimensions as the original basemap
        BufferedImage highlightedImage = new BufferedImage(basemap.getWidth(), basemap.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // create a Graphics2D object for drawing on the new map
        Graphics2D g2d = highlightedImage.createGraphics();

        // draw the original basemap on the new map
        g2d.drawImage(basemap, 0, 0, null);

        // set the rendering hints for smooth antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // create a cicle with a transparent fill and a solid border
        g2d.setColor(new Color(255, 0, 0, 90));

        g2d.fillOval(poi.positionX, poi.positionY, 80, 80);
        // dispose of the Graphics2D object to free up resources
        g2d.dispose();

        focalPoint.x = poi.positionX;
        focalPoint.y = poi.positionY;

        return highlightedImage;
    }


}

