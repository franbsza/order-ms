package com.digital.orderms.usecase.order;

import com.digital.orderms.domain.Order;
import com.digital.orderms.domain.Vehicle;
import com.digital.orderms.dto.OrderRequest;
import com.digital.orderms.dto.OrderResponse;
import com.digital.orderms.mappers.OrderEntityMapper;
import com.digital.orderms.repository.OrderRepository;
import com.digital.orderms.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;
    private final OrderEntityMapper mapper;

    public OrderResponse create(OrderRequest orderRequest) {

        Vehicle vehicle = vehicleRepository.findById(orderRequest.getVehicleId()).orElseThrow(
                () -> new IllegalArgumentException("Vehicle not found")
        );
        if(Boolean.FALSE.equals(vehicle.getIsProtected())){
            throw new IllegalArgumentException("Vehicle is not protected");
        }
        Order order = mapper.mappingOrderRequestToOrder(orderRequest);

        return mapper.mappingOrderToOrderResponse(orderRepository.save(order));
    }
}