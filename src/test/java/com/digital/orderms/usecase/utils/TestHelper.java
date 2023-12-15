package com.digital.orderms.usecase.utils;

import com.digital.orderms.domain.Customer;
import com.digital.orderms.usecase.customer.dto.CustomerDto;
import com.digital.orderms.usecase.customer.dto.CustomerRequest;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class TestHelper {
    public static CustomerRequest mockCustomerRequest() {
        return new EasyRandom(new EasyRandomParameters()).nextObject(CustomerRequest.class);
    }

    public static Customer mockCustomer() {
        return new EasyRandom(new EasyRandomParameters()).nextObject(Customer.class);
    }

    public static CustomerDto mockCustomerDto() {
        return new EasyRandom(new EasyRandomParameters()).nextObject(CustomerDto.class);
    }
}