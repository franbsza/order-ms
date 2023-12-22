package com.digital.orderms.usecase.customer;

import com.digital.orderms.domain.Customer;
import com.digital.orderms.enums.CustomerStatus;
import com.digital.orderms.mappers.CustomerEntityMapper;
import com.digital.orderms.repository.CustomerRepository;
import com.digital.orderms.usecase.common.PageControl;
import com.digital.orderms.usecase.customer.dto.CustomerListResponse;
import com.digital.orderms.usecase.customer.dto.CustomerRequest;
import com.digital.orderms.usecase.customer.dto.CustomerDto;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper mapper;

    public CustomerDto create(CustomerRequest request) {
            Customer customer = mapper.mappingCustomerRequestCreateToCustomer(request);
            return mapper.mappingCustomerToCustomerDto(customerRepository.save(customer));
    }

    public CustomerDto update(CustomerRequest request) {
        Customer customer = findEntityById(request.getId());
        customer = mapper.mappingCustomerRequestToCustomer(request);
        return mapper.mappingCustomerToCustomerDto(customerRepository.save(customer));
    }

    public void updateStatus(Long id, String status) {
        findEntityById(id);
        try {
            CustomerStatus.valueOf(status);
            customerRepository.updateStatus(status, id);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    public CustomerDto findById(Long id) {
        Customer customer = findEntityById(id);
        return mapper.mappingCustomerToCustomerDto(customer);
    }

    private Customer findEntityById(Long id){
        return customerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Customer not found id: " + id));
    }

    public CustomerDto findByEmail(String email) {
       Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Customer not found id: " + email));
        return mapper.mappingCustomerToCustomerDto(customer);
    }

    public CustomerListResponse findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage =
                customerRepository.findAll(pageable);

        List<CustomerDto> customerDtos = customerPage
                .getContent()
                .stream()
                .map(mapper::mappingCustomerToCustomerDto)
                .collect(Collectors.toList());

        return CustomerListResponse.builder()
                .data(customerDtos)
                .pageControl(PageControl.builder()
                        .total(customerPage.getTotalElements())
                        .build())
                .build();
    }
}