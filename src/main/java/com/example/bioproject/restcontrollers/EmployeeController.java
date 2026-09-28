package com.example.bioproject.restcontrollers;


import com.example.bioproject.dtos.EmployeeAccountCredentials;
import com.example.bioproject.dtos.LoginCredentials;
import com.example.bioproject.entities.Employee;
import com.example.bioproject.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create-employee")
    public boolean createEmployee(@RequestBody EmployeeAccountCredentials credentials){
        return employeeService.createEmployee(credentials);
    }

    @GetMapping("/login-request")
    public Employee loginRequest(@RequestBody LoginCredentials credentials){
        return employeeService.validateLoginCredentials(credentials);
    }

    /*@GetMapping("/get-employee")
    public EmployeeAccountCredentials GetEmployee(@RequestBody LoginCredentials credentials){
        return
    }*/

}
