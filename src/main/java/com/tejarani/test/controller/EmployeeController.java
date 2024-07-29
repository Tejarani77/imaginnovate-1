package com.tejarani.test.controller;

import com.tejarani.test.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tejarani.test.model.Employee;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/{employeeId}/tax")
    public ResponseEntity<EmployeeService.TaxDetails> getEmployeeTax(@PathVariable String employeeId) {
        EmployeeService.TaxDetails taxDetails = employeeService.calculateTax(employeeId);
        return ResponseEntity.ok(taxDetails);
    }
}

