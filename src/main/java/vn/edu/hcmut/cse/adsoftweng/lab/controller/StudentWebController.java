package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    @GetMapping
    public String getAllStudents(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("dsSinhVien", service.searchByName(keyword));
        } else {
            model.addAttribute("dsSinhVien", service.getAll());
        }
        return "students";
    }

    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
			return "redirect:/students";
		}
        model.addAttribute("student", student);
        return "student-detail";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Thêm Sinh Viên Mới");
        return "student-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        model.addAttribute("student", student);
        model.addAttribute("pageTitle", "Chỉnh Sửa Sinh Viên");
        return "student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.delete(id);
        return "redirect:/students";
    }
}