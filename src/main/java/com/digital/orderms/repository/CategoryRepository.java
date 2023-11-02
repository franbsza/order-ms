package com.digital.orderms.repository;

import com.digital.orderms.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

    Page<Category> findAll(Pageable pageable);
}