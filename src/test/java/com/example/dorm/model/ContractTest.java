package com.example.dorm.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Contract}.
 */
class ContractTest {

    @Test
    void testGettersAndSetters() {
        Contract c = new Contract();
        c.setId(1L);
        Student s = new Student();
        c.setStudent(s);
        Room r = new Room();
        c.setRoom(r);
        LocalDate start = LocalDate.now();
        c.setStartDate(start);
        LocalDate end = start.plusDays(1);
        c.setEndDate(end);
        c.setStatus("ACTIVE");

        assertEquals(1L, c.getId());
        assertSame(s, c.getStudent());
        assertSame(r, c.getRoom());
        assertEquals(start, c.getStartDate());
        assertEquals(end, c.getEndDate());
        assertEquals("ACTIVE", c.getStatus());
        assertNotNull(c.toString());
    }
}
