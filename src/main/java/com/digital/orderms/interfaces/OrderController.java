package com.digital.orderms.interfaces;

import com.digital.orderms.application.order.OrderService;
import com.digital.orderms.dto.OrderRequest;
import com.digital.orderms.dto.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order", produces = "application/json")
public class OrderController {

    private final OrderService service;

    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The order was created",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "The order contains invalid parameters.",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Error.class)) })})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(order));
    }
}
