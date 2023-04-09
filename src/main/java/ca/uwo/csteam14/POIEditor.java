package ca.uwo.csteam14;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import static java.awt.Font.BOLD;
import static javax.swing.SwingConstants.*;

/**
 * This class represents an editor for adding, modifying, bookmarking, and deleting POIs.
 *
 * @author Jason B. Shew
 * @version 1.0
 * @since 2023-03-07
 */
public class POIEditor extends JDialog {

    /** The editor implemented as a JDialog object. */
    private final JDialog editor = new JDialog();

    /** A boolean value to detect if a POI is saved after editing. */
    protected static boolean isSaved = false;

    /** An alert shown for uses to confirm deletion, implemented as a JWindow object. */
    protected static JWindow deletionAlert = new JWindow();


    /**
     * Constructs a POI editor object
     * @param poi The POI object to be added, edited, bookmarked, or deleted.
     */
    public POIEditor(POI poi) {
        AppMenu.clearWindows(); // Closes all floating windows.
        UIManager.put("TextArea.font", new Font("Arial", Font.PLAIN, 16));
        // Determines the title of the editor depending on the nature of a POI
        if (poi.name.length() > 0)
            editor.setTitle(poi.name);
        else editor.setTitle("New Location");
        editor.setResizable(false);
        editor.setModalityType(ModalityType.MODELESS);

        // Creates the editor's main panel (halved into left and right panels) and adds components
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

        // Sets the labels and alignment on the left hand side
        JLabel POINameLabel = new JLabel("Location Name");
        JLabel POIRoomNumberLabel = new JLabel("Room Number");
        JLabel POIFloorLabel = new JLabel("Floor");
        JLabel POIBuildingLabel = new JLabel("Building");
        JLabel POICategoryLabel = new JLabel("Category");
        JLabel POIDescriptionLabel = new JLabel("Description");
        JLabel leftPanelPadding = new JLabel("");
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
        leftPanel.add(leftPanelPadding, toTheRight);
        // Depending on the status of bookmarks, shows either Add Bookmark or Remove Bookmark
        if (!Data.containsPOI(Data.bookmarks,poi)) {
            leftPanel.add(notABookmark, toTheRight);
            leftPanel.add(bookmarkAdd, toTheRight);

        }
        else {
            leftPanel.add(isABookmark, toTheRight);
            leftPanel.add(bookmarkRemove, toTheRight);

        }

        //  Sets the labels and alignment on the right hand side
        JTextField POINameField = new JTextField(poi.name);
        JTextField POIRoomNumberField = new JTextField(String.valueOf(poi.roomNumber));
        JTextField POIFloorField = new JTextField(poi.floor);
        JTextField POIBuildingField = new JTextField(poi.building);
        JTextField POICategoryField = new JTextField(poi.category);
        JTextArea POIDescriptionArea = new JTextArea(poi.description);
        JScrollPane POIDescriptionScrollPane = new JScrollPane(POIDescriptionArea); // wrap the text area in a scroll pane
        POIDescriptionScrollPane.setPreferredSize(new Dimension(198,160));
        JLabel rightPanelPadding = new JLabel("");
        JButton saveButton = new JButton("Save Changes");
        JButton deleteButton = new JButton("Delete Location");
        rightPanel.add(POINameField, toTheLeft);
        rightPanel.add(POIRoomNumberField, toTheLeft);
        rightPanel.add(POIFloorField, toTheLeft);
        rightPanel.add(POIBuildingField, toTheLeft);
        rightPanel.add(POICategoryField, toTheLeft);
        rightPanel.add(POIDescriptionScrollPane, toTheLeft);
        rightPanel.add(rightPanelPadding, toTheLeft);
        rightPanel.add(saveButton, toTheLeft);
        rightPanel.add(deleteButton, toTheLeft);


        // Stylizes the left hand side components
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

        // Since right hand side has a multiline text area, we create two JLabels to level the two panels.
        leftPanelPadding.setPreferredSize(new Dimension(1,80));
        rightPanelPadding.setPreferredSize(new Dimension(1,1));

        // Stylizes the right hand side components
        for (Component j: rightPanel.getComponents()) {
            if (j instanceof JTextField) {
                j.setPreferredSize(new Dimension(200, 40));
                ((JTextField) j).setMargin(new Insets(3, 30, 3, 0));
                ((JTextField) j).setHorizontalAlignment(LEFT);
            }
            else {
                j.setPreferredSize(new Dimension(200, 40));
            }
            j.setFont(new Font("Arial", Font.PLAIN, 14));
        }

        // Stylizes the text area for POI description
        POIDescriptionArea.setLineWrap(true);
        POIDescriptionArea.setWrapStyleWord(true);
        POIDescriptionArea.setMargin(new Insets(6, 5, 6, 0));
        POIDescriptionArea.setRows(4);
        POIDescriptionScrollPane.setPreferredSize(new Dimension(198,80));
        POIDescriptionScrollPane.setBorder(null);
        POIDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        POIDescriptionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        POIDescriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); // Shows scrollbars when needed

        // Stylizes the checkboxes and buttons
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


        // Defines behaviour of the Save button
        saveButton.addActionListener(e -> {
            boolean result = false; // a boolean value to check if saving is successful
            // Restores the appearance of the editor, when information is properly entered
            POINameLabel.setFont(new Font("Arial",Font.PLAIN, 14));
            POINameLabel.setPreferredSize(new Dimension(100, 40));
            POINameLabel.setForeground(Color.BLACK);
            POICategoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            POICategoryLabel.setPreferredSize(new Dimension(100, 40));
            POICategoryLabel.setForeground(Color.BLACK);
            POIRoomNumberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            POIRoomNumberLabel.setPreferredSize(new Dimension(100, 40));
            POIRoomNumberLabel.setForeground(Color.BLACK);

            // In Dev Mode, Save button will result in saving more information into a POI
            if (Main.devMode) {
                // POI name must not be empty, POI category must be legit, and POI room number must be a positive integer
                if (POINameField.getText().length() > 0 && POI.hasLegalCategory(POICategoryField.getText()) && POI.isInteger(POIRoomNumberField.getText()) && Integer.parseInt(POIRoomNumberField.getText()) >= 0) {
                    poi.roomNumber = Integer.parseInt(POIRoomNumberField.getText());
                    poi.category = POICategoryField.getText();
                    poi.description = POIDescriptionArea.getText();
                    poi.name = POINameField.getText();
                    result = Data.addPOI(poi, Data.builtInPOIs);
                    // If the POI is also bookmarked, then updates bookmark
                    if (Data.containsPOI(Data.bookmarks,poi))
                        Data.addPOI(poi, Data.bookmarks);
                }
                else { // If any piece of POI information is not correct, make that label red as a hint
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
                    if (!POI.isInteger(POIRoomNumberField.getText()) || Integer.parseInt(POIRoomNumberField.getText()) < 0 ) {
                        POIRoomNumberLabel.setFont(new Font("Arial", BOLD, 14));
                        POIRoomNumberLabel.setPreferredSize(new Dimension(110, 40));
                        POIRoomNumberLabel.setForeground(Color.RED);
                    }
                }
            }
            else { // For regular users
                if (!poi.isBuiltIn) { // For user-created POIs (My Locations)
                    if (POINameField.getText().length() != 0) { // POI name must not be empty, otherwise prompts the user
                        poi.description = POIDescriptionArea.getText();
                        poi.name = POINameField.getText();
                        result = Data.addPOI(poi, Data.userCreatedPOIs);

                        if (Data.containsPOI(Data.bookmarks, poi)) // If it's a bookmarked POI, update bookmarks
                            Data.addPOI(poi, Data.bookmarks); // If user opts to add or remove bookmark, do it
                        if (bookmarkAdd.isSelected()) {
                            Data.addPOI(poi, Data.bookmarks);
                        }
                        if (bookmarkRemove.isSelected()) {
                            try {
                                Data.removePOI(poi, Data.bookmarks);
                            } catch (IOException ex) {
                                resultDisplay("Uh-oh!", Color.PINK);
                            }
                        }
                    }
                    else {
                        POINameLabel.setFont(new Font("Arial", BOLD, 14));
                        POINameLabel.setPreferredSize(new Dimension(110, 40));
                        POINameLabel.setForeground(Color.RED);
                    }
                }
                else { // For built-in POIs
                    if (bookmarkAdd.isSelected()) { // Only add / remove bookmarks
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

            if (result) { // If saving is successful, then gives user a message, updates system's current POI cursors
                isSaved = true;
                resultDisplay("Saved successfully!", Color.GREEN);
                editor.dispose();
                if(GUI.frame.getContentPane() == (GUIForPOIs.secondary)) { // refreshes the map viewer
                    new GUIForPOIs(GUIForPOIs.POIsGroup);
                    try {
                        LayerFilter.showAllLayers();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if (GUI.frame.getContentPane() == (GUI.canvas)){
                    new GUI(poi.code, new Point(poi.positionX, poi.positionY));
                    try {
                        LayerFilter.refreshLayers();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
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

        // Defines behaviours of Delete button
        deleteButton.addActionListener(e -> {
            editor.setVisible(false); // hide the editor, displays the deletion confirmation window
            // This code will be executed when the button is pressed
            deletionAlert.setSize(480, 100);
            deletionAlert.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    // Center the window on the screen
                    deletionAlert.setLocationRelativeTo(null);
                }
            });
            // Stylizes the confirmation message
            JPanel deletionAlertPanel = new JPanel();
            deletionAlertPanel.setForeground(new Color(220,50,32));
            deletionAlertPanel.setForeground(new Color(255,255,255));
            JLabel message = new JLabel("Delete this location forever?");
            message.setFont(new Font("Arial",BOLD,18));
            message.setForeground(new Color(220,50,32));
            deletionAlertPanel.add(message);
            JButton cancel = new JButton("Continue Editing");
            cancel.setEnabled(true);
            cancel.addActionListener(e2-> {
                deletionAlert.setVisible(false);
                editor.setVisible(true);
            });
            JButton confirm = new JButton("Confirm Deletion");
            // Defines the behaviours of the delete button

            confirm.addActionListener(e3-> {
                boolean result; // a boolean value to check if deletion is successful
                if (Main.devMode) {  // In Dev Mode, removes built-in POIs
                    try {
                        result = Data.removePOI(POISelector.focus, Data.builtInPOIs);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try { // if a POI is bookmarks, deletes that bookmark too
                        Data.removePOI(POISelector.focus, Data.bookmarks);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else { // For regular users
                    try {
                        result = Data.removePOI(POISelector.focus, Data.userCreatedPOIs); // Deletes My Location (user-created POI)
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try { // if a POI is bookmarks, deletes that bookmark too
                        Data.removePOI(POISelector.focus, Data.bookmarks);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (result) { // If deletion is successful, then gives user a message, updates system's current POI cursor
                    resultDisplay("Successfully deleted!",Color.GREEN);
                    MapView.cancelHighlight();
                    if(GUI.frame.getContentPane() == (GUIForPOIs.secondary)) { // refreshes the map viewer
                        new GUIForPOIs(GUIForPOIs.POIsGroup);
                        try {
                            LayerFilter.showAllLayers();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else if (GUI.frame.getContentPane() == (GUI.canvas)){
                        new GUI(poi.code, new Point(poi.positionX, poi.positionY));
                        try {
                            LayerFilter.refreshLayers();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                else {
                    resultDisplay("Oops... You can't do that!",Color.PINK);
                }
                if (GUI.frame.getContentPane() == GUIForPOIs.secondary) new GUIForPOIs(GUIForPOIs.POIsGroup); // Refreshes the map viewer
                else if (GUI.frame.getContentPane() == GUI.canvas) new GUI(poi.code, new Point(poi.positionX,poi.positionY));
                deletionAlert.setVisible(false);
                editor.dispose();
            });

            deletionAlertPanel.add(confirm);
            deletionAlertPanel.add(cancel);
            deletionAlert.pack();
            deletionAlertPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            deletionAlert.add(deletionAlertPanel);
            deletionAlert.setAlwaysOnTop(true);
            deletionAlert.setFocusableWindowState(false);
            deletionAlert.setFocusable(false);
            deletionAlert.setVisible(true);
        });

        if (!Main.devMode) { // For regular users, for built-in POIs, all fields should be read-only and the Delete button is disabled
            if (Data.containsPOI(Data.builtInPOIs,poi)) {
                deleteButton.setEnabled(false);
                deleteButton.setBackground(new Color(200, 200, 200));
                deleteButton.setForeground(new Color(20, 20, 20));
                POINameField.setEditable(false);
                POIRoomNumberField.setEditable(false);
                POIFloorField.setEditable(false);
                POIBuildingField.setEditable(false);
                POICategoryField.setEditable(false);
                POIDescriptionArea.setEditable(false);
                // If Add Bookmark / Remove Bookmark is not selected, then disables Save button as well
                if (!bookmarkAdd.isSelected() || !bookmarkRemove.isSelected()) {
                    saveButton.setEnabled(false);
                    saveButton.setBackground(new Color(200, 200, 200));
                    saveButton.setForeground(new Color(20, 20, 20));
                    // If Add Bookmark / Remove Bookmark is selected, activates Save button
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
            } // For user-created POIs, only POI name and description are editable.
            else {
                POINameField.setEditable(true);
                POINameField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (POINameField.getText().contains("My Location #"))
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
                POIDescriptionArea.setEditable(true);
                POIDescriptionArea.addFocusListener(new FocusListener() { // Automatically clears placeholder text for user's convenience
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (POIDescriptionArea.getText().equals("Your description goes here."))
                            POIDescriptionArea.setText("");
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                });
                // If this POI was already created, then Delete button is enabled.
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

        else { // For developers, floor and building information is associated with a certain map, so they are always uneditable.
            POIFloorField.setEditable(false);
            POIBuildingField.setEditable(false);
            // Developers do not have access to user bookmarks, so change them into something else (copyright info display).
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

        // Adds left and right panels to the main panel, sets size, and packs.
        main.add(leftPanel);
        main.add(rightPanel);
        main.setPreferredSize(new Dimension(500, 450));
        main.setFont(new Font("Arial", Font.PLAIN, 16));
        pack();

        // Wraps it in a scroll pane
        JScrollPane scrollPane = new JScrollPane(main);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        JViewport viewport = scrollPane.getViewport();
        Point point = new Point(0, 0);
        viewport.setViewPosition(point);

        // Adds the scroll pane to the editor
        editor.add(scrollPane);


        // Creates a JButton to close the editor.
        JButton closeButton = new JButton("Cancel");
        closeButton.addActionListener(e -> {
            editor.dispose();
            POISelector.focus = poi;
            MapView.currentHighlighted = poi;
            Main.updateCurrent(poi); // Updates system's POI cursors
            MapView.cancelHighlight(); // Cancels highlighting
        });
        //closeButton.setFocusTraversalKeysEnabled(true);
        //editor.getRootPane().setDefaultButton(closeButton);
        editor.add(closeButton, BorderLayout.SOUTH);

        // Packs the editor.
        editor.pack();

        // Displays the editor.


        // Sets the location of the editor.
        editor.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // Places the editor at a proper location over the floor map.
                editor.setLocationRelativeTo(GUI.frame);
                editor.setLocation((int) (GUI.frame.getSize().width * 0.23), (int) (GUI.frame.getSize().height * 0.38));
            }
        });

        // Dev heads-up: Keep this repeated line to prevent the POI editor from flashing briefly in the top left corner!
        editor.setLocation((int) (GUI.frame.getSize().width * 0.23), (int) (GUI.frame.getSize().height * 0.38));

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

        editor.setVisible(true);
    }

    /**
     * Displays the final result of saving / editing / deleting a POI
     *
     * @param text The text to display in the deletion confirmation window.
     * @param color Sets the colour of the window background.
     */
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