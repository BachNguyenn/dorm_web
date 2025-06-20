package com.example.dorm.repository;

import com.example.dorm.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
    // Search by related contract fields or fee type
    java.util.List<Fee> findByContract_Student_NameContainingIgnoreCaseOrContract_Room_NumberContainingIgnoreCaseOrTypeContainingIgnoreCase(
            String studentName, String roomNumber, String type);
}