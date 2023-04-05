package ca.uwo.csteam14;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the LayerFilter class.
 *  @author Daniel Gomes
 *  @version 1.0.0
 *  @since 2023-03-20
 */
class LayerFilterTest {

    /**
     * This method is called after each test to clean up resources.
     */
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    /**
     * This test method tests the isExisting(String layerName) method of LayerFilter.
     */
    @org.junit.jupiter.api.Test
    void isExistingLayerName() {
        System.out.println("isExisting(String layerName)");

        // test if some layer exists
        LayerFilter.selectedLayers.clear();
        assertFalse(LayerFilter.isExisting("Labs"));
        LayerFilter.selectedLayers.add("Labs");

        // check results
        assertTrue(LayerFilter.isExisting("Labs"));
    }

    /**
     * This test method tests the isExisting(POI poi) method of LayerFilter.
     */
    @org.junit.jupiter.api.Test
    void isExistingPOI() {
        System.out.println("isExisting(POI poi)");

        // test if some POI exists in a selected layer
        LayerFilter.POIsOnSelectedLayer.clear();
        POI poi1 = new POI(123456);
        poi1.setX(100);
        poi1.setY(100);
        POI poi2 = new POI(123457);
        poi2.setX(200);
        poi2.setY(200);
        assertFalse(LayerFilter.isExisting(poi1));
        LayerFilter.POIsOnSelectedLayer.add(poi1);

        // check results
        assertTrue(LayerFilter.isExisting(poi1));
        assertFalse(LayerFilter.isExisting(poi2));
    }

    /**
     * This test method tests the getLayerIcon() method of LayerFilter.
     */
    @org.junit.jupiter.api.Test
    void getLayerIcon() {
        System.out.println("getLayerIcon()");

        // create a layer and confirm its path
        String layer = "Classroom";
        String expResult = "./images/classroom.png";
        assertEquals(expResult, LayerFilter.getLayerIcon(layer));
    }
}
