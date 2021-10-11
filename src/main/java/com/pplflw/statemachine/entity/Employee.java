package com.pplflw.statemachine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Bishoy Georgy
 * @version 1.0
 * @date 10/10/2021
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String contractInfo;
    private State state;

    public Employee(String name, int age, String contractInfo, State state) {
        this.name = name;
        this.age = age;
        this.contractInfo = contractInfo;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", contractInfo='" + contractInfo + '\'' +
                ", state=" + state +
                '}';
    }
}
