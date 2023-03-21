package ca.uwo.csteam14;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Data extends LinkedList<POI>{
    protected static LinkedList<POI> builtInPOIs;
    protected static LinkedList<POI> userCreatedPOIs;
    protected static LinkedList<POI> bookmarks;

    public Data() {
        JSONParser parser = new JSONParser();
        builtInPOIs = new LinkedList<>();
        userCreatedPOIs = new LinkedList<>();
        bookmarks = new LinkedList<>();

        try {
            Object obj1 = parser.parse(new FileReader("./data/builtin.json"));
            Object obj2 = parser.parse(new FileReader("./data/user.json"));
            Object obj3 = parser.parse(new FileReader("./data/bookmarks.json"));
            JSONObject jObject1 = (JSONObject)obj1;
            JSONObject jObject2 = (JSONObject)obj2;
            JSONObject jObject3 = (JSONObject)obj3;
            JSONArray POIArray1 = (JSONArray)jObject1.get("BuiltInPOIs");
            JSONArray POIArray2 = (JSONArray)jObject2.get("UserPOIs");
            JSONArray POIArray3 = (JSONArray)jObject3.get("Bookmarks");

            Iterator var8;
            Object o;
            JSONObject point;
            long POIId;
            int id;
            String name;
            String building;
            String buildingCode;
            String floor;
            long roomNum;
            String roomNumber;
            String category;
            String description;
            String mapFile;
            long x;
            int mapX;
            long y;
            int mapY;
            boolean builtIn;
            POI newPoint;

            for(var8 = POIArray1.iterator(); var8.hasNext(); builtInPOIs.add(newPoint)) {

                o = var8.next();
                point = (JSONObject)o;
                POIId = (Long)point.get("POIId");
                id = (int)POIId;
                name = (String)point.get("POIName");
                building = (String)point.get("building");
                buildingCode = (String)point.get("buildingCode");
                floor = (String)point.get("floor");
                roomNum = (Long)point.get("roomNumber");
                roomNumber = String.valueOf(roomNum);
                category = (String)point.get("category");
                description = (String)point.get("description");
                mapFile = (String)point.get("map");
                x = (Long)point.get("mapX");
                mapX = (int)x;
                y = (Long)point.get("mapY");
                mapY = (int)y;
                builtIn = (Boolean)point.get("built-in");
                newPoint = new POI(id);
                newPoint.setCategory(category);
                newPoint.setMap(mapFile);
                newPoint.setX(mapX);
                newPoint.setY(mapY);
                newPoint.setName(name);
                newPoint.setBuilding(building);
                newPoint.setCode(buildingCode);
                newPoint.setFloor(floor);
                newPoint.setRoomNum(roomNumber);
                newPoint.setDescription(description);
                newPoint.setBuiltIn(builtIn);

                switch (floor) {
                    case "Ground Floor" -> newPoint.setPath("./maps/" + buildingCode + "0F.png");
                    case "First Floor" -> newPoint.setPath("./maps/" + buildingCode + "1F.png");
                    case "Second Floor" -> newPoint.setPath("./maps/" + buildingCode + "2F.png");
                    case "Third Floor" -> newPoint.setPath("./maps/" + buildingCode + "3F.png");
                    default -> newPoint.setPath("./maps/" + buildingCode + "4F.png");
                }
            }

            for(var8 = POIArray2.iterator(); var8.hasNext(); userCreatedPOIs.add(newPoint)) {

                o = var8.next();
                point = (JSONObject)o;
                POIId = (Long)point.get("POIId");
                id = (int)POIId;
                name = (String)point.get("POIName");
                building = (String)point.get("building");
                buildingCode = (String)point.get("buildingCode");
                floor = (String)point.get("floor");
                roomNum = (Long)point.get("roomNumber");
                roomNumber = String.valueOf(roomNum);
                category = (String)point.get("category");
                description = (String)point.get("description");
                mapFile = (String)point.get("map");
                x = (Long)point.get("mapX");
                mapX = (int)x;
                y = (Long)point.get("mapY");
                mapY = (int)y;
                builtIn = (Boolean)point.get("built-in");
                newPoint = new POI(id);
                newPoint.setCategory(category);
                newPoint.setMap(mapFile);
                newPoint.setX(mapX);
                newPoint.setY(mapY);
                newPoint.setName(name);
                newPoint.setBuilding(building);
                newPoint.setCode(buildingCode);
                newPoint.setFloor(floor);
                newPoint.setRoomNum(roomNumber);
                newPoint.setDescription(description);
                newPoint.setBuiltIn(builtIn);

                switch (floor) {
                    case "Ground Floor" -> newPoint.setPath("./maps/" + buildingCode + "0F.png");
                    case "First Floor" -> newPoint.setPath("./maps/" + buildingCode + "1F.png");
                    case "Second Floor" -> newPoint.setPath("./maps/" + buildingCode + "2F.png");
                    case "Third Floor" -> newPoint.setPath("./maps/" + buildingCode + "3F.png");
                    default -> newPoint.setPath("./maps/" + buildingCode + "4F.png");
                }
            }
            for(var8 = POIArray3.iterator(); var8.hasNext(); bookmarks.add(newPoint)) {

                o = var8.next();
                point = (JSONObject)o;
                POIId = (Long)point.get("POIId");
                id = (int)POIId;
                name = (String)point.get("POIName");
                building = (String)point.get("building");
                buildingCode = (String)point.get("buildingCode");
                floor = (String)point.get("floor");
                roomNum = (Long)point.get("roomNumber");
                roomNumber = String.valueOf(roomNum);
                category = (String)point.get("category");
                description = (String)point.get("description");
                mapFile = (String)point.get("map");
                x = (Long)point.get("mapX");
                mapX = (int)x;
                y = (Long)point.get("mapY");
                mapY = (int)y;
                builtIn = (Boolean)point.get("built-in");
                newPoint = new POI(id);
                newPoint.setCategory(category);
                newPoint.setMap(mapFile);
                newPoint.setX(mapX);
                newPoint.setY(mapY);
                newPoint.setName(name);
                newPoint.setBuilding(building);
                newPoint.setCode(buildingCode);
                newPoint.setFloor(floor);
                newPoint.setRoomNum(roomNumber);
                newPoint.setDescription(description);
                newPoint.setBuiltIn(builtIn);

                switch (floor) {
                    case "Ground Floor" -> newPoint.setPath("./maps/" + buildingCode + "0F.png");
                    case "First Floor" -> newPoint.setPath("./maps/" + buildingCode + "1F.png");
                    case "Second Floor" -> newPoint.setPath("./maps/" + buildingCode + "2F.png");
                    case "Third Floor" -> newPoint.setPath("./maps/" + buildingCode + "3F.png");
                    default -> newPoint.setPath("./maps/" + buildingCode + "4F.png");
                }
            }
        } catch (Exception var30) {
            var30.printStackTrace();
        }

    }

    public static ArrayList<POI> getCategory(String layerName, String floor) {
        ArrayList<POI> result = new ArrayList<>();
        for (POI p : builtInPOIs) {
            if (layerName.contains(p.category) && p.map.contains(floor))
                result.add(p);
            if (p.description != null && layerName.contains("Labs") && p.description.contains("Computer lab") && p.map.contains(floor))
                result.add(p);
    }
        return result;
    }

    public static void addPOI(String name, int mapx, int mapy) {
        // generate POI ID, add 4 prefix then attach next ID according to our protocol
        int userID = (4 * 10000 ) + userCreatedPOIs.getLast().getId() + 1;

        // Create a new instance of POI
        POI poi = new POI(userID);

        // Get user info from popup window
        // method in POI selector that opens a popup window and returns the user input

        // something like String building = getUserInfo("Enter building name:");


        // Set the POI attributes based on the user input and the parameters passed to this method
        poi.setName(name);
        poi.setX(mapx);
        poi.setY(mapy);

        // Add the POI to the list of user-created POIs
        userCreatedPOIs.add(poi);

        // write to user.json

    }

    public static POI removePOI() {
        // user clicks on the POI they want to delete
        // get info from click event

        // remove from LinkedList

        // wipe from JSON file

        return null;
    }

    public LinkedList<POI> getUserCreatedPOIs() {
        return userCreatedPOIs;
    }

    public LinkedList<POI> getBookmarks() {
        return bookmarks;
    }

    public static void main(String[] args) {
        new Data();
        System.out.println("Hello");
    }
}
