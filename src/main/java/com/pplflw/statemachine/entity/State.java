package com.pplflw.statemachine.entity;

/**
 * @author Bishoy Georgy
 * @version 1.0
 * @date 11/10/2021
 */
public enum State {
    ADDED("added"), IN_CHECK("inCheck"), APPROVED("approved"), ACTIVE("active");
    private final String value;

    public String getValue() {
        return value;
    }

    State(String value) {
        this.value = value;
    }
}
