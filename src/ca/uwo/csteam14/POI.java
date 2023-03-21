package ca.uwo.csteam14;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    protected String pathName;
    protected int positionX;
    protected int positionY;
    protected boolean isBuiltIn;

    // node construction
    protected POI current;
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

    public void setBuiltIn(boolean builtIn) {
        this.isBuiltIn = builtIn;
    }
    public void getNodeData() {

    }

    public POI(int id) {
        this.id = id;
        // get data from json file based in ID
    }

    public BufferedImage highlight() throws IOException {

        System.out.println(this.map);

        BufferedImage basemap = ImageIO.read(new File("./maps/" + this.map));

        // create a new image with the same dimensions as the original basemap
        BufferedImage highlightedImage = new BufferedImage(basemap.getWidth(), basemap.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // create a Graphics2D object for drawing on the new map
        Graphics2D g2d = highlightedImage.createGraphics();

        // draw the original basemap on the new map
        g2d.drawImage(basemap, 0, 0, null);

        // set the rendering hints for smooth antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // create a cicle with a transparent fill and a solid border
        g2d.setColor(new Color(255, 0, 0, 90));

        g2d.fillOval(this.positionX, this.positionY, 80, 80);

        // dispose of the Graphics2D object to free up resources
        g2d.dispose();
        return highlightedImage;
    }


}
