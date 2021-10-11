package com.pplflw.statemachine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bishoy Georgy
 * @version 1.0
 * @date 11/10/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private int id;
    private String name;
    private int age;
    private String contractInfo;
    private String state;
}
