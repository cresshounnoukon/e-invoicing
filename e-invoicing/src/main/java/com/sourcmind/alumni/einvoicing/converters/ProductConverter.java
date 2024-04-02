package com.sourcmind.alumni.einvoicing.converters;


import com.sourcmind.alumni.einvoicing.entities.Category;
import com.sourcmind.alumni.einvoicing.entities.Product;
import com.sourcmind.alumni.einvoicing.payloads.requests.CategoryRequest;
import com.sourcmind.alumni.einvoicing.payloads.requests.ProductRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.CategoryResponse;
import com.sourcmind.alumni.einvoicing.payloads.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final ModelMapper modelMapper ;

    public Product convert(ProductRequest request){
        return  modelMapper.map(request, Product.class);
    }

    public ProductResponse convert(Product product){ return  modelMapper.map(product, ProductResponse.class);}
}
