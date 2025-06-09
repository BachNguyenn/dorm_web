package com.example.dorm.controller;

import com.example.dorm.model.Fee;
import com.example.dorm.repository.FeeRepository;
import com.example.dorm.repository.ContractRepository;
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
    private FeeRepository feeRepository;
    @Autowired
    private ContractRepository contractRepository;

    @GetMapping
    public String listFees(Model model) {
        model.addAttribute("fees", feeRepository.findAll());
        return "fees/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("fee", new Fee());
        model.addAttribute("contracts", contractRepository.findAll());
        return "fees/form";
    }

    @PostMapping
    public String createFee(@Valid @ModelAttribute Fee fee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contracts", contractRepository.findAll());
            return "fees/form";
        }
        feeRepository.save(fee);
        return "redirect:/fees";
    }

    @GetMapping("/{id}")
    public String viewFee(@PathVariable Long id, Model model) {
        Optional<Fee> feeOptional = feeRepository.findById(id);
        if (feeOptional.isPresent()) {
            model.addAttribute("fee", feeOptional.get());
            return "fees/detail";
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy phí với ID: " + id);
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Fee> feeOptional = feeRepository.findById(id);
        if (feeOptional.isPresent()) {
            model.addAttribute("fee", feeOptional.get());
            model.addAttribute("contracts", contractRepository.findAll());
            return "fees/form";
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy phí với ID: " + id);
            return "error";
        }
    }

    @PostMapping("/{id}")
    public String updateFee(@PathVariable Long id, @Valid @ModelAttribute Fee fee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contracts", contractRepository.findAll());
            return "fees/form";
        }
        Optional<Fee> feeOptional = feeRepository.findById(id);
        if (feeOptional.isPresent()) {
            Fee existingFee = feeOptional.get();
            existingFee.setAmount(fee.getAmount());
            existingFee.setContract(fee.getContract());
            existingFee.setType(fee.getType());
            existingFee.setDueDate(fee.getDueDate());
            existingFee.setPaymentStatus(fee.getPaymentStatus());
            feeRepository.save(existingFee);
            return "redirect:/fees";
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy phí với ID: " + id);
            return "error";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteFee(@PathVariable Long id, Model model) {
        Optional<Fee> feeOptional = feeRepository.findById(id);
        if (feeOptional.isPresent()) {
            feeRepository.deleteById(id);
            return "redirect:/fees";
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy phí với ID: " + id);
            return "error";
        }
    }

}