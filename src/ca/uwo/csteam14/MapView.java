package ca.uwo.csteam14;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class MapView extends JPanel {
    private static BufferedImage mapImage;
    private static int imageWidth, imageHeight;
    private final Point focalPoint;



    public MapView(String mapName, Point focalPoint) {

        this.setLayout(null);

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
        this.setLayout(null);
        mapImage = bufferedMap;
        try {
            imageWidth = mapImage.getWidth();
            imageHeight = mapImage.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.focalPoint = focalPoint;
    }

    public MapView(ImageIcon bufferedMap, Point focalPoint) {
        this.setLayout(null);
        mapImage = new BufferedImage(bufferedMap.getIconWidth(), bufferedMap.getIconHeight(), BufferedImage.TYPE_INT_RGB);
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
        this.setLayout(new OverlayLayout(this));
        JComponent[] clickables = new JComponent[LayerFilter.selectedLayers.size()];
        for (int i = 0; i < LayerFilter.selectedLayers.size(); ++i) {
            clickables[i] = getClickableAreas(BuildingBuddy.currentFloor,LayerFilter.selectedLayers);
            clickables[i].setPreferredSize(new Dimension(48,48));
            add(clickables[i]);
            setComponentZOrder(clickables[i], 0);
        }
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

        // create a circle with a transparent fill and a solid border
        g2d.setColor(new Color(255, 0, 0, 90));

        g2d.fillOval(poi.positionX, poi.positionY, 80, 80);
        // dispose of the Graphics2D object to free up resources
        g2d.dispose();

        focalPoint.x = poi.positionX;
        focalPoint.y = poi.positionY;

        return highlightedImage;
    }

    public JComponent getClickableAreas(String currentFloor, ArrayList<String> layerNames) {
        ArrayList<POI> POIs = new ArrayList<>();
        for (String s: layerNames) {
            POIs.addAll(Data.getPOIs(currentFloor, s));
        }
        Rectangle[] clickableAreas = new Rectangle[POIs.size()];
        for (int i = 0; i < clickableAreas.length; ++i)
            clickableAreas[i] = new Rectangle(POIs.get(i).positionX, POIs.get(i).positionY, 80,80);
        String[] messages = new String[POIs.size()];
        for (int i = 0; i < clickableAreas.length; ++i)
            messages[i] = POIs.get(i).name;
        // Create a custom component to display the image
        JComponent component = new JComponent() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mapImage, 0, 0, null);
            }
        };
        component.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < clickableAreas.length; ++i) {
                    if (e.getX() >= clickableAreas[i].x && e.getX() <= clickableAreas[i].x + 80 && e.getY() >= clickableAreas[i].y && e.getY() <= clickableAreas[i].y + 80) {
                        System.out.println("You clicked on " + messages[i] + ".");
                        break;
                    }
                }
            }
        });
        return component;
    }


}

