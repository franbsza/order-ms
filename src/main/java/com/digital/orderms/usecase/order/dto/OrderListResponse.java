package com.digital.orderms.usecase.order.dto;

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
public class OrderListResponse {

    List<OrderDto> data;

    @JsonProperty("meta")
    private PageControl pageControl;
}
