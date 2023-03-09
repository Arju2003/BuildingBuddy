import java.util.LinkedList;
public class FloorMap {

    private int floorMapID;
    private String floorMapFile;
    private String building;
    private int floor;
    private LinkedList floorPois;

    public FloorMap() {
        floorMapID = 0;
        floorMapFile = "";
        building = "";
        floor = 0;
        floorPois = new LinkedList();
    }

    public int getFloorMapID() {
        return floorMapID;
    }

    public String getFloorMapFile() {
        return floorMapFile;
    }

    public String getBuilding() {
        return building;
    }

    public int getFloor() {
        return floor;
    }

    public LinkedList getFloorPois() {
        return floorPois;
    }

    public void setFloorMapID(int floorMapID) {
        this.floorMapID = floorMapID;
    }

    public void setFloorMapFile(String floorMapFile) {
        this.floorMapFile = floorMapFile;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setFloorPois(LinkedList floorPois) {
        this.floorPois = floorPois;
    }
}
