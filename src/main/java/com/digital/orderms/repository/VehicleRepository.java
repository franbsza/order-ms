package com.digital.orderms.repository;

import com.digital.orderms.domain.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {
    Page<Vehicle> findAll(Pageable pageable);
}