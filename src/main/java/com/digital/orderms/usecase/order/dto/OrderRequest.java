package com.digital.orderms.usecase.order.dto;

import com.digital.orderms.enums.Period;
import com.digital.orderms.usecase.customer.dto.AddressDto;
import com.digital.orderms.usecase.vehicle.dto.VehicleDto;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long id;

    @Hidden
    private String orderStatus;

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Period period;

    @NotNull
    private ZonedDateTime dateTime;

    @NotNull
    private AddressDto address;

    @NotNull
    private Long serviceId;

    @NotNull
    private VehicleDto vehicle;
}