package com.example.bioproject.rest;


import com.example.bioproject.dtos.EmployeeAccountCredentials;
import com.example.bioproject.dtos.LoginCredentials;
import com.example.bioproject.dtos.LoginResponse;
import com.example.bioproject.services.EmployeeService;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<LoginResponse> loginRequest(@RequestBody LoginCredentials credentials, HttpSession session ){
        LoginResponse user = employeeService.validateLoginCredentials(credentials);
            if(user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            session.setAttribute("user", user);
           return ResponseEntity.ok(user);
    }

}
