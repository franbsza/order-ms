package com.digital.orderms.mappers;

import com.digital.orderms.domain.Order;
import com.digital.orderms.dto.OrderRequest;
import com.digital.orderms.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {

    @Mapping(target = "service.id", source = "serviceId")
    @Mapping(target = "address.id", source = "addressId")
    @Mapping(target = "vehicle.id", source = "vehicleId")
    Order mappingOrderRequestToOrder(OrderRequest orderRequest);

    OrderResponse mappingOrderToOrderResponse(Order order);
}