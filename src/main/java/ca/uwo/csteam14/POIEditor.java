/**
 * @author Jason
 * POIEditor Class
 * Edit POI appearance in application
 */

package ca.uwo.csteam14;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import static java.awt.Font.BOLD;
import static javax.swing.SwingConstants.*;

public class POIEditor extends JDialog {
    // Create a new JDialog with the desired title
    private  final JDialog editor = new JDialog();
    protected static boolean isSaved = false;
    protected static JWindow deletionAlert = new JWindow();


    /**
     * @param poi 
     */
    public POIEditor(POI poi) {
        AppMenu.clearWindows(); // close all floating windows (the WeatherInfo window, specifically)
        UIManager.put("TextArea.font", new Font("Arial", Font.PLAIN, 16));
        if (poi.name.length() > 0)
            editor.setTitle(poi.name);
        else editor.setTitle("New Location");
        editor.setResizable(false);
        editor.setModalityType(ModalityType.MODELESS);

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
        JLabel notABookmark = new JLabel("Not in Your Bookmarks");
        JCheckBox bookmarkAdd = new JCheckBox("Add Bookmark");
        JLabel isABookmark = new JLabel("Already in Your Bookmarks");
        JCheckBox bookmarkRemove = new JCheckBox("Remove Bookmark");
        leftPanel.add(POINameLabel, toTheRight);
        leftPanel.add(POIRoomNumberLabel,toTheRight);
        leftPanel.add(POIFloorLabel,toTheRight);
        leftPanel.add(POIBuildingLabel,toTheRight);
        leftPanel.add(POICategoryLabel,toTheRight);
        leftPanel.add(POIDescriptionLabel,toTheRight);
        if (!Data.containsPOI(Data.bookmarks,poi)) {
            leftPanel.add(notABookmark, toTheRight);
            leftPanel.add(bookmarkAdd, toTheRight);

        }
        else {
            leftPanel.add(isABookmark, toTheRight);
            leftPanel.add(bookmarkRemove, toTheRight);

        }

        JTextField POINameField = new JTextField(poi.name);
        JTextField POIRoomNumberField = new JTextField(String.valueOf(poi.roomNumber));
        JTextField POIFloorField = new JTextField(poi.floor);
        JTextField POIBuildingField = new JTextField(poi.building);
        JTextField POICategoryField = new JTextField(poi.category);
        JTextField POIDescriptionField = new JTextField(poi.description);
        JButton saveButton = new JButton("Save Changes");
        JButton deleteButton = new JButton("Delete Location");
        rightPanel.add(POINameField, toTheLeft);
        rightPanel.add(POIRoomNumberField, toTheLeft);
        rightPanel.add(POIFloorField, toTheLeft);
        rightPanel.add(POIBuildingField, toTheLeft);
        rightPanel.add(POICategoryField, toTheLeft);
        rightPanel.add(POIDescriptionField, toTheLeft);
        rightPanel.add(saveButton, toTheLeft);
        rightPanel.add(deleteButton, toTheLeft);


        for (Component j: leftPanel.getComponents()) {
            j.setFont(new Font("Arial", Font.PLAIN, 14));
            if (j instanceof JLabel) {
                j.setPreferredSize(new Dimension(100, 40));
                isABookmark.setPreferredSize(new Dimension(200,40));
                notABookmark.setPreferredSize(new Dimension(200,40));
                ((JLabel) j).setHorizontalAlignment(RIGHT);

            } else if (j instanceof JCheckBox) {
                ((JCheckBox) j).setHorizontalAlignment(RIGHT);
                ((JCheckBox) j).setHorizontalAlignment(RIGHT);
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

        notABookmark.setForeground(new Color(0,90,181));
        notABookmark.setFont(new Font("Arial", BOLD,14));
        bookmarkAdd.setForeground(new Color(0,90,181));
        bookmarkAdd.setFont(new Font("Arial", BOLD,14));

        isABookmark.setForeground(new Color(230,97,0));
        isABookmark.setFont(new Font("Arial", BOLD,14));
        bookmarkRemove.setForeground(new Color(230,97,0));
        bookmarkRemove.setFont(new Font("Arial", BOLD,14));

        saveButton.setOpaque(true);
        saveButton.setForeground(new Color(255,255,255));
        saveButton.setBackground(new Color(0,90,181));
        saveButton.setUI(new BasicButtonUI());
        saveButton.setFont(new Font("Arial", BOLD,14));


        saveButton.addActionListener(e -> {
            boolean result = false;
            POINameLabel.setFont(new Font("Arial",Font.PLAIN, 14));
            POINameLabel.setPreferredSize(new Dimension(100, 40));
            POINameLabel.setForeground(Color.BLACK);
            POICategoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            POICategoryLabel.setPreferredSize(new Dimension(100, 40));
            POICategoryLabel.setForeground(Color.BLACK);
            POIRoomNumberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            POIRoomNumberLabel.setPreferredSize(new Dimension(100, 40));
            POIRoomNumberLabel.setForeground(Color.BLACK);

            if (Main.devMode) {
                if (POINameField.getText().length() > 0 && POI.hasLegalCategory(POICategoryField.getText()) && POI.isInteger(POIRoomNumberField.getText())) {
                    poi.roomNumber = Integer.parseInt(POIRoomNumberField.getText());
                    poi.category = POICategoryField.getText();
                    poi.description = POIDescriptionField.getText();
                    poi.name = POINameField.getText();
                    result = Data.addPOI(poi, Data.builtInPOIs);
                    if (Data.containsPOI(Data.bookmarks,poi))
                        Data.addPOI(poi, Data.bookmarks);
                }
                else {
                    if (POINameField.getText().length() == 0) {
                        POINameLabel.setFont(new Font("Arial", BOLD, 14));
                        POINameLabel.setPreferredSize(new Dimension(110, 40));
                        POINameLabel.setForeground(Color.RED);

                    }
                    if (!POI.hasLegalCategory(POICategoryField.getText())) {
                        POICategoryLabel.setFont(new Font("Arial", BOLD, 14));
                        POICategoryLabel.setPreferredSize(new Dimension(110, 40));
                        POICategoryLabel.setForeground(Color.RED);
                    }
                    if (!POI.isInteger(POIRoomNumberField.getText())) {
                        POIRoomNumberLabel.setFont(new Font("Arial", BOLD, 14));
                        POIRoomNumberLabel.setPreferredSize(new Dimension(110, 40));
                        POIRoomNumberLabel.setForeground(Color.RED);
                    }
                }
            }
            else {
                if (!poi.isBuiltIn) {
                    poi.description = POIDescriptionField.getText();
                    poi.name = POINameField.getText();
                    result = Data.addPOI(poi, Data.userCreatedPOIs);
                    if (Data.containsPOI(Data.bookmarks,poi))
                        Data.addPOI(poi, Data.bookmarks);
                    if (bookmarkAdd.isSelected()) {
                        Data.addPOI(poi, Data.bookmarks);
                    }
                    if (bookmarkRemove.isSelected()) {
                        try {
                            Data.removePOI(poi, Data.bookmarks);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                else {
                    if (bookmarkAdd.isSelected()) {
                        result = Data.addPOI(poi, Data.bookmarks);
                    } if (bookmarkRemove.isSelected()) {
                        try {
                            result = Data.removePOI(poi, Data.bookmarks);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }

            if (result) {
                isSaved = true;
                resultDisplay("Saved successfully!", Color.GREEN);
                editor.dispose();
                POISelector.focus = poi;
                Main.updateCurrent(poi);
                if(GUI.frame.getContentPane() == (GUIForPOIs.secondary)) {
                    new GUIForPOIs(GUIForPOIs.POIsGroup);
                }
                else {
                    new GUI(Main.currentBuildingCode);
                }
            }
            else {
                resultDisplay("Oops... Be careful!",Color.PINK);
            }
        });

        deleteButton.setOpaque(true);
        deleteButton.setForeground(new Color(255,255,255));
        deleteButton.setBackground(new Color(220,50,32));
        deleteButton.setUI(new BasicButtonUI());
        deleteButton.setFont(new Font("Arial", BOLD,14));

        deleteButton.addActionListener(e -> {
            editor.setVisible(false);
            // This code will be executed when the button is pressed
            deletionAlert.setSize(480, 100);
            deletionAlert.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    // Center the window on the screen
                    deletionAlert.setLocationRelativeTo(null);
                }
            });
            JPanel deletionAlertPanel = new JPanel();
            deletionAlertPanel.setForeground(new Color(220,50,32));
            deletionAlertPanel.setForeground(new Color(255,255,255));
            JLabel message = new JLabel("Delete this location forever?");
            message.setFont(new Font("Arial",BOLD,18));
            message.setForeground(new Color(220,50,32));
            deletionAlertPanel.add(message);
            JButton cancel = new JButton("Continue Editing");
            cancel.setEnabled(true);
            deletionAlert.setLocationRelativeTo(editor);
            cancel.addActionListener(e2-> {
                deletionAlert.setVisible(false);
                editor.setVisible(true);
            });
            JButton confirm = new JButton("Confirm Deletion");
            confirm.addActionListener(e3-> {
                boolean result;
                if (Main.devMode) {
                    try {
                        result = Data.removePOI(poi, Data.builtInPOIs);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        Data.removePOI(poi, Data.bookmarks);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    try {
                        result = Data.removePOI(poi, Data.userCreatedPOIs);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        Data.removePOI(poi, Data.bookmarks);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (result) {
                    resultDisplay("Successfully deleted!",Color.GREEN);
                }
                else {
                    resultDisplay("Oops... Be careful!",Color.PINK);
                }
                new GUIForPOIs(GUIForPOIs.POIsGroup);
                deletionAlert.setVisible(false);
                editor.dispose();
            });

            deletionAlertPanel.add(confirm);
            deletionAlertPanel.add(cancel);
            deletionAlertPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            deletionAlert.add(deletionAlertPanel);
            deletionAlert.pack();
            deletionAlert.setAlwaysOnTop(true);
            deletionAlert.setFocusableWindowState(false);
            deletionAlert.setFocusable(false);
            deletionAlert.setVisible(true);
        });

        if (!Main.devMode) {
            if (Data.containsPOI(Data.builtInPOIs,poi)) {
                deleteButton.setEnabled(false);
                deleteButton.setBackground(new Color(200, 200, 200));
                deleteButton.setForeground(new Color(20, 20, 20));
                POINameField.setEditable(false);
                POIRoomNumberField.setEditable(false);
                POIFloorField.setEditable(false);
                POIBuildingField.setEditable(false);
                POICategoryField.setEditable(false);
                POIDescriptionField.setEditable(false);
                if (!bookmarkAdd.isSelected() || !bookmarkRemove.isSelected()) {
                    saveButton.setEnabled(false);
                    saveButton.setBackground(new Color(200, 200, 200));
                    saveButton.setForeground(new Color(20, 20, 20));
                    bookmarkAdd.addItemListener(e -> {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            saveButton.setEnabled(true);
                            saveButton.setForeground(new Color(255, 255, 255));
                            saveButton.setBackground(new Color(0, 90, 181));
                        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                            saveButton.setEnabled(false);
                            saveButton.setBackground(new Color(200, 200, 200));
                            saveButton.setForeground(new Color(20, 20, 20));
                        }

                    });
                    bookmarkRemove.addItemListener(e -> {
                        if (poi.isBuiltIn && Data.containsPOI(Data.bookmarks, poi)) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                saveButton.setEnabled(true);
                                saveButton.setForeground(new Color(255, 255, 255));
                                saveButton.setBackground(new Color(0, 90, 181));
                            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                                saveButton.setEnabled(false);
                                saveButton.setBackground(new Color(200, 200, 200));
                                saveButton.setForeground(new Color(20, 20, 20));
                            }
                        }
                    });

                }
            }
            else {
                POINameField.setEditable(true);
                POINameField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        POINameField.setText("");
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                });
                POIRoomNumberField.setEditable(false);
                POIRoomNumberField.setEnabled(false);
                POIRoomNumberLabel.setEnabled(false);
                POIFloorField.setEditable(false);
                POIBuildingField.setEditable(false);
                POICategoryField.setEditable(false);
                POIDescriptionField.setEditable(true);
                POIDescriptionField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        POIDescriptionField.setText("");
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                });
                if (Data.containsPOI(Data.userCreatedPOIs, poi)) {
                    deleteButton.setForeground(new Color(255, 255, 255));
                    deleteButton.setBackground(new Color(220, 50, 32));
                }
                else {
                    deleteButton.setEnabled(false);
                    deleteButton.setBackground(new Color(200, 200, 200));
                    deleteButton.setForeground(new Color(20, 20, 20));
                }
            }
        }

        else {
            POIFloorField.setEditable(false);
            POIBuildingField.setEditable(false);
            notABookmark.setText("© 2023 BuildingBuddy");
            notABookmark.setForeground(Color.BLACK);
            bookmarkAdd.setText(" Team 14 at UWO");
            bookmarkAdd.setForeground(Color.WHITE);
            notABookmark.setEnabled(false);
            bookmarkAdd.setEnabled(false);
            isABookmark.setText("© 2023 BuildingBuddy");
            isABookmark.setForeground(Color.BLACK);
            bookmarkRemove.setText(" Team 14 at UWO");
            bookmarkRemove.setForeground(Color.WHITE);
            isABookmark.setEnabled(false);
            bookmarkRemove.setEnabled(false);
            if (!Data.containsPOI(Data.builtInPOIs,poi)) {
                deleteButton.setEnabled(false);
                deleteButton.setBackground(new Color(200, 200, 200));
                deleteButton.setForeground(new Color(20, 20, 20));
            }
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
        editor.add(scrollPane);


        // Create a JButton to close the dialog
        JButton closeButton = new JButton("Cancel");
        closeButton.addActionListener(e -> {
            editor.dispose();
            POISelector.focus = poi;
            Main.updateCurrent(poi);
            if (GUI.frame.getContentPane() == (GUIForPOIs.secondary))
                GUIForPOIs.mapView.highlight(poi.positionX, poi.positionY, "OFF");
            else if (GUI.frame.getContentPane() == (GUI.canvas))
                GUI.mapView.highlight(poi.positionX, poi.positionY, "OFF");
        });
        closeButton.setFocusTraversalKeysEnabled(true);
        editor.getRootPane().setDefaultButton(closeButton);
        editor.add(closeButton, BorderLayout.SOUTH);

        // Pack the JDialog
        editor.pack();

        // Set the location of the JDialog to the center of the screen
        editor.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // Center the window on the screen
                editor.setLocationRelativeTo(null);
            }
        });

        // Display the JDialog
        editor.setVisible(true);

        editor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                editor.dispose();
                if (MapView.currentHighlighted != null) {
                    if (GUI.frame.getContentPane().equals(GUIForPOIs.secondary)) {
                        GUIForPOIs.mapView.highlight(MapView.currentHighlighted.positionX, MapView.currentHighlighted.positionY, "OFF");
                    }
                    else if (GUI.frame.getContentPane().equals(GUI.canvas)) {
                        GUI.mapView.highlight(MapView.currentHighlighted.positionX, MapView.currentHighlighted.positionY, "OFF");
                    }
                }
            }
        });
    }

    public static void resultDisplay(String text, Color color) {
        deletionAlert.dispose();
        JWindow result = new JWindow();
        result.setSize(400, 100);
        result.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // Center the window on the screen
                result.setLocationRelativeTo(null);
            }
        });
        JLabel message = new JLabel(text);
        JPanel panel = new JPanel();
        message.setFont(new Font("Arial", BOLD,24));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(color);
        panel.add(message);
        result.add(panel);
        result.pack();
        result.setAlwaysOnTop(true);
        result.setVisible(true);
        Timer timer = new Timer(1000, e5 -> result.dispose());
        timer.setRepeats(false);
        timer.start();
    }
}