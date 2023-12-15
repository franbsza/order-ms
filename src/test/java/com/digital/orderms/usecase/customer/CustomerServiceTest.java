package com.digital.orderms.usecase.customer;

import com.digital.orderms.mappers.CustomerEntityMapper;
import com.digital.orderms.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.digital.orderms.usecase.utils.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.digital.orderms.usecase.customer.dto.*;
import com.digital.orderms.domain.Customer;

class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerEntityMapper mapper;

    @Test
    void createCustomer() {

        CustomerRequest customerRequest = mockCustomerRequest();
        Customer customer = mockCustomer();
        CustomerDto customerDto = mockCustomerDto();

        when(mapper.mappingCustomerRequestToCustomer(customerRequest)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(mapper.mappingCustomerToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto response = customerService.create(customerRequest);

        assertEquals(customerDto, response);
        assertEquals(customerRequest.getFirstName(), response.getFirstName());
        verify(customerRepository, times(1)).save(customer);
    }
}
