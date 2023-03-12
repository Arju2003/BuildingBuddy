package ca.uwo.csteam14;

public class POI {

    private int id;
    private String name;
    private FloorMap belongsTo;
    private String category;
    private String roomNum;
    private int positionX;
    private int positionY;
    private String description;
    private String creator;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FloorMap getMap() {
        return belongsTo;
    }

    public String getCategory() {
        return category;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public int getX() {
        return positionX;
    }
    
    public int getY() {
        return positionY;
    }

    public String getDescription() {
        return description;
    }

    public String getCreator() {
        return creator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMap(FloorMap map) {
        belongsTo = map;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public void setX(int x) {
        this.positionX = x;
    }

    public void setY(int y) {
        this.positionY = y;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    // POI Discovery,
    // Built-In POIs,
    // POI Favourites,
    // User Created POIs.

}
