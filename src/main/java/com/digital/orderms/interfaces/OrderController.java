package com.digital.orderms.interfaces;

import com.digital.orderms.application.OrderService;
import com.digital.orderms.domain.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/order", produces = "application/json")
public class OrderController {

    private final OrderService service;

    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The order was created"),
            @ApiResponse(responseCode = "400", description = "The order contains invalid parameters.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) })})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(order));
    }
}
