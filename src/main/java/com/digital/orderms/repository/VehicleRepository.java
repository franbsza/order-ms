package com.digital.orderms.repository;

import com.digital.orderms.domain.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {
    Page<Vehicle> findAll(Pageable pageable);

    @Query("SELECT v " +
            "FROM Vehicle  AS v " +
            "LEFT JOIN Customer AS c " +
            "ON v.customer.id = c.id " +
            "WHERE c.email like :email ")
    Page<Vehicle> findByEmail(Pageable pageable, @Param(value = "email") String email);
}