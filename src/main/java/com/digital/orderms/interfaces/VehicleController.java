package com.digital.orderms.interfaces;

import com.digital.orderms.usecase.vehicle.VehicleService;
import com.digital.orderms.usecase.vehicle.dto.VehicleDto;
import com.digital.orderms.usecase.vehicle.dto.VehicleListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vehicles", produces = "application/json")
public class VehicleController {

    private final VehicleService service;

    @GetMapping
    public ResponseEntity<VehicleListResponse> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
}