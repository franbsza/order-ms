package com.digital.orderms.interfaces;

import com.digital.orderms.usecase.order.OrderService;
import com.digital.orderms.usecase.order.dto.OrderRequest;
import com.digital.orderms.usecase.order.dto.OrderCreateResponse;
import com.digital.orderms.usecase.order.dto.OrderDto;
import com.digital.orderms.usecase.order.dto.OrderListResponse;
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
@RequestMapping(value = "/api/orders", produces = "application/json")
public class OrderController {

    private final OrderService service;

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The order was created",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderCreateResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "The order contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Error.class)) })})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<OrderCreateResponse> create(@RequestBody OrderRequest order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(order));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @Operation(summary = "Update order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The order was updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderCreateResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "The order contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)) })})
    @PutMapping(value="/{id}" , consumes = "application/json")
    public ResponseEntity<OrderCreateResponse> update(@RequestBody OrderRequest order,
                                                     @PathVariable("id") Long id) {
        OrderCreateResponse response =  service.update(order, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity<OrderListResponse> find(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                 @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer size,
                                                 @RequestParam(value = "email", required = false) String email){
        OrderListResponse response = service.find(page, size, email);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
}
