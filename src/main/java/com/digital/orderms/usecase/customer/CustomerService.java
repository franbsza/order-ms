package com.digital.orderms.usecase.customer;

import com.digital.orderms.domain.Customer;
import com.digital.orderms.enums.CustomerStatus;
import com.digital.orderms.mappers.CustomerEntityMapper;
import com.digital.orderms.repository.CustomerRepository;
import com.digital.orderms.usecase.customer.dto.CustomerRequest;
import com.digital.orderms.usecase.customer.dto.CustomerDto;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
        findEntityById(request.getId());
        Customer customer = mapper.mappingCustomerRequestToCustomer(request);
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
}