package com.example.dorm.service;

import com.example.dorm.model.Student;
import com.example.dorm.repository.RoomRepository;
import com.example.dorm.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchStudentsEmptyReturnsAll() {
        List<Student> all = Collections.singletonList(new Student());
        when(studentRepository.findAll()).thenReturn(all);
        List<Student> result = studentService.searchStudents("");
        assertEquals(1, result.size());
        verify(studentRepository).findAll();
    }

    @Test
    void searchStudentsByName() {
        List<Student> found = Arrays.asList(new Student(), new Student());
        when(studentRepository.findByNameIgnoreCase("test")).thenReturn(found);
        List<Student> result = studentService.searchStudents("test");
        assertEquals(2, result.size());
        verify(studentRepository).findByNameIgnoreCase("test");
    }
}
