package ca.uwo.csteam14;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The LayerFilterTest class tests the methods in the LayerFilter class.
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

        LayerFilter.selectedLayers.clear();
        assertFalse(LayerFilter.isExisting("Labs"));
        LayerFilter.selectedLayers.add("Labs");
        assertTrue(LayerFilter.isExisting("Labs"));
    }

    /**
     * This test method tests the isExisting(POI poi) method of LayerFilter.
     */
    @org.junit.jupiter.api.Test
    void isExistingPOI() {
        System.out.println("isExisting(POI poi)");
        LayerFilter.POIsOnSelectedLayer.clear();
        POI poi1 = new POI(123456);
        poi1.setX(100);
        poi1.setY(100);
        POI poi2 = new POI(123457);
        poi2.setX(200);
        poi2.setY(200);
        assertFalse(LayerFilter.isExisting(poi1));
        LayerFilter.POIsOnSelectedLayer.add(poi1);
        assertTrue(LayerFilter.isExisting(poi1));
        assertFalse(LayerFilter.isExisting(poi2));
    }

    /**
     * This test method tests the getLayerIcon() method of LayerFilter.
     */
    @org.junit.jupiter.api.Test
    void getLayerIcon() {
        System.out.println("getLayerIcon()");
        String layer = "Classroom";
        String expResult = "./images/classroom.png";
        assertEquals(expResult, LayerFilter.getLayerIcon(layer));
    }
}
