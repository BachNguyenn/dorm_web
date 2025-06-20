package com.example.dorm.repository;

import com.example.dorm.model.Fee;
import com.example.dorm.model.FeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
    // Search fees by student name or room number
    java.util.List<Fee> findByContract_Student_NameContainingIgnoreCaseOrContract_Room_NumberContainingIgnoreCase(
            String studentName, String roomNumber);

    // Find fees by exact fee type
    java.util.List<Fee> findByType(FeeType type);
}