package org.example.baikt1_doquangduc_th04091.controller;

import jakarta.validation.Valid;
import org.example.baikt1_doquangduc_th04091.entity.Employee;
import org.example.baikt1_doquangduc_th04091.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Hiển thị danh sách nhân viên
    @GetMapping("/employees")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "views/employees";
    }

    // Hiển thị form thêm mới nhân viên
    @GetMapping("/employees/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "views/new_employee";
    }

    // Lưu nhân viên mới hoặc cập nhật
    @PostMapping("/employees/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "views/new_employee";
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }

    // Hiển thị form sửa
    @GetMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "views/new_employee";
    }

    // Xóa nhân viên
    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
