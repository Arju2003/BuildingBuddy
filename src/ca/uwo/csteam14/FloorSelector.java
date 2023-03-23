package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FloorSelector {

    protected static MapView currentMap = GUI.getMap();
    public FloorSelector() {
        /* make a floor selector and add a button */
        ArrayList<String> floorSet = new ArrayList<>();
        floorSet.add("Ground Floor");
        floorSet.add("First Floor");
        floorSet.add("Second Floor");
        floorSet.add("Third Floor");
        if (BuildingBuddy.currentBuildingCode.contains("MC")) floorSet.add("Fourth Floor");
        String[] floors = floorSet.toArray(new String[0]);
        JComboBox<? extends String> floorSelector = new JComboBox<>(floors);
        floorSelector.setPreferredSize(new Dimension(200, 60));
        floorSelector.setBounds(450, 300, 250, 50);
        floorSelector.setFont(new Font("Arial", Font.PLAIN, 18));
        floorSelector.setRenderer(BuildingBuddy.centerRenderer);
        switch (BuildingBuddy.currentBuildingCode ) {
            case "MC" -> floorSelector.setSelectedItem(getFloorName(BuildingBuddy.currentFloor_MC));
            case "KB" -> floorSelector.setSelectedItem(getFloorName(BuildingBuddy.currentFloor_KB));
            case "PAB" -> floorSelector.setSelectedItem(getFloorName(BuildingBuddy.currentFloor_PAB));
        }

        GUI.primary.load(floorSelector, 'L');
        floorSelector.addActionListener(e -> {
            // Get the selected item in the dropdown list
            String selectedItem = (String) floorSelector.getSelectedItem();

            // Navigate to the corresponding building
            switch (Objects.requireNonNull(selectedItem)) {
                case "Ground Floor" -> {
                    switch (BuildingBuddy.currentBuildingCode ) {
                        case "MC" -> {
                            BuildingBuddy.currentFloor_MC = "MC0F";
                            currentMap = new MapView("./maps/MC0F.png", BuildingBuddy.getOptimumPoint("MC"));
                            BuildingBuddy.currentFloor = "MC0F";
                        }
                        case "KB" -> {
                            BuildingBuddy.currentFloor_KB = "KB0F";
                            currentMap = new MapView("./maps/KB0F.png", BuildingBuddy.getOptimumPoint("KB"));
                            BuildingBuddy.currentFloor = "KB0F";
                        }
                        case "PAB" -> {
                            BuildingBuddy.currentFloor_PAB = "PAB0F";
                            currentMap = new MapView("./maps/PAB0F.png", BuildingBuddy.getOptimumPoint("PAB"));
                            BuildingBuddy.currentFloor = "PAB0F";
                        }
                    }
                }
                case "First Floor" -> {
                    switch (BuildingBuddy.currentBuildingCode ) {
                        case "MC" -> {
                            BuildingBuddy.currentFloor_MC = "MC1F";
                            currentMap = new MapView("./maps/MC1F.png", BuildingBuddy.getOptimumPoint("MC"));
                            BuildingBuddy.currentFloor = "MC1F";
                        }
                        case "KB" -> {
                            BuildingBuddy.currentFloor_KB = "KB1F";
                            currentMap = new MapView("./maps/KB1F.png", BuildingBuddy.getOptimumPoint("KB"));
                            BuildingBuddy.currentFloor = "KB1F";
                        }
                        case "PAB" -> {
                            BuildingBuddy.currentFloor_PAB = "PAB1F";
                            currentMap = new MapView("./maps/PAB1F.png", BuildingBuddy.getOptimumPoint("PAB"));
                            BuildingBuddy.currentFloor = "PAB1F";

                        }
                    }
                }
                case "Second Floor" -> {
                    switch (BuildingBuddy.currentBuildingCode ) {
                        case "MC" -> {
                            BuildingBuddy.currentFloor_MC = "MC2F";
                            currentMap = new MapView("./maps/MC2F.png", BuildingBuddy.getOptimumPoint("MC"));
                            BuildingBuddy.currentFloor = "MC2F";
                        }
                        case "KB" -> {
                            BuildingBuddy.currentFloor_KB = "KB2F";
                            currentMap = new MapView("./maps/KB2F.png", BuildingBuddy.getOptimumPoint("KB"));
                            BuildingBuddy.currentFloor = "KB2F";
                        }
                        case "PAB" -> {
                            BuildingBuddy.currentFloor_PAB = "PAB2F";
                            currentMap = new MapView("./maps/PAB2F.png", BuildingBuddy.getOptimumPoint("PAB"));
                            BuildingBuddy.currentFloor = "PAB2F";
                        }
                    }
                }
                case "Third Floor" -> {
                    switch (BuildingBuddy.currentBuildingCode ) {
                        case "MC" -> {
                            BuildingBuddy.currentFloor_MC = "MC3F";
                            currentMap = new MapView("./maps/MC3F.png", BuildingBuddy.getOptimumPoint("MC"));
                            BuildingBuddy.currentFloor = "MC3F";
                        }
                        case "KB" -> {
                            BuildingBuddy.currentFloor_KB = "KB3F";
                            currentMap = new MapView("./maps/KB3F.png", BuildingBuddy.getOptimumPoint("KB"));
                            BuildingBuddy.currentFloor = "KB3F";
                        }
                        case "PAB" -> {
                            BuildingBuddy.currentFloor_PAB = "PAB3F";
                            currentMap = new MapView("./maps/PAB3F.png", BuildingBuddy.getOptimumPoint("PAB"));
                            BuildingBuddy.currentFloor = "PAB3F";
                        }
                    }
                }
                case "Fourth Floor" -> {
                    if ("MC".equals(BuildingBuddy.currentBuildingCode )) {
                        BuildingBuddy.currentFloor_MC = "MC4F";
                        currentMap = new MapView("./maps/MC4F.png", BuildingBuddy.getOptimumPoint("MC"));
                        BuildingBuddy.currentFloor = "MC4F";
                    }
                }
            }

            GUI.primary.setVisible(false);
            GUI.primary.replaceWith(currentMap.loadMapViewer(), 'R');
            try {
                LayerFilter.refreshLayers();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            GUI.primary.setVisible(true);
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
}
