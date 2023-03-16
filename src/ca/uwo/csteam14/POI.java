package ca.uwo.csteam14;

import java.util.LinkedList;

public class POI {

    private int id;
    private String name;
    private FloorMap belongsTo;
    private String code;
    private String floor;
    private String roomNum;
    private String category;
    private String description;
    private String pathName;
    private int positionX;
    private int positionY;
    private boolean creator;

    // node construction
    private POI current;
    private POI next;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FloorMap getMap() {
        return belongsTo;
    }
    public String getCode() {return code;}
    public String getFloor() {return floor;}

    public String getCategory() {
        return category;
    }

    public String getRoomNum() {
        return roomNum;
    }
    public String getPath() {return pathName;}

    public int getX() {
        return positionX;
    }
    
    public int getY() {
        return positionY;
    }

    public String getDescription() {
        return description;
    }

    public boolean getCreator() {
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
    public void setCode(String code) { this.code = code;}
    public void setFloor(String floor) {this.floor = floor;}

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
    public void setPath(String path) { pathName = path;}

    public void setX(int x) {
        this.positionX = x;
    }

    public void setY(int y) {
        this.positionY = y;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreator(boolean creator) {
        this.creator = creator;
    }

    public POI(int id) {
        // get data from json file based in ID, attach it to a variable for the node

    }

    // POI Discovery,
    // Built-In POIs,
    // POI Favourites,
    // User Created POIs.

}
