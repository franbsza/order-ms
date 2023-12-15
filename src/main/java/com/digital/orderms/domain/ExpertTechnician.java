package com.digital.orderms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "experts_technician")
public class ExpertTechnician {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String documentNumber;

    private Boolean isPartner;

    private String description;

    @NotNull
    private Boolean isActive;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_address_id", referencedColumnName = "id", nullable = false)
    private Address personalAddress;

    @NotNull
    private String phone;
}
