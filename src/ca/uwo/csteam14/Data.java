package ca.uwo.csteam14;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Data extends LinkedList<POI>{
    private LinkedList<POI> builtInPOIs;
    private LinkedList<POI> userCreatedPOIs;
    private LinkedList<POI> bookmarks;

    public Data() {
        JSONParser parser = new JSONParser();
        this.builtInPOIs = new LinkedList();
        this.userCreatedPOIs = new LinkedList();
        this.bookmarks = new LinkedList();

        try {
            Object obj = parser.parse(new FileReader("./data/builtin.json"));
            Object objUsr = parser.parse(new FileReader("./data/user.json"));
            JSONObject jObject = (JSONObject)obj;
            JSONObject jObject2 = (JSONObject)objUsr;
            JSONArray POIs = (JSONArray)jObject.get("BuiltInPOIs");
            JSONArray createdPOIs = (JSONArray)jObject2.get("UserPOIs");

            Iterator var8;
            Object o;
            JSONObject point;
            long POIId;
            int id;
            String name;
            String buildingCode;
            String floor;
            long roomNum;
            String roomNumber;
            String category;
            String description;
            long x;
            int mapx;
            long y;
            int mapy;
            boolean builtIn;
            POI newPoint;
            for(var8 = POIs.iterator(); var8.hasNext(); this.builtInPOIs.add(newPoint)) {
                o = var8.next();
                point = (JSONObject)o;
                POIId = (Long)point.get("POIId");
                id = (int)POIId;
                name = (String)point.get("POIName");
                buildingCode = (String)point.get("buildingCode");
                floor = (String)point.get("floor");
                roomNum = (Long)point.get("roomNumber");
                roomNumber = String.valueOf(roomNum);
                category = (String)point.get("category");
                description = (String)point.get("description");
                x = (Long)point.get("mapX");
                mapx = (int)x;
                y = (Long)point.get("mapY");
                mapy = (int)y;
                builtIn = (Boolean)point.get("built-in");
                newPoint = new POI(id);
                newPoint.setCategory(category);
                newPoint.setX(mapx);
                newPoint.setY(mapy);
                newPoint.setName(name);
                newPoint.setCode(buildingCode);
                newPoint.setFloor(floor);
                newPoint.setRoomNum(roomNumber);
                newPoint.setDescription(description);
                newPoint.setCreator(builtIn);
                if (floor.equals("Ground Floor")) {
                    newPoint.setPath("./maps/" + buildingCode + "0F.png");
                } else if (floor.equals("First Floor")) {
                    newPoint.setPath("./maps/" + buildingCode + "1F.png");
                } else if (floor.equals("Second Floor")) {
                    newPoint.setPath("./maps/" + buildingCode + "2F.png");
                } else if (floor.equals("Third Floor")) {
                    newPoint.setPath("./maps/" + buildingCode + "3F.png");
                } else {
                    newPoint.setPath("./maps/" + buildingCode + "4F.png");
                }
            }

            for(var8 = createdPOIs.iterator(); var8.hasNext(); this.builtInPOIs.add(newPoint)) {
                o = var8.next();
                point = (JSONObject)o;
                POIId = (Long)point.get("POIId");
                id = (int)POIId;
                name = (String)point.get("POIName");
                buildingCode = (String)point.get("buildingCode");
                floor = (String)point.get("floor");
                roomNum = (Long)point.get("roomNumber");
                roomNumber = String.valueOf(roomNum);
                category = (String)point.get("category");
                description = (String)point.get("description");
                x = (Long)point.get("mapX");
                mapx = (int)x;
                y = (Long)point.get("mapY");
                mapy = (int)y;
                builtIn = (Boolean)point.get("built-in");
                newPoint = new POI(id);
                newPoint.setCategory(category);
                newPoint.setX(mapx);
                newPoint.setY(mapy);
                newPoint.setName(name);
                newPoint.setCode(buildingCode);
                newPoint.setFloor(floor);
                newPoint.setRoomNum(roomNumber);
                newPoint.setDescription(description);
                newPoint.setCreator(builtIn);
                if (floor.equals("Ground Floor")) {
                    newPoint.setPath("./maps/" + buildingCode + "0F.png");
                } else if (floor.equals("First Floor")) {
                    newPoint.setPath("./maps/" + buildingCode + "1F.png");
                } else if (floor.equals("Second Floor")) {
                    newPoint.setPath("./maps/" + buildingCode + "2F.png");
                } else if (floor.equals("Third Floor")) {
                    newPoint.setPath("./maps/" + buildingCode + "3F.png");
                } else {
                    newPoint.setPath("./maps/" + buildingCode + "4F.png");
                }
            }
        } catch (FileNotFoundException var30) {
            var30.printStackTrace();
        } catch (IOException var31) {
            var31.printStackTrace();
        } catch (ParseException var32) {
            var32.printStackTrace();
        } catch (Exception var33) {
            var33.printStackTrace();
        }

    }

    public LinkedList<POI> getBuiltInPOIs() {
        return this.builtInPOIs;
    }

    public LinkedList<POI> getUserCreatedPOIs() {
        return this.userCreatedPOIs;
    }

    public LinkedList<POI> getBookmarks() {
        return this.bookmarks;
    }

    public static void main(String[] args) {
        new Data();
        System.out.println("Hello");
    }
}
