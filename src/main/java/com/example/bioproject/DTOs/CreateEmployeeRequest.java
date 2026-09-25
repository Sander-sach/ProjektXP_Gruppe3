package com.example.bioproject.DTOs;

import com.example.bioproject.enums.EmployeeRole;

public record CreateEmployeeRequest(String userName, String password, EmployeeRole employeeRole) {
}
