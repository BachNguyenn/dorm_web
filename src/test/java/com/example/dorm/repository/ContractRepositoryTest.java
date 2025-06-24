package com.example.dorm.repository;

import com.example.dorm.model.Contract;
import com.example.dorm.model.Room;
import com.example.dorm.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link ContractRepository}.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void countByRoomIdWorks() {
        Room room = new Room();
        room.setNumber("R1");
        room.setCapacity(5);
        room = roomRepository.save(room);

        Student student = new Student();
        student.setCode("S1");
        student.setName("Test");
        student.setRoom(room);
        student = studentRepository.save(student);

        Contract contract = new Contract();
        contract.setRoom(room);
        contract.setStudent(student);
        contractRepository.save(contract);

        long count = contractRepository.countByRoom_Id(room.getId());
        assertEquals(1, count);
    }
}
