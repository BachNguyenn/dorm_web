// Repository: StudentRepository

package com.example.dorm.repository;

import com.example.dorm.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(
            String name, String email, String phone);
    List<Student> findByRoom_Id(Long roomId);
}