package com.example.dorm.repository;

import com.example.dorm.model.Fee;
import com.example.dorm.model.FeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
    java.util.List<Fee> findByType(FeeType type);
    Page<Fee> findByContract_Student_CodeContainingIgnoreCaseOrContract_Student_NameContainingIgnoreCaseOrTypeContainingIgnoreCase(
            String code, String name, String type, Pageable pageable);
}
