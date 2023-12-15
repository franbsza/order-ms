package com.digital.orderms.usecase.customer.dto;

import com.digital.orderms.usecase.common.PageControl;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListResponse {

    List<CustomerDto> data;

    @JsonProperty("meta")
    private PageControl pageControl;
}