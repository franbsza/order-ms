package com.digital.orderms.usecase.vehicle.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    @NotNull
    private Long id;
    private String name;
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
}