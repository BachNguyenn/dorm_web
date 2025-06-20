package com.example.dorm.controller;

import com.example.dorm.service.StudentService;
import com.example.dorm.service.RoomService;
import com.example.dorm.service.ContractService;
import com.example.dorm.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final StudentService studentService;
    private final RoomService roomService;
    private final ContractService contractService;
    private final FeeService feeService;

    @GetMapping("/")
    public String showDashboard(Model model) {
        model.addAttribute("studentCount", studentService.getAllStudents().size());
        model.addAttribute("roomCount", roomService.getAllRooms().size());
        model.addAttribute("contractCount", contractService.getAllContracts().size());
        model.addAttribute("feeCount", feeService.getAllFees().size());
        return "dashboard";
    }
}