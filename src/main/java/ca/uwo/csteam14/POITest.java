package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the POI class.
 * @author Daniel Gomes
 * @version 1.0.0
 * @since 2023-03-20
 */

class POITest {

    /**
     * This method is called after each test to clean up resources.
     */
    @BeforeEach
    void setUp() {
        System.out.println("setUp()");
    }

    /**
     * This method is called after each test is run to clean up any test data that was created.
     */
    @AfterEach
    void tearDown() {
        System.out.println("tearDown()");
    }

    /**
     * This method tests the getID() method of the POI class.
     */
    @Test
    void getId() {
        System.out.println("getId()");
        POI instance = new POI(8103);  // using this id for every test case.

        int expResult = 8103;  // expected ID
        int result = instance.getId();  // get the actual ID

        // check if results are equal
        assertEquals(expResult, result);
    }

    /**
     * This method tests the setId() method of the POI class.
     */
    @Test
    void setId() {
        System.out.println("setId()");
        POI instance = new POI(8103);

        instance.setId(123);  // set ID
        int expResult = 123;  // expected ID

        // check if results are equal
        assertEquals(expResult, instance.getId());
    }

    /**
     * This method tests the setName() method of the POI class.
     */
    @Test
    void setName() {
        System.out.println("setName()");
        POI instance = new POI(8103);

        instance.setName("Test Name");  // set name
        String expResult = "Test Name";  // expected name

        // check if results are equal
        assertEquals(expResult, instance.name);
    }

    /**
     * This method tests the setMap() method of the POI class.
     */
    @Test
    void setMap() {
        System.out.println("setMap()");
        POI instance = new POI(8103);

        instance.setMap("PAB2F.png");  // set map
        String expResult = "PAB2F.png";  // expected map

        // check if results are equal
        assertEquals(expResult, instance.map);
    }

    /**
     * This method tests the setCode() method of the POI class.
     */
    @Test
    void setCode() {
        System.out.println("setCode()");
        POI instance = new POI(8103);

        instance.setCode("PAB");  // set code
        String expResult = "PAB";  // expected code

        // check if results are equal
        assertEquals(expResult, instance.code);
    }

    /**
     * This method tests the setFloor() method of the POI class.
     */
    @Test
    void setFloor() {
        System.out.println("setFloor()");
        POI instance = new POI(8103);

        instance.setFloor("Ground Floor");  // set floor
        String expResult = "Ground Floor";  // expected floor

        // check if results are equal
        assertEquals(expResult, instance.floor);
    }

    /**
     * This method tests the setCategory() method of the POI class.
     */
    @Test
    void setCategory() {
        System.out.println("setCategory()");
        POI instance = new POI(8103);

        instance.setCategory("Classroom");  // set category
        String expResult = "Classroom";  // expected category

        // check if results are equal
        assertEquals(expResult, instance.category);
    }

    /**
     * This method tests the setRoomNumber() method of the POI class.
     */
    @Test
    void setRoomNumber() {
        System.out.println("setRoomNumber()");
        POI instance = new POI(8103);

        instance.setRoomNumber(10);  // set room num
        int expResult = 10;  // expected room num

        // check if results are equal
        assertEquals(expResult, instance.roomNumber);
    }

    /**
     * This method tests the setX() method of the POI class.
     */
    @Test
    void setX() {
        System.out.println("setX()");
        POI instance = new POI(8103);

        instance.setX(200);  // set x coordinate
        int expResult = 200;  // expected x

        // check if results are equal
        assertEquals(expResult, instance.positionX);
    }

    /**
     * This method tests the setY() method of the POI class.
     */
    @Test
    void setY() {
        System.out.println("setY()");
        POI instance = new POI(8103);

        instance.setY(300);  // set y coordinate
        int expResult = 300;  // expected y

        // check if results are equal
        assertEquals(expResult, instance.positionY);
    }

    /**
     * This method tests the setDescription() method of the POI class.
     */
    @Test
    void setDescription() {
        System.out.println("setDescription()");
        POI instance = new POI(8103);

        instance.setDescription("desc");  // set description
        String expResult = "desc";  // expected description

        // check if results are equal
        assertEquals(expResult, instance.description);
    }

    /**
     * This method tests the setBuiltIn() method of the POI class.
     */
    @Test
    void setBuiltIn() {
        System.out.println("setBuiltIn()");
        POI instance = new POI(8103);

        instance.setBuiltIn(true);  // set creator of POI
        boolean expResult = true;  // expected builtIn or not

        // check if results are equal
        assertEquals(expResult, instance.isBuiltIn);
    }

    /**
     * This method tests the setPath() method of the POI class.
     */
    @Test
    void setPath() {
        System.out.println("setPath()");
        POI instance = new POI(8103);

        instance.setPath("./maps/PAB0F");  // set a path
        String expResult = "./maps/PAB0F";  // expected path

        // check if results are equal
        assertEquals(expResult, instance.path);
    }
}