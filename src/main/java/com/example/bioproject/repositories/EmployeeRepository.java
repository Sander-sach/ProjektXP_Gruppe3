package com.example.bioproject.repositories;

import com.example.bioproject.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findEmployeeByPasswordAndUserName(String userName, String password);

    boolean existsByUserName(String userName);

    Employee findEmployeeByUserName(String userName);
}
