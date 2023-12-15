package com.digital.orderms.repository;

import com.digital.orderms.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByNeighborhood(String neighborhood);
}
