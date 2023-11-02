package com.digital.orderms.dto;

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
public class CategoryResponse {

    List<CategoryDto> data;

    @JsonProperty("links")
    Link links;

    @JsonProperty("meta")
    private MetaDto metaDto;
}