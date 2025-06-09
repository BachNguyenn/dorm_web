// Controller: StudentController
package com.example.dorm.controller;

import com.example.dorm.model.Student;
import com.example.dorm.repository.StudentRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students/list";
        }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping
    public String createStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        Optional<Student> studentOptional = studentRepository.findById(id);
    if (studentOptional.isPresent()) {
        model.addAttribute("student", studentOptional.get());
        return "students/detail";
    } else {
        model.addAttribute("errorMessage", "Không tìm thấy sinh viên với ID: " + id);
        return "error";
    }
}

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Student> studentOptional = studentRepository.findById(id);
    if (studentOptional.isPresent()) {
        model.addAttribute("student", studentOptional.get());
        return "students/form";
    } else {
        model.addAttribute("errorMessage", "Không tìm thấy sinh viên với ID: " + id);
        return "error";
    }
}

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student, Model model) {
        Optional<Student> studentOptional = studentRepository.findById(id);
    if (studentOptional.isPresent()) {
        student.setId(id);
        studentRepository.save(student);
        return "redirect:/students";
    } else {
        model.addAttribute("errorMessage", "Không tìm thấy sinh viên với ID: " + id);
        return "error";
    }
  }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id, Model model) {
        Optional<Student> studentOptional = studentRepository.findById(id);
    if (studentOptional.isPresent()) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    } else {
        model.addAttribute("errorMessage", "Không tìm thấy sinh viên với ID: " + id);
        return "error";
    }
  }

    @GetMapping("/search")
    public String searchStudents(@RequestParam("search") String search, Model model) {
        if (search == null || search.trim().isEmpty()) {
            model.addAttribute("students", studentRepository.findAll());
        } else {
            model.addAttribute("students", studentRepository.findByNameContainingIgnoreCase(search));
        }
        model.addAttribute("search", search);
        return "students/list";
    }
}
