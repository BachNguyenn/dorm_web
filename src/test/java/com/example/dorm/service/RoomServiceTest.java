package com.example.dorm.service;

import com.example.dorm.model.Room;
import com.example.dorm.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRoomSetsPrice() {
        Room room = new Room();
        room.setType("Phòng bốn");
        when(roomRepository.save(any(Room.class))).thenAnswer(i -> i.getArgument(0));
        Room saved = roomService.createRoom(room);
        assertEquals(2000000, saved.getPrice());
    }
}
