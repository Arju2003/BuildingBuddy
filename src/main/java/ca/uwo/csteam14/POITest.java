package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class POITest {

    @BeforeEach
    void setUp() {
        System.out.println("setUp()");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown()");
    }

    @Test
    void getId() {
        System.out.println("getId()");
        POI instance = new POI(8103);  // using this id for every test case.
        int expResult = 8103;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    void setId() {
        System.out.println("setId()");
        POI instance = new POI(8103);
        instance.setId(123);
        int expResult = 123;
        assertEquals(expResult, instance.getId());
    }

    @Test
    void setName() {
        System.out.println("setName()");
        POI instance = new POI(8103);
        instance.setName("Test Name");
        String expResult = "Test Name";
        assertEquals(expResult, instance.name);
    }

    @Test
    void setMap() {
        System.out.println("setMap()");
        POI instance = new POI(8103);
        instance.setMap("PAB2F.png");
        String expResult = "PAB2F.png";
        assertEquals(expResult, instance.map);
    }

    @Test
    void setCode() {
        System.out.println("setCode()");
        POI instance = new POI(8103);
        instance.setCode("PAB");
        String expResult = "PAB";
        assertEquals(expResult, instance.code);
    }

    @Test
    void setFloor() {
        System.out.println("setFloor()");
        POI instance = new POI(8103);
        instance.setFloor("Ground Floor");
        String expResult = "Ground Floor";
        assertEquals(expResult, instance.floor);
    }

    @Test
    void setCategory() {
        System.out.println("setCategory()");
        POI instance = new POI(8103);
        instance.setCategory("Classroom");
        String expResult = "Classroom";
        assertEquals(expResult, instance.category);
    }

    @Test
    void setRoomNumber() {
        System.out.println("setRoomNumber()");
        POI instance = new POI(8103);
        instance.setRoomNumber(10);
        int expResult = 10;
        assertEquals(expResult, instance.roomNumber);
    }


    @Test
    void setX() {
        System.out.println("setX()");
        POI instance = new POI(8103);
        instance.setX(200);
        int expResult = 200;
        assertEquals(expResult, instance.positionX);
    }

    @Test
    void setY() {
        System.out.println("setY()");
        POI instance = new POI(8103);
        instance.setY(300);
        int expResult = 300;
        assertEquals(expResult, instance.positionY);
    }

    @Test
    void setDescription() {
        System.out.println("setDescription()");
        POI instance = new POI(8103);
        instance.setDescription("desc");
        String expResult = "desc";
        assertEquals(expResult, instance.description);
    }

    @Test
    void setBuiltIn() {
        System.out.println("setBuiltIn()");
        POI instance = new POI(8103);
        instance.setBuiltIn(true);
        boolean expResult = true;
        assertEquals(expResult, instance.isBuiltIn);
    }

    @Test
    void setPath() {
        System.out.println("setPath()");
        POI instance = new POI(8103);
        instance.setPath("./maps/PAB0F");
        String expResult = "./maps/PAB0F";
        assertEquals(expResult, instance.path);
    }
}