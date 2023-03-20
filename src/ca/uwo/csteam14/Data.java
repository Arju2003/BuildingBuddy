package ca.uwo.csteam14;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Data {

    private String dataFile;
    private boolean canRead;
    private boolean canWrite;
    private LinkedList createdPOIs;
    private String POIId;
    private String building;
    private String buildingCode;
    private String floor;
    private int roomNumber;
    private String category;
    private String description;
    private int mapx;
    private int mapy;
    private boolean builtIn;

    public Data () {

            JSONParser parser = new JSONParser();

            try {

                Object obj = parser.parse(new FileReader("user.json"));
                JSONObject jObject = (JSONObject) obj;

                POIId = (String) jObject.get("POIId");
                building = (String) jObject.get("building");
                buildingCode = (String) jObject.get("buildingCode");
                floor = (String) jObject.get("floor");
                roomNumber = (Integer) jObject.get("roomNumber");
                category = (String) jObject.get("category");
                description = (String) jObject.get("description");
                mapx = (Integer) jObject.get("mapX");
                mapy = (Integer) jObject.get("mapY");
                builtIn = (Boolean)  jObject.get("builtIn");



            }
            catch (FileNotFoundException e) { e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }
            catch (ParseException e) { e.printStackTrace(); }
            catch (Exception e) { e.printStackTrace(); }
    }



    public void lockFile() {
        canWrite = false;
    }

    public void unlockFile() {
        canWrite = true;
    }

}
