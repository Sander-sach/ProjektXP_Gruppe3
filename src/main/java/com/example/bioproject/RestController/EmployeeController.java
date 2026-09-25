package com.example.bioproject.RestController;

import com.example.bioproject.DTOs.CreateEmployeeRequest;
import com.example.bioproject.Service.EmployeeService;
import com.example.bioproject.enums.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    @PostMapping("/create/employee")
    public boolean CreateEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        return true;
    }
}
