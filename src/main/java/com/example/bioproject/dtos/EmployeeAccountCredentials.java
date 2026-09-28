package com.example.bioproject.dtos;

import com.example.bioproject.enums.EmployeeRole;

public record EmployeeAccountCredentials(String userName,
                                         String password,
                                         EmployeeRole employeeRole) {
}
