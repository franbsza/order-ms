package com.digital.orderms.usecase.vehicle.dto;

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
public class VehicleListResponse {

    List<VehicleDto> data;

    @JsonProperty("meta")
    private PageControl pageControl;
}