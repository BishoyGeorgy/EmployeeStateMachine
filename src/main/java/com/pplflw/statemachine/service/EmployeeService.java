package com.pplflw.statemachine.service;

import com.pplflw.statemachine.dto.EmployeeDTO;
import com.pplflw.statemachine.entity.Employee;
import com.pplflw.statemachine.entity.State;
import com.pplflw.statemachine.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bishoy Georgy
 * @version 1.0
 * @date 11/10/2021
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.createEmployee(employeeDTO);
        employee = employeeRepository.save(employee);
        employeeDTO.setId(employee.getId());
        employeeDTO.setState(employee.getState().getValue());

        return employeeDTO;
    }

    public EmployeeDTO updateEmployeeState(int id, EmployeeDTO employeeDTO) {
        if (employeeRepository.findById(id).isPresent()) {
            Employee existingEmployee = employeeRepository.findById(id).get();

            existingEmployee.setState(employeeMapper.getState(employeeDTO.getState()));
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            employeeDTO.setState(updatedEmployee.getState().getValue());

            return employeeDTO;

        } else {
            return null;
        }
    }
}
