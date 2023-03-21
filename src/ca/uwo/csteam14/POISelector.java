package ca.uwo.csteam14;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class POISelector extends JPanel {
    JPanel selector = new JPanel();

    JScrollPane scrollPane;

    public POISelector(LinkedList<POI> collection) {

        ArrayList<String> items = new ArrayList<>();
        for (POI poi: collection) {
            items.add(poi.name + " (" + poi.building +")");
        }
        JList<String> itemList = new JList<>(items.toArray(new String[items.size()]));
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(e -> {
            // Handle selection event here
        });
        itemList.setCellRenderer(new CustomListCellRenderer());
        scrollPane = new JScrollPane(itemList);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setPreferredSize(new Dimension(300,600));
        GUIForPOIs.secondary.load(scrollPane, 'L');
    }

    public static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {

            // Call the parent class to get the default renderer
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Customize the appearance of the cell
            c.setBackground(Color.WHITE);
            c.setForeground(Color.BLACK);
            c.setFont(new Font("Arial", Font.PLAIN, 18));

            Insets insets = new Insets(10, 20, 5, 20);
            ((JComponent) c).setBorder(new EmptyBorder(insets));

            // Return the customized cell
            return c;
        }
    }
}
