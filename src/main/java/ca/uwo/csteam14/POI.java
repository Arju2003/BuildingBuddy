/**
 * @author Daniel, Bobby
 * POI class
 * Construct a POI and instantiate its attributes
 */

package ca.uwo.csteam14;

public class POI {

    protected int id;
    protected String name;
    protected String building;
    protected String map;
    protected String code;
    protected String floor;
    protected int roomNumber;
    protected String category;
    protected static final String[] categories = {"Classroom","CompSci Spot", "Restaurant", "Lab", "Stairwell", "Elevator", "Entrance", "Exit", "Washroom"};
    protected String description;
    protected int positionX;
    protected int positionY;
    protected boolean isBuiltIn;
    protected String path;

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
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @param id
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

    public boolean isEqualTo(POI p) {
        return (this.id == p.id || (this.map.equals(p.map) && this.positionX <= p.positionX + 48 && this.positionX >= p.positionX && this.positionY <= p.positionY + 48 && this.positionY >= p.positionY));
    }

    public boolean hasLegalCategory() {
        for (String s: LayerFilter.labelArray) {
            if (this.category.equals(s)) return true;
        }
        return false;
    }

    public static boolean hasLegalCategory(String str) {
        for (String s: categories) {
            if (str.equals(s)) return true;
        }
        return false;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
