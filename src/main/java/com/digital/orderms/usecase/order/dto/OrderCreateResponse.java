package com.digital.orderms.usecase.order.dto;

import com.digital.orderms.enums.OrderStatus;
import com.digital.orderms.enums.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateResponse {

    private Long id;
    private OrderStatus orderStatus;
    private Period period;
    private ZonedDateTime dateTime;
}