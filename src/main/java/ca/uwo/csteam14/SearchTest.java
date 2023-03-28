package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        // create a new Search object
        Search search = new Search();

        // search for a keyword that is expected to return a result
        String userInput1 = "library";
        LinkedList<POI> results1 = search.searchResults(userInput1);
        assertNotNull(results1);
        assertEquals(1, results1.size());
        assertEquals("Weldon Library", results1.get(0).name);

        // search for a keyword that is expected to return multiple results
        String userInput2 = "food";
        LinkedList<POI> results2 = search.searchResults(userInput2);
        assertNotNull(results2);
        assertEquals(3, results2.size());

        // search for a keyword that is not expected to return any results
        String userInput3 = "xyz";
        LinkedList<POI> results3 = search.searchResults(userInput3);
        assertEquals(null, results3);
    }
}