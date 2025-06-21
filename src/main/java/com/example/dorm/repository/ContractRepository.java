package com.example.dorm.repository;

import com.example.dorm.model.Contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    Page<Contract> findByStudent_CodeContainingIgnoreCaseOrStudent_NameContainingIgnoreCaseOrRoom_NumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String code, String studentName, String roomNumber, String status, Pageable pageable);

    java.util.List<Contract> findByRoom_Id(Long roomId);

    long countByRoom_Id(Long roomId);
}
