package ca.uwo.csteam14;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

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



}
