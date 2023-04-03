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
        int expResult = 8103;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * This method tests the setId() method of the POI class.
     */
    @Test
    void setId() {
        System.out.println("setId()");
        POI instance = new POI(8103);
        instance.setId(123);
        int expResult = 123;
        assertEquals(expResult, instance.getId());
    }

    /**
     * This method tests the setName() method of the POI class.
     */
    @Test
    void setName() {
        System.out.println("setName()");
        POI instance = new POI(8103);
        instance.setName("Test Name");
        String expResult = "Test Name";
        assertEquals(expResult, instance.name);
    }

    /**
     * This method tests the setMap() method of the POI class.
     */
    @Test
    void setMap() {
        System.out.println("setMap()");
        POI instance = new POI(8103);
        instance.setMap("PAB2F.png");
        String expResult = "PAB2F.png";
        assertEquals(expResult, instance.map);
    }

    /**
     * This method tests the setCode() method of the POI class.
     */
    @Test
    void setCode() {
        System.out.println("setCode()");
        POI instance = new POI(8103);
        instance.setCode("PAB");
        String expResult = "PAB";
        assertEquals(expResult, instance.code);
    }

    /**
     * This method tests the setFloor() method of the POI class.
     */
    @Test
    void setFloor() {
        System.out.println("setFloor()");
        POI instance = new POI(8103);
        instance.setFloor("Ground Floor");
        String expResult = "Ground Floor";
        assertEquals(expResult, instance.floor);
    }

    /**
     * This method tests the setCategory() method of the POI class.
     */
    @Test
    void setCategory() {
        System.out.println("setCategory()");
        POI instance = new POI(8103);
        instance.setCategory("Classroom");
        String expResult = "Classroom";
        assertEquals(expResult, instance.category);
    }

    /**
     * This method tests the setRoomNumber() method of the POI class.
     */
    @Test
    void setRoomNumber() {
        System.out.println("setRoomNumber()");
        POI instance = new POI(8103);
        instance.setRoomNumber(10);
        int expResult = 10;
        assertEquals(expResult, instance.roomNumber);
    }

    /**
     * This method tests the setX() method of the POI class.
     */
    @Test
    void setX() {
        System.out.println("setX()");
        POI instance = new POI(8103);
        instance.setX(200);
        int expResult = 200;
        assertEquals(expResult, instance.positionX);
    }

    /**
     * This method tests the setY() method of the POI class.
     */
    @Test
    void setY() {
        System.out.println("setY()");
        POI instance = new POI(8103);
        instance.setY(300);
        int expResult = 300;
        assertEquals(expResult, instance.positionY);
    }

    /**
     * This method tests the setDescription() method of the POI class.
     */
    @Test
    void setDescription() {
        System.out.println("setDescription()");
        POI instance = new POI(8103);
        instance.setDescription("desc");
        String expResult = "desc";
        assertEquals(expResult, instance.description);
    }

    /**
     * This method tests the setBuiltIn() method of the POI class.
     */
    @Test
    void setBuiltIn() {
        System.out.println("setBuiltIn()");
        POI instance = new POI(8103);
        instance.setBuiltIn(true);
        boolean expResult = true;
        assertEquals(expResult, instance.isBuiltIn);
    }

    /**
     * This method tests the setPath() method of the POI class.
     */
    @Test
    void setPath() {
        System.out.println("setPath()");
        POI instance = new POI(8103);
        instance.setPath("./maps/PAB0F");
        String expResult = "./maps/PAB0F";
        assertEquals(expResult, instance.path);
    }
}