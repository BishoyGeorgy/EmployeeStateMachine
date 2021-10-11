package com.pplflw.statemachine.repository;

import com.pplflw.statemachine.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bishoy Georgy
 * @version 1.0
 * @date 11/10/2021
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
