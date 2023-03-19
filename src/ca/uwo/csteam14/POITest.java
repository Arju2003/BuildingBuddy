package ca.uwo.csteam14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class POITest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
        System.out.println("getId");
        POI instance = new POI(8103);
        int expResult = 8103;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getName() {
        System.out.println("getName");
        POI instance = new POI(8103);
        String expResult = "KB 103";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    void getMap() {
    }

    @Test
    void getCode() {
    }

    @Test
    void getFloor() {
    }

    @Test
    void getCategory() {
    }

    @Test
    void getRoomNum() {
    }

    @Test
    void getPath() {
    }

    @Test
    void getX() {
    }

    @Test
    void getY() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void getCreator() {
    }

    @Test
    void setId() {
    }

    @Test
    void setName() {
    }

    @Test
    void setMap() {
    }

    @Test
    void setCode() {
    }

    @Test
    void setFloor() {
    }

    @Test
    void setCategory() {
    }

    @Test
    void setRoomNum() {
    }

    @Test
    void setPath() {
    }

    @Test
    void setX() {
    }

    @Test
    void setY() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void setCreator() {
    }
}