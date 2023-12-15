package com.digital.orderms.usecase.technician.dto;

import com.digital.orderms.usecase.customer.dto.AddressDto;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicianRequest {
    @Hidden
    private Long id;
    private String documentNumber;
    private Boolean isPartner;
    private String description;
    private Boolean isActive;
    private String name;
    private String email;
    private String phone;
    private AddressDto personalAddress;
}