package ca.uwo.csteam14;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LayerFilter extends JPanel {

    private static final JPanel layerSelector = new JPanel();

    protected static JPanel checkboxPanel;
    private static final String[] labelArray = {"Bookmarks", "Classrooms","Labs","CompSci Spots",
            "Restaurants", "Stairwells / Elevators","Entrances / Exits", "My Locations","Washrooms","Accessibility"};
    private static final String[] iconArray = {"./images/bookmark.png", "./images/classroom.png","./images/lab.png","./images/compsci.png","./images/restaurant.png","./images/stairwell.png","./images/entrance.png","./images/location.png","./images/washroom.png","./images/accessibility.png"};

    protected static BufferedImage baseMap;

    private static String selectedLayer;

    //main class
    public LayerFilter() throws IOException {
        layerSelector.removeAll();
        checkboxPanel = new JPanel();
        this.setFont(new Font("Arial", Font.PLAIN, 24));
        checkboxPanel.setLayout(new GridLayout(0, 1));
        Font regularFont = new Font("Arial", Font.PLAIN, 18);
        JCheckBox[] checkboxes = new JCheckBox[labelArray.length];
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        for (int i = 0; i < checkboxes.length; ++i) {
            ImageIcon icon = new ImageIcon(iconArray[i]);
            Image image = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);
            JCheckBox checkbox = new JCheckBox();
            checkbox.setIcon(scaledIcon);
            checkbox.setText("  " + labelArray[i]);
            Border border = BorderFactory.createCompoundBorder(checkbox.getBorder(), padding);
            checkbox.setBorder(border);
            checkbox.setPreferredSize(new Dimension(270, checkbox.getPreferredSize().height));
            checkbox.setOpaque(true);
            Color originalBackground = checkbox.getBackground();
            checkbox.addItemListener(e -> {
                if (checkbox.isSelected()) {
                    selectedLayer = checkbox.getText();
                    checkbox.setBackground(new Color(209, 204, 255));
                    try {
                        refreshLayers();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    if (checkbox.getText().contains("Washroom") || checkbox.getText().contains("Accessibility")) {
                        checkbox.setSelected(true);
                    } else {
                        checkbox.setBackground(originalBackground);
                    }
                    try {
                        refreshLayers();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            if (labelArray[i].contains("Washrooms") || labelArray[i].contains("Accessibility")) {
                checkbox.setEnabled(true);
                checkbox.setSelected(true);
                checkbox.setFocusable(false);
            }

            checkboxes[i] = checkbox;
        }

        for (JCheckBox checkbox : checkboxes) {
            checkbox.setFont(regularFont);
            if (BuildingBuddy.currentBuildingCode.contains("MC"))
                checkboxPanel.add(checkbox);
            else if (!checkbox.getText().contains("CompSci Spots"))
                checkboxPanel.add(checkbox);
        }

        Border border = BorderFactory.createCompoundBorder(checkboxPanel.getBorder(), padding);
        checkboxPanel.setBorder(border);
        layerSelector.add(checkboxPanel, BorderLayout.CENTER);
        GUI.primary.load(layerSelector, 'L');

    }

    public static void refreshLayers() throws IOException {
        baseMap = ImageIO.read(new File("./maps/" + BuildingBuddy.currentFloor + ".png"));
        Point center = BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode);
        for (String layerName: LayerFilter.selectedLayers()) {
            // Load the original images
            BufferedImage iconImage = ImageIO.read(new File(getLayerIcon(layerName)));
            BufferedImage layeredMap = baseMap;
            ArrayList<POI> POIs = Data.getCategory(layerName, BuildingBuddy.currentFloor);
            // Create a new buffered image for the resized icon
            int newWidth = 48;
            int newHeight = 48;
            BufferedImage resizedIcon = new BufferedImage(newWidth, newHeight, iconImage.getType());

            // Scale the icon image to the new size
            Graphics2D g = resizedIcon.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.drawImage(iconImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            for (POI poi : POIs) {
                g = layeredMap.createGraphics();
                g.drawImage(resizedIcon, poi.positionX, poi.positionY, null);
                g.dispose();
                if (selectedLayer.contains(poi.category)) {
                    center.x = poi.positionX;
                    center.y = poi.positionY;
                }
            }

            MapView layer = new MapView(layeredMap, center);
            baseMap = layeredMap;
            GUI.primary.setVisible(false);
            GUI.primary.replaceWith(layer.loadMapViewer(), 'R');
            GUI.primary.setVisible(true);
        }
    }

    public static ArrayList<String> selectedLayers() {
        ArrayList<String> result = new ArrayList<>();
        for (Component c : checkboxPanel.getComponents()) {
            if (c instanceof JCheckBox && ((JCheckBox)c).isSelected()) {
                result.add(((JCheckBox) c).getText());
            }
        }
        return result;
    }

    public static String getLayerIcon(String layer) {
        String filepath = "./images/";
        if (layer.contains("Classrooms"))
            filepath += "classroom.png";
        else if (layer.contains("Bookmarks"))
            filepath += "bookmark.png";
        else if (layer.contains("CompSci Spots"))
            filepath += "compsci.png";
        else if (layer.contains("Restaurants"))
            filepath += "restaurant.png";
        else if (layer.contains("Labs"))
                filepath += "lab.png";
        else if (layer.contains("Stairwells"))
                filepath += "stairwell.png";
        else if (layer.contains("Entrances"))
                filepath += "entrance.png";
        else if (layer.contains("Locations"))
                filepath += "location.png";
        else if (layer.contains("Washrooms"))
                filepath += "washroom.png";
        else if (layer.contains("Accessibility"))
                filepath += "accessibility.png";
        return filepath;
    }
}
