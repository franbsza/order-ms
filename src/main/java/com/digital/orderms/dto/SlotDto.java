package com.digital.orderms.dto;

import com.digital.orderms.domain.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotDto {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Period period;

    @NotNull
    private ZonedDateTime dateTime;
}
