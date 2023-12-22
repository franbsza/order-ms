package com.digital.orderms.mappers.helper;

import com.digital.orderms.enums.OrderStatus;
import com.digital.orderms.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEntityMapperHelper {

    @Named("setOrderStatus")
    public OrderStatus setOrderStatus(final String orderStatus) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.name().equals(orderStatus)) {
                return value;
            }
        }
        return orderStatus == null ? null : OrderStatus.OPEN;
    }

    @Named("getOrderStatus")
    public String getOrderStatus(final OrderStatus orderStatus) {
        if(orderStatus == null) return null;
        return orderStatus.toString();
    }

    @Named("setVehicleName")
    public String setVehicleName(final Vehicle vehicle) {
        if(vehicle == null) return null;
        String vehicleName = "";
        if(vehicle.getBrand() != null)
            vehicleName = vehicleName + vehicle.getBrand();
        if(vehicle.getModel() != null)
            vehicleName = vehicleName + " "+ vehicle.getModel();
        return vehicleName;
    }
}