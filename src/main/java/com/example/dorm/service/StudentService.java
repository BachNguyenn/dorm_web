package com.example.dorm.service;

import com.example.dorm.model.Student;
import com.example.dorm.repository.StudentRepository;
import com.example.dorm.repository.RoomRepository;
import com.example.dorm.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudents(String search) {
        if (search == null || search.trim().isEmpty()) {
            return studentRepository.findAll();
        }
        try {
            Long id = Long.parseLong(search);
            return studentRepository.findById(id)
                    .map(java.util.List::of)
                    .orElse(java.util.Collections.emptyList());
        } catch (NumberFormatException e) {
            String term = search.trim();
            return studentRepository
                    .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
                            term, term, term, term);
        }
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
