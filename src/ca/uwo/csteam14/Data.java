package ca.uwo.csteam14;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Data extends LinkedList<POI>{

    protected String dataFile;
    protected boolean canRead;
    protected boolean canWrite;
    protected LinkedList createdPOIs;
    protected String POIId;
    protected String building;
    protected String buildingCode;
    protected String floor;
    protected int roomNumber;
    protected String category;
    protected String description;
    protected int mapX;
    protected int mapY;
    protected boolean builtIn;

    public Data (String JSONFile) {

            JSONParser parser = new JSONParser();

            try {

                Object obj = parser.parse(new FileReader(JSONFile));
                JSONObject jObject = (JSONObject) obj;

                POIId = (String) jObject.get("POIId");
                building = (String) jObject.get("building");
                buildingCode = (String) jObject.get("buildingCode");
                floor = (String) jObject.get("floor");
                roomNumber = (Integer) jObject.get("roomNumber");
                category = (String) jObject.get("category");
                description = (String) jObject.get("description");
                mapX = (Integer) jObject.get("mapX");
                mapY = (Integer) jObject.get("mapY");
                builtIn = (Boolean)  jObject.get("builtIn");



            } catch (Exception e) { e.printStackTrace(); }
    }



    public void lockFile() {
        canWrite = false;
    }

    public void unlockFile() {
        canWrite = true;
    }

}
