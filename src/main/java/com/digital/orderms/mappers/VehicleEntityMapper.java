package com.digital.orderms.mappers;

import com.digital.orderms.domain.Customer;
import com.digital.orderms.domain.Vehicle;
import com.digital.orderms.mappers.helper.VehicleEntityMapperHelper;
import com.digital.orderms.usecase.vehicle.dto.VehicleDto;
import com.digital.orderms.usecase.vehicle.dto.VehicleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VehicleEntityMapperHelper.class})
public interface VehicleEntityMapper {

    @Mapping(target = "name" , source = "vehicle", qualifiedByName = "setVehicleName")
    VehicleDto mappingVehicleToDto(final Vehicle vehicle);


    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "customer" , source = "customer")
    @Mapping(target = "isActive" , source = "request", qualifiedByName = "setActive")
    @Mapping(target = "isProtected" , source = "request", qualifiedByName = "setProtected")
    Vehicle mappingVehicleRequestToVehicle(VehicleRequest request, Customer customer);
}
