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
import java.util.Arrays;

import static java.awt.Font.BOLD;
import static javax.swing.SwingConstants.*;

public class POIEditor extends JDialog {
    // Create a new JDialog with the desired title
    private final JDialog dialog = new JDialog();
    protected static boolean isSaved = false;

    JWindow alert = new JWindow();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - alert.getWidth()) / 2;
    int y = (screenSize.height - alert.getHeight()) / 2;


    /**
     * @param poi 
     */
    public POIEditor(POI poi) {
        AppMenu.clearWindows(); // close all floating windows (the WeatherInfo window, specifically)
        UIManager.put("TextArea.font", new Font("Arial", Font.PLAIN, 16));
        if (poi.name.length() > 0)
            dialog.setTitle(poi.name);
        else dialog.setTitle("New Location");
        dialog.setResizable(false);
        dialog.setModalityType(ModalityType.MODELESS);

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

        notABookmark.setForeground(new Color(93,58,155));
        bookmarkAdd.setForeground(new Color(93,58,155));
        bookmarkAdd.setFont(new Font("Arial", BOLD,14));

        isABookmark.setForeground(new Color(230,97,0));
        bookmarkRemove.setForeground(new Color(230,97,0));
        bookmarkRemove.setFont(new Font("Arial", BOLD,14));


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

            if(Main.devMode) {
                if (POINameField.getText().length() > 0 && POI.hasLegalCategory(POICategoryField.getText()) && POI.isInteger(POIRoomNumberField.getText())) {
                    poi.roomNumber = Integer.parseInt(POIRoomNumberField.getText());
                    poi.category = POICategoryField.getText();
                    poi.description = POIDescriptionField.getText();
                    poi.name = POINameField.getText();
                    result = Data.addPOI(poi, Data.builtInPOIs);

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
                if(!Data.containsPOI(Data.userCreatedPOIs, poi) && !Data.containsPOI(Data.builtInPOIs,poi)) {
                    poi.description = POIDescriptionField.getText();
                    poi.name = POINameField.getText();
                    result = Data.addPOI(poi, Data.userCreatedPOIs);
                }
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

            if (result) {
                isSaved = true;
                resultDisplay("Saved successfully!", Color.GREEN);
                dialog.dispose();
                if(GUI.frame.getContentPane().equals(GUIForPOIs.secondary))
                    new GUIForPOIs(GUIForPOIs.POIsGroup);
                else new GUI(Main.currentBuildingCode);
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
            dialog.setVisible(false);
            // This code will be executed when the button is pressed
            alert.setSize(480, 48);
            alert.setLocation(x, y); // Set the position of the window to the center of the screen
            JPanel panel = new JPanel();
            panel.setForeground(new Color(220,50,32));
            panel.setForeground(new Color(255,255,255));
            JLabel message = new JLabel("Delete this location forever?");
            panel.add(message);
            JButton cancel = new JButton("Continue Editing");
            cancel.setEnabled(true);
            alert.setLocationRelativeTo(dialog);
            cancel.addActionListener(e2-> {
                alert.setVisible(false);
                dialog.setVisible(true);
            });
            JButton confirm = new JButton("Confirm Deletion");
            confirm.addActionListener(e3-> {
                try {
                    boolean user = Data.removePOI(poi,Data.userCreatedPOIs);
                    boolean bookmark = Data.removePOI(poi,Data.bookmarks);
                    boolean builtin = Data.removePOI(poi,Data.builtInPOIs);
                    if (user || bookmark || builtin) {
                        resultDisplay("Successfully removed!",Color.GREEN);
                    }
                    else {
                        resultDisplay("Oops... Be careful!",Color.PINK);
                    }
                    new GUIForPOIs(GUIForPOIs.POIsGroup);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);

                }
                alert.setVisible(false);
                dialog.dispose();
            });
            panel.add(confirm);
            panel.add(cancel);
            alert.add(panel);
            alert.pack();
            alert.setVisible(true);
            alert.setAlwaysOnTop(true);
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
            }
            else {
                POINameField.setEditable(true);
                POIRoomNumberField.setEditable(false);
                POIFloorField.setEditable(false);
                POIBuildingField.setEditable(false);
                POICategoryField.setEditable(false);
                POIDescriptionField.setEditable(true);
                if (Data.containsPOI(Data.userCreatedPOIs, poi)) {
                    deleteButton.setForeground(new Color(255, 255, 255));
                    deleteButton.setBackground(new Color(220, 50, 32));
                }
            }
        }

        else {
            POIFloorField.setEditable(false);
            POIBuildingField.setEditable(false);
            notABookmark.setEnabled(false);
            bookmarkAdd.setEnabled(false);
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
        dialog.add(scrollPane);

        // Create a JButton to close the dialog
        JButton closeButton = new JButton("Cancel");
        closeButton.addActionListener(e -> {
            dialog.dispose();
            if (MapView.currentHighlighted != null) {
                if (GUI.frame.getContentPane().equals(GUIForPOIs.secondary)) {
                    GUIForPOIs.mapView.highlight(MapView.currentHighlighted.positionX, MapView.currentHighlighted.positionY, "OFF");
                }
                else if (GUI.frame.getContentPane().equals(GUI.canvas)) {
                    GUI.mapView.highlight(MapView.currentHighlighted.positionX, MapView.currentHighlighted.positionY, "OFF");
                }
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
                dialog.dispose();
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

    public void resultDisplay(String text, Color color) {
        alert.dispose();
        JWindow result = new JWindow();
        result.setLocationRelativeTo(dialog);
        result.setLocation(x, y);
        JLabel message = new JLabel(text);
        JPanel panel = new JPanel();
        message.setFont(new Font("Arial", BOLD,24));
        result.setPreferredSize(new Dimension(480,48));
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