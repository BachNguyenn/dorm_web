package com.example.dorm.service;

import com.example.dorm.model.Fee;
import com.example.dorm.model.FeeType;
import com.example.dorm.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeService {

    @Autowired
    private FeeRepository feeRepository;

    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    public Optional<Fee> getFee(Long id) {
        return feeRepository.findById(id);
    }

    public Fee createFee(Fee fee) {
        return feeRepository.save(fee);
    }

    public Fee updateFee(Long id, Fee fee) {
        Fee existing = feeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fee not found"));
        existing.setAmount(fee.getAmount());
        existing.setContract(fee.getContract());
        existing.setType(fee.getType());
        existing.setDueDate(fee.getDueDate());
        existing.setPaymentStatus(fee.getPaymentStatus());
        return feeRepository.save(existing);
    }

    public void deleteFee(Long id) {
        feeRepository.deleteById(id);
    }

    /**
     * Search fees by id, student name, room number or fee type.
     */
    public List<Fee> searchFees(String search) {
        if (search == null || search.trim().isEmpty()) {
            return feeRepository.findAll();
        }
        try {
            Long id = Long.parseLong(search);
            return feeRepository.findById(id)
                    .map(java.util.List::of)
                    .orElse(java.util.Collections.emptyList());
        } catch (NumberFormatException e) {
            String term = search.trim();
            java.util.List<Fee> results = feeRepository
                    .findByContract_Student_NameContainingIgnoreCaseOrContract_Room_NumberContainingIgnoreCase(
                            term, term);

            // attempt to match fee type
            FeeType type = null;
            try {
                type = FeeType.valueOf(term.toUpperCase());
            } catch (IllegalArgumentException ignored) {
                // not an exact enum value
            }

            if (type != null) {
                results.addAll(feeRepository.findByType(type));
            } else {
                results = results.stream()
                        .filter(f -> f.getType().name().toLowerCase().contains(term.toLowerCase()))
                        .collect(java.util.stream.Collectors.toList());
            }

            return results.stream().distinct().collect(java.util.stream.Collectors.toList());
        }
    }
}
