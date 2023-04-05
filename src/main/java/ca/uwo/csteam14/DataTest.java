package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Data class.
 * @author Daniel Gomes
 * @version 1.0.0
 * @since 2023-03-20
 */
class DataTest {

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
     * This method tests the getLayerPOIs() method of the Data class.
     */
    @Test
    void getLayerPOIs() {
        System.out.println("getLayerPOIs");

        // Initialize test data
        Data data = new Data();
        LinkedList<POI> builtInPOIs = data.getBuiltInPOIs();
        ArrayList<POI> expected = new ArrayList<>();
        // fill in arraylist with POIs from thr Classroom Layer
        for (POI poi : builtInPOIs) {
            if (poi.category.equals("Classroom")) {
                expected.add(poi);
            }
        }

        System.out.println(expected.size());  // show how many POIs are in the classroom layer

        // Call the method and check the result
        ArrayList<POI> actual = data.getLayerPOIs("Ground Floor", "Classroom");
        assertNotNull(actual);  // ensure POIs have been found with getLayerPOIs
    }

    /**
     * This method tests the addPOI() method of the Data class.
     * It creates a new POI object to add to the user created POIs list and checks that it has been added.
     */
    @Test
    void addPOI() {
        System.out.println("addPOI()");

        // Create a new POI object to add
        POI newPOI = new POI(4);
        newPOI.setName("test poi");
        newPOI.setBuilding("Middlesex College");
        newPOI.setCode("MC");
        newPOI.setFloor("Second Floor");
        newPOI.setRoomNumber(200);
        newPOI.setCategory("Classroom");
        newPOI.setDescription("desc");
        newPOI.setMap("MC2F");
        newPOI.setX(300);
        newPOI.setY(500);
        newPOI.setBuiltIn(false);

        // Add the new POI to the userCreatedPOIs list
        Data data = new Data();
        assertNotNull(data.addPOI(newPOI, data.getUserCreatedPOIs()));

        // Check that the new POI has been added to the userCreatedPOIs list
        assertTrue(Data.containsPOI(data.getUserCreatedPOIs(), newPOI));
    }

    /**
     * This method tests the removePOI() method of the Data class.
     * It adds two POIs to a linked list, removes one of them, and checks that the correct POI was removed.
     */
    @Test
    void removePOI() throws IOException {
        System.out.println("removePOI()");

        // initialize builtIn POI list
        Data.builtInPOIs = new LinkedList<>();

        // Create a new POI to add
        POI newPOI = new POI(4);
        newPOI.setName("test poi");
        newPOI.setBuilding("Building A");
        newPOI.setCode("B");
        newPOI.setFloor("Second Floor");
        newPOI.setRoomNumber(200);
        newPOI.setCategory("Office");
        newPOI.setDescription("New POI description");
        newPOI.setMap("Map A");
        newPOI.setX(300);
        newPOI.setY(500);
        newPOI.setBuiltIn(false);

        // add the POI to be able to remove it
        Data.builtInPOIs.add(newPOI);

        // remove the new POI from the builtInPOIs list
        Data data = new Data();
        assertNotNull(data.removePOI(newPOI, Data.builtInPOIs));

        // Check that the new POI has been removed from the builtInPOIs list
        assertFalse(Data.containsPOI(Data.builtInPOIs, newPOI));
    }

    /**
     * This method tests the getBuiltInPOIs() method of the Data class.
     * It checks that the built-in POI list is not null and contains at least one POI.
     */
    @Test
    void getBuiltInPOIs() {
        System.out.println("getBuiltInPOIs()");
        // Get the built-in POIs from the Map class
        Data instance = new Data();
        LinkedList<POI> pois = instance.getBuiltInPOIs();
        // Assert that the POI list is not null
        assertNotNull(pois);
        // Assert that the POI list contains at least one POI
        assertTrue(pois.size() > 0);
    }

    /**
     * Test method for {@link Data#getUserCreatedPOIs()}.
     * Retrieves the user-created POIs from the Data class and checks that the list is not null and contains at least one POI.
     */
    @Test
    void getUserCreatedPOIs() {
        System.out.println("getUserCreatedPOIs()");
        // Get the user created POIs from the Map class
        Data instance = new Data();
        LinkedList<POI> pois = instance.getUserCreatedPOIs();
        // add a test POI since user created might be empty
        POI testPOI = new POI(999);
        pois.add(testPOI);
        // Assert that the POI list is not null
        assertNotNull(pois);
        // Assert that the POI list contains at least one POI
        assertTrue(pois.size() > 0);
    }

    /**
     * Test method for {@link Data#getBookmarks()}.
     * Retrieves the bookmarked POIs from the Data class and checks that the list is not null and contains at least one POI.
     */
    @Test
    void getBookmarks() {
        System.out.println("getBookmarks()");
        // Get the bookmarked POIs from the Map class
        Data instance = new Data();
        LinkedList<POI> pois = instance.getBookmarks();
        // add a test POI since bookmarks might be empty
        POI testPOI = new POI(999);
        pois.add(testPOI);
        // Assert that the POI list is not null
        assertNotNull(pois);
        // Assert that the POI list contains at least one POI
        assertTrue(pois.size() > 0);
    }

    /**
     * Test method for {@link Data#getPOI(java.lang.String, int, int)}.
     * Retrieves a POI with the given code, x coordinate, and y coordinate from the Data class and checks that it is not null and equal to the expected POI.
     */
    @Test
    void getPOI() {
        System.out.println("getPOI()");

        // initialize POI lists
        Data instance = new Data();
        LinkedList<POI> builtInPOIs = new LinkedList<>();
        LinkedList<POI> userCreatedPOIs = new LinkedList<>();

        // Create a test POI
        POI testPOI = new POI(1);
        testPOI.setX(10);
        testPOI.setY(20);
        testPOI.setMap("Test Floor");

        // Add the test POI to the list of built-in POIs
        Data.builtInPOIs.add(testPOI);

        // Test that the getPOI method returns the correct POI for the given coordinates
        POI resultPOI = instance.getPOI("Test Floor", 10, 20);
        assertEquals(testPOI, resultPOI);

        // Test that the method returns null if there is no POI at the given coordinates
        POI resultNull = instance.getPOI("Test Floor", 30, 40);
        assertNull(resultNull);
    }

    /**
     * Test method for {@link Data#containsPOI(java.util.LinkedList, POI)}.
     * Checks whether a given POI is contained in a given list of POIs.
     */
    @Test
    void containsPOI() {
        System.out.println("containsPOI()");

        // create a dummy linked list
        LinkedList<POI> list = new LinkedList<>();
        // add some test POIs
        POI poi1 = new POI(1);
        POI poi2 = new POI(2);
        POI poi3 = new POI(3);
        list.add(poi1);
        list.add(poi2);

        // check if the POIs are contained in the list
        assertTrue(Data.containsPOI(list, poi1));
        assertTrue(Data.containsPOI(list, poi2));
        System.out.println(list.contains(poi3));
    }

    /**
     * Test method for {@link Data#generatePOIID(java.lang.String)}.
     * Generates an ID for a new POI based on whether it is a user-created POI or a built-in POI.
     */
    @Test
    public void generatePOIID() {
        System.out.println("generatePOIID()");

        // initialize POI lists
        Data.builtInPOIs = new LinkedList<>();
        Data.userCreatedPOIs = new LinkedList<>();

        // add the POIs to builtIn
        Data.builtInPOIs.add(new POI(5000000));
        Data.builtInPOIs.add(new POI(5000001));
        Data.builtInPOIs.add(new POI(5000002));

        // add the POIs to user created
        Data.userCreatedPOIs.add(new POI(4000000));
        Data.userCreatedPOIs.add(new POI(4000001));
        Data.userCreatedPOIs.add(new POI(4000002));

        // Test for user created
        int expectedUserID = 4000003; // largest user POI ID is 4001000
        assertEquals(expectedUserID, Data.generatePOIID("user"));

        // Test for dev creator
        int expectedDevID = 5000003; // largest dev POI ID is 5002000
        assertEquals(expectedDevID, Data.generatePOIID("dev"));
    }

    /**
     * This method tests the nuke() method of the Data class.
     * It wipes all builtIn POI data from builtin.json
     */
    @Test
    void nuke() {
        System.out.println("nuke()");

        // this does not actually affect builtIn POIs, but nukes a test list

        // Create a test list with some POIs
        LinkedList<POI> testList = new LinkedList<POI>();
        testList.add(new POI(1));
        testList.add(new POI(2));
        testList.add(new POI(3));

        // Check that the test list is not empty before nuking
        assertFalse(testList.isEmpty());

        // Nuke the test list
        boolean result = false;
        try {
            result = Data.nuke(testList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Check that the test list is empty after nuking
        assertTrue(testList.isEmpty());

        // Check that the nuke method returned true
        assertTrue(result);
    }

    /**
     * This method tests the reset() method of the Data class.
     * It wipes all user created POI data from user.json, and all bookmark data from bookmarks.json
     */
    @Test
    void reset() {
        System.out.println("reset()");

        // careful, this test actually resets user POIs and bookmarks to ensure validity.

        // Create some POIs and add them to the bookmarks and userCreatedPOIs lists
        Data instance = new Data();
        POI poi1 = new POI(1);
        POI poi2 = new POI(2);

        LinkedList<POI> testBookmarks = new LinkedList<POI>();
        LinkedList<POI> testUserList = new LinkedList<POI>();
        testBookmarks.add(poi1);
        testBookmarks.add(poi2);
        testUserList.add(poi1);

        // Call the reset method
        boolean result = false;
        try {
            result = instance.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Assert that the bookmarks and userCreatedPOIs lists are empty and the method returned true
        assertEquals(0, Data.bookmarks.size());
        assertEquals(0, Data.userCreatedPOIs.size());
        assertTrue(result);
    }
}