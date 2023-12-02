package com.sourcmind.alumni.einvoicing.controllers;

import com.sourcmind.alumni.einvoicing.facades.CategoryFacade;
import com.sourcmind.alumni.einvoicing.payloads.requests.CategoryRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("categories")
public class CategoryController {
    private final CategoryFacade facade;

    @GetMapping
    public Page<CategoryResponse> readAll(Pageable pageable) {
        return facade.readAll(pageable);
    }

    @PostMapping
    public CategoryResponse create(@RequestBody  CategoryRequest categoryRequest) {
        return facade.create(categoryRequest);
    }

    @GetMapping("/{uuid}")
    public CategoryResponse readById(@PathVariable UUID uuid) {
        return facade.readById(uuid);
    }

    @PutMapping("/{uuid}")
    public CategoryResponse update(@PathVariable UUID uuid, CategoryRequest categoryRequest) {
        return facade.update(categoryRequest);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        facade.delete(uuid);
    }


}
