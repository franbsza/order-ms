package com.digital.orderms.application.order;

import com.digital.orderms.domain.Category;
import com.digital.orderms.dto.CategoryDto;
import com.digital.orderms.dto.CategoryResponse;
import com.digital.orderms.dto.MetaDto;
import com.digital.orderms.mappers.CategoryEntityMapper;
import com.digital.orderms.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryEntityMapper mapper;

    public CategoryResponse findAll(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoriesPage = repository.findAll(pageable);
        List<CategoryDto> categoryDtoList = categoriesPage.getContent().stream()
                .map(mapper::mappingCategoryToCategoryResponse)
                .collect(Collectors.toList());

        return CategoryResponse.builder()
                .data(categoryDtoList)
                .metaDto(MetaDto.builder()
                        .total(categoriesPage.getTotalElements())
                        .build())
                .build();
    }

    public CategoryDto findById(Integer id){
        Category category = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Entity not found id: " + id)
        );
        return mapper.mappingCategoryToCategoryResponse(category);
    }


    public CategoryDto create(CategoryDto categoryDto){
        Category category = mapper.mappingCategoryDtoToCategoryEntity(categoryDto);
        return mapper.mappingCategoryToCategoryResponse(repository.save(category));
    }

    public CategoryDto update(CategoryDto categoryDto){
        repository.findById(categoryDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("Entity not found id: " + categoryDto.getId())
        );

        Category category = mapper.mappingCategoryDtoToCategoryEntity(categoryDto);
        return mapper.mappingCategoryToCategoryResponse(repository.save(category));
    }

    public void delete(Integer id){
        try {
            repository.deleteById(id);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Fail to delete id: "+ id);
        }
    }
}