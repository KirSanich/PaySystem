package com.example.paysystem.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public enum Operation {
    BalanceUp("up"),
    BalanceDown("down");

    private final String abbreviation;

    Operation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @JsonValue
    public String getAbbreviation() {
        return abbreviation;
    }
}
