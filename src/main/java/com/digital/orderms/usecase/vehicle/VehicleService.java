package com.digital.orderms.usecase.vehicle;

import com.digital.orderms.domain.Customer;
import com.digital.orderms.domain.Vehicle;
import com.digital.orderms.mappers.VehicleEntityMapper;
import com.digital.orderms.repository.CustomerRepository;
import com.digital.orderms.repository.VehicleRepository;
import com.digital.orderms.usecase.common.PageControl;
import com.digital.orderms.usecase.vehicle.dto.VehicleDto;
import com.digital.orderms.usecase.vehicle.dto.VehicleListResponse;
import com.digital.orderms.usecase.vehicle.dto.VehicleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository repository;
    private final CustomerRepository customerRepository;
    private final VehicleEntityMapper mapper;

    public VehicleListResponse findAll(int page, int size, String email) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Vehicle> vehiclePage = email != null ?
                repository.findByEmail(pageable, email) :
                repository.findAll(pageable);

        List<VehicleDto> vehicleDtos = vehiclePage.getContent().stream()
                .map(mapper::mappingVehicleToDto)
                .collect(Collectors.toList());

        return VehicleListResponse.builder()
                .data(vehicleDtos)
                .pageControl(PageControl.builder()
                        .total(vehiclePage.getTotalElements())
                        .build())
                .build();
    }

    public VehicleDto findById(Long id){
        Vehicle vehicle = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Entity not found id: " + id)
        );
        return mapper.mappingVehicleToDto(vehicle);
    }

    public VehicleDto create(VehicleRequest request) {
        Customer customer = customerRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("Customer not found email: " + request.getEmail())
        );
        Vehicle vehicle = mapper.mappingVehicleRequestToVehicle(request, customer);
        return mapper.mappingVehicleToDto(repository.save(vehicle));
    }

    public void updateStatus(Long id) {
        Vehicle vehicle = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Entity not found id: " + id)
        );
        vehicle.setIsActive(!vehicle.getIsActive());
        vehicle.setIsProtected(!vehicle.getIsActive());
        repository.save(vehicle);
    }
}