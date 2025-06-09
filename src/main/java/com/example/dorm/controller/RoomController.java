package com.example.dorm.controller;

import com.example.dorm.model.Room;
import com.example.dorm.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "rooms/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("room", new Room());
        return "rooms/form";
    }

    @PostMapping
    public String createRoom(@ModelAttribute Room room) {
        roomRepository.save(room);
        return "redirect:/rooms";
    }

    @GetMapping("/{id}")
    public String viewRoom(@PathVariable Long id, Model model) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            model.addAttribute("room", roomOptional.get());
            return "rooms/detail";
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy phòng với ID: " + id);
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            model.addAttribute("room", roomOptional.get());
            return "rooms/form";
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy phòng với ID: " + id);
            return "error";
        }
    }

    @PostMapping("/{id}")
    public String updateRoom(@PathVariable Long id, @ModelAttribute Room room, Model model) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            room.setId(id);
            roomRepository.save(room);
            return "redirect:/rooms";
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy phòng với ID: " + id);
            return "error";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteRoom(@PathVariable Long id, Model model) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            roomRepository.deleteById(id);
            return "redirect:/rooms";
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy phòng với ID: " + id);
            return "error";
        }
    }

}