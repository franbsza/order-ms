package com.digital.orderms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    private Boolean isActive;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    @Column(name = "vehicle_year")
    private String year;

    @NotNull
    private String plateNumber;

    @NotNull
    private String color;

    @NotNull
    private String renavam;

    private Boolean isProtected;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER ,cascade=CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "vehicle")
    private List<Order> order;
}
