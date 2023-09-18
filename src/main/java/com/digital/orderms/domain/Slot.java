package com.digital.orderms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "slots")
public class Slot {

    @Id
    private String id;
    private String period;
    private ZonedDateTime dateTime;
}
