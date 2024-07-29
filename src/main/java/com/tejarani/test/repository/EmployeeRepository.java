package com.tejarani.test.repository;

import com.tejarani.test.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeId(String employeeId);
}

