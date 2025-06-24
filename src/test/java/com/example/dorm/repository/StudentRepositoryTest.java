package com.example.dorm.repository;

import com.example.dorm.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link StudentRepository}.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void searchByCodeOrNameWordMatchesNamePart() {
        Student s1 = new Student();
        s1.setCode("A1");
        s1.setName("Alice Nguyen");
        studentRepository.save(s1);

        Student s2 = new Student();
        s2.setCode("B1");
        s2.setName("Bob");
        studentRepository.save(s2);

        Page<Student> result = studentRepository.searchByCodeOrNameWord("Alice", PageRequest.of(0,10));
        assertEquals(1, result.getTotalElements());
        assertEquals("Alice Nguyen", result.getContent().get(0).getName());
    }
}
