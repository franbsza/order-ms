package com.digital.orderms.usecase.order.dto;

import com.digital.orderms.domain.OrderStatus;
import com.digital.orderms.domain.Period;
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
    private OrderStatus orderStatus;

    //slot
    private Period period;
    private ZonedDateTime dateTime;

    //address
    private Long addressId;
    private String neighborhood;
    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;

    //service
    private Long serviceId;
    private String serviceName;

    //expertTechnician
    private String expertTechnicianName;

    //vehicle
    private Long vehicleId;
    private String vehicleName;
}
