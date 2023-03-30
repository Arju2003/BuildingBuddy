package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void searchResults() {
        System.out.println("searchResults()");

        Data instance = new Data();

        // create some POI objects with specific attributes
        POI poi1 = new POI(1);
        poi1.setName("Bathroom");
        poi1.setCategory("Restrooms");
        poi1.setDescription("This is a bathroom");
        poi1.setMap("First Floor");
        instance.builtInPOIs.add(poi1);

        POI poi2 = new POI(2);
        poi2.setName("Math Department");
        poi2.setCategory("Academic Departments");
        poi2.setDescription("The math department is located on the second floor");
        poi2.setMap("Second Floor");
        instance.builtInPOIs.add(poi2);

        POI poi3 = new POI(4000001);
        poi3.setName("My House");
        poi3.setCategory("My Locations");
        poi3.setDescription("This is where I live");
        poi3.setMap("Home");
        instance.userCreatedPOIs.add(poi3);

        // test searching for a POI by name
        LinkedList<POI> searchResults = Search.searchResults("Bathroom");
        assertNotNull(searchResults);
        assertTrue(searchResults.contains(poi1));

        // test searching for a POI by category
        searchResults = Search.searchResults("Academic Departments");
        assertNotNull(searchResults);
        assertTrue(searchResults.contains(poi2));;

        // test searching for a POI by description
        searchResults = Search.searchResults("located on the second floor");
        assertNotNull(searchResults);
        assertTrue(searchResults.contains(poi2));

        // test searching for a POI by map
        searchResults = Search.searchResults("First Floor");
        assertNotNull(searchResults);
        assertTrue(searchResults.contains(poi1));

        // test searching for a user-created POI
        searchResults = Search.searchResults("My House");
        assertNotNull(searchResults);
        assertTrue(searchResults.contains(poi3));
    }
}