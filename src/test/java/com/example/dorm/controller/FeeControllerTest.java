package com.example.dorm.controller;

import com.example.dorm.config.SecurityConfig;
import com.example.dorm.model.Fee;
import com.example.dorm.service.ContractService;
import com.example.dorm.service.FeeService;
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
 * Tests for {@link FeeController}.
 */
@WebMvcTest(FeeController.class)
@Import(SecurityConfig.class)
class FeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeeService feeService;
    @MockBean
    private ContractService contractService;

    @Test
    void listFeesReturnsView() throws Exception {
        Page<Fee> page = new PageImpl<>(List.of(new Fee()));
        when(feeService.searchFees(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/fees"))
                .andExpect(status().isOk())
                .andExpect(view().name("fees/list"));
    }
}
