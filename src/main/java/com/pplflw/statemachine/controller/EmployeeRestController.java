package com.pplflw.statemachine.controller;

import com.pplflw.statemachine.dto.EmployeeDTO;
import com.pplflw.statemachine.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bishoy Georgy
 * @version 1.0
 * @date 11/10/2021
 */
@RestController
@RequestMapping(value = "/")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "employee")
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "employee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeState(@PathVariable(value = "id") int id, @RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.updateEmployeeState(id, employeeDTO), HttpStatus.OK);
    }
}
