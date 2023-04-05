package ca.uwo.csteam14;

/**
 * POI entity used for storing information about a POI such as the name, coordinates, category, etc.
 * <br /><br />
 * A POI is created with the {@link #POI(int) constructor}, and the other attributes are set using setters.
 * POIs can be compared for equality using the {@link #isEqualTo(POI) isEqualTo} method.
 * <br /><br />
 * <b>Example Use:</b>
 * <pre>
 * {@code
 *     POI p = new POI(40001);
 *     p.setName("The Grad Club");
 *     p.setBuilding("Middlesex College");
 *     p.setFloor("0");
 * }
 * </pre>
 * <b>Example Output:</b> No output, but a POI object is created with the given ID, name, building, and floor.
 * @version 1.0.0
 * @author Daniel Gomes
 * @author Robert Beemer
 * @since 2023-03-07
 */
public class POI {
    /** The POI's id */
    protected int id;
    /** The POI's name */
    protected String name;
    /** The building the POI is in */
    protected String building;
    /** The POI's corresponding map */
    protected String map;
    /** The code of the building the POI is in */
    protected String code;
    /** The floor number that the POI is on */
    protected String floor;
    /** The POI's room number */
    protected int roomNumber;
    /** The category of the POI */
    protected String category;
    /** An array of valid POI categories */
    protected static final String[] categories = {"Classroom","CompSci Spot", "Restaurant", "Lab", "Stairwell", "Elevator", "Entrance", "Exit", "Washroom"};
    /** A description of the POI */
    protected String description;
    /** The POI's x coordinate */
    protected int positionX;
    /** The POI's y coordinate */
    protected int positionY;
    /** A boolean attribute indicating whether the POI is built-in or not */
    protected boolean isBuiltIn;
    /** The path to the map that the POI is on */
    protected String path;

    /** A pointer to the next POI (since POIs are stored in a LinkedList */
    // node construction
    protected POI next;


    /**
     * Getter method for accessing a POI's id
     * @return id the POI's id
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set the id of a poi
     * @param id The new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to set the name of a POI
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to set the building of the POI
     * @param building The building the POI resides in
     */
    public void setBuilding(String building) {this.building = building;}

    /**
     * Method to set the map of a POI
     * @param map The map the POI resides in
     */
    public void setMap(String map) {
        this.map = map;
    }

    /**
     * Method to set the code of a POI
     * @param code The new code
     */
    public void setCode(String code) { this.code = code;}

    /**
     * Method to set the floor of a POI
     * @param floor The floor of the POI
     */
    public void setFloor(String floor) {this.floor = floor;}

    /**
     * Method to set the category of a POI
     * @param category The POI's category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Method to set the room number of a POI
     * @param roomNumber The POI's room number
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Method to set the x co-ordinate of a POI
     * @param x The POI's x co-ordinate
     */
    public void setX(int x) {
        this.positionX = x;
    }

    /**
     * Method to set the y co-ordinate of a POI
     * @param y The POI's y co-ordinate
     */
    public void setY(int y) {
        this.positionY = y;
    }

    /**
     * Method to set the description of a POI
     * @param description The new description for the POI
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method to set the POI's boolean builtIn value, the value is true if the POI is a built-in POI and the value is false if the POI is a user created POI
     * @param builtIn a boolean parameter, true if the POI is a built-in POI and the value is false if the POI is a user created POI
     */
    public void setBuiltIn(boolean builtIn) {
        this.isBuiltIn = builtIn;
    }

    /**
     * Method to set the POI's map file path
     * @param path the path of the map file
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * POI constructor. Creates a new POI object.
     * @param id the POI's ID
     */
    public POI(int id) {
        this.id = id;
        name = "";
        building = "";
        map = "";
        code = "";
        floor = "";
        roomNumber = 0;
        category = "";
        description = "";
        positionX = 0;
        positionY = 0;
        isBuiltIn = false;
        next = null;
        // get data from json file based in ID
    }

    /**
     * POI alternative constructor. Copies a new POI object from an existing POI object.
     * @param that another POI object
     */
    public POI (POI that) {
        this.id = that.id;
        this.name = that.name;
        this.building = that.building;
        this.map = that.map;
        this.code = that.code;
        this.floor = that.floor;
        this.roomNumber = that.roomNumber;
        this.category = that.category;
        this.description = that.description;
        this.positionX = that.positionX;
        this.positionY = that.positionY;
        this.isBuiltIn = that.isBuiltIn;
        this.next = that.next;
    }

    /**
     * Compares this POI to the given POI and returns true if the POIs are determined to be equal, or false otherwise.
     * @param p the POI that will be compared to this POI object
     * @return a boolean value indicating whether the POIs are determined to be equal
     */
    public boolean isEqualTo(POI p) {
        return (this.id == p.id || (this.map.equals(p.map) && (this.positionX <= p.positionX + 48 && this.positionX >= p.positionX && this.positionY <= p.positionY + 48 && this.positionY >= p.positionY) && (this.isBuiltIn == p.isBuiltIn)));
    }


    /**
     * Checks if the given category is valid. Returns true if the category is valid, or false otherwise.
     * @param str the given category
     * @return a boolean value indicating whether the category is valid or not
     */
    public static boolean hasLegalCategory(String str) {
        for (String s: categories) {
            if (str.equals(s)) return true;
        }
        return false;
    }

    /**
     * Checks if a given string can be parsed as an integer.
     *
     * @param str the string to check
     * @return true if the string can be parsed as an integer, false otherwise
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
