package com.example.dorm.repository;

import com.example.dorm.model.Fee;
import com.example.dorm.model.FeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
    java.util.List<Fee> findByType(FeeType type);
}