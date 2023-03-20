package ca.uwo.csteam14;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Data {

    private LinkedList<POI> builtInPOIs;
    private LinkedList<POI> userCreatedPOIs;
    private LinkedList<POI> bookmarks;


    public Data () {

            JSONParser parser = new JSONParser();
            builtInPOIs = new LinkedList<POI>();
            userCreatedPOIs = new LinkedList<POI>();
            bookmarks = new LinkedList<POI>();


            try {

                // first make a POI list for built-in POIs
                Object obj = parser.parse(new FileReader("./data/builtin.json"));
                Object objUsr = parser.parse(new FileReader("./data/user.json"));
                JSONObject jObject = (JSONObject) obj;
                JSONObject jObject2 = (JSONObject) objUsr;
                JSONArray POIs = (JSONArray) jObject.get("BuiltInPOIs");
                JSONArray createdPOIs = (JSONArray) jObject2.get("UserPOIs");
                for (Object o : POIs) {
                    JSONObject point = (JSONObject) o;
                    long POIId = (long) point.get("POIId");
                    int id = (int) POIId;
                    String name = (String) point.get("POIName");
                    String buildingCode = (String) point.get("buildingCode");
                    String floor = (String) point.get("floor");
                    long roomNum = (long) point.get("roomNumber");
                    String roomNumber = String.valueOf(roomNum);
                    String category = (String) point.get("category");
                    String description = (String) point.get("description");
                    long x = (long) point.get("mapX");
                    int mapx = (int) x;
                    long y = (long) point.get("mapY");
                    int mapy = (int) y;
                    boolean builtIn = (boolean) point.get("built-in");

                    POI newPoint = new POI(id);
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
                        newPoint.setPath("./maps/" + buildingCode + "0" + "F.png");
                    }
                    else if (floor.equals("First Floor")) {
                        newPoint.setPath("./maps/" + buildingCode + "1" + "F.png");
                    }

                    else if (floor.equals("Second Floor")) {
                        newPoint.setPath("./maps/" + buildingCode + "2" + "F.png");
                    }

                    else if (floor.equals("Third Floor")) {
                        newPoint.setPath("./maps/" + buildingCode + "3" + "F.png");
                    }
                    else {
                        newPoint.setPath("./maps/" + buildingCode + "4" + "F.png");
                    }

                    builtInPOIs.add(newPoint);

                }

                for (Object o : createdPOIs) {
                    JSONObject point = (JSONObject) o;
                    long POIId = (long) point.get("POIId");
                    int id = (int) POIId;
                    String name = (String) point.get("POIName");
                    String buildingCode = (String) point.get("buildingCode");
                    String floor = (String) point.get("floor");
                    long roomNum = (long) point.get("roomNumber");
                    String roomNumber = String.valueOf(roomNum);
                    String category = (String) point.get("category");
                    String description = (String) point.get("description");
                    long x = (long) point.get("mapX");
                    int mapx = (int) x;
                    long y = (long) point.get("mapY");
                    int mapy = (int) y;
                    boolean builtIn = (boolean) point.get("built-in");

                    POI newPoint = new POI(id);
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
                        newPoint.setPath("./maps/" + buildingCode + "0" + "F.png");
                    }
                    else if (floor.equals("First Floor")) {
                        newPoint.setPath("./maps/" + buildingCode + "1" + "F.png");
                    }

                    else if (floor.equals("Second Floor")) {
                        newPoint.setPath("./maps/" + buildingCode + "2" + "F.png");
                    }

                    else if (floor.equals("Third Floor")) {
                        newPoint.setPath("./maps/" + buildingCode + "3" + "F.png");
                    }
                    else {
                        newPoint.setPath("./maps/" + buildingCode + "4" + "F.png");
                    }

                    builtInPOIs.add(newPoint);

                }

            }

            catch (FileNotFoundException e) { e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }
            catch (ParseException e) { e.printStackTrace(); }
            catch (Exception e) { e.printStackTrace(); }




    }

    public LinkedList<POI> getBuiltInPOIs() {
        return builtInPOIs;
    }

    public LinkedList<POI> getUserCreatedPOIs() {
        return userCreatedPOIs;
    }

    public LinkedList<POI> getBookmarks() {
        return bookmarks;
    }

    public static void main(String[] args) {
        Data test = new Data();
        System.out.println("Hello");

    }


}
