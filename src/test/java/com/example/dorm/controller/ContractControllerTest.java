package com.example.dorm.controller;

import com.example.dorm.config.SecurityConfig;
import com.example.dorm.model.Contract;
import com.example.dorm.service.ContractService;
import com.example.dorm.service.RoomService;
import com.example.dorm.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for {@link ContractController}.
 */
@WebMvcTest(ContractController.class)
@Import(SecurityConfig.class)
class ContractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractService contractService;
    @MockBean
    private StudentService studentService;
    @MockBean
    private RoomService roomService;

    @Test
    void listContractsReturnsView() throws Exception {
        Page<Contract> page = new PageImpl<>(List.of(new Contract()));
        when(contractService.searchContracts(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/contracts"))
                .andExpect(status().isOk())
                .andExpect(view().name("contracts/list"));
    }
}
