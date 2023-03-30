package ca.uwo.csteam14;

/**
 * POI entity used for storing information about a POI such as the name, coordinates, category, etc.
 * <br></br>
 * A POI is created with the {@link #POI(int) constructor}, and the other attributes are set using setters.
 * POIs can be compared for equality using the {@link #isEqualTo(POI) isEqualTo} method.
 * <br></br>
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
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param building
     */
    public void setBuilding(String building) {this.building = building;}

    /**
     * @param map
     */
    public void setMap(String map) {
        this.map = map;
    }

    /**
     * @param code
     */
    public void setCode(String code) { this.code = code;}

    /**
     * @param floor
     */
    public void setFloor(String floor) {this.floor = floor;}

    /**
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * @param roomNumber
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.positionX = x;
    }

    /**
     * @param y
     */
    public void setY(int y) {
        this.positionY = y;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param builtIn
     */
    public void setBuiltIn(boolean builtIn) {
        this.isBuiltIn = builtIn;
    }

    /**
     * Method to set the POI's path attribute to the value of the given parameter
     * @param path
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
     * Compares this POI to the given POI and returns true if the POIs are determined to be equal, or false otherwise.
     * @param p the POI that will be compared to this POI object
     * @return a boolean value indicating whether the POIs are determined to be equal
     */
    public boolean isEqualTo(POI p) {
        return (this.id == p.id || (this.map.equals(p.map) && this.positionX <= p.positionX + 48 && this.positionX >= p.positionX && this.positionY <= p.positionY + 48 && this.positionY >= p.positionY));
    }

    // do we need this? Looks like it's never called
    public boolean hasLegalCategory() {
        for (String s: LayerFilter.labelArray) {
            if (this.category.equals(s)) return true;
        }
        return false;
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



}
