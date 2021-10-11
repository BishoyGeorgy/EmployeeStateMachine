package com.pplflw.statemachine.service;

import com.pplflw.statemachine.dto.EmployeeDTO;
import com.pplflw.statemachine.entity.Employee;
import com.pplflw.statemachine.entity.State;
import org.springframework.stereotype.Service;

/**
 * @author Bishoy Georgy
 * @version 1.0
 * @date 11/10/2021
 */
@Service
public class EmployeeMapper {

    public Employee createEmployee(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getName(), employeeDTO.getAge(), employeeDTO.getContractInfo(), State.ADDED);
    }

    public State getState(String state) {
        switch (state) {
            case "inCheck":
                return State.IN_CHECK;
            case "approved":
                return State.APPROVED;
            case "active":
                return State.ACTIVE;
            default:
                return State.ADDED;
        }
    }
}
