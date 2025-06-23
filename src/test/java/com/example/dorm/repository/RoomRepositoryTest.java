package com.example.dorm.repository;

import com.example.dorm.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link RoomRepository}.
 */
@DataJpaTest
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    void findByNumberContainingIgnoreCaseOrTypeContainingIgnoreCaseWorks() {
        Room r1 = new Room();
        r1.setNumber("A1");
        r1.setType("Type1");
        roomRepository.save(r1);

        Room r2 = new Room();
        r2.setNumber("B2");
        r2.setType("Type2");
        roomRepository.save(r2);

        Page<Room> result = roomRepository.findByNumberContainingIgnoreCaseOrTypeContainingIgnoreCase("A1", "", PageRequest.of(0,10));
        assertEquals(1, result.getTotalElements());
        assertEquals("A1", result.getContent().get(0).getNumber());
    }
}
