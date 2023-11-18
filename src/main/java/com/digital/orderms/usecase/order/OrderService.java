package com.digital.orderms.usecase.order;

import com.digital.orderms.domain.Order;
import com.digital.orderms.domain.Vehicle;
import com.digital.orderms.mappers.OrderEntityMapper;
import com.digital.orderms.repository.OrderRepository;
import com.digital.orderms.repository.VehicleRepository;
import com.digital.orderms.usecase.common.PageControl;
import com.digital.orderms.usecase.order.dto.OrderCreateRequest;
import com.digital.orderms.usecase.order.dto.OrderCreateResponse;
import com.digital.orderms.usecase.order.dto.OrderDto;
import com.digital.orderms.usecase.order.dto.OrderListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;
    private final OrderEntityMapper mapper;

    public OrderCreateResponse create(OrderCreateRequest request) {

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElseThrow(
                () -> new IllegalArgumentException("Vehicle not found")
        );
        if(Boolean.FALSE.equals(vehicle.getIsProtected())){
            throw new IllegalArgumentException("Vehicle is not protected");
        }
        Order order = mapper.mappingOrderRequestToOrder(request);
        return mapper.mappingOrderToOrderResponse(orderRepository.save(order));
    }

    public OrderListResponse find(int page, int size, String email) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Order> ordersPage = email != null ?
                orderRepository.findByEmail(pageable, email) :
                orderRepository.findAll(pageable);

        List<OrderDto> orderDtoList = ordersPage.getContent().stream()
                .map(mapper::mappingOrderToOrderDto)
                .collect(Collectors.toList());

        return OrderListResponse.builder()
                .data(orderDtoList)
                .pageControl(PageControl.builder()
                        .total(ordersPage.getTotalElements())
                        .build())
                .build();
    }

    public OrderDto findById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Entity not found id: " + id)
        );
        return mapper.mappingOrderToOrderDto(order);
    }
}