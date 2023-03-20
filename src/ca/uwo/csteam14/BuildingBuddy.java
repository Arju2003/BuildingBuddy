package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BuildingBuddy {

    protected static Data userData;

    protected static Data builtinData;

    protected static Data bookmarksData;
    protected static String currentBuildingCode;
    protected static String currentFloor;
    protected static String currentFloor_MC;
    protected static String currentFloor_KB;
    protected static String currentFloor_PAB;
    protected static String version = "You're running the latest version (Ver 1.0) of BuildingBuddy!";

    public static ListCellRenderer<? super String> centerRenderer;

    public static Point getOptimumPoint(String buildingCode) {
        switch (buildingCode){
            case ("MC") -> {
                return new Point(1700,350);
            }
            case ("KB") -> {
                return new Point(5000, 700);
            }
            case ("PAB") -> {
                return new Point(900,800);
            }
        }
        return new Point(1700, 1100);
    }

    public static String getCurrentFloor(String currentBuildingCode) {
        switch (currentBuildingCode) {
            case ("MC") -> {
                return currentFloor_MC;
            }
            case ("KB") -> {
                return currentFloor_KB;
            }
            case ("PAB") -> {
                return currentFloor_PAB;
            }
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        currentBuildingCode = "MC";
        currentFloor_MC = "MC0F";
        currentFloor_KB = "KB0F";
        currentFloor_PAB = "PAB0F";
        currentFloor = currentFloor_MC;
        centerRenderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        };

        new Splash("./images/"+currentBuildingCode+"_hero.png").build();
    }
}
