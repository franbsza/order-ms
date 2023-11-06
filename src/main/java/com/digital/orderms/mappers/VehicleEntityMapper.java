package com.digital.orderms.mappers;

import com.digital.orderms.domain.Vehicle;
import com.digital.orderms.mappers.helper.VehicleEntityMapperHelper;
import com.digital.orderms.usecase.vehicle.dto.VehicleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VehicleEntityMapperHelper.class})
public interface VehicleEntityMapper {

    @Mapping(target = "name" , source = "vehicle", qualifiedByName = "setVehicleName")
    VehicleDto mappingOrderToOrderDto(final Vehicle vehicle);
}
