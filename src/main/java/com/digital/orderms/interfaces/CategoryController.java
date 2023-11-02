package com.digital.orderms.interfaces;

import com.digital.orderms.application.order.CategoryService;
import com.digital.orderms.dto.CategoryDto;
import com.digital.orderms.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/categories", produces = "application/json")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<CategoryResponse> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                    @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("id") Integer id){
       return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto request){
        CategoryDto response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto request){
        CategoryDto response = service.update(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Integer id){
        service.delete(id);
        return HttpStatus.OK;
    }
}