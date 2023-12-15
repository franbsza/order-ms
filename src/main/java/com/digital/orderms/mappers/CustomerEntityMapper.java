package com.digital.orderms.mappers;

import com.digital.orderms.domain.Customer;
import com.digital.orderms.mappers.helper.CustomerEntityMapperHelper;
import com.digital.orderms.usecase.customer.dto.CustomerRequest;
import com.digital.orderms.usecase.customer.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerEntityMapperHelper.class})
public interface CustomerEntityMapper {

    CustomerDto mappingCustomerToCustomerDto(Customer customer);

    @Mapping(target = "status", source = "request", qualifiedByName = "setCustomerStatus")
    Customer mappingCustomerRequestCreateToCustomer(CustomerRequest request);

    Customer mappingCustomerRequestToCustomer(CustomerRequest request);
}