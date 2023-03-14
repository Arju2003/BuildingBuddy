package ca.uwo.csteam14;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

public class GUI {
    protected static JFrame frame = new JFrame("BuildingBuddy (Beta)");
    private final AppMenu appMenu = new AppMenu();
    protected static Container primary;

    protected static JLabel floorName = new JLabel();

    protected static JLabel buildingName = new JLabel();

    protected MapView map;



    public GUI(String buildingCode) {
        BuildingBuddy.currentBuildingCode = buildingCode;
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }

            try {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setJMenuBar(appMenu.load());
                /* A split screen to show map and layer filter on the left side*/
                /* Show the correct background picture and building name */
                switch (buildingCode) {
                    case "KB" -> {
                        primary = new Container("./images/KB_hero.png");
                        buildingName.setText("Kresge Building (KB)");
                        map = new MapView("./maps/"+BuildingBuddy.currentFloor_KB+".png", BuildingBuddy.getOptimumPoint(buildingCode));
                        primary.load(map.loadMapViewer(), 'R');
                    }
                    case "MC" -> {
                        primary = new Container("./images/MC_hero.png");
                        buildingName.setText("Middlesex College (MC)");
                        map = new MapView("./maps/"+BuildingBuddy.currentFloor_MC+".png", BuildingBuddy.getOptimumPoint(buildingCode));
                        primary.load(map.loadMapViewer(), 'R');
                    }
                    case "PAB" -> {
                        primary = new Container("./images/PAB_hero.png");
                        buildingName.setText("Physics & Astronomy Building (PAB)");
                        map = new MapView("./maps/"+BuildingBuddy.currentFloor_PAB+".png", BuildingBuddy.getOptimumPoint(buildingCode));
                        primary.load(map.loadMapViewer(), 'R');

                    }
                }

                buildingName = new JLabel(getBuildingName((buildingCode)));
                floorName = new JLabel(getFloorName(BuildingBuddy.getCurrentFloor(buildingCode)) + " ➔");
                // Set the font size and style
                Font title = new Font("Arial", Font.BOLD, 48);
                buildingName.setFont(title);
                Font subtitle = new Font("Arial", Font.BOLD, 32);
                floorName.setFont(subtitle);
                // Set the foreground color
                Color yellow = new Color(255, 255, 0);
                Color red = new Color(0, 200 ,180);
                Color background = new Color(0,0,0, 0.25f);
                padding(buildingName);
                padding(floorName);
                buildingName.setForeground(red);
                floorName.setForeground(yellow);
                buildingName.setOpaque(true);
                floorName.setOpaque(true);
                buildingName.setBackground(background);
                floorName.setBackground(background);
                primary.load(buildingName,'L');
                primary.load(floorName,'L');

                String[] layerList = {"🏊 Bookmarks", "🏫 Classrooms","🧪 Labs","💻 CompSci Spots",
                        "🍴 Restaurants", "🛗 Stairwell / Elevators","🚪 Entrances / Exits", "\uD83D\uDCCD My Locations","🚽 Washrooms","♿️ Accessibility"};
                LayerFilter layerFilter = new LayerFilter("", layerList);
                if (!buildingCode.contains("MC")) layerFilter.hideCheckbox("CompSci Spots");
                primary.load(layerFilter.load(), 'L');

                /* make a floor selector and add a button */
                ArrayList<String> floorSet = new ArrayList<>();
                floorSet.add("Ground Floor");
                floorSet.add("First Floor");
                floorSet.add("Second Floor");
                floorSet.add("Third Floor");
                if (buildingCode.contains("MC")) floorSet.add("Fourth Floor");
                String[] floors = floorSet.toArray(new String[0]);
                JComboBox<? extends String> floorSelector = new JComboBox<>(floors);
                floorSelector.setBounds(450, 300, 200, 30);

                switch (buildingCode) {
                    case "MC" -> floorSelector.setSelectedItem(getFloorName(BuildingBuddy.currentFloor_MC));
                    case "KB" -> floorSelector.setSelectedItem(getFloorName(BuildingBuddy.currentFloor_KB));
                    case "PAB" -> floorSelector.setSelectedItem(getFloorName(BuildingBuddy.currentFloor_PAB));
                }

                primary.load(floorSelector, 'L');
                JButton goToButton = new JButton("Take Me There");
                goToButton.addActionListener(e -> {
                    // Get the selected item in the dropdown list
                    String selectedItem = (String) floorSelector.getSelectedItem();

                    // Navigate to the corresponding building
                    switch (Objects.requireNonNull(selectedItem)) {
                        case "Ground Floor" -> {
                            switch (buildingCode) {
                                case "MC" -> {
                                    BuildingBuddy.currentFloor_MC = "MC0F";
                                    map = new MapView("./maps/MC0F.png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                                    BuildingBuddy.currentFloor = "MC0F";
                                }
                                case "KB" -> {
                                    BuildingBuddy.currentFloor_KB = "KB0F";
                                    map = new MapView("./maps/KB0F.png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                                    BuildingBuddy.currentFloor = "KB0F";
                                }
                                case "PAB" -> {
                                    BuildingBuddy.currentFloor_PAB = "PAB0F";
                                    map = new MapView("./maps/PAB0F.png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                                    BuildingBuddy.currentFloor = "PAB0F";
                                }
                            }
                        }
                        case "First Floor" -> {
                            switch (buildingCode) {
                                case "MC" -> {
                                    BuildingBuddy.currentFloor_MC = "MC1F";
                                    map = new MapView("./maps/MC1F.png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                                    BuildingBuddy.currentFloor = "MC1F";
                                }
                                case "KB" -> {
                                    BuildingBuddy.currentFloor_KB = "KB1F";
                                    map = new MapView("./maps/KB1F.png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                                    BuildingBuddy.currentFloor = "KB1F";
                                }
                                case "PAB" -> {
                                    BuildingBuddy.currentFloor_PAB = "PAB1F";
                                    map = new MapView("./maps/PAB1F.png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                                    BuildingBuddy.currentFloor = "PAB1F";

                                }
                            }
                        }
                        case "Second Floor" -> {
                            switch (buildingCode) {
                                case "MC" -> {
                                    BuildingBuddy.currentFloor_MC = "MC2F";
                                    map = new MapView("./maps/MC2F.png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                                    BuildingBuddy.currentFloor = "MC2F";
                                }
                                case "KB" -> {
                                    BuildingBuddy.currentFloor_KB = "KB2F";
                                    map = new MapView("./maps/KB2F.png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                                    BuildingBuddy.currentFloor = "KB2F";
                                }
                                case "PAB" -> {
                                    BuildingBuddy.currentFloor_PAB = "PAB2F";
                                    map = new MapView("./maps/PAB2F.png", BuildingBuddy.getOptimumPoint(BuildingBuddy.currentBuildingCode));
                                    BuildingBuddy.currentFloor = "PAB2F";
                                }
                            }
                        }
                        case "Third Floor" -> {
                            switch (buildingCode) {
                                case "MC" -> {
                                    BuildingBuddy.currentFloor_MC = "MC3F";
                                    map = new MapView("./maps/MC3F.png", BuildingBuddy.getOptimumPoint(buildingCode));
                                    BuildingBuddy.currentFloor = "MC3F";
                                }
                                case "KB" -> {
                                    BuildingBuddy.currentFloor_KB = "KB3F";
                                    map = new MapView("./maps/KB3F.png", BuildingBuddy.getOptimumPoint(buildingCode));
                                    BuildingBuddy.currentFloor = "KB3F";
                                }
                                case "PAB" -> {
                                    BuildingBuddy.currentFloor_PAB = "PAB3F";
                                    map = new MapView("./maps/PAB3F.png", BuildingBuddy.getOptimumPoint(buildingCode));
                                    BuildingBuddy.currentFloor = "PAB3F";
                                }
                            }
                        }
                        case "Fourth Floor" -> {
                            if ("MC".equals(buildingCode)) {
                                BuildingBuddy.currentFloor_MC = "MC4F";
                                map = new MapView("./maps/MC4F.png", BuildingBuddy.getOptimumPoint(buildingCode));
                                BuildingBuddy.currentFloor = "MC4F";
                            }
                        }
                    }

                    primary.setVisible(false);
                    floorName.setText(getFloorName(BuildingBuddy.getCurrentFloor(buildingCode))+" ➔");
                    primary.replaceWith(map.loadMapViewer(), 'R');
                    primary.setVisible(true);
                });
                primary.load(goToButton, 'L');
                frame.setContentPane(primary);
                frame.pack();
                frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
                frame.setVisible(true);

            } catch (IOException exp) {
                exp.printStackTrace();
            }

        });
    }

    public static String getFloorName(String currentFloor) {
        if (currentFloor.contains("0F")) return "Ground Floor";
        else if (currentFloor.contains("1F")) return "First Floor";
        else if (currentFloor.contains("2F")) return "Second Floor";
        else if (currentFloor.contains("3F")) return "Third Floor";
        else if (currentFloor.contains("4F")) return "Fourth Floor";
        return "";
    }

    public static String getBuildingName(String currentBuilding) {
        switch (currentBuilding) {
            case ("MC") -> {
                return "Middlesex College";
            }
            case ("KB") -> {
                return "Kresge Building";
            }
            case ("PAB") -> {
                return "Physics & Astronomy Building";
            }
        }
        return "";
    }

    public void padding(JLabel label) {
        label.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 70));
        // Set the preferred size of the JLabel to include the padding
        Dimension size = label.getPreferredSize();
        size.width += 10;
        size.height += 5;
        label.setPreferredSize(size);
    }
}