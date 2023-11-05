package com.digital.orderms.mappers;

import com.digital.orderms.domain.Order;
import com.digital.orderms.dto.OrderRequest;
import com.digital.orderms.dto.OrderResponse;
import com.digital.orderms.mappers.helper.OrderEntityMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderEntityMapperHelper.class})
public interface OrderEntityMapper {

    @Mapping(target = "service.id", source = "serviceId")
    @Mapping(target = "address.id", source = "addressId")
    @Mapping(target = "vehicle.id", source = "vehicleId")
    @Mapping(target = "orderStatus", source = "orderRequest.orderStatus", qualifiedByName = "setOrderStatus")
    @Mapping(target= "slot.period", source = "period")
    @Mapping(target = "slot.dateTime", source = "dateTime")
    Order mappingOrderRequestToOrder(OrderRequest orderRequest);

    OrderResponse mappingOrderToOrderResponse(Order order);
}