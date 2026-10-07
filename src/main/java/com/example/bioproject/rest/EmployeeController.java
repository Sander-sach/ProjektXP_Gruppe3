package com.example.bioproject.rest;


import com.example.bioproject.dtos.EmployeeAccountCredentials;
import com.example.bioproject.dtos.LoginCredentials;
import com.example.bioproject.dtos.LoginResponse;
import com.example.bioproject.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create-employee")
    public ResponseEntity<Boolean> createEmployee(@RequestBody EmployeeAccountCredentials credentials){
        Boolean response = employeeService.createEmployee(credentials);
        if(!response){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login-request")
    public ResponseEntity<LoginResponse> loginRequest(@RequestBody LoginCredentials credentials){
        LoginResponse response = employeeService.validateLoginCredentials(credentials);
            if(response == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
           return ResponseEntity.ok(response);
    }

}
