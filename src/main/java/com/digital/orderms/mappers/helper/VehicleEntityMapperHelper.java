package com.digital.orderms.mappers.helper;

import com.digital.orderms.domain.Vehicle;
import com.digital.orderms.usecase.vehicle.dto.VehicleRequest;
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
        if(vehicle.getBrand() != null)
            vehicleName = vehicleName + vehicle.getBrand();
        if(vehicle.getModel() != null)
            vehicleName = vehicleName + " "+ vehicle.getModel();
        return vehicleName;
    }

    @Named("setActive")
    public Boolean setActive(final VehicleRequest vehicle){
        return true;
    }

    @Named("setProtected")
    public Boolean setProtected (final VehicleRequest vehicle){
        return true;
    }
}
