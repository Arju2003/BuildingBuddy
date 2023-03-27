package ca.uwo.csteam14;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LayerFilterTest {

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void refreshLayers() {
        System.out.println("refreshLayers()");
        // Set up the required variables and objects
        BuildingBuddy.currentFloor = "Floor1";
        BuildingBuddy.currentBuildingCode = "ABC";
        ArrayList<String> selectedLayers = new ArrayList<>();
        selectedLayers.add("Layer1");
        selectedLayers.add("Layer2");
        LayerFilter.selectedLayers = selectedLayers;

        // Call the method
        try {
            LayerFilter.refreshLayers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Check that the map view has been updated
        assertNotNull(LayerFilter.currentMapView);

        // Check that the base map image has been updated
        assertNotNull(LayerFilter.baseMapImage);

        // Check that the POIs on the selected layer have been updated
        assertNotNull(LayerFilter.POIsOnSelectedLayer);
        assertFalse(LayerFilter.POIsOnSelectedLayer.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void isExisting() {
        System.out.println("isExisting()");
        // for isExisting(String layer)
        String testlayer = "layer";
        assertTrue(LayerFilter.isExisting(testlayer));
        // for isExisting(POI poi)
        POI testPOI = new POI(1);
        assertTrue(LayerFilter.isExisting(testPOI));
    }

    @org.junit.jupiter.api.Test
    void getLayerIcon() {
        System.out.println("getLayerIcon()");
        String layer = "Classroom";
        String expResult = "./images/classroom.png";
        assertEquals(expResult, LayerFilter.getLayerIcon(layer));
    }
}