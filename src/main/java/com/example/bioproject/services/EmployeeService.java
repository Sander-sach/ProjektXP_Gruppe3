package com.example.bioproject.services;

import com.example.bioproject.dtos.EmployeeAccountCredentials;
import com.example.bioproject.dtos.LoginCredentials;
import com.example.bioproject.entities.Employee;
import com.example.bioproject.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createEmployee(EmployeeAccountCredentials credentials){
        String hashedPassword;
        hashedPassword = hashPassword(credentials.password());

        if(!employeeRepository.existsByUserName(credentials.userName())){
            return false;
        }
        if(!validatePasswordCharacteres(credentials.password())){
            return false;
        }
        Employee newEmployee = new Employee(
                credentials.userName(),
                hashedPassword,
                credentials.employeeRole());

        employeeRepository.save(newEmployee);
        return true;
    }

    public boolean validatePasswordCharacteres(String plainPassword){
        if(!plainPassword.matches(".*[0-9].*") || plainPassword.length() > 20){
            return false;
        }
        return true;
    }

    public String hashPassword(String plainPassword){
        String hashedPassword = passwordEncoder.encode(plainPassword);
        return hashedPassword;
    }

    public boolean validateLoginPassword(String plainPassword,String hashedPassword){
        return passwordEncoder.matches(plainPassword,hashedPassword);
    }

    /*public Employee validateLoginCredentials(LoginCredentials credentials){
        Employee employee = employeeRepository.findEmployeeByUserName(credentials.userName())

    }*/
}
