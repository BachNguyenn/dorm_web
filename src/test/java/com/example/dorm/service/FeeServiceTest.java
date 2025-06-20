package com.example.dorm.service;

import com.example.dorm.model.Fee;
import com.example.dorm.repository.FeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeeServiceTest {

    @Mock
    private FeeRepository feeRepository;

    @InjectMocks
    private FeeService feeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateFeeDelegatesSave() {
        Fee existing = new Fee();
        existing.setId(1L);
        when(feeRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(feeRepository.save(any(Fee.class))).thenAnswer(i -> i.getArgument(0));
        Fee update = new Fee();
        Fee result = feeService.updateFee(1L, update);
        assertNotNull(result);
        verify(feeRepository).save(existing);
    }
}
