package com.sourcmind.alumni.einvoicing.converters;


import com.sourcmind.alumni.einvoicing.entities.Category;
import com.sourcmind.alumni.einvoicing.payloads.requests.CategoryRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

@RequiredArgsConstructor
public class CategoryConverter {

    private final ModelMapper modelMapper ;

    public Category convert(CategoryRequest request){
        return  modelMapper.map(request, Category.class);
    }

    public CategoryResponse convert(Category category){
        return  modelMapper.map(category, CategoryResponse.class);
    }
}
