package ca.uwo.csteam14;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.SwingConstants.*;

public class POIEditor extends JDialog {
    // Create a new JDialog with the desired title
    JDialog dialog = new JDialog();

    public POIEditor(POI poi) {
        AppMenu.clearWindows(); // close all floating windows (the WeatherInfo window, specifically)
        UIManager.put("TextArea.font", new Font("Arial", Font.PLAIN, 16));
        dialog.setTitle(poi.name);
        dialog.setResizable(false);
        // Create a new JTextPane
        setLayout(null);
        JPanel main = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        main.setLayout(new GridLayout(0,2));
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setOpaque(true);
        rightPanel.setOpaque(true);
        rightPanel.setLayout(new GridBagLayout());

        GridBagConstraints toTheLeft = new GridBagConstraints();
        toTheLeft.gridx= 0;
        toTheLeft.gridwidth = GridBagConstraints.REMAINDER;
        toTheLeft.anchor = GridBagConstraints.WEST;
        toTheLeft.insets = new Insets(0, 0, 3, 30);
        GridBagConstraints toTheRight = new GridBagConstraints();
        toTheRight.gridx= 0;
        toTheRight.gridwidth = GridBagConstraints.REMAINDER;
        toTheRight.anchor = GridBagConstraints.EAST;
        toTheRight.insets = new Insets(0, 30, 3, 0);

        JLabel POINameLabel = new JLabel("Location Name");
        JLabel POIRoomNumberLabel = new JLabel("Room Number");
        JLabel POIFloorLabel = new JLabel("Floor");
        JLabel POIBuildingLabel = new JLabel("Building");
        JLabel POICategoryLabel = new JLabel("Category");
        JLabel POIDescriptionLabel = new JLabel("Description");
        JRadioButton bookmarkAdd = new JRadioButton("Add Bookmark");
        JRadioButton bookmarkRemove = new JRadioButton("Remove Bookmark");
        leftPanel.add(POINameLabel, toTheRight);
        leftPanel.add(POIRoomNumberLabel,toTheRight);
        leftPanel.add(POIFloorLabel,toTheRight);
        leftPanel.add(POIBuildingLabel,toTheRight);
        leftPanel.add(POICategoryLabel,toTheRight);
        leftPanel.add(POIDescriptionLabel,toTheRight);
        leftPanel.add(bookmarkAdd, toTheRight);
        leftPanel.add(bookmarkRemove, toTheRight);
        JTextField POIMameField = new JTextField(poi.name);
        JTextField POIRoomNumberField = new JTextField(poi.roomNum);
        JTextField POIFloorField = new JTextField(poi.floor);
        JTextField POIBuildingField = new JTextField(poi.building);
        JTextField POICategoryField = new JTextField(poi.category);
        JTextField POIDescriptionField = new JTextField(poi.description);
        JButton saveButton = new JButton("Save Changes");
        JButton deleteButton = new JButton("Delete Location");
        rightPanel.add(POIMameField, toTheLeft);
        rightPanel.add(POIRoomNumberField, toTheLeft);
        rightPanel.add(POIFloorField, toTheLeft);
        rightPanel.add(POIBuildingField, toTheLeft);
        rightPanel.add(POICategoryField, toTheLeft);
        rightPanel.add(POIDescriptionField, toTheLeft);
        rightPanel.add(saveButton, toTheLeft);
        rightPanel.add(deleteButton, toTheLeft);

        bookmarkAdd.addActionListener( e-> {
            if (!Data.bookmarks.contains(poi)) bookmarkAdd.setSelected(true);
            if (bookmarkAdd.isSelected()) bookmarkRemove.setSelected(false);
        });

        bookmarkRemove.addActionListener( e-> {
            if (Data.bookmarks.contains(poi)) bookmarkAdd.setSelected(true);
            if (bookmarkRemove.isSelected()) bookmarkAdd.setSelected(false);
        });

        saveButton.setOpaque(true);
        saveButton.setForeground(new Color(10,250,250));
        saveButton.setBackground(new Color(0,128,0));
        saveButton.setUI(new BasicButtonUI());

        deleteButton.setOpaque(true);
        deleteButton.setForeground(new Color(204,255,0));
        deleteButton.setBackground(new Color(128,0,0));
        deleteButton.setUI(new BasicButtonUI());

        for (Component j: leftPanel.getComponents()) {
            j.setFont(new Font("Arial", Font.PLAIN, 14));
            if (j instanceof JLabel) {
                j.setPreferredSize(new Dimension(100, 40));
                ((JLabel) j).setHorizontalAlignment(RIGHT);
            } else if (j instanceof JRadioButton) {
                ((JRadioButton) j).setHorizontalAlignment(RIGHT);
                ((JRadioButton) j).setHorizontalAlignment(RIGHT);
                j.setPreferredSize(new Dimension(160, 40));
            }
            else {
                j.setPreferredSize(new Dimension(200, 40));

            }
        }

        for (Component j: rightPanel.getComponents()) {

            if (j instanceof JTextField) {
                j.setPreferredSize(new Dimension(200, 40));
                ((JTextField) j).setMargin(new Insets(0, 30, 3, 0));
                ((JTextField) j).setHorizontalAlignment(LEFT);
            }
            else {
                j.setPreferredSize(new Dimension(200, 40));
                j.setFont(new Font("Arial", Font.PLAIN, 14));
            }
        }

            if (!BuildingBuddy.devMode) {
                for (Component j : rightPanel.getComponents()) {
                    if (j instanceof JTextField) {
                        ((JTextField) j).setEditable(false);
                    } else if (j instanceof JRadioButton) {
                       j.setEnabled(true);
                    }
                }
                deleteButton.setEnabled(false);
                deleteButton.setBackground(new Color(181,181,181));
                deleteButton.setForeground(new Color(0,0,0));
            }
            else {
                bookmarkAdd.setEnabled(false);
                bookmarkRemove.setEnabled(false);
            }

        main.add(leftPanel);
        main.add(rightPanel);
        main.setPreferredSize(new Dimension(500, 350));
        main.setFont(new Font("Arial", Font.PLAIN, 16));
        pack();

        JScrollPane scrollPane = new JScrollPane(main);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JViewport viewport = scrollPane.getViewport();
        Point point = new Point(0, 0);
        viewport.setViewPosition(point);


        // Add the JScrollPane to the JDialog
        dialog.add(scrollPane);

        // Create a JButton to close the dialog
        JButton closeButton = new JButton("Cancel");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                GUIForPOIs.map.highlight(MapView.currentHighlighted.positionX,MapView.currentHighlighted.positionY,"OFF");
            }
        });
        dialog.add(closeButton, BorderLayout.SOUTH);

        // Pack the JDialog
        dialog.pack();

        // Set the location of the JDialog to the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - dialog.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - dialog.getHeight()) / 2);
        dialog.setLocation(x, y);

// Display the JDialog
        dialog.setVisible(true);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Define your desired behavior here
                dialog.dispose();
                if (GUIForPOIs.map != null)
                    GUIForPOIs.map.highlight(MapView.currentHighlighted.positionX,MapView.currentHighlighted.positionY,"OFF");
                if (GUI.map != null)
                    GUI.map.highlight(MapView.currentHighlighted.positionX,MapView.currentHighlighted.positionY,"OFF");
            }
        });
    }
}