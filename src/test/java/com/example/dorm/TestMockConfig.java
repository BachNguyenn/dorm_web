package com.example.dorm;

import com.example.dorm.service.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestMockConfig {
    @Bean
    public ContractService contractService() {
        return Mockito.mock(ContractService.class);
    }

    @Bean
    public StudentService studentService() {
        return Mockito.mock(StudentService.class);
    }

    @Bean
    public RoomService roomService() {
        return Mockito.mock(RoomService.class);
    }

    @Bean
    public FeeService feeService() {
        return Mockito.mock(FeeService.class);
    }
}
