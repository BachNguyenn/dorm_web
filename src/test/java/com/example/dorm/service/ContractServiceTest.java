package com.example.dorm.service;

import com.example.dorm.model.Contract;
import com.example.dorm.model.Room;
import com.example.dorm.model.Student;
import com.example.dorm.repository.ContractRepository;
import com.example.dorm.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContractServiceTest {

    @Mock
    private ContractRepository contractRepository;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private ContractService contractService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createContractFailsWhenRoomFull() {
        Room room = new Room();
        room.setId(1L);
        room.setCapacity(1);
        when(studentRepository.findByRoom_Id(1L)).thenReturn(List.of(new Student()));
        Contract contract = new Contract();
        contract.setRoom(room);
        assertThrows(IllegalStateException.class, () -> contractService.createContract(contract));
    }

    @Test
    void updateContractChecksCapacityWhenRoomChanged() {
        Room oldRoom = new Room();
        oldRoom.setId(1L);
        Room newRoom = new Room();
        newRoom.setId(2L);
        newRoom.setCapacity(1);
        Contract existing = new Contract();
        existing.setId(10L);
        existing.setRoom(oldRoom);
        when(contractRepository.findById(10L)).thenReturn(Optional.of(existing));
        when(studentRepository.findByRoom_Id(2L)).thenReturn(Collections.singletonList(new Student()));

        Contract update = new Contract();
        update.setRoom(newRoom);
        assertThrows(IllegalStateException.class, () -> contractService.updateContract(10L, update));
    }
}
