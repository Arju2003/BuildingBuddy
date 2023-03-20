package ca.uwo.csteam14;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.LinkedList;

public class POI {

    protected int id;
    protected String name;
    protected FloorMap belongsTo;
    protected String code;
    protected String floor;
    protected String roomNum;
    protected String category;
    protected String description;
    protected String pathName;
    protected int positionX;
    protected int positionY;
    protected boolean creator;

    // node construction
    protected POI current;
    protected POI next;


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
    public void getNodeData() {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("builtin.json"));
            JSONObject jObject = (JSONObject) obj;

            belongsTo = (FloorMap) jObject.get("building");
            code = (String) jObject.get("buildingCode");
            floor = (String) jObject.get("floor");
            roomNum = (String) jObject.get("roomNumber");
            category = (String) jObject.get("category");
            description = (String) jObject.get("description");
            positionX = (Integer) jObject.get("mapX");
            positionY = (Integer) jObject.get("mapY");
            creator = (Boolean)  jObject.get("builtIn");



        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (ParseException e) { e.printStackTrace(); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public POI(int id) {
        this.id = id;

        // get data from json file based in ID, attach it to a variable for the node

    }

    // POI Discovery,
    // Built-In POIs,
    // POI Favourites,
    // User Created POIs.

}
