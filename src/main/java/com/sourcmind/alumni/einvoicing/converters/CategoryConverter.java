package com.sourcmind.alumni.einvoicing.converters;


import com.sourcmind.alumni.einvoicing.entities.Category;
import com.sourcmind.alumni.einvoicing.payloads.requests.CategoryRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category convert(CategoryRequest request){
        return  Category
                .builder()
                .code(request.getCode())
                .name(request.getName())
                .build();
    }

    public CategoryResponse convert(Category category){
        return  CategoryResponse
                .builder()
                .id(category.getId())
                .code(category.getCode())
                .name(category.getName())
                .build();
    }
}
