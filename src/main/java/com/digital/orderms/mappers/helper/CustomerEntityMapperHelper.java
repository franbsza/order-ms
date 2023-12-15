package com.digital.orderms.mappers.helper;

import com.digital.orderms.enums.CustomerStatus;
import com.digital.orderms.usecase.customer.dto.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEntityMapperHelper {

    @Named("setCustomerStatus")
    public String setCustomerStatus(final CustomerRequest request) {
        return CustomerStatus.PENDING_ACTIVATION.name();
    }
}
