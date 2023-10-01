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

    @NotNull
    private Boolean isActive;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @OneToOne
    private Address personalAddress;

    @NotNull
    private String phone;

    @OneToOne
    private BaseAddress baseAddress;
}
