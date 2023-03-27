package ca.uwo.csteam14;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


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
                newPoint.setRoomNumber(roomNumber);
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
                newPoint.setRoomNumber(roomNumber);
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
                newPoint.setRoomNumber(roomNumber);
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

    public LinkedList<POI> getLinkedListOfPOIs(ArrayList<POI> data) {
        return new LinkedList<>(data);
    }

    public static ArrayList<POI> getPOIs(String currentFloor, String layerName) {
        ArrayList<POI> result = new ArrayList<>();
        for (POI p : builtInPOIs) {
            if (LayerFilter.selectedLayers.contains(layerName)) {
                if (layerName.contains(p.category) && p.map.contains(currentFloor))
                    result.add(p);
                if (p.description != null && layerName.contains("Labs") && p.description.contains("Computer lab") && p.map.contains(currentFloor))
                    result.add(p);
                if (p.description != null && layerName.contains("Accessibility") && p.description.contains("Accessible facility") && p.map.contains(currentFloor))
                    result.add(p);
            }
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

    public static int removePOI(POI p) throws IOException {
        // user clicks on the POI they want to delete

        // get info from click event

        LinkedList<POI> newUserCreatedPOIs = new LinkedList<POI>();

        // In built linked list removal
        if (userCreatedPOIs.contains(p)) {
            userCreatedPOIs.remove(p);
        }

        else {
            return 1;
        }

        // Write from linked list to json file
        JSONArray jsonArray = new JSONArray();

        // Write the empty array to the file
        try (FileWriter fileWriter = new FileWriter("user.json")) {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Create a new json array of json objects that hold objects from the linked list of POIs
        JSONArray filledJsonArray = new JSONArray();
        for (POI data : userCreatedPOIs) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mapx", data.positionX);
            jsonObject.put("mapy", data.positionY);
            jsonObject.put("POIName", data.name);
            jsonObject.put("POIId", data.id);
            jsonObject.put("map", data.map);
            jsonObject.put("description", data.description);
            jsonObject.put("category", data.category);
            jsonObject.put("buildingCode", data.code);
            jsonObject.put("floor", data.floor);
            jsonObject.put("building", data.building);
            jsonObject.put("built-in", data.isBuiltIn);
            jsonObject.put("roomNumber", data.roomNumber);

            filledJsonArray.add(jsonObject);
        }

        // Write the Json file

        try (FileWriter fileWriter = new FileWriter("user.json")) {
            fileWriter.write(filledJsonArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;

    }



    public LinkedList<POI> getUserCreatedPOIs() {
        return userCreatedPOIs;
    }

    public LinkedList<POI> getBookmarks() {
        return bookmarks;
    }

    public POI getPOI (String currentFloor,int x, int y){
        for (POI p : builtInPOIs) {
            if (p.map.contains(currentFloor) && p.positionX == x && p.positionY == y) {
                    return p;
                }
            }
            for (POI p : userCreatedPOIs) {
                if (p.map.contains(currentFloor) && p.positionX == x && p.positionY == y) {
                    return p;
                }
            }
            return null;
    }



    public static void main(String[] args) {
        Data x = new Data();
        System.out.println("Hello");
    }
}
