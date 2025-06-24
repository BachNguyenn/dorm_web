package com.example.dorm.controller;

import com.example.dorm.config.SecurityConfig;
import com.example.dorm.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import com.example.dorm.TestMockConfig;
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
 * Tests for {@link RoomController}.
 */
@WebMvcTest(RoomController.class)
@Import({SecurityConfig.class, TestMockConfig.class})
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoomService roomService;

    @Test
    void listRoomsReturnsView() throws Exception {
        Page<com.example.dorm.model.Room> page = new PageImpl<>(List.of(new com.example.dorm.model.Room()));
        when(roomService.searchRooms(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(view().name("rooms/list"))
                .andExpect(model().attributeExists("roomsPage"));
    }
}
