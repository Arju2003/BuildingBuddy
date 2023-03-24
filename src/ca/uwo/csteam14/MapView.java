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
    protected static POI currentHighlighted;
    private final String USER_DEFINED = "UDP";
    private final String BUILT_IN = "BIP";
    private final String OFF = "OFF";



    public MapView(String mapFileName, Point focalPoint) {

        this.setLayout(null);

        try {
            mapImage = ImageIO.read(new File("./maps/"+mapFileName));
            imageWidth = mapImage.getWidth();
            imageHeight = mapImage.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.focalPoint = focalPoint;
        setLayout(new OverlayLayout(this));
        JComponent[] clickables = new JComponent[LayerFilter.selectedLayers.size()];
        for (int i = 0; i < LayerFilter.selectedLayers.size(); ++i) {
            clickables[i] = getClickableAreas(BuildingBuddy.currentFloor,LayerFilter.selectedLayers);
            clickables[i].setPreferredSize(new Dimension(48,48));
            add(clickables[i]);
            setComponentZOrder(clickables[i], 0);
        }
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
        setLayout(new OverlayLayout(this));
        JComponent[] clickables = new JComponent[LayerFilter.selectedLayers.size()];
        for (int i = 0; i < LayerFilter.selectedLayers.size(); ++i) {
            clickables[i] = getClickableAreas(BuildingBuddy.currentFloor,LayerFilter.selectedLayers);
            clickables[i].setPreferredSize(new Dimension(48,48));
            add(clickables[i]);
            setComponentZOrder(clickables[i], 0);
        }
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
        setLayout(new OverlayLayout(this));
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

    public BufferedImage applyHighlighter(int x, int y, String mode) throws IOException {

        BufferedImage basemap = LayerFilter.updatedMap;

        if (basemap == null) basemap = ImageIO.read(new File("./maps/"+BuildingBuddy.currentFloor+".png"));

        // create a new image with the same dimensions as the original basemap
        BufferedImage highlightedImage = new BufferedImage(basemap.getWidth(), basemap.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // create a Graphics2D object for drawing on the new map
        Graphics2D g2d = highlightedImage.createGraphics();

        // draw the original basemap on the new map
        g2d.drawImage(basemap, 0, 0, null);

        // set the rendering hints for smooth antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // create a circle with a transparent fill and a solid border


        if (mode.strip().equalsIgnoreCase(BUILT_IN)) {
            g2d.setColor(new Color(255, 0, 0, 90));
            g2d.fillOval(x - 6 , y - 6 , 60, 60);
        }
        else if (mode.strip().equalsIgnoreCase(USER_DEFINED)) {
            g2d.setColor(new Color(255, 255,0 , 150));
            g2d.fillOval(x - 30, y - 30, 60, 60);
        }

        else assert !mode.strip().equalsIgnoreCase(OFF) || true;

        // dispose of the Graphics2D object to free up resources
        g2d.dispose();

        focalPoint.x = x;
        focalPoint.y = y;

        return highlightedImage;
    }
    public JComponent getClickableAreas(String currentFloor, ArrayList<String> layerNames) {
        // Create a custom component to display the image
        JComponent component = new JComponent() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mapImage, 0, 0, null);
            }
        };
        component.addMouseListener(new MouseAdapter() {
            BufferedImage newMap;
            public void mouseClicked(MouseEvent e) {

                POI p = identifyPOI(currentFloor, layerNames,e.getX(), e.getY());
                    if (p != null) {
                        if (currentHighlighted == null) {
                            currentHighlighted = p;
                            highlight(p.positionX,p.positionY,BUILT_IN);
                            new POIEditor(p);
                        }
                        else {
                            AppMenu.clearWindows();
                            highlight(currentHighlighted.positionX, currentHighlighted.positionY, OFF);
                            if (!p.equals(currentHighlighted)) {
                                currentHighlighted = p;
                                highlight(p.positionX,p.positionY,BUILT_IN);
                                new POIEditor(p);
                            }
                            else
                                currentHighlighted = null;
                        }

                    }
                    else {
                        highlight(e.getX(),e.getY(),USER_DEFINED);
                        int largestUD = 4000;
                        for (POI poi : Data.userCreatedPOIs) {
                            if (poi.id > largestUD)
                                largestUD = poi.id;
                        }
                        POI newPOI = new POI(largestUD + 1);
                        newPOI.positionX = e.getX();
                        newPOI.positionY = e.getY();
                        newPOI.category = "My Locations";
                        newPOI.map = BuildingBuddy.currentFloor.replaceAll("\\dF","") + ".png";
                        newPOI.building = BuildingBuddy.getBuildingFullName(BuildingBuddy.currentFloor);
                        newPOI.floor = BuildingBuddy.getFloorFullName(BuildingBuddy.currentFloor);
                        newPOI.code = BuildingBuddy.currentBuildingCode;
                        newPOI.isBuiltIn = false;
                        newPOI.next = null;
                        new POIEditor(newPOI);
                    }
            }
        });
        return component;
    }

    public POI identifyPOI(String floorName, ArrayList<String> layerNames, int x, int y) {
        ArrayList<POI> list = new ArrayList<>();
        for (String s: layerNames) {
            if (!s.contains("Bookmarks"))
                list.addAll(Data.getPOIs(floorName, s));
        }
        for (POI p : list) {
            if (x >= p.positionX - 12 && x <= p.positionX + 60 && y >= p.positionY - 12 && y <= p.positionY + 60)
                return p;
        }
        return null;
    }

    public static void refreshMaps(BufferedImage newMap, int x, int y) {
        if (GUIForPOIs.secondary != null) {
            GUIForPOIs.map = new MapView(newMap, new Point(x, y ));
            GUIForPOIs.secondary.setVisible(false);
            GUIForPOIs.secondary.replaceWith(GUIForPOIs.map.loadMapViewer(), 'R');
            GUIForPOIs.secondary.setVisible(true);
        }
        if (GUI.primary != null) {
            GUI.map = new MapView(newMap, new Point(x , y ));
            GUI.primary.setVisible(false);
            GUI.primary.replaceWith(GUI.map.loadMapViewer(), 'R');
            GUI.primary.setVisible(true);
        }
    }
    public void highlight(int x, int y, String mode) {
        BufferedImage newMap;
        loadMapViewer();
        if (GUIForPOIs.secondary != null) {
            try {
                newMap = GUIForPOIs.map.applyHighlighter(x, y,mode);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            refreshMaps(newMap, x, y);
        }
        if (GUI.primary != null) {
            try {
                newMap = GUI.map.applyHighlighter(x, y, mode);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            refreshMaps(newMap, x, y);
        }
    }
}

