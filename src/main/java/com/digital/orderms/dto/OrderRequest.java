package com.digital.orderms.dto;

import com.digital.orderms.domain.*;
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

    @Hidden
    private OrderStatus orderStatus;

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Period period;

    @NotNull
    private ZonedDateTime dateTime;

    @NotNull
    private Long serviceId;

    @NotNull
    private Long addressId;

    @NotNull
    private Long vehicleId;
}
