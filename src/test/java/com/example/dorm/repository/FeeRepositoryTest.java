package com.example.dorm.repository;

import com.example.dorm.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link FeeRepository}.
 */
@DataJpaTest
class FeeRepositoryTest {

    @Autowired
    private FeeRepository feeRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void searchByContractStudentWordWorks() {
        Room room = new Room();
        room.setNumber("R1");
        room.setCapacity(5);
        room = roomRepository.save(room);

        Student student = new Student();
        student.setCode("C1");
        student.setName("Charlie");
        student.setRoom(room);
        student = studentRepository.save(student);

        Contract contract = new Contract();
        contract.setRoom(room);
        contract.setStudent(student);
        contract = contractRepository.save(contract);

        Fee fee = new Fee();
        fee.setContract(contract);
        fee.setType(FeeType.CLEANING);
        fee.setAmount(BigDecimal.ONE);
        feeRepository.save(fee);

        Page<Fee> result = feeRepository.searchByContractStudentWord("Charlie", PageRequest.of(0,10));
        assertEquals(1, result.getTotalElements());
        assertEquals(FeeType.CLEANING, result.getContent().get(0).getType());
    }
}
