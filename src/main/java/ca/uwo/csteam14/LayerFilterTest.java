package ca.uwo.csteam14;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LayerFilterTest {

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void isExistingLayerName() {
        System.out.println("isExisting(String layerName)");

        LayerFilter.selectedLayers.clear();
        assertFalse(LayerFilter.isExisting("Labs"));
        LayerFilter.selectedLayers.add("Labs");
        assertTrue(LayerFilter.isExisting("Labs"));
    }

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

    @org.junit.jupiter.api.Test
    void getLayerIcon() {
        System.out.println("getLayerIcon()");
        String layer = "Classroom";
        String expResult = "./images/classroom.png";
        assertEquals(expResult, LayerFilter.getLayerIcon(layer));
    }
}