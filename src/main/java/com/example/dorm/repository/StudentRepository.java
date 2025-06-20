// Repository: StudentRepository

package com.example.dorm.repository;

import com.example.dorm.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByNameIgnoreCase(String name);
    List<Student> findByRoom_Id(Long roomId);
}