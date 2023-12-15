package com.digital.orderms.usecase.technician;

import com.digital.orderms.domain.Address;
import com.digital.orderms.domain.ExpertTechnician;
import com.digital.orderms.mappers.TechnicianEntityMapper;
import com.digital.orderms.repository.AddressRepository;
import com.digital.orderms.repository.TechnicianRepository;
import com.digital.orderms.usecase.common.PageControl;
import com.digital.orderms.usecase.technician.dto.TechnicianDto;
import com.digital.orderms.usecase.technician.dto.TechnicianListResponse;
import com.digital.orderms.usecase.technician.dto.TechnicianRequest;
import java.util.Collections;
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
public class TechnicianService {

    private final TechnicianRepository repository;
    private final AddressRepository addressRepository;
    private final TechnicianEntityMapper mapper;

    public TechnicianDto create(TechnicianRequest request) {
        ExpertTechnician expertTechnician = mapper.mappingTechnicianRequestToExpertTechnician(request);
        return mapper.mappingTechnicianToDto(repository.save(expertTechnician));
    }

    public TechnicianDto update(TechnicianRequest request) {
        findById(request.getId());
        ExpertTechnician expertTechnician = mapper.mappingTechnicianRequestToExpertTechnician(request);
        return mapper.mappingTechnicianToDto(repository.save(expertTechnician));
    }
    public void updateStatus(Long id, Boolean status) {
        findById(id);
        repository.updateStatus(status, id);
    }

    public TechnicianListResponse findAll(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<ExpertTechnician> expertTechnicians = repository.findAll(pageable);

        List<TechnicianDto> technicianDtos = expertTechnicians.getContent().stream()
                .map(mapper::mappingTechnicianToDto)
                .collect(Collectors.toList());

        return TechnicianListResponse.builder()
                .data(technicianDtos)
                .pageControl(PageControl.builder()
                        .total(expertTechnicians.getTotalElements())
                        .build())
                .build();
    }

    public TechnicianDto findById(Long id){
        ExpertTechnician expertTechnician = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Entity not found id: " + id)
        );
        return mapper.mappingTechnicianToDto(expertTechnician);
    }

    public List<TechnicianDto> findByRegion(String neighborhood) {
        Optional<Address> address = addressRepository.findByNeighborhood(neighborhood).stream().findFirst();
        if(address.isPresent()){
            String id = address.get().getBaseAddress().getId();
            List<ExpertTechnician> expertTechnicians = repository.findByBaseAddress(id);
            return expertTechnicians.stream().map(
                    mapper::mappingTechnicianToDto
            ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public ExpertTechnician findOneByRegion(String neighborhood) {
        Optional<Address> address = addressRepository.findByNeighborhood(neighborhood).stream().findFirst();
        if(address.isPresent()){
            String id = address.get().getBaseAddress().getId();
            List<ExpertTechnician> expertTechnicians = repository.findByBaseAddress(id);
            return expertTechnicians != null ? expertTechnicians.stream().findFirst().get() : null;
        }
        return null;
    }
}