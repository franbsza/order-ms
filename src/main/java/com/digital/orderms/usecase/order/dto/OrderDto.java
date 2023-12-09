package com.digital.orderms.usecase.order.dto;

import com.digital.orderms.enums.OrderStatus;
import com.digital.orderms.enums.Period;
import com.digital.orderms.usecase.customer.dto.AddressDto;
import com.digital.orderms.usecase.vehicle.dto.VehicleDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String orderStatus;

    //slot
    private Period period;
    private ZonedDateTime dateTime;

    //address
    private AddressDto address;

    //service
    private Long serviceId;
    private String serviceName;

    //expertTechnician
    private String expertTechnicianName;

    //vehicle
    private VehicleDto vehicle;

    private String email;
}
