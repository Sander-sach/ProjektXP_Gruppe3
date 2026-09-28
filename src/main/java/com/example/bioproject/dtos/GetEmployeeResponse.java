package com.example.bioproject.dtos;

import com.example.bioproject.enums.EmployeeRole;

public record GetEmployeeResponse(String userName,
                                  String password,
                                  EmployeeRole role,
                                  Long userId) {
}
