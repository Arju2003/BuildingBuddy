package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the MapView class.
 * @author Daniel Gomes
 * @version 1.0.0
 * @since 2023-03-20
 */

class MapViewTest {

    /**
     * This method is called before each test is run to set up any required test data.
     */
    @BeforeEach
    void setUp() {
    }

    /**
     * This method is called after each test is run to clean up any test data that was created.
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * This method tests the identifyPOI() method of the MapView class.
     */
    @Test
    void identifyPOI() {
        System.out.println("identifyPOI()");

        Data data = new Data();
        LinkedList<POI> builtin = data.getBuiltInPOIs();

        String floorName = "Ground Floor";
        ArrayList<String> layerNames = new ArrayList<String>();
        layerNames.add("Restaurant");
        int x = 1717;
        int y = 628;

        MapView instance = new MapView("MC0F.png", new Point(1,2));
        // Call the method and check the result
        POI result = instance.identifyPOI(floorName, layerNames, x, y);
        assertNotNull(result);
    }
}