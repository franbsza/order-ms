package com.digital.orderms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "base_addresses")
public class BaseAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @NotNull
    private String region;

    @OneToMany(mappedBy = "baseAddress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;
}
