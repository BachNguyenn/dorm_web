package com.example.dorm.service;

import com.example.dorm.model.Contract;
import com.example.dorm.model.Room;
import com.example.dorm.repository.ContractRepository;
import com.example.dorm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Page<Contract> getAllContracts(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getContract(Long id) {
        return contractRepository.findById(id);
    }

    private void checkRoomCapacity(Room room) {
        long current = contractRepository.countByRoom_Id(room.getId());
        if (current >= room.getCapacity()) {
            throw new IllegalStateException("Room capacity exceeded");
        }
    }

    public Contract createContract(Contract contract) {
        checkRoomCapacity(contract.getRoom());
        // assign student to the selected room
        contract.getStudent().setRoom(contract.getRoom());
        studentRepository.save(contract.getStudent());
        return contractRepository.save(contract);
    }

    public Contract updateContract(Long id, Contract contract) {
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found"));
        if (!existing.getRoom().getId().equals(contract.getRoom().getId())) {
            checkRoomCapacity(contract.getRoom());
        }
        existing.setStudent(contract.getStudent());
        existing.setRoom(contract.getRoom());
        contract.getStudent().setRoom(contract.getRoom());
        studentRepository.save(contract.getStudent());
        existing.setStartDate(contract.getStartDate());
        existing.setEndDate(contract.getEndDate());
        existing.setStatus(contract.getStatus());
        return contractRepository.save(existing);
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }

    public Page<Contract> searchContracts(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return contractRepository.findAll(pageable);
        }
        return contractRepository
                .findByStudent_CodeContainingIgnoreCaseOrStudent_NameContainingIgnoreCaseOrRoom_NumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
                        search, search, search, search, pageable);
    }
}
