package com.digital.orderms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @Schema(hidden = true)
    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
    @Schema(hidden = true)
    private LocalDate deletedAt;
    @Schema(hidden = true)
    private LocalDate createdAt;
    @Schema(hidden = true)
    private LocalDate updatedAt;
}