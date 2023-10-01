package com.digital.orderms.dto;

import com.digital.orderms.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private OrderStatus orderStatus = OrderStatus.OPEN;

    private String description;

    @NotNull
    private SlotDto slot;

    @NotNull
    private Long serviceId;

    @NotNull
    private Long addressId;

    @NotNull
    private Long vehicleId;
}
