package com.digital.orderms.mappers.helper;

import com.digital.orderms.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleEntityMapperHelper {
    @Named("setVehicleName")
    public String setVehicleName(final Vehicle vehicle) {
        if(vehicle == null) return null;
        String vehicleName = "";
        if(vehicle.getBrand() != null || !vehicle.getBrand().isBlank())
            vehicleName = vehicleName + vehicle.getBrand();
        if(vehicle.getModel() != null || !vehicle.getModel().isBlank())
            vehicleName = vehicleName + " "+ vehicle.getModel();
        return vehicleName;
    }
}