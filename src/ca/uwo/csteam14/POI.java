package ca.uwo.csteam14;

public class POI {

    protected int id;
    protected String name;
    protected String building;
    protected String map;
    protected String code;
    protected String floor;
    protected String roomNum;
    protected String category;
    protected String description;
    protected int positionX;
    protected int positionY;
    protected boolean isBuiltIn;

    // node construction
    protected POI next;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuilding(String building) {this.building = building;}

    public void setMap(String map) {
        this.map = map;
    }
    public void setCode(String code) { this.code = code;}
    public void setFloor(String floor) {this.floor = floor;}

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

    public void setBuiltIn(boolean builtIn) {
        this.isBuiltIn = builtIn;
    }
    public void setNext(POI next) {
        this.next = next;
    }
    public POI getNext() {
        return this.next;
    }

    public POI(int id) {
        this.id = id;
        // get data from json file based in ID
    }



}
