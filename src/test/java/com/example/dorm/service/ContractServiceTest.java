package com.example.dorm.service;

import com.example.dorm.model.Contract;
import com.example.dorm.model.Room;
import com.example.dorm.model.Student;
import com.example.dorm.repository.ContractRepository;
import com.example.dorm.repository.StudentRepository;
import com.example.dorm.repository.RoomRepository;
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
    @Mock
    private RoomRepository roomRepository;

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
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(studentRepository.countByRoom_Id(1L)).thenReturn(1L);
        Contract contract = new Contract();
        contract.setRoom(room);
        Student s = new Student();
        contract.setStudent(s);
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
        when(roomRepository.findById(2L)).thenReturn(Optional.of(newRoom));
        when(studentRepository.countByRoom_Id(2L)).thenReturn(1L);

        Contract update = new Contract();
        update.setRoom(newRoom);
        update.setStudent(new Student());
        assertThrows(IllegalStateException.class, () -> contractService.updateContract(10L, update));
    }

    @Test
    void searchContractsUsesCustomQuery() {
        org.springframework.data.domain.Page<Contract> page =
                new org.springframework.data.domain.PageImpl<>(java.util.Collections.emptyList());
        when(contractRepository.searchByStudentWordOrCodeOrRoomOrStatus(eq("An"), any(org.springframework.data.domain.Pageable.class)))
                .thenReturn(page);
        var result = contractService.searchContracts("An", org.springframework.data.domain.Pageable.unpaged());
        assertSame(page, result);
        verify(contractRepository).searchByStudentWordOrCodeOrRoomOrStatus(eq("An"), any(org.springframework.data.domain.Pageable.class));
    }

    @Test
    void createContractUpdatesStudentRoom() {
        Room room = new Room();
        room.setId(3L);
        room.setCapacity(2);
        Student student = new Student();
        student.setId(11L);

        when(roomRepository.findById(3L)).thenReturn(Optional.of(room));
        when(studentRepository.countByRoom_Id(3L)).thenReturn(0L);
        when(studentRepository.findById(11L)).thenReturn(Optional.of(student));
        when(contractRepository.save(any(Contract.class))).thenAnswer(i -> i.getArgument(0));
        when(studentRepository.save(any(Student.class))).thenAnswer(i -> i.getArgument(0));

        Contract contract = new Contract();
        contract.setRoom(room);
        contract.setStudent(student);
        Contract saved = contractService.createContract(contract);

        assertSame(room, saved.getRoom());
        assertSame(room, student.getRoom());
    }
}
