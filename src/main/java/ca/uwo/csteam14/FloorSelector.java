/**
 * @author Jason
 * FloorSelector Class
 * Methods for selecting a user's desired floor depending on their selected building
 */

package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FloorSelector {

    protected static MapView currentMap;

    /**
     *
     */
    public FloorSelector() {
        /* make a floor selector and add a button */
        ArrayList<String> floorSet = new ArrayList<>();
        floorSet.add("Ground Floor");
        floorSet.add("First Floor");
        floorSet.add("Second Floor");
        floorSet.add("Third Floor");
        if (Main.currentBuildingCode.contains("MC")) floorSet.add("Fourth Floor");
        String[] floors = floorSet.toArray(new String[0]);
        JComboBox<? extends String> floorSelector = new JComboBox<>(floors);
        floorSelector.setPreferredSize(new Dimension(200, 60));
        floorSelector.setBounds(450, 300, 250, 50);
        floorSelector.setFont(new Font("Arial", Font.PLAIN, 18));
        floorSelector.setRenderer(Main.centerRenderer);
        switch (Main.currentBuildingCode ) {
            case "MC" -> floorSelector.setSelectedItem(getFloorName(Main.currentFloor_MC));
            case "KB" -> floorSelector.setSelectedItem(getFloorName(Main.currentFloor_KB));
            case "PAB" -> floorSelector.setSelectedItem(getFloorName(Main.currentFloor_PAB));
        }

        GUI.canvas.load(floorSelector, 'L');
        floorSelector.addActionListener(e -> {
            // Get the selected item in the dropdown list
            String selectedItem = (String) floorSelector.getSelectedItem();

            // Navigate to the corresponding building
            switch (Objects.requireNonNull(selectedItem)) {
                case "Ground Floor" -> {
                    switch (Main.currentBuildingCode ) {
                        case "MC" -> {
                            Main.currentFloor_MC = "MC0F";
                            currentMap = new MapView("MC0F.png", Main.getOptimumPoint("MC"));
                            Main.currentFloor = "MC0F";
                        }
                        case "KB" -> {
                            Main.currentFloor_KB = "KB0F";
                            currentMap = new MapView("KB0F.png", Main.getOptimumPoint("KB"));
                            Main.currentFloor = "KB0F";
                        }
                        case "PAB" -> {
                            Main.currentFloor_PAB = "PAB0F";
                            currentMap = new MapView("PAB0F.png", Main.getOptimumPoint("PAB"));
                            Main.currentFloor = "PAB0F";
                        }
                    }
                }
                case "First Floor" -> {
                    switch (Main.currentBuildingCode ) {
                        case "MC" -> {
                            Main.currentFloor_MC = "MC1F";
                            currentMap = new MapView("MC1F.png", Main.getOptimumPoint("MC"));
                            Main.currentFloor = "MC1F";
                        }
                        case "KB" -> {
                            Main.currentFloor_KB = "KB1F";
                            currentMap = new MapView("KB1F.png", Main.getOptimumPoint("KB"));
                            Main.currentFloor = "KB1F";
                        }
                        case "PAB" -> {
                            Main.currentFloor_PAB = "PAB1F";
                            currentMap = new MapView("PAB1F.png", Main.getOptimumPoint("PAB"));
                            Main.currentFloor = "PAB1F";

                        }
                    }
                }
                case "Second Floor" -> {
                    switch (Main.currentBuildingCode ) {
                        case "MC" -> {
                            Main.currentFloor_MC = "MC2F";
                            currentMap = new MapView("MC2F.png", Main.getOptimumPoint("MC"));
                            Main.currentFloor = "MC2F";
                        }
                        case "KB" -> {
                            Main.currentFloor_KB = "KB2F";
                            currentMap = new MapView("KB2F.png", Main.getOptimumPoint("KB"));
                            Main.currentFloor = "KB2F";
                        }
                        case "PAB" -> {
                            Main.currentFloor_PAB = "PAB2F";
                            currentMap = new MapView("PAB2F.png", Main.getOptimumPoint("PAB"));
                            Main.currentFloor = "PAB2F";
                        }
                    }
                }
                case "Third Floor" -> {
                    switch (Main.currentBuildingCode ) {
                        case "MC" -> {
                            Main.currentFloor_MC = "MC3F";
                            currentMap = new MapView("MC3F.png", Main.getOptimumPoint("MC"));
                            Main.currentFloor = "MC3F";
                        }
                        case "KB" -> {
                            Main.currentFloor_KB = "KB3F";
                            currentMap = new MapView("KB3F.png", Main.getOptimumPoint("KB"));
                            Main.currentFloor = "KB3F";
                        }
                        case "PAB" -> {
                            Main.currentFloor_PAB = "PAB3F";
                            currentMap = new MapView("PAB3F.png", Main.getOptimumPoint("PAB"));
                            Main.currentFloor = "PAB3F";
                        }
                    }
                }
                case "Fourth Floor" -> {
                    if ("MC".equals(Main.currentBuildingCode )) {
                        Main.currentFloor_MC = "MC4F";
                        currentMap = new MapView("MC4F.png", Main.getOptimumPoint("MC"));
                        Main.currentFloor = "MC4F";
                    }
                }
            }

            GUI.canvas.setVisible(false);
            try {
                LayerFilter.refreshLayers();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            GUI.canvas.setVisible(true);
        });
    }

    /**
     * @param currentFloor
     * @return
     */
    public static String getFloorName(String currentFloor) {
        if (currentFloor.contains("0F")) return "Ground Floor";
        else if (currentFloor.contains("1F")) return "First Floor";
        else if (currentFloor.contains("2F")) return "Second Floor";
        else if (currentFloor.contains("3F")) return "Third Floor";
        else if (currentFloor.contains("4F")) return "Fourth Floor";
        return "";
    }
}
