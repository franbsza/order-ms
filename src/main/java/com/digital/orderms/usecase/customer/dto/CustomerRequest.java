package com.digital.orderms.usecase.customer.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @Hidden
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDto address;
    private String status;
}