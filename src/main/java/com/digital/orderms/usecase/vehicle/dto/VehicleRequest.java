package com.digital.orderms.usecase.vehicle.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {

    @Hidden
    private Long id;

    @NotNull
    private Boolean isActive;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private String year;
    @NotNull
    private String plateNumber;
    @NotNull
    private String color;
    @NotNull
    private String renavam;

    private Boolean isProtected;

    private String email;
}
