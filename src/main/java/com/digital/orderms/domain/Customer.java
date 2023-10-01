package com.digital.orderms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    private String userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phone;

    @NotNull
    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY , cascade=CascadeType.ALL)
    private List<Vehicle> vehicles;
}