package ca.uwo.csteam14;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class POISelector extends JPanel {
    protected JScrollPane scrollPane;
    protected LinkedList<POI> currentCollection;

    public POISelector(LinkedList<POI> collection) {
        currentCollection = collection;
        ArrayList<String> items = new ArrayList<>();
        ArrayList<String> poiIDs = new ArrayList<>();
        for (POI poi: collection) {
            items.add(poi.category + " – " + poi.name + " (" + poi.floor +", "+poi.code+")");
            poiIDs.add(String.valueOf((Integer)poi.id));
        }
        JList<String> itemList = new JList<>(items.toArray(new String[items.size()]));

        DefaultListCellRenderer renderer = new CustomListCellRenderer();
        renderer.setHorizontalAlignment(DefaultListCellRenderer.LEFT);
        itemList.setCellRenderer(renderer);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                try {
                    POI focus = currentCollection.get(itemList.getSelectedIndex());
                    BuildingBuddy.currentFloor = focus.pathName.replace(".png","").replace("./maps/","");
                    BuildingBuddy.currentBuildingCode = BuildingBuddy.currentFloor.replaceAll("\\dF", "");
                    GUIForPOIs.secondary.setBackground(ImageIO.read(new File("./images/"+BuildingBuddy.currentBuildingCode+"_hero.png")));
                    BufferedImage newMap = GUIForPOIs.map.highlight(focus);
                    GUIForPOIs.map = new MapView(newMap, new Point(focus.positionX, focus.positionY));
                    GUIForPOIs.secondary.setVisible(false);
                    GUIForPOIs.secondary.replaceWith(GUIForPOIs.map.loadMapViewer(), 'R');
                    GUIForPOIs.secondary.setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        scrollPane = new JScrollPane(itemList);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setPreferredSize(new Dimension(450,450));
        GUIForPOIs.secondary.load(scrollPane, 'L');
    }

    public void refreshPOISelector() {
        new POISelector(currentCollection);
    }

    public static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {

            // Call the parent class to get the default renderer
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Customize the appearance of the cell
            if (isSelected) {
                c.setBackground(new Color(209, 204, 255));
                c.setForeground(Color.BLACK);
            }
            else {
            c.setBackground(Color.WHITE);
            c.setForeground(Color.BLACK);
            }

            c.setFont(new Font("Arial", Font.PLAIN, 16));
            Insets insets = new Insets(3, 20, 3, 20);
            ((JComponent) c).setBorder(new EmptyBorder(insets));

            // Return the customized cell
            return c;
        }
    }
}
