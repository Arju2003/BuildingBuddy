package ca.uwo.csteam14;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class represents a selector of POIs so user can interact with them, grab attributes, etc.
 *  @author Jason B. Shew
 *  @version 1.0.0
 *  @since 2023-03-07
 */
public class POISelector extends JPanel {

    /**
     A scroll pane for displaying a list of POIs, with a current collection of POIs
     and a focus on a single POI.
     */
    protected JScrollPane scrollPane;
    /** The data list associated with the POI list. */
    protected static LinkedList<POI> currentCollection;
    /**
     The currently focused POI.
     */
    protected static POI focus;



    /**
     * Constructs a POISelector object with the specified POI group.
     *
     * @param POIsGroup a string indicating the group of POIs to display
     */
    public POISelector(String POIsGroup) {
        switch (POIsGroup) {
            case "UDP" -> currentCollection = Data.userCreatedPOIs;
            case "BMK" -> currentCollection = Data.bookmarks;
            case "BIP" -> currentCollection = Data.builtInPOIs;
            case "SRC" -> currentCollection = Search.searchResults(Search.userInput);
        }

        ArrayList<String> items = new ArrayList<>();
        JList<String> itemListFinal = new JList<>();
        if (currentCollection != null) {
            for (POI poi : currentCollection) {
                // This is what a POI looks like in a POIs list in the selector
                items.add(poi.category + " – " + poi.name + " (" + poi.floor + ", " + poi.code + ")");
            }

            // Stylizes the POIs list
            JList<String> itemList = new JList<>(items.toArray(new String[items.size()]));
            DefaultListCellRenderer renderer = new CustomListCellRenderer();
            renderer.setHorizontalAlignment(DefaultListCellRenderer.LEFT);
            itemList.setCellRenderer(renderer);

            // POI selector only allows single selections
            itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Defines the behaviour of the selector
            itemList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    try {
                        if (!MapView.mouseClickedOnPOI)
                            // Sets the first POI on the list as focus if mouse has not clicked on any POI
                            focus = currentCollection.get(itemList.getSelectedIndex());
                        else {
                            // If the mouse has clicked on a POI then it becomes the focus
                            focus = MapView.currentHighlighted;
                            // Restores mouse click status
                            MapView.mouseClickedOnPOI = false;
                        }
                        // If in Dev Mode, shows all layers.
                        if (Main.devMode) LayerFilter.showAllLayers();
                        // If there exists a focus POI, updates the current POI of the whole system, and creates a GUI to highlight that POI
                        if (focus != null) {
                            Main.updateCurrent(focus);
                            GUIForPOIs.secondary.setBackground(ImageIO.read(new File("./images/" + Main.currentBuildingCode + "_hero.png")));
                            GUIForPOIs.mapView = new MapView(focus.map, new Point(focus.positionX, focus.positionY));
                            GUIForPOIs.mapView.highlight(focus.positionX, focus.positionY, POIsGroup);
                            // Launches the POI editor
                            new POIEditor(focus);
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            });
            itemListFinal = itemList; // Updates the final item list loaded into the selector during iterations
        }
        scrollPane = new JScrollPane(itemListFinal); // Loads it into the scrollable POI selector
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setPreferredSize(new Dimension(450,450));
        GUIForPOIs.secondary.load(scrollPane, 'L'); // Loads the selector to the left panel of GUI
    }



    /**
     * CustomListCellRenderer is a subclass of DefaultListCellRenderer that provides a customized
     * appearance for the cells of a JList.
     */
    public static class CustomListCellRenderer extends DefaultListCellRenderer {

        /**
         * Default constructor
         */
        public CustomListCellRenderer() {
            // Does nothing.
        }

        /**
         * Returns a customized renderer for a cell in a JList.
         *
         * @param list          the JList containing the cell to be rendered
         * @param value         the value of the cell to be rendered
         * @param index         the index of the cell to be rendered
         * @param isSelected   a boolean indicating whether the cell is selected
         * @param cellHasFocus  a boolean indicating whether the cell has focus
         * @return              the customized renderer for the cell
         */
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
