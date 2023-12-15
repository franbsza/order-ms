package com.digital.orderms.mappers;

import com.digital.orderms.domain.ExpertTechnician;
import com.digital.orderms.domain.Order;
import com.digital.orderms.mappers.helper.OrderEntityMapperHelper;
import com.digital.orderms.usecase.order.dto.OrderRequest;
import com.digital.orderms.usecase.order.dto.OrderCreateResponse;
import com.digital.orderms.usecase.order.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderEntityMapperHelper.class})
public interface OrderEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "service.id", source = "request.serviceId")
    @Mapping(target = "address.id", ignore = true)
    @Mapping(target = "vehicle.id", source = "request.vehicle.id")
    @Mapping(target = "orderStatus", source = "request.orderStatus", qualifiedByName = "setOrderStatus")
    @Mapping(target= "slot.period", source = "request.period")
    @Mapping(target = "slot.dateTime", source = "request.dateTime")
    @Mapping(target = "expertTechnician.id", source = "expertTechnician.id")
    @Mapping(target = ".email", ignore = true)
    @Mapping(target = "description", source = "request.description")
    Order mappingCreateOrderRequestToOrder(OrderRequest request, ExpertTechnician expertTechnician);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "service.id", source = "request.serviceId")
    @Mapping(target = "address.id", source = "request.address.id")
    @Mapping(target = "vehicle.id", source = "request.vehicle.id")
    @Mapping(target = "orderStatus", source = "request.orderStatus", qualifiedByName = "setOrderStatus")
    @Mapping(target = "slot.period", source = "request.period")
    @Mapping(target = "slot.dateTime", source = "request.dateTime")
    @Mapping(target = "description", source = "request.description")
    @Mapping(target = "createdAt", source = "orderResponse.createdAt")
    Order mappingUpdateOrderRequestToOrder(OrderRequest request, Long id, Order orderResponse);

    @Mapping(target = "period", source = "order.slot.period")
    @Mapping(target = "dateTime", source = "order.slot.dateTime")
    OrderCreateResponse mappingOrderToOrderResponse(Order order);

    @Mapping(target = "period", source= "slot.period")
    @Mapping(target = "dateTime", source = "slot.dateTime")
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "serviceName", source = "service.name")
    @Mapping(target = "expertTechnicianName", source = "expertTechnician.name")
    @Mapping(target = "vehicle.name", source = "order.vehicle", qualifiedByName = "setVehicleName")
    @Mapping(target = "vehicle", source = "order.vehicle")
    @Mapping(target = "address", source = "order.address")
    @Mapping(target = "orderStatus", source = "order.orderStatus", qualifiedByName = "getOrderStatus")
    OrderDto mappingOrderToOrderDto(Order order);
}