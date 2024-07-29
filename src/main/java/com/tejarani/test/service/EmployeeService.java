package com.tejarani.test.service;


import com.tejarani.test.model.Employee;
import com.tejarani.test.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public TaxDetails calculateTax(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        LocalDate currentDate = LocalDate.now();
        int monthsWorked = (int) ChronoUnit.MONTHS.between(employee.getDoj(), currentDate);
        if (monthsWorked < 1) {
            monthsWorked = 1; // at least 1 month should be considered
        }

        double yearlySalary = employee.getSalary() * monthsWorked;
        double tax = calculateTaxAmount(yearlySalary);
        double cess = yearlySalary > 2500000 ? (yearlySalary - 2500000) * 0.02 : 0;

        return new TaxDetails(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), yearlySalary, tax, cess);
    }

    private double calculateTaxAmount(double yearlySalary) {
        double tax = 0;
        if (yearlySalary > 1000000) {
            tax += (yearlySalary - 1000000) * 0.2;
            yearlySalary = 1000000;
        }
        if (yearlySalary > 500000) {
            tax += (yearlySalary - 500000) * 0.1;
            yearlySalary = 500000;
        }
        if (yearlySalary > 250000) {
            tax += (yearlySalary - 250000) * 0.05;
        }
        return tax;
    }

    public static class TaxDetails {
        private String employeeId;
        private String firstName;
        private String lastName;
        private double yearlySalary;
        private double tax;
        private double cess;

        public TaxDetails(String employeeId, String firstName, String lastName, double yearlySalary, double tax, double cess) {
            this.employeeId = employeeId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.yearlySalary = yearlySalary;
            this.tax = tax;
            this.cess = cess;
        }

        // Getters and Setters
    }
}
