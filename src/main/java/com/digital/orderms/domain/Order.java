package com.digital.orderms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name="description" , length = 65535, columnDefinition="text")
    @Type(type="text")
    private String description;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private Slot slot;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
    @JoinColumn(name = "expert_technician_id", referencedColumnName = "id")
    private ExpertTechnician expertTechnician;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}
