package ca.uwo.csteam14;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

/**
 * The Main class is the entry point of BuildingBuddy.
 * Contains methods for getting optimum points, full names of floors and buildings, updating current POI,
 * and changing security keys. Also has a method to restart the application.
 *
 *  @author Jason B. Shew
 *  @author Daniel Gomes
 *  @author Robert Beemer
 *  @author Arjuna Kadirgamar
 *  @author Joshua Cini
 *  @version 1.0.0
 *  @since 2023-03-07
 */

public class Main {
    /**
     * A boolean that indicates whether the application is running in developer mode.
     */
    protected static boolean devMode;

    /**
     * A character array that holds the security key used for authentication purposes.
     */
    private static char[] securityKey;

    /**
     * A string that holds the code for the current building being displayed.
     */
    protected static String currentBuildingCode;

    /**
     * A string that holds the code for the current floor being displayed.
     */
    protected static String currentFloor;

    /**
     * A string that holds the code for the current floor being displayed in the McIntyre Medical Building.
     */
    protected static String currentFloor_MC;

    /**
     * A string that holds the code for the current floor being displayed in the Kirkbride Building.
     */
    protected static String currentFloor_KB;

    /**
     * A string that holds the code for the current floor being displayed in the Purvis Hall and Armes Building.
     */
    protected static String currentFloor_PAB;

    /**
     * A string that holds the current version of the application.
     */
    protected static String currentAppVersion = "1.0";

    /**
     * A string that holds the name of the developer(s) who created the application.
     */
    protected static String developerName = "Team 14";

    /**
     * A POI object that serves as a fallback in case of errors or missing data.
     */
    protected static POI fallbackPOI = new POI(-1);

    /**
     * A ListCellRenderer that centers the text of a JList cell.
     */
    public static ListCellRenderer<? super String> centerRenderer;

    /**
     * Default constructor
     */
    public Main() { 
        // Does nothing.
    }

    /**
     * Returns optimum point based on buildingCode provided.
     * @param buildingCode Code of the building
     * @return Point optimum point for buildingCode
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
     * Returns the full name of the floor for a given floorMapName.
     * @param floorMapName Name of the floor map file
     * @return Full name of the floor in English words
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
     * Returns the full name of the building for a given floorMapName.
     * @param floorMapName Name of the floor map file
     * @return Full name of the building
     */
    public static String getBuildingFullName(String floorMapName) {
        if (floorMapName.contains("MC")) return "Middlesex College";
        else if (floorMapName.contains("KB")) return "Kresge Building";
        else if (floorMapName.contains("PAB")) return "Physics & Astronomy Building";
        return "Unknown";
    }

    /**
     * Restarts the BuildingBuddy application.
     * @throws IOException if error occurs while restarting
     */
    public static void restartApplication() throws IOException {
        String javaCommand = System.getProperty("java.home") + "/bin/java";
        String javaClassPath = System.getProperty("java.class.path");
        String className = Main.class.getName();
        String[] command = { javaCommand, "-cp", javaClassPath, className };
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.start();
        System.exit(0);
    }

    /**
     * Updates the current building code and current floor based on POI provided.
     * @param poi Point of Interest to update current building code and floor for
     */
    public static void updateCurrent(POI poi) {
        if (poi != null) {
            Main.currentBuildingCode = poi.map.replaceAll("\\dF.png","").toUpperCase();
            Main.currentFloor = poi.map.replaceAll(".png","").toUpperCase();
        }

    }

    /**
     * Reads the security key from the file system and returns it as a char array.
     *
     * @return The security key as a char array.
     */
    public static char[] getSecurityKey() {

        String fileName = "./data/security_key";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                securityKey = line.toCharArray();
            }

        } catch (IOException e) {
            System.out.println("Key file ");
        }
        return securityKey;
    }

    /**
     * Changes the security key in the file system to the provided new key.
     *
     * @param newKey The new security key as a char array.
     */
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

    /**
     * Checks if a new version of the app is available.
     *
     * @return A message that shows the user whether there is a new version of the app available.
     * @throws IOException when the source file cannot be read.
     */
    public static String updateChecker() throws IOException {
        String urlStr = "https://raw.githubusercontent.com/dan1el5/BuildingBuddy/master/README.md"; // where the README file is stored
        URL url = new URL(urlStr);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String firstLine = reader.readLine(); // Reads the first line of the README file that contains the version number
            reader.close();
            if (firstLine.contains(currentAppVersion)) // Compares version numbers and returns the result
                return "You're running the latest version of BuildingBuddy!<br><br>Happy exploring!";
            else return "There's a new version of BuildingBuddy available.<br><br>You may download it now from our GitHub repo (click About in the menu).";
    }


    /**
     * The main method that initializes the application and sets up the fallback POI and other data structures.
     *
     * @param args The command line arguments.
     * @throws IOException If an I/O error occurs.
     */
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

        // Centers certain elements
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
