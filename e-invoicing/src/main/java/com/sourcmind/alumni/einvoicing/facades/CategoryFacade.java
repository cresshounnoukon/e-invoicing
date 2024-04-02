package com.sourcmind.alumni.einvoicing.facades;

import com.sourcmind.alumni.einvoicing.converters.CategoryConverter;
import com.sourcmind.alumni.einvoicing.entities.Category;
import com.sourcmind.alumni.einvoicing.payloads.requests.CategoryRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.CategoryResponse;
import com.sourcmind.alumni.einvoicing.services.impl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryFacade {

    private final CategoryConverter converter;
    private final CategoryService categoryService;

    public Page<CategoryResponse> readAll(Pageable pageable) {
       return categoryService.readAll(pageable).map(converter::convert);
    }

    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = converter.convert(categoryRequest);
        return converter.convert(categoryService.save(category));

    }


    public CategoryResponse readByCode(String code) {
        return converter.convert(categoryService.readByCode(code));
    }

    public CategoryResponse readById(UUID id) {
        return converter.convert(categoryService.readById(id));
    }

    public void delete(UUID id) {
        categoryService.delete(categoryService.readById(id));
    }

    public CategoryResponse update(CategoryRequest categoryRequest) {
        return converter.convert(categoryService.save(converter.convert(categoryRequest)));

    }
}
