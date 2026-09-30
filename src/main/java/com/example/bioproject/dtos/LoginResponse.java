package com.example.bioproject.dtos;

import com.example.bioproject.enums.EmployeeRole;

public record LoginResponse(String userName,
                                  String password,
                                  EmployeeRole role) {
}
