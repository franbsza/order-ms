package com.digital.orderms.mappers;

import com.digital.orderms.domain.Order;
import com.digital.orderms.mappers.helper.OrderEntityMapperHelper;
import com.digital.orderms.usecase.order.dto.OrderCreateRequest;
import com.digital.orderms.usecase.order.dto.OrderCreateResponse;
import com.digital.orderms.usecase.order.dto.OrderDto;
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
    Order mappingOrderRequestToOrder(OrderCreateRequest orderRequest);

    @Mapping(target = "period", source = "order.slot.period")
    @Mapping(target = "dateTime", source = "order.slot.dateTime")
    OrderCreateResponse mappingOrderToOrderResponse(Order order);

    @Mapping(target = "period", source= "slot.period")
    @Mapping(target = "dateTime", source = "slot.dateTime")
    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "neighborhood", source = "address.neighborhood")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "number", source = "address.number")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "state", source = "address.state")
    @Mapping(target = "zipCode", source = "address.zipCode")
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "serviceName", source = "service.name")
    @Mapping( target = "expertTechnicianName", source = "expertTechnician.name")
    @Mapping(target = "vehicleId", source = "vehicle.id")
    @Mapping(target = "vehicleName", source = "order.vehicle", qualifiedByName = "setVehicleName")
    OrderDto mappingOrderToOrderDto(Order order);
}