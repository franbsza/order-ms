package com.digital.orderms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Period {
    MORNING("Manhã"),
    AFTERNOON("Tarde");

    private final String name;
}