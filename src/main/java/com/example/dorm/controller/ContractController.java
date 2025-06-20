package com.example.dorm.controller;

import com.example.dorm.model.Contract;
import com.example.dorm.service.ContractService;
import com.example.dorm.service.StudentService;
import com.example.dorm.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String listContracts(Model model) {
        try {
            model.addAttribute("contracts", contractService.getAllContracts());
            return "contracts/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lấy danh sách hợp đồng: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        try {
            model.addAttribute("contract", new Contract());
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "contracts/form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi hiển thị form tạo hợp đồng: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping
    public String createContract(@Valid @ModelAttribute Contract contract, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "contracts/form";
        }
        try {
            contractService.createContract(contract);
            return "redirect:/contracts";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu hợp đồng: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String viewContract(@PathVariable Long id, Model model) {
        try {
            Optional<Contract> contractOptional = contractService.getContract(id);
            if (contractOptional.isPresent()) {
                model.addAttribute("contract", contractOptional.get());
                return "contracts/detail";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy hợp đồng với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xem hợp đồng: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        try {
            Optional<Contract> contractOptional = contractService.getContract(id);
            if (contractOptional.isPresent()) {
                model.addAttribute("contract", contractOptional.get());
                model.addAttribute("students", studentService.getAllStudents());
                model.addAttribute("rooms", roomService.getAllRooms());
                return "contracts/form";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy hợp đồng với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi tải form chỉnh sửa: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/{id}")
    public String updateContract(@PathVariable Long id, @Valid @ModelAttribute Contract contract, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "contracts/form";
        }
        try {
            contractService.updateContract(id, contract);
            return "redirect:/contracts";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi cập nhật hợp đồng: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteContract(@PathVariable Long id, Model model) {
        try {
            Optional<Contract> contractOptional = contractService.getContract(id);
            if (contractOptional.isPresent()) {
                contractService.deleteContract(id);
                return "redirect:/contracts";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy hợp đồng với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xoá hợp đồng: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/search")
    public String searchContracts(@RequestParam(value = "search", required = false, defaultValue = "") String search,
                                  Model model) {
        try {
            model.addAttribute("contracts", contractService.searchContracts(search));
            model.addAttribute("search", search);
            return "contracts/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi tìm kiếm hợp đồng: " + e.getMessage());
            return "error";
        }
    }
}
