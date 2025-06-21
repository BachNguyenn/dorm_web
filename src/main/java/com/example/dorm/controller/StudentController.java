package com.example.dorm.controller;

import com.example.dorm.model.Student;
import com.example.dorm.service.RoomService;
import com.example.dorm.service.StudentService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private RoomService roomService;

    @GetMapping
    public String listStudents(@RequestParam(value = "search", required = false) String search,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "10") int size,
                               Model model) {
        try {
            var pageable = org.springframework.data.domain.PageRequest.of(page, size);
            var studentsPage = studentService.searchStudents(search, pageable);
            model.addAttribute("studentsPage", studentsPage);
            model.addAttribute("search", search);
            return "students/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi tải danh sách sinh viên: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        try {
            model.addAttribute("student", new Student());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "students/form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi hiển thị form tạo sinh viên: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping
    public String createStudent(@ModelAttribute Student student, Model model) {
        try {
            studentService.saveStudent(student);
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi tạo sinh viên: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable("id") Long id, Model model) {
        try {
            Optional<Student> studentOptional = studentService.getStudent(id);
            if (studentOptional.isPresent()) {
                model.addAttribute("student", studentOptional.get());
                return "students/detail";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy sinh viên với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xem thông tin sinh viên: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        try {
            Optional<Student> studentOptional = studentService.getStudent(id);
            if (studentOptional.isPresent()) {
                model.addAttribute("student", studentOptional.get());
                model.addAttribute("rooms", roomService.getAllRooms());
                return "students/form";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy sinh viên với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi hiển thị form cập nhật: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute Student student, Model model) {
        try {
            Optional<Student> studentOptional = studentService.getStudent(id);
            if (studentOptional.isPresent()) {
                student.setId(id);
                studentService.saveStudent(student);
                return "redirect:/students";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy sinh viên với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi cập nhật sinh viên: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        try {
            Optional<Student> studentOptional = studentService.getStudent(id);
            if (studentOptional.isPresent()) {
                studentService.deleteStudent(id);
                return "redirect:/students";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy sinh viên với ID: " + id);
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi xoá sinh viên: " + e.getMessage());
            return "error";
        }
    }

    // search handled by listStudents
}
