package com.example.paysystem.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {

    Registered("regist"),
    New("new"),
    Cancelled("cancel");

    private String abbreviation;

    OrderStatus(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @JsonValue
    public String getAbbreviation() {
        return abbreviation;
    }
}
