package com.example.bioproject.Service;

import com.example.bioproject.DTOs.CreateEmployeeRequest;
import com.example.bioproject.Repository.EmployeeRepository;
import com.example.bioproject.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

   /* public boolean createEmployee(CreateEmployeeRequest createEmployeeRequest){

        if(!validatePassword(createEmployeeRequest.password())){
            return false;
        }
       EncyptedPassword = EncyptPassword();
    }*/

    public String EncyptPassword(String plainPassword ){
        return passwordEncoder.encode(plainPassword);
    }

    public boolean validatePassword(String plainPassword){
        if(!plainPassword.matches(".*[0-9].*") || plainPassword.length() > 20){
            return false;
        }
        return true;
    }


}
