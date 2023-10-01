package com.digital.orderms.dto;

import com.digital.orderms.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private OrderStatus orderStatus;
    private SlotDto slot;
}