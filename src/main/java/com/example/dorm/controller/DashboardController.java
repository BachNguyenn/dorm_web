package com.example.dorm.controller;

import com.example.dorm.repository.StudentRepository;
import com.example.dorm.repository.RoomRepository;
import com.example.dorm.repository.ContractRepository;  
import com.example.dorm.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private FeeRepository feeRepository;

    @GetMapping("/")
    public String showDashboard(Model model) {
        model.addAttribute("studentCount", studentRepository.count());
        model.addAttribute("roomCount", roomRepository.count());
        model.addAttribute("contractCount", contractRepository.count());
        model.addAttribute("feeCount", feeRepository.count());
        return "dashboard";
    }
}