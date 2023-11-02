package com.digital.orderms.mappers.helper;

import com.digital.orderms.domain.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEntityMapperHelper {

    @Named("setOrderStatus")
    public OrderStatus setOrderStatus(final OrderStatus orderStatus) {
        return orderStatus == null || orderStatus.name().isBlank() ?  OrderStatus.OPEN : orderStatus;
    }
}