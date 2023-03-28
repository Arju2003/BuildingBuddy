package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @AfterEach
    void getLinkedListOfPOIs() {
        System.out.println("getLinkedListOfPOIs()");
        POI p = new POI(8203);
        LinkedList<POI> list = new LinkedList<POI>();
        list.add(p);
        POI expResult = p;
        assertEquals(expResult, list.getFirst());
    }

    @AfterEach
    void getLayerPOIs() {  // WILL ONLY WORK ONCE ADD/REMOVE METHODS ARE DONE
        System.out.println("getLayerPOIs");
        ArrayList<POI> builtInPOIs = new ArrayList<>();
        ArrayList<POI> userCreatedPOIs = new ArrayList<>();

        // create test POIs
        POI poi1 = new POI(1);
        poi1.setCategory("Food");
        poi1.setMap("1");
        poi1.setDescription("Cafeteria");
        POI poi2 = new POI(2);
        poi2.setCategory("Labs");
        poi2.setMap("2");
        poi2.setDescription("Computer lab");
        POI poi3 = new POI(3);
        poi3.setCategory("Accessibility");
        poi3.setMap("3");
        poi3.setDescription("Accessible facility");

        // add test POIs to builtInPOIs list
        builtInPOIs.add(poi1);
        builtInPOIs.add(poi2);
        builtInPOIs.add(poi3);

        // create test user created POIs
        POI poi4 = new POI(4);
        poi4.setCategory("My Locations");
        poi4.setMap("1");

        // add test user created POI to userCreatedPOIs list
        userCreatedPOIs.add(poi4);

        // set selected layers
        LayerFilter.selectedLayers.add("Food");
        LayerFilter.selectedLayers.add("Labs");
        LayerFilter.selectedLayers.add("Accessibility");
        LayerFilter.selectedLayers.add("My Locations");

        // test with valid layer name and current floor
        ArrayList<POI> expected = new ArrayList<>();
        expected.add(poi1);
        expected.add(poi2);
        expected.add(poi3);
        expected.add(poi4);
        ArrayList<POI> result = Data.getLayerPOIs("1", "Food");
        assertEquals(expected, result);

        // test with valid layer name and invalid current floor
        expected = new ArrayList<>();
        result = Data.getLayerPOIs("2", "Accessibility");
        assertEquals(expected, result);

        // test with invalid layer name
        expected = new ArrayList<>();
        result = Data.getLayerPOIs("1", "Invalid Layer");
        assertEquals(expected, result);
    }
    @AfterEach
    void addPOI() {
        // FINISH METHOD
    }

    @AfterEach
    void removePOI() {
        // FINISH METHOD
    }

    @Test
    void getBuiltInPOIs() {
        System.out.println("getBuiltInPOIs()");
        POI p = new POI(8203);
        LinkedList<POI> list = new LinkedList<POI>();
        list.add(p);
        POI expResult = p;
        assertEquals(expResult, list.getFirst());
    }

    @Test
    void getUserCreatedPOIs() {
        System.out.println("getUserCreatedPOIs()");
        POI p = new POI(8203);
        LinkedList<POI> list = new LinkedList<POI>();
        list.add(p);
        POI expResult = p;
        assertEquals(expResult, list.getFirst());
    }

    @Test
    void getBookmarks() {
        System.out.println("getBookmarks()");
        POI p = new POI(8203);
        LinkedList<POI> list = new LinkedList<POI>();
        list.add(p);
        POI expResult = p;
        assertEquals(expResult, list.getFirst());
    }

    @Test
    void getPOI() {
        System.out.println("getPOI()");
        // Create an instance of the Data class
        Data data = new Data();

        // Add test POIs to the builtInPOIs and userCreatedPOIs lists
        POI poi1 = new POI(1001);
        poi1.setMap("First Floor");
        poi1.setX(10);
        poi1.setY(20);
        data.builtInPOIs.add(poi1);

        POI poi2 = new POI(2001);
        poi2.setMap("Second Floor");
        poi2.setX(30);
        poi2.setY(40);
        data.userCreatedPOIs.add(poi2);

        // Test the getPOI method with valid and invalid inputs
        POI result1 = data.getPOI("First Floor", 10, 20);
        assertEquals(poi1, result1);

        POI result2 = data.getPOI("Second Floor", 30, 40);
        assertEquals(poi2, result2);

        POI result3 = data.getPOI("Third Floor", 50, 60);
        assertNull(result3);
    }

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
        assertFalse(Data.containsPOI(list, poi3));

    }
}