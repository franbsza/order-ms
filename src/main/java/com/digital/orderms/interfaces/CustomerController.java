package com.digital.orderms.interfaces;

import com.digital.orderms.usecase.customer.CustomerService;
import com.digital.orderms.usecase.customer.dto.CustomerDto;
import com.digital.orderms.usecase.customer.dto.CustomerListResponse;
import com.digital.orderms.usecase.customer.dto.CustomerRequest;
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
@RequestMapping(value = "/api/customers", produces = "application/json")
public class CustomerController {

    private final CustomerService service;

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The customer was created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class)) }),
            @ApiResponse(responseCode = "400", description = "The customer contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerRequest customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(customer));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @Operation(summary = "Update customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The customer was updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class)) }),
            @ApiResponse(responseCode = "400", description = "The customer contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PutMapping(value="/{id}", consumes = "application/json")
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerRequest customer,
                                              @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(customer));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDto> findByEmail(@PathVariable("email") String email){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByEmail(email));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_STAFF', 'ROLE_ADMIN')")
    @Operation(summary = "Update customer status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The customer status was updated"),
            @ApiResponse(responseCode = "400", description = "The customer contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PutMapping(value= "/{id}/status/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable("id") Long id,
                                                    @PathVariable("status") String status){
        service.updateStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_STAFF', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<CustomerListResponse> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                        @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer size){
        CustomerListResponse response = service.findAll(page, size);
        return ResponseEntity.ok(response);
    }
}
