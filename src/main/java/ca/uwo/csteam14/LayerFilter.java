package ca.uwo.csteam14;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class displays a layer filter with checkboxes for each layer of points of interest on the map.
 * It also serves as a legend for layer icons.
 *
 *  @author Jason B. Shew
 *  @version 1.0.0
 *  @since 2023-03-07
 */
public class LayerFilter extends JPanel {

    // Fields
    /**
     A JPanel object used for selecting layers in the application.
     */
    private static final JPanel layerSelector = new JPanel();
    /**
     A JPanel object for checkboxes to select layers in the application.
     */
    protected static JPanel checkboxPanel;
    /**
     An ArrayList containing the names of the different layers available in the application.
     */
    protected static final ArrayList<String> labelArray = new ArrayList<>(Arrays.asList("Bookmarks", "Classrooms","Labs","CompSci Spots",
            "Restaurants", "Stairwells / Elevators","Entrances / Exits", "My Locations","Accessibility","Washrooms"));
    /**
     An ArrayList containing the file paths for the icons associated with each layer in the application.
     */
    protected static final ArrayList<String> iconArray = new ArrayList<>(Arrays.asList("./images/bookmark.png", "./images/classroom.png","./images/lab.png","./images/compsci.png","./images/restaurant.png","./images/stairwell.png","./images/entrance.png","./images/location.png","./images/accessibility.png","./images/washroom.png"));
    /**
     A BufferedImage object representing the base map image in the application.
     */
    protected static BufferedImage baseMapImage;
    /**
     A String object representing the currently selected layer in the application.
     */
    protected static String currentLayer = "Washrooms";
    /**
     An ArrayList containing the names of the selected layers in the application.
     */
    protected static ArrayList<String> selectedLayers = new ArrayList<>();
    /**
     An ArrayList containing the POIs associated with the currently selected layer in the application.
     */
    protected static ArrayList<POI> POIsOnSelectedLayer = new ArrayList<>();
    /**
     A MapView object representing the current view of the map in the application.
     */
    protected static MapView currentMapView;
    /**
     An integer representing the width of the icons associated with the layers in the application.
     */
    protected static int iconWidth = 60;
    /**
     An integer representing the height of the icons associated with the layers in the application.
     */
    protected static int iconHeight = 60;

    /**
     * Constructs a new LayerFilter object.
     *
     * @throws IOException if there is an error reading an image file.
     */
    public LayerFilter() throws IOException {
        layerSelector.removeAll();
        checkboxPanel = new JPanel();
        setFont(new Font("Arial", Font.PLAIN, 10));
        checkboxPanel.setLayout(new GridLayout(0, 1));
        Font regularFont = new Font("Arial", Font.PLAIN, 16);
        JCheckBox[] checkboxes = new JCheckBox[labelArray.size()];
        Border padding = BorderFactory.createEmptyBorder(3, 10, 3, 10);

        // Create checkbox for each layer
        for (int i = 0; i < checkboxes.length; ++i) {
            ImageIcon icon = new ImageIcon(iconArray.get(i));
            Image image = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);
            JCheckBox checkbox = new JCheckBox();
            checkbox.setIcon(scaledIcon);
            checkbox.setText(labelArray.get(i));
            checkbox.setIconTextGap(20);
            Border border = BorderFactory.createCompoundBorder(checkbox.getBorder(), padding);
            checkbox.setBorder(border);
            checkbox.setPreferredSize(new Dimension(270, checkbox.getPreferredSize().height));
            checkbox.setOpaque(true);
            Color originalBackground = checkbox.getBackground();

            // Add listener to checkbox
            checkbox.addItemListener(e -> {
                if (checkbox.isSelected()) {
                    currentLayer = checkbox.getText();
                    if (!isExisting(checkbox.getText())) {
                        selectedLayers.add(checkbox.getText());
                        ArrayList<POI> list = Data.getLayerPOIs(Main.currentFloor, checkbox.getText());
                        POIsOnSelectedLayer.addAll(list);
                    }
                    checkbox.setBackground(new Color(209, 204, 255));
                    try {
                        refreshLayers();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    if (checkbox.getText().contains("Washroom") || checkbox.getText().contains("Accessibility")) {
                        checkbox.setSelected(true);
                        currentLayer = checkbox.getText();
                    } else {
                        checkbox.setBackground(originalBackground);
                        currentLayer = selectedLayers.get(selectedLayers.size()-1);
                        if (isExisting(checkbox.getText())) {
                            selectedLayers.removeAll(Collections.singleton(checkbox.getText()));
                            ArrayList<POI> list = Data.getLayerPOIs(Main.currentFloor, checkbox.getText());
                            POIsOnSelectedLayer.removeAll(list);
                        }
                    }
                    try {
                        refreshLayers();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            if (labelArray.get(i).contains("Washrooms") || labelArray.get(i).contains("Accessibility")) {
                checkbox.setEnabled(true);
                checkbox.setSelected(true);
                checkbox.setFocusable(false);
            }

            checkboxes[i] = checkbox;
        }

        for (JCheckBox checkbox : checkboxes) {
            checkbox.setFont(regularFont);
            if (Main.currentBuildingCode.contains("MC"))
                checkboxPanel.add(checkbox);
            else if (!checkbox.getText().contains("CompSci Spots"))
                checkboxPanel.add(checkbox);
        }

        Border border = BorderFactory.createCompoundBorder(checkboxPanel.getBorder(), padding);
        checkboxPanel.setBorder(border);
        layerSelector.add(checkboxPanel, BorderLayout.CENTER);
        GUI.canvas.load(layerSelector, 'L');

    }

    /**
     * Refreshes the selected layers on the current map view
     *
     * @throws IOException if an IO exception occurs
     */
    public static void refreshLayers() throws IOException {
        baseMapImage = ImageIO.read(new File("./maps/" + Main.currentFloor + ".png"));
        Point center = Main.getOptimumPoint(Main.currentBuildingCode);
        for (String layerName: selectedLayers) {
            // Load the original images
            BufferedImage iconImage = ImageIO.read(new File(getLayerIcon(layerName)));
            BufferedImage mapImageWithLayers = baseMapImage;
            POIsOnSelectedLayer = Data.getLayerPOIs(Main.currentFloor, layerName);

            // Create a new buffered image for the resized icon
            BufferedImage resizedIcon = new BufferedImage(iconWidth, iconHeight, iconImage.getType());

            // Scale the icon image to the new size
            Graphics2D g = resizedIcon.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.drawImage(iconImage, 0, 0, iconWidth, iconHeight, null);
            g.dispose();

            for (POI poi : POIsOnSelectedLayer) {
                g = mapImageWithLayers.createGraphics();
                g.drawImage(resizedIcon, poi.positionX - iconWidth / 2, poi.positionY - iconHeight / 2, null);
                g.dispose();
                if (currentLayer.contains(poi.category)) {
                    center.x = poi.positionX;
                    center.y = poi.positionY;
                }
            }
            currentMapView = new MapView(mapImageWithLayers, center);
            baseMapImage = mapImageWithLayers;
        }
    }



    /**
     * Shows all the layers on the current map view
     *
     * @throws IOException if an IO exception occurs
     */
    public static void showAllLayers() throws IOException {
        baseMapImage = ImageIO.read(new File("./maps/" + Main.currentFloor + ".png"));
        Point center = Main.getOptimumPoint(Main.currentBuildingCode);
        for (String layerName: labelArray) {
            // In Dev Mode, Bookmarks and My Locations are not supposed to be seen
            if (Main.devMode && (layerName.contains("My Locations") || layerName.contains("Bookmarks")))
                continue;
            BufferedImage iconImage = ImageIO.read(new File(getLayerIcon(layerName)));
            BufferedImage mapImageWithLayers = baseMapImage;
            POIsOnSelectedLayer = Data.getLayerPOIs(Main.currentFloor, layerName);
            // Create a new buffered image for the resized icon

            BufferedImage resizedIcon = new BufferedImage(iconWidth, iconHeight, iconImage.getType());

            // Scale the icon image to the new size
            Graphics2D g = resizedIcon.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.drawImage(iconImage, 0, 0, iconWidth, iconHeight, null);
            g.dispose();

            for (POI poi : POIsOnSelectedLayer) {
                g = mapImageWithLayers.createGraphics();
                g.drawImage(resizedIcon, poi.positionX - iconWidth / 2, poi.positionY - iconHeight / 2, null);
                g.dispose();
            }
            currentMapView = new MapView(mapImageWithLayers, center);
            baseMapImage = mapImageWithLayers;
        }
    }

    /**
     * This method checks whether a given layer name is in the list of selected layers.
     *
     * @param layerName the name of the layer to check
     * @return true if the layer exists in the list of selected layers, false otherwise
     */
    public static boolean isExisting(String layerName) {
        for (String s: selectedLayers) {
            if (s.equals(layerName)) return true;
        }
        return false;
    }

    /**
     * This method checks whether a given point of interest (POI) exists on the currently selected layer.
     *
     * @param poi the POI to check
     * @return true if the POI exists on the currently selected layer, false otherwise
     */
    public static boolean isExisting(POI poi) {
        for (POI p: POIsOnSelectedLayer) {
            if (Math.abs(p.positionX - poi.positionX) <= iconWidth && Math.abs(p.positionY - poi.positionY) <= iconHeight) return true;
        }
        return false;
    }

    /**
     * This method returns the filepath of the icon corresponding to a given layer name.
     *
     * @param layer the name of the layer
     * @return the filepath of the icon corresponding to the layer name
     */
    public static String getLayerIcon(String layer) {
        String filepath = "./images/";
        if (layer.contains("Classroom"))
            filepath += "classroom.png";
        else if (layer.contains("Bookmark"))
            filepath += "bookmark.png";
        else if (layer.contains("CompSci"))
            filepath += "compsci.png";
        else if (layer.contains("Restaurant"))
            filepath += "restaurant.png";
        else if (layer.contains("Lab"))
            filepath += "lab.png";
        else if (layer.contains("Stairwell") || layer.contains("Elevator"))
            filepath += "stairwell.png";
        else if (layer.contains("Entrance") || layer.contains("Exit"))
            filepath += "entrance.png";
        else if (layer.contains("My"))
            filepath += "location.png";
        else if (layer.contains("Washroom"))
            filepath += "washroom.png";
        else if (layer.contains("Accessibility"))
            filepath += "accessibility.png";
        else filepath += "void.png";
        return filepath;
    }
}
