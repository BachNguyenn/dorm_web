package com.example.dorm.controller;

import com.example.dorm.model.Fee;
import com.example.dorm.service.FeeService;
import com.example.dorm.service.ContractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/fees")
public class FeeController {
    @Autowired
    private FeeService feeService;
    @Autowired
    private ContractService contractService;

    @GetMapping
    public String listFees(Model model) {
        try {
            model.addAttribute("fees", feeService.getAllFees());
            return "fees/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lấy danh sách phí: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        try {
            model.addAttribute("fee", new Fee());
            model.addAttribute("contracts", contractService.getAllContracts());
            return "fees/form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi hiển thị form tạo phí: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping
    public String createFee(@Valid @ModelAttribute Fee fee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contracts", contractService.getAllContracts());
            return "fees/form";
        }
        try {
            feeService.createFee(fee);
            return "redirect:/fees";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu phí: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String viewFee(@PathVariable Long id, Model model) {
        try {
            Optional<Fee> feeOptional = feeService.getFee(id);
            if (feeOptional.isPresent()) {
                model.addAttribute("fee", feeOptional.get());
                return "fees/detail";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy phí với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xem chi tiết phí: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        try {
            Optional<Fee> feeOptional = feeService.getFee(id);
            if (feeOptional.isPresent()) {
                model.addAttribute("fee", feeOptional.get());
                model.addAttribute("contracts", contractService.getAllContracts());
                return "fees/form";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy phí với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi hiển thị form chỉnh sửa: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/{id}")
    public String updateFee(@PathVariable Long id, @Valid @ModelAttribute Fee fee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contracts", contractService.getAllContracts());
            return "fees/form";
        }
        try {
            feeService.updateFee(id, fee);
            return "redirect:/fees";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi cập nhật phí: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteFee(@PathVariable Long id, Model model) {
        try {
            Optional<Fee> feeOptional = feeService.getFee(id);
            if (feeOptional.isPresent()) {
                feeService.deleteFee(id);
                return "redirect:/fees";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy phí với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xoá phí: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/search")
    public String searchFees(@RequestParam("search") String search, Model model) {
        try {
            java.util.List<Fee> results = feeService.searchFees(search);
            model.addAttribute("fees", results);
            model.addAttribute("search", search);
            if (results.isEmpty() && search != null && !search.trim().isEmpty()) {
                model.addAttribute("errorMessage", "Không tìm thấy phí phù hợp");
            }
            return "fees/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi tìm kiếm phí: " + e.getMessage());
            return "error";
        }
    }
}
