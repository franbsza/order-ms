package com.digital.orderms.interfaces;

import com.digital.orderms.usecase.technician.TechnicianService;
import com.digital.orderms.usecase.technician.dto.TechnicianDto;
import com.digital.orderms.usecase.technician.dto.TechnicianListResponse;
import com.digital.orderms.usecase.technician.dto.TechnicianRequest;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/technicians", produces = "application/json")
public class TechnicianController {

    private final TechnicianService service;

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<TechnicianListResponse> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer size){
        TechnicianListResponse response = service.findAll(page, size);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_STAFF', 'ROLE_ADMIN')")
    @Operation(summary = "Create a new technician")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The technician was registered",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicianDto.class)) }),
            @ApiResponse(responseCode = "400", description = "The technician contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<TechnicianDto> create(@RequestBody TechnicianRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_STAFF', 'ROLE_ADMIN')")
    @Operation(summary = "Update technician")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The technician was updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicianDto.class)) }),
            @ApiResponse(responseCode = "400", description = "The technician contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PutMapping(value="/{id}", consumes = "application/json")
    public ResponseEntity<TechnicianDto> update(@RequestBody TechnicianRequest request,
                                              @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(request));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDto> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @GetMapping("/neighborhood/{neighborhood}")
    public ResponseEntity<List<TechnicianDto>> findByRegion(@PathVariable("neighborhood") String neighborhood){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByRegion(neighborhood));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_STAFF', 'ROLE_ADMIN')")
    @Operation(summary = "Update technician status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The technician status was updated"),
            @ApiResponse(responseCode = "400", description = "The technician contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PutMapping(value= "/{id}/status/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable("id") Long id,
                                                      @PathVariable("status") boolean status){
        service.updateStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
