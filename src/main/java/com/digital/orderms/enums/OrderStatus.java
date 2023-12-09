package com.digital.orderms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    OPEN("Em aberto"),
    IN_PROGRESS("Em andamento"),
    PENDING("Pendente"),
    CANCELED("Cancelado"),
    COMPLETED_SUCCESS("Concluído");

    private final String name;
}