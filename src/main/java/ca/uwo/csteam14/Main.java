/**
 * @author Jason, Daniel, Arjuna, Bobby, Josh
 * Main Class
 * Run BuildingBuddy, ensure events occur when user interacts with application
 */

package ca.uwo.csteam14;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class Main {

    protected static boolean devMode;
    private static char[] securityKey;
    protected static String currentBuildingCode;
    protected static String currentFloor;
    protected static String currentFloor_MC;
    protected static String currentFloor_KB;
    protected static String currentFloor_PAB;
    protected static String version = "You're running the latest version (Ver 1.0) of BuildingBuddy!";
    protected static POI fallbackPOI = new POI(-1);


    public static ListCellRenderer<? super String> centerRenderer;

    /**
     * @param buildingCode
     * @return
     */
    public static Point getOptimumPoint(String buildingCode) {
        switch (buildingCode){
            case ("MC") -> {
                return new Point(1700,700);
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


    /**
     * @param floorMapName
     * @return
     */
    public static String getFloorFullName(String floorMapName) {
        if (floorMapName.contains("0F")) return "Ground Floor";
        else if (floorMapName.contains("1F")) return "First Floor";
        else if (floorMapName.contains("2F")) return "Second Floor";
        else if (floorMapName.contains("3F")) return "Third Floor";
        else if (floorMapName.contains("4F")) return "Fourth Floor";
        return "Unknown";
    }

    /**
     * @param floorMapName
     * @return
     */
    public static String getBuildingFullName(String floorMapName) {
        if (floorMapName.contains("MC")) return "Middlesex College";
        else if (floorMapName.contains("KB")) return "Kresge Building";
        else if (floorMapName.contains("PAB")) return "Physics & Astronomy Building";
        return "Unknown";
    }


    public static void restartApplication() throws IOException {
        String javaCommand = System.getProperty("java.home") + "/bin/java";
        String javaClassPath = System.getProperty("java.class.path");
        String className = Main.class.getName();
        String[] command = { javaCommand, "-cp", javaClassPath, className };
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.start();
        System.exit(0);
    }

    public static void updateCurrent(POI poi) {
        if (poi != null) {
            Main.currentBuildingCode = poi.map.replaceAll("\\dF.png","").toUpperCase();
            Main.currentFloor = poi.map.replaceAll(".png","").toUpperCase();
        }

    }

    public static char[] getSecurityKey() {

            String fileName = "./data/security_key";

            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    securityKey = line.toCharArray();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return securityKey;
    }

    public static void changeSecurityKey(char[] newKey) {

        String fileName = "./data/security_key";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            String sk = new String(newKey);
            bw.write(sk);
            securityKey = newKey;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        devMode = false;
        new Data();
        if (currentFloor_MC == null) currentFloor_MC = "MC0F";
        if (currentFloor_KB == null) currentFloor_KB = "KB0F";
        if (currentFloor_PAB == null) currentFloor_PAB = "PAB0F";
        if (currentFloor == null) currentFloor = currentFloor_MC;
        if (currentBuildingCode == null) currentBuildingCode = "MC";

        fallbackPOI.id=719;
        fallbackPOI.name = "Grad Club";
        fallbackPOI.building = "Middlesex College";
        fallbackPOI.code = "MC";
        fallbackPOI.floor = "Ground Floor";
        fallbackPOI.roomNumber = 19;
        fallbackPOI.category = "Restaurant";
        fallbackPOI.description = "The Grad Club is a non-profit graduate student pub & eatery.";
        fallbackPOI.map = "MC0F.png";
        fallbackPOI.positionX = 1717;
        fallbackPOI.positionY = 628;
        fallbackPOI.isBuiltIn = true;

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
