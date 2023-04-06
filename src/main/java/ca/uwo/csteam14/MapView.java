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

/**
 *  This class represents a map viewer shown on the right hand side of the interface.
 *  It consists of a scrollable pane and loads a floor map at a time.
 *  It also highlights a POI in question, and pops up a POI editor.
 *
 *  @author Jason B. Shew
 *  @version 1.0.0
 *  @since 2023-03-07
 */
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
     * Constructs a new MapView with the given map image file name and focal point.
     *
     * @param mapFileName the file name of the map image
     * @param focalPoint the focal point of the map view
     */
    public MapView(String mapFileName, Point focalPoint) {
        try {
            mapImage = ImageIO.read(new File("./maps/" + mapFileName)); // load the map image from file
            imageWidth = mapImage.getWidth();
            imageHeight = mapImage.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.focalPoint = focalPoint; // Sets the focal point of the map view

        // Sets the layout of the panel to overlay
        setLayout(new OverlayLayout(this));

        // Adds clickable areas for each layer to the panel
        if (GUI.frame.getContentPane() == (GUIForPOIs.secondary)) {
            JComponent[] clickables = new JComponent[LayerFilter.labelArray.size()];
            for (int i = 0; i < LayerFilter.labelArray.size(); ++i) {
                clickables[i] = getClickableAreas(Main.currentFloor, LayerFilter.labelArray); // Gets the clickable areas for the layer
                clickables[i].setPreferredSize(new Dimension(MapView.imageWidth, MapView.imageHeight));
                add(clickables[i]);
                setComponentZOrder(clickables[i], 0);
            }
        }
        else {
            JComponent[] clickables = new JComponent[LayerFilter.selectedLayers.size()];
            for (int i = 0; i < LayerFilter.selectedLayers.size(); ++i) {
                clickables[i] = getClickableAreas(Main.currentFloor, LayerFilter.selectedLayers); // Gets the clickable areas for the selected layers
                clickables[i].setPreferredSize(new Dimension(MapView.imageWidth, MapView.imageHeight));
                add(clickables[i]);
                setComponentZOrder(clickables[i], 0);
            }
        }

        // Add the map view to a scroll pane
        JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JViewport viewport = new JViewport();
        viewport.setView(this);

        // Set the position of the map view within the scroll pane
        Dimension viewportSize = viewport.getViewSize(); // get the size of the viewport
        int x = focalPoint.x - viewportSize.width / 8; // calculate the x position to display in the center of the viewport
        int y = focalPoint.y - viewportSize.height / 4; // calculate the y position to display in the center of the viewport
        viewport.setViewPosition(new Point(x, y));
        scrollPane.setViewport(viewport);

        // Replace the current content pane of the frame with the scroll pane
        if (GUI.frame.getContentPane() instanceof Canvas)
            ((Canvas)GUI.frame.getContentPane()).replaceWith(scrollPane,'R'); // replace the canvas with the scroll pane
    }



    /**
     * Constructs a new MapView with a given buffered map image and a focal point.
     *
     * @param bufferedMap the buffered map image to display
     * @param focalPoint the point to focus the map on
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
        // Gets the size of the viewport
        Dimension viewportSize = viewport.getViewSize();
        // Calculates the position to display in the center of the viewport
        int x = focalPoint.x - viewportSize.width / 8;
        int y = focalPoint.y - viewportSize.height / 4;
        viewport.setViewPosition(new Point(x, y));
        scrollPane.setViewport(viewport);
        if (GUI.frame.getContentPane() instanceof Canvas)
            ((Canvas)GUI.frame.getContentPane()).replaceWith(scrollPane,'R');
    }


    /**
     * This method paints the component with a given Graphics object.
     * It draws a map image in the component if it exists.
     *
     * @param g The Graphics object used to draw the component.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the map image if it exists
        if (mapImage != null) {
            g2d.drawImage(mapImage, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }


    /**
     * Returns the preferred size of this MapView, based on the dimensions of its
     * underlying map image. This is used by layout managers to determine the best
     * size for the component.
     *
     * @return the preferred size of this MapView
     */
    public Dimension getPreferredSize() {
        return new Dimension(imageWidth, imageHeight);
    }



    /**
     * Applies a highlight effect on the map image to indicate the position of a point of interest or search result.
     * The image is modified in place by creating a new image, drawing the original map image on it, drawing a circle
     * around the specified point, and then returning the modified image.
     *
     * @param x the x-coordinate of the point to highlight
     * @param y the y-coordinate of the point to highlight
     * @param mode the mode of the highlight effect to apply
     * @return the modified image with the highlight effect applied
     * @throws IOException if there is an error reading an image file
     */
    public BufferedImage applyHighlighter(int x, int y, String mode) throws IOException {

        // determine which map image to use based on the current GUI
        if (GUI.frame.getContentPane().equals(GUI.canvas)) {
            basemap = LayerFilter.baseMapImage;
        } else if (GUI.frame.getContentPane().equals(GUIForPOIs.secondary)) {
            basemap = ImageIO.read(new File("./maps/" + Main.currentFloor + ".png"));
        }

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
                p = identifyPOI(Main.currentFloor, LayerFilter.labelArray, x, y);
            }
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

        // determine the dimensions of the highlight circle based on the icon size
        int highlightOvalWidth = (int) (LayerFilter.iconWidth * 1.5);
        int highlightOvalHeight = (int) (LayerFilter.iconHeight * 1.5);

        // apply the appropriate highlight effect based on the specified mode

        if (mode.strip().equalsIgnoreCase("BIP")) {
            g2d.setColor(new Color(255, 0, 0, 90));
            g2d.fillOval(x - highlightOvalWidth / 2 , y - highlightOvalHeight / 2 , highlightOvalWidth, highlightOvalHeight);
        }
        else if (mode.strip().equalsIgnoreCase("UDP")) {
            g2d.setColor(new Color(155, 255,223 , 110));
            g2d.fillOval(x - highlightOvalWidth / 2, y - highlightOvalHeight / 2, highlightOvalWidth, highlightOvalHeight);
        }

        else if (mode.strip().equalsIgnoreCase("BMK")) {
            g2d.setColor(new Color(255, 255,0 , 110));
            g2d.fillOval(x - highlightOvalWidth / 2, y - highlightOvalHeight / 2, highlightOvalWidth, highlightOvalHeight);
        }

        else if (mode.strip().equalsIgnoreCase("SRC")) {
            g2d.setColor(new Color(8, 120,255 , 110));
            g2d.fillOval(x - highlightOvalWidth / 2, y - highlightOvalHeight / 2, highlightOvalWidth, highlightOvalHeight);
        }

        else if (mode.strip().equalsIgnoreCase("DIS")) {
            g2d.setColor(new Color(255, 139,0 , 110));
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
     * Returns a JComponent object that displays the image and provides clickable areas for POIs.
     *
     * @param currentFloor the current floor being displayed
     * @param layerNames   an ArrayList of layer names for the current building
     * @return             the custom component with clickable areas
     */
    public JComponent getClickableAreas(String currentFloor, ArrayList<String> layerNames) {
        // Create a custom component to display the image
        JComponent component = new JComponent() {
            /**
             * Paints the component with the map image.
             *
             * @param g the graphics context to paint with
             */
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mapImage, 0, 0, null);
            }
        };

        component.addMouseListener(new MouseAdapter() {
            /**
             * Handles a mouse click on the component.
             *
             * @param e the MouseEvent containing information about the click
             */
            public void mouseClicked(MouseEvent e) {
                // Detects if there is an existing POI where the mouse click event occurs
                POI p = identifyPOI(currentFloor, layerNames,e.getX(), e.getY());
                // If the POI exists, then highlights it and opens a reader / editor to read / modify the POI
                if (p != null) {
                    POISelector.focus = p;
                    Main.updateCurrent(p);
                    mouseClickedOnPOI = true;
                    if (currentHighlighted == null) { // If clicks on it once, highlights it
                        currentHighlighted = p;
                        highlight(p.positionX,p.positionY,"BIP");
                        new POIEditor(p);
                    }
                    else { // if clicks on it twice, reverses the highlight
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
                else {  // If the POI does not exist, then highlights it and opens an editor to create a new POI
                    POISelector.focus = null;
                    mouseClickedOnPOI = false;
                    if (Main.devMode) { // In Dev Mode, creates a new POI as a built-in POI
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
                    // In Exploration Mode, creates a new POI as a user-defined POI
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
     * Identifies the point of interest (POI) given the floor name, layer names, and x,y coordinates.
     * @param floorName The name of the floor.
     * @param layerNames The list of layer names to check for POIs.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return The POI object if found, null otherwise.
     */
    public POI identifyPOI(String floorName, ArrayList<String> layerNames, int x, int y) {
        ArrayList<POI> list = new ArrayList<>();
        for (String s: layerNames) {
            if (!s.contains("Bookmarks")) // Bookmarks are never shown because they are duplicates of existing POIs
                if (Main.devMode && !s.contains("My Locations")) // In dev mode, My Locations are not shown
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
     * Highlights a point on the map based on the provided coordinates and mode.
     *
     * @param x The x-coordinate of the point to highlight.
     * @param y The y-coordinate of the point to highlight.
     * @param mode The mode of the highlight.
     *             Valid values are "BIP", "UDP", "SRC", "BMK", "NEW", and "OFF".*
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

    /**
     * Cancels the current highlight on the map view.
     * If a POI is currently highlighted, the highlight will be removed and the currentHighlighted POI reference and POISelector focus will be set to null.
     */
    public static void cancelHighlight() {
        if (MapView.currentHighlighted != null) { // If there is currently a highlighted POI
            try {
                if (GUI.frame.getContentPane() == GUI.canvas) { // If the current view is the main map
                    // Call the highlight method on the main map view, passing the highlighted POI's position and the "OFF" mode
                    GUI.mapView.highlight(MapView.currentHighlighted.positionX, MapView.currentHighlighted.positionY, "OFF");
                } else if (GUI.frame.getContentPane() == GUIForPOIs.secondary) { // If the current view is the POI search results view
                    // Call the highlight method on the POI search results view, passing the highlighted POI's position and the "OFF" mode
                    GUIForPOIs.mapView.highlight(MapView.currentHighlighted.positionX, MapView.currentHighlighted.positionY, "OFF");
                }
                // Reset the currentHighlighted POI to null and the focus to null
                MapView.currentHighlighted = null;
                POISelector.focus = null;
            } catch (Exception ignored) {
                // Ignore any exceptions that occur
            }
        }
    }
}

