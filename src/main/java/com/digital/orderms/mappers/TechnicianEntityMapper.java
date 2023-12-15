package com.digital.orderms.mappers;
import com.digital.orderms.domain.ExpertTechnician;
import com.digital.orderms.usecase.technician.dto.TechnicianDto;
import com.digital.orderms.usecase.technician.dto.TechnicianRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicianEntityMapper {

    TechnicianDto mappingTechnicianToDto(ExpertTechnician technician);

    ExpertTechnician mappingTechnicianRequestToExpertTechnician(TechnicianRequest request);
}