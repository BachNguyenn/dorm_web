package com.example.dorm.service;

import com.example.dorm.model.Contract;
import com.example.dorm.model.Room;
import com.example.dorm.repository.ContractRepository;
import com.example.dorm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getContract(Long id) {
        return contractRepository.findById(id);
    }

    private void checkRoomCapacity(Room room) {
        int current = studentRepository.findByRoom_Id(room.getId()).size();
        if (current >= room.getCapacity()) {
            throw new IllegalStateException("Room capacity exceeded");
        }
    }

    public Contract createContract(Contract contract) {
        checkRoomCapacity(contract.getRoom());
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
        existing.setStartDate(contract.getStartDate());
        existing.setEndDate(contract.getEndDate());
        existing.setStatus(contract.getStatus());
        return contractRepository.save(existing);
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }

    public List<Contract> searchContracts(String search) {
        if (search == null || search.trim().isEmpty()) {
            return contractRepository.findAll();
        }
        return contractRepository
                .findByStudent_NameContainingIgnoreCaseOrRoom_NumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
                        search, search, search);
    }
}
