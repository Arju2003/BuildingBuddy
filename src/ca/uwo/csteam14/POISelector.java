package ca.uwo.csteam14;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class POISelector extends JPanel {
    protected JScrollPane scrollPane;
    protected LinkedList<POI> currentCollection;

    public POISelector(String POIsGroup) {
        switch (POIsGroup) {
            case "UDP" -> currentCollection = Data.userCreatedPOIs;
            case "BMK" -> currentCollection = Data.bookmarks;
            case "BIP" -> currentCollection = Data.builtInPOIs;
            case "SRC" -> currentCollection = Search.searchResults(Search.userInput);
        }

        ArrayList<String> items = new ArrayList<>();
        ArrayList<String> poiIDs = new ArrayList<>();
        if (currentCollection != null) {
            for (POI poi : currentCollection) {
                items.add(poi.category + " – " + poi.name + " (" + poi.floor + ", " + poi.code + ")");
                poiIDs.add(String.valueOf((Integer) poi.id));
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
                        GUIForPOIs.secondary.setBackground(ImageIO.read(new File("./images/" + BuildingBuddy.currentBuildingCode + "_hero.png")));
                        BuildingBuddy.currentFloor = focus.map.replace(".png", "").toUpperCase();
                        BuildingBuddy.currentBuildingCode = focus.map.replaceAll("\\dF.png", "");
                        new MapView(focus.map, new Point(focus.positionX,focus.positionY));
                        GUIForPOIs.mapView.highlight(focus.positionX, focus.positionY, POIsGroup);
                        new POIEditor(focus);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            });

            scrollPane = new JScrollPane(itemList);
        }
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setPreferredSize(new Dimension(450,450));
        GUIForPOIs.secondary.load(scrollPane, 'L');
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
