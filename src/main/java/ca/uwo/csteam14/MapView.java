/**
 *  This class represents a map viewer shown on the right hand side of the interface.
 *  It consists of a scrollable pane and loads a floor map at a time.
 *  It also highlights a POI in question, and pops up a POI editor.
 *
 *  @author Jason B. Shew
 *  @version 1.0.0
 *  @since 2023-03-07
 */

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
    /** The original map image to display. */
    private static BufferedImage mapImage;
    /** The dimension of the map image. */
    private static int imageWidth, imageHeight;
    /** The focal point of view, typically where a highlighted POI is. */
    private final Point focalPoint;

    /** The POI that is currently highlighted. */
    protected static POI currentHighlighted;

    /** The base map (relative to layers and highlighter). */
    private static  BufferedImage basemap;

    /** A boolean value to determine whether the mouse has clicked on a POI. */
    protected static boolean mouseClickedOnPOI = false;


    /**
     * @param mapFileName
     * @param focalPoint
     */
    public MapView(String mapFileName, Point focalPoint) {
        try {
            mapImage = ImageIO.read(new File("./maps/"+mapFileName));
            imageWidth = mapImage.getWidth();
            imageHeight = mapImage.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.focalPoint = focalPoint;

        setLayout(new OverlayLayout(this));
        if (GUI.frame.getContentPane().equals(GUIForPOIs.secondary)) {
            JComponent[] clickables = new JComponent[LayerFilter.labelArray.size()];
            for (int i = 0; i < LayerFilter.labelArray.size(); ++i) {
                clickables[i] = getClickableAreas(Main.currentFloor, LayerFilter.labelArray);
                clickables[i].setPreferredSize(new Dimension(MapView.imageWidth, MapView.imageHeight));
                add(clickables[i]);
                setComponentZOrder(clickables[i], 0);
            }
        }
        else {
            JComponent[] clickables = new JComponent[LayerFilter.selectedLayers.size()];
            for (int i = 0; i < LayerFilter.selectedLayers.size(); ++i) {
                clickables[i] = getClickableAreas(Main.currentFloor,LayerFilter.selectedLayers);
                clickables[i].setPreferredSize(new Dimension(MapView.imageWidth, MapView.imageHeight));
                add(clickables[i]);
                setComponentZOrder(clickables[i], 0);
            }
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
        if (GUI.frame.getContentPane() instanceof Canvas)
            ((Canvas)GUI.frame.getContentPane()).replaceWith(scrollPane,'R');
        }


    /**
     * @param bufferedMap
     * @param focalPoint
     */
    public MapView(BufferedImage bufferedMap, Point focalPoint) {
        mapImage = bufferedMap;
        try {
            imageWidth = mapImage.getWidth();
            imageHeight = mapImage.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.focalPoint = focalPoint;
        setLayout(new OverlayLayout(this));
        if (GUI.frame.getContentPane().equals(GUIForPOIs.secondary)) {
            ArrayList<String> allLayers = LayerFilter.labelArray;
            JComponent[] clickables = new JComponent[allLayers.size()];
            for (int i = 0; i < LayerFilter.labelArray.size(); ++i) {
                clickables[i] = getClickableAreas(Main.currentFloor, allLayers);
                clickables[i].setPreferredSize(new Dimension(MapView.imageWidth, MapView.imageHeight));
                add(clickables[i]);
                setComponentZOrder(clickables[i], 0);
            }
        }
        else {
            JComponent[] clickables = new JComponent[LayerFilter.selectedLayers.size()];
            for (int i = 0; i < LayerFilter.selectedLayers.size(); ++i) {
                clickables[i] = getClickableAreas(Main.currentFloor,LayerFilter.selectedLayers);
                clickables[i].setPreferredSize(new Dimension(MapView.imageWidth, MapView.imageHeight));
                add(clickables[i]);
                setComponentZOrder(clickables[i], 0);
            }
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
        if (GUI.frame.getContentPane() instanceof Canvas)
            ((Canvas)GUI.frame.getContentPane()).replaceWith(scrollPane,'R');
    }


    /**
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (mapImage != null) {
            g2d.drawImage(mapImage, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    /**
     * @return
     */
    public Dimension getPreferredSize() {
        return new Dimension(imageWidth, imageHeight);
    }


    /**
     * @param x
     * @param y
     * @param mode
     * @return
     * @throws IOException
     */
    public BufferedImage applyHighlighter(int x, int y, String mode) throws IOException {

        if (GUI.frame.getContentPane().equals(GUI.canvas)) basemap = LayerFilter.baseMapImage;
        else if (GUI.frame.getContentPane().equals(GUIForPOIs.secondary)) basemap = ImageIO.read(new File("./maps/"+ Main.currentFloor+".png"));


        // create a new image with the same dimensions as the original basemap
        BufferedImage highlightedImage = new BufferedImage(basemap.getWidth(), basemap.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // create a Graphics2D object for drawing on the new map
        Graphics2D g2d = highlightedImage.createGraphics();

        // draw the original basemap on the new map
        g2d.drawImage(basemap, 0, 0, null);

        // set the rendering hints for smooth antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // create a circle with a transparent fill and a solid border


        if (GUI.frame.getContentPane() == (GUIForPOIs.secondary)) {
            POI p = POISelector.focus;
            if (p == null || mode.equals("NEW")) {
                p = identifyPOI(Main.currentFloor,LayerFilter.labelArray,x,y);}
            if (p != null) {
                BufferedImage iconImage = ImageIO.read(new File(LayerFilter.getLayerIcon(p.category)));
                BufferedImage resizedIcon = new BufferedImage(LayerFilter.iconWidth, LayerFilter.iconHeight, iconImage.getType());
                // Scale the icon image to the new size
                Graphics2D g = resizedIcon.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g.drawImage(iconImage, 0, 0, LayerFilter.iconWidth, LayerFilter.iconHeight, null);
                g.dispose();
                g = highlightedImage.createGraphics();
                if (!mode.equals("NEW"))
                    g.drawImage(resizedIcon, x - LayerFilter.iconWidth / 2, y - LayerFilter.iconHeight / 2, LayerFilter.iconWidth, LayerFilter.iconHeight, null);
                g.dispose();
                }
            }

        int highlightOvalWidth = (int) ( LayerFilter.iconWidth * 1.5);
        int highlightOvalHeight = (int) ( LayerFilter.iconHeight * 1.5);

        if (mode.strip().equalsIgnoreCase("BIP")) {
            g2d.setColor(new Color(255, 0, 0, 90));
            g2d.fillOval(x - highlightOvalWidth / 2 , y - highlightOvalHeight / 2 , highlightOvalWidth, highlightOvalHeight);
        }
        else if (mode.strip().equalsIgnoreCase("UDP")) {
            g2d.setColor(new Color(255, 255,0 , 110));
            g2d.fillOval(x - highlightOvalWidth / 2, y - highlightOvalHeight / 2, highlightOvalWidth, highlightOvalHeight);
        }

        else if (mode.strip().equalsIgnoreCase("BMK")) {
            g2d.setColor(new Color(255, 255,0 , 110));
            g2d.fillOval(x - highlightOvalWidth / 2, y - highlightOvalHeight / 2, highlightOvalWidth, highlightOvalHeight);
        }

        else if (mode.strip().equalsIgnoreCase("SRC")) {
            g2d.setColor(new Color(255, 255,0 , 110));
            g2d.fillOval(x - highlightOvalWidth / 2, y - highlightOvalHeight / 2, highlightOvalWidth, highlightOvalHeight);
        }

        else if (mode.strip().equalsIgnoreCase("OFF")) {
            g2d.setColor(new Color(8, 222,0 , 0));
            g2d.fillOval(0,0,0,0);
        }

        else if (mode.strip().equalsIgnoreCase("NEW"))  {
            g2d.setColor(new Color(8, 222,0 , 110));
            g2d.fillOval(x - highlightOvalWidth / 2, y - highlightOvalHeight / 2, highlightOvalWidth, highlightOvalHeight);
        }

        // dispose of the Graphics2D object to free up resources
        g2d.dispose();

        focalPoint.x = x;
        focalPoint.y = y;
        if (GUI.frame.getContentPane() == (GUIForPOIs.secondary)) LayerFilter.showAllLayers();
        else if (GUI.frame.getContentPane() == (GUI.canvas)) LayerFilter.refreshLayers();

        return highlightedImage;
    }

    /**
     * @param currentFloor
     * @param layerNames
     * @return
     */
    public JComponent getClickableAreas(String currentFloor, ArrayList<String> layerNames) {
        // Create a custom component to display the image
        JComponent component = new JComponent() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mapImage, 0, 0, null);
            }
        };
        component.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                POI p = identifyPOI(currentFloor, layerNames,e.getX(), e.getY());
                if (p != null) {
                    POISelector.focus = p;
                    Main.updateCurrent(p);
                    mouseClickedOnPOI = true;
                    if (currentHighlighted == null) {
                        currentHighlighted = p;
                        highlight(p.positionX,p.positionY,"BIP");
                        new POIEditor(p);
                    }
                    else {
                        AppMenu.clearWindows();
                        highlight(currentHighlighted.positionX, currentHighlighted.positionY, "OFF");
                        if (!p.isEqualTo(currentHighlighted)) {
                            currentHighlighted = p;
                            highlight(p.positionX,p.positionY,"BIP");
                            new POIEditor(p);
                        }
                        else
                            currentHighlighted = null;
                    }
                }
                else {
                    POISelector.focus = null;
                    mouseClickedOnPOI = false;
                    if (Main.devMode) {
                        highlight(e.getX(), e.getY(), "NEW");
                        POI newPOI = new POI(Data.generatePOIID("dev"));
                        newPOI.name = "";
                        newPOI.positionX = e.getX();
                        newPOI.positionY = e.getY();
                        newPOI.category = "";
                        newPOI.roomNumber = 0;
                        newPOI.map = Main.currentFloor + ".png";
                        newPOI.building = Main.getBuildingFullName(Main.currentFloor);
                        newPOI.floor = Main.getFloorFullName(Main.currentFloor);
                        newPOI.code = Main.currentBuildingCode;
                        newPOI.description = "";
                        newPOI.isBuiltIn = true;
                        newPOI.next = null;
                        new POIEditor(newPOI);
                        currentHighlighted = newPOI;
                        if (POIEditor.isSaved) {
                            POIEditor.isSaved = false;
                            POISelector.focus = newPOI;
                            Main.updateCurrent(newPOI);
                        }
                    }
                    else if (GUI.frame.getContentPane().equals(GUI.canvas)) {
                        highlight(e.getX(), e.getY(), "NEW");
                        int id = Data.generatePOIID("user");
                        POI newPOI = new POI(id);
                        newPOI.name = "My Location #" + (id - Data.userPOIStartID);
                        newPOI.positionX = e.getX();
                        newPOI.positionY = e.getY();
                        newPOI.category = "My Location";
                        newPOI.roomNumber = 0;
                        newPOI.map = Main.currentFloor + ".png";
                        newPOI.building = Main.getBuildingFullName(Main.currentFloor);
                        newPOI.floor = Main.getFloorFullName(Main.currentFloor);
                        newPOI.code = Main.currentBuildingCode;
                        newPOI.description = "Your description goes here.";
                        newPOI.isBuiltIn = false;
                        newPOI.next = null;
                        new POIEditor(newPOI);
                        currentHighlighted = newPOI;
                        if (POIEditor.isSaved) {
                            POIEditor.isSaved = false;
                            POISelector.focus = newPOI;
                            Main.updateCurrent(newPOI);
                        }
                    }
                }
            }
        });
        return component;
    }

    /**
     * @param floorName
     * @param layerNames
     * @param x
     * @param y
     * @return
     */
    public POI identifyPOI(String floorName, ArrayList<String> layerNames, int x, int y) {
        ArrayList<POI> list = new ArrayList<>();
        for (String s: layerNames) {
            if (!s.contains("Bookmarks"))
                if (Main.devMode && !s.contains("My Locations"))
                    list.addAll(Data.getLayerPOIs(floorName, s));
                else if (!Main.devMode)
                    list.addAll(Data.getLayerPOIs(floorName, s));
        }
        for (POI p : list) {
            if (x <= p.positionX + LayerFilter.iconWidth && x >= p.positionX - LayerFilter.iconWidth && y <= p.positionY + LayerFilter.iconHeight && y >= p.positionY - LayerFilter.iconHeight)
                return p;
        }
        return null;
    }


    /**
     * @param x
     * @param y
     * @param mode
     */
    public void highlight(int x, int y, String mode) {
        BufferedImage newMap;
        if (GUI.frame.getContentPane().equals(GUIForPOIs.secondary)) {
            try {
                newMap = GUIForPOIs.mapView.applyHighlighter(x, y,mode);
            } catch (IOException ex) {
                return;
            }
            new MapView(newMap, new Point(x , y ));
        }
        else if (GUI.frame.getContentPane().equals(GUI.canvas)) {
            try {
                newMap = LayerFilter.currentMapView.applyHighlighter(x, y, mode);
            } catch (IOException ex) {
                return;
            }
            new MapView(newMap, new Point(x , y ));
        }
    }

    public static void cancelHighlight() {
        if (MapView.currentHighlighted != null)
            try {
                if (GUI.frame.getContentPane() == GUI.canvas)
                    GUI.mapView.highlight(MapView.currentHighlighted.positionX, MapView.currentHighlighted.positionY, "OFF");
                else if (GUI.frame.getContentPane() == GUIForPOIs.secondary)
                    GUIForPOIs.mapView.highlight(MapView.currentHighlighted.positionX, MapView.currentHighlighted.positionY, "OFF");
                MapView.currentHighlighted = null;
                POISelector.focus = null;
            } catch (Exception ignored) {
            }
    }
}

