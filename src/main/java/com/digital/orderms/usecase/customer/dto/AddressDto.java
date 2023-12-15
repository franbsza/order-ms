package com.digital.orderms.usecase.customer.dto;

import com.digital.orderms.usecase.technician.dto.BaseAddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    @NotNull
    private String street;
    @NotNull
    private String number;
    @NotNull
    private String neighborhood;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String zipCode;

    private BaseAddressDto baseAddress;
}