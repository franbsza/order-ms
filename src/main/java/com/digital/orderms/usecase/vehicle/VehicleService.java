package com.digital.orderms.usecase.vehicle;

import com.digital.orderms.domain.Vehicle;
import com.digital.orderms.mappers.VehicleEntityMapper;
import com.digital.orderms.repository.VehicleRepository;
import com.digital.orderms.usecase.common.PageControl;
import com.digital.orderms.usecase.vehicle.dto.VehicleDto;
import com.digital.orderms.usecase.vehicle.dto.VehicleListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository repository;
    private final VehicleEntityMapper mapper;

    public VehicleListResponse findAll(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Vehicle> vehiclePage = repository.findAll(pageable);
        List<VehicleDto> orderDtoList = vehiclePage.getContent().stream()
                .map(mapper::mappingOrderToOrderDto)
                .collect(Collectors.toList());

        return VehicleListResponse.builder()
                .data(orderDtoList)
                .pageControl(PageControl.builder()
                        .total(vehiclePage.getTotalElements())
                        .build())
                .build();
    }

    public VehicleDto findById(Long id){
        Vehicle vehicle = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Entity not found id: " + id)
        );
        return mapper.mappingOrderToOrderDto(vehicle);
    }
}