package com.digital.orderms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    OPEN("Em aberto"),
    IN_PROGRESS("Em andamento"),
    PENDING("Pendente"),
    CANCELED("Cancelada"),
    COMPLETED_SUCCESS("Concluída");

    private final String name;
}