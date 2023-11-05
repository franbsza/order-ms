package com.digital.orderms.interfaces;

import com.digital.orderms.usecase.order.OrderService;
import com.digital.orderms.usecase.order.dto.OrderCreateRequest;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/orders", produces = "application/json")
public class OrderController {

    private final OrderService service;

    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The order was created",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderCreateResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "The order contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Error.class)) })})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<OrderCreateResponse> create(@RequestBody OrderCreateRequest order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(order));
    }

    @GetMapping
    public ResponseEntity<OrderListResponse> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                     @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
}
