package com.example.dorm.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Room}.
 */
class RoomTest {

    @Test
    void testGettersAndSetters() {
        Room r = new Room();
        r.setId(2L);
        r.setNumber("A101");
        r.setType("Type1");
        r.setCapacity(2);
        r.setPrice(100);
        Student s = new Student();
        r.setStudents(List.of(s));

        assertEquals(2L, r.getId());
        assertEquals("A101", r.getNumber());
        assertEquals("Type1", r.getType());
        assertEquals(2, r.getCapacity());
        assertEquals(100, r.getPrice());
        assertEquals(1, r.getStudents().size());
        assertNotNull(r.toString());
    }
}
