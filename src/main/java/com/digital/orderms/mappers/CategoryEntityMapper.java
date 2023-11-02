package com.digital.orderms.mappers;

import com.digital.orderms.domain.Category;
import com.digital.orderms.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    CategoryDto mappingCategoryToCategoryResponse(Category category);

    Category mappingCategoryDtoToCategoryEntity(CategoryDto categoryDto);
}