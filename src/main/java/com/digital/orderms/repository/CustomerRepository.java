package com.digital.orderms.repository;

import com.digital.orderms.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Customer c set c.status = :status, c.updatedAt = now() where c.id = :id")
    void updateStatus(@Param(value = "status") String status,
                               @Param(value = "id") Long id);
}