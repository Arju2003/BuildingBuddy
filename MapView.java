public class MapView {

    private boolean hasMap;
    private FloorMap map;

    // I set hasMap to false initially since when the application starts there is no map on the screen
    // idk if that was the right thing to do
    public MapView() {
        hasMap = false;
        map = new FloorMap();
    }
    // not really sure what to do here but hopefully this makes sense?
    public void loadMap(FloorMap map) {
        this.map = map;
        hasMap = true;
    }


    // Browsing Maps,
    // Scrolling Maps,
    // Displaying Layers,
    // Searching Maps,
    // POI Discovery,
    // Clicking on POIs,
    // Exit/Close Nav,
    // User Help,
    // Editing Mode.

    // build these and display in Container.

}
