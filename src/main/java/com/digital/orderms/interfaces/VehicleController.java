package com.digital.orderms.interfaces;

import com.digital.orderms.usecase.technician.dto.TechnicianDto;
import com.digital.orderms.usecase.vehicle.VehicleService;
import com.digital.orderms.usecase.vehicle.dto.VehicleDto;
import com.digital.orderms.usecase.vehicle.dto.VehicleListResponse;
import com.digital.orderms.usecase.vehicle.dto.VehicleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vehicles", produces = "application/json")
public class VehicleController {

    private final VehicleService service;

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<VehicleListResponse> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer size,
                                                       @RequestParam(value = "email", required = false) String email){
        VehicleListResponse response = service.findAll(page, size, email);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @Operation(summary = "Create a new vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The vehicle was created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicianDto.class)) }),
            @ApiResponse(responseCode = "400", description = "The vehicle contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<VehicleDto> create(@RequestBody VehicleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @Operation(summary = "Update vehicle status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The vehicle status was updated"),
            @ApiResponse(responseCode = "400", description = "The vehicle contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PutMapping(value= "/desactivate/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable("id") Long id){
        service.updateStatus(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
