package com.example.bioproject.rest;


import com.example.bioproject.dtos.EmployeeAccountCredentials;
import com.example.bioproject.dtos.LoginCredentials;
import com.example.bioproject.dtos.LoginResponse;
import com.example.bioproject.enums.EmployeeRole;
import com.example.bioproject.services.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.apache.juli.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create-employee")
    public ResponseEntity<Boolean> createEmployee(@RequestBody EmployeeAccountCredentials credentials, HttpSession session){
        //Check if current user is Authorized to create
        LoginResponse current = (LoginResponse) session.getAttribute("user");
        if(current == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if(current.role() != EmployeeRole.FILMOPERATOER) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        //Create new user
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

    @GetMapping("/user")
public ResponseEntity<LoginResponse> currentUser(HttpSession session){
        //Check current user login
        LoginResponse user = (LoginResponse) session.getAttribute("user");
        return user == null ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() : ResponseEntity.ok(user);
    }
}
