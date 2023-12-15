package com.digital.orderms.usecase.order;

import com.digital.orderms.domain.ExpertTechnician;
import com.digital.orderms.domain.Order;
import com.digital.orderms.domain.Vehicle;
import com.digital.orderms.mappers.OrderEntityMapper;
import com.digital.orderms.repository.OrderRepository;
import com.digital.orderms.repository.VehicleRepository;
import com.digital.orderms.usecase.common.PageControl;
import com.digital.orderms.usecase.order.dto.OrderRequest;
import com.digital.orderms.usecase.order.dto.OrderCreateResponse;
import com.digital.orderms.usecase.order.dto.OrderDto;
import com.digital.orderms.usecase.order.dto.OrderListResponse;
import com.digital.orderms.usecase.technician.TechnicianService;
import com.digital.orderms.usecase.vehicle.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;
    private final OrderEntityMapper mapper;
    private final TechnicianService technicianService;

    public OrderCreateResponse create(OrderRequest request) {
        isProtected(request.getVehicle().getId());
        ExpertTechnician expertTechnician =  allocateTechnician(request.getAddress().getNeighborhood());
        Order order = mapper.mappingCreateOrderRequestToOrder(request, expertTechnician);
        return mapper.mappingOrderToOrderResponse(orderRepository.save(order));
    }

    public OrderCreateResponse update(OrderRequest request, Long id) {
        isProtected(request.getVehicle().getId());
        Order orderResponse = orderRepository.findById(request.getId()).orElseThrow(
                () -> new IllegalArgumentException("Order not found id: " + id));

        Order order = mapper.mappingUpdateOrderRequestToOrder(request, id, orderResponse);
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

    private void isProtected(Long id){
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Vehicle not found")
        );
        if(Boolean.FALSE.equals(vehicle.getIsProtected())){
            throw new IllegalArgumentException("Vehicle is not protected");
        }
    }

    private ExpertTechnician allocateTechnician(String neighborhood){
        return technicianService.findOneByRegion(neighborhood);
    }
}