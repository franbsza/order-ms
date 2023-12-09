package com.digital.orderms.domain;

import com.digital.orderms.enums.OrderStatus;
import com.digital.orderms.utils.TokenUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
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

    @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
    @JoinColumn(name = "slot_id", referencedColumnName = "id", nullable = false)
    private Slot slot;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    private Service service;

    @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
    @JoinColumn(name = "expert_technician_id", referencedColumnName = "id")
    private ExpertTechnician expertTechnician;

    @ManyToOne(fetch = FetchType.LAZY , cascade=CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @CreatedBy
    @Column(name = "email", updatable = false, nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreateEntity() {
        this.email = TokenUtils.getAttribute("email");
    }
}
