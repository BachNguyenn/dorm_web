package com.example.dorm.controller;

import com.example.dorm.config.SecurityConfig;
import com.example.dorm.model.Student;
import com.example.dorm.service.ContractService;
import com.example.dorm.service.RoomService;
import com.example.dorm.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Controller tests for {@link StudentController}.
 */
@WebMvcTest(StudentController.class)
@Import(SecurityConfig.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    @MockBean
    private RoomService roomService;
    @MockBean
    private ContractService contractService;

    @Test
    void listStudentsReturnsView() throws Exception {
        Page<Student> page = new PageImpl<>(List.of(new Student()));
        when(studentService.searchStudents(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/list"))
                .andExpect(model().attributeExists("studentsPage"));

        verify(studentService).searchStudents(anyString(), any(Pageable.class));
    }
}
