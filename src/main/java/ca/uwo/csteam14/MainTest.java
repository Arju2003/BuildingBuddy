package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Main class.
 * @author Daniel Gomes
 * @version 1.0.0
 * @since 2023-03-20
 */

class MainTest {

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
     * This method tests the getOptimumPoint() method of the Main class.
     */
    @Test
    void getOptimumPoint() {
        System.out.println("getOptimumPoint()");

        Main instance = new Main();
        Point result = instance.getOptimumPoint("MC");
        Point expResult = new Point(1700, 700);
        assertEquals(expResult, result);
    }

    /**
     * This method tests the getFloorFullName() method of the Main class.
     */
    @Test
    void getFloorFullName() {
        System.out.println("getFloorFullName()");

        Main instance = new Main();
        String result = instance.getFloorFullName("MC0F");
        String expResult = "Ground Floor";
        assertEquals(expResult, result);
    }

    /**
     * This method tests the getBuildingFullName() method of the Main class.
     */
    @Test
    void getBuildingFullName() {
        System.out.println("getBuildingFloorName()");

        Main instance = new Main();
        String result = instance.getBuildingFullName("MC0F");
        String expResult = "Middlesex College";
        assertEquals(expResult, result);
    }

    /**
     * This method tests the updateCurrent() method of the Main class.
     */
    @Test
    void updateCurrent() {
        System.out.println("updateCurrent()");

        // Create a POI object with a map attribute
        POI poi = new POI(1);
        poi.setMap("MC1F.png");

        // Call the method with the POI object
        Main.updateCurrent(poi);

        // Check that the current building code and floor have been updated
        assertEquals("MC", Main.currentBuildingCode);
        assertEquals("MC1F", Main.currentFloor);
    }

    /**
     * This method tests the getSecurity() method of the Main class.
     */
    @Test
    void getSecurityKey() {
        System.out.println("getSecurityKey()");

        Main instance = new Main();
        char[] result = instance.getSecurityKey();
        char[] expResult = "CS2212BB".toCharArray();

        assertNotNull(result);
        assertArrayEquals(expResult, result);
    }

    /**
     * This method tests the changeSecurityKey() method of the Main class.
     */
    @Test
    void changeSecurityKey() {
        System.out.println("changeSecurityKey()");

        Main instance = new Main();

        // careful, this changes the security key
        char[] newKey = "newkey".toCharArray();
        instance.changeSecurityKey(newKey);

        // check that the file was updated correctly
        try (BufferedReader br = new BufferedReader(new FileReader("./data/security_key"))) {
            char[] savedKey = br.readLine().toCharArray();
            assertArrayEquals(newKey, savedKey);
        } catch (IOException e) {
            fail("IOException: " + e.getMessage());
        }

        // check that the security key was updated in the instance of the Main class
        assertArrayEquals(newKey, Main.getSecurityKey());
    }
}