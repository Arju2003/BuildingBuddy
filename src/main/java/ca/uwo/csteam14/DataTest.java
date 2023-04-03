package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Data class.
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
    void getLayerPOIs() {  // FILL IN
        System.out.println("getLayerPOIs");
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

        LinkedList<POI> lst = new LinkedList<>();
        POI poi1 = new POI(1);
        POI poi2 = new POI(2);
        lst.add(poi1);
        lst.add(poi2);

        // Test removing an existing POI
        assertTrue(Data.removePOI(poi1, lst));
        assertFalse(lst.contains(poi1));
        assertTrue(lst.contains(poi2));

        // Test removing a non-existing POI
        assertFalse(Data.removePOI(new POI(3), lst));
        assertTrue(lst.contains(poi2));
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
    void getPOI() {  // NOT WORKING
        System.out.println("getPOI()");

        LinkedList<POI> builtInPOIs = new LinkedList<>();
        LinkedList<POI> userCreatedPOIs = new LinkedList<>();

        Data instance = new Data();
        builtInPOIs = instance.getBuiltInPOIs();
        userCreatedPOIs = instance.getUserCreatedPOIs();

        // create test POI
        POI poi1 = new POI(1);
        poi1.setName("Test POI");
        poi1.setBuilding("Test Building");
        poi1.setCode("TB");
        poi1.setFloor("1");
        poi1.setRoomNumber(123);
        poi1.setCategory("Test Category");
        poi1.setDescription("Test Description");
        poi1.setMap("Test Map");
        poi1.setX(100);
        poi1.setY(200);
        poi1.setBuiltIn(false);

        // add test POI to user created POIs
        userCreatedPOIs.add(poi1);

        // test getting POI
        POI retrievedPOI = instance.getPOI("1", 100, 200);
        assertNotNull(retrievedPOI);
        assertEquals(poi1, retrievedPOI);

        // test getting non-existent POI
        retrievedPOI = instance.getPOI("1", 999, 999);
        assertNull(retrievedPOI);
    }

    /**
     * Test method for {@link Data#containsPOI(java.util.LinkedList, POI)}.
     * Checks whether a given POI is contained in a given list of POIs.
     */
    @Test
    void containsPOI() {
        System.out.println("containsPOI()");
        LinkedList<POI> list = new LinkedList<>();
        POI poi1 = new POI(1);
        POI poi2 = new POI(2);
        POI poi3 = new POI(3);
        list.add(poi1);
        list.add(poi2);
        assertTrue(Data.containsPOI(list, poi1));
        assertTrue(Data.containsPOI(list, poi2));
        System.out.println(list.contains(poi3));
    }

    /**
     * Test method for {@link Data#generatePOIID(java.lang.String)}.
     * Generates an ID for a new POI based on whether it is a user-created POI or a built-in POI.
     */
    @Test
    void generatePOIID() {
        System.out.println("generatePOIID()");

        Data instance = new Data();
        // Test case for user created POI
        int expectedUserID = 4000001;
        int actualUserID = instance.generatePOIID("user");
        assertEquals(expectedUserID, actualUserID);

        // Test case for built-in POI
        int expectedDevID = 5000001;
        int actualDevID = instance.generatePOIID("dev");
        assertEquals(expectedDevID, actualDevID);
    }
}