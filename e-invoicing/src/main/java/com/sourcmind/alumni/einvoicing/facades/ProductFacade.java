package com.sourcmind.alumni.einvoicing.facades;

import com.sourcmind.alumni.einvoicing.converters.CategoryConverter;
import com.sourcmind.alumni.einvoicing.converters.ProductConverter;
import com.sourcmind.alumni.einvoicing.converters.TaxGroupConverter;
import com.sourcmind.alumni.einvoicing.entities.Category;
import com.sourcmind.alumni.einvoicing.entities.Product;
import com.sourcmind.alumni.einvoicing.entities.TaxGroup;
import com.sourcmind.alumni.einvoicing.payloads.requests.ProductRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.ProductResponse;
import com.sourcmind.alumni.einvoicing.services.impl.CategoryService;
import com.sourcmind.alumni.einvoicing.services.impl.ProductService;
import com.sourcmind.alumni.einvoicing.services.impl.TaxGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductFacade {

    private final ProductConverter converter;
    private final CategoryConverter categoryConverter;
    private final TaxGroupConverter taxGroupConverter;
    private final CategoryService categoryService;
    private final TaxGroupService taxGroupService;
    private final ProductService productService;


    public ProductResponse create(ProductRequest request) {
        productService.checkCode(request.getCode());

        Product product = converter.convert(request);
        product = addCategoryAndTaxGroup(product, request.getCategoryId(), request.getTaxGroupId());

        product = productService.save(product);

        return localConvert(product);

    }


    public ProductResponse readByid(UUID uuid) {
        return localConvert(productService.readById(uuid));
    }

    private ProductResponse localConvert(Product product) {

        ProductResponse productResponse = converter.convert(product);
        productResponse.setCategory(categoryConverter.convert(product.getCategory()));
        productResponse.setTaxGroup(taxGroupConverter.convert(product.getTaxGroup()));
        return productResponse;
    }


    public Page<ProductResponse> readAll(Pageable pageable) {
        return productService.readAll(pageable).map(this::localConvert);
    }


    public ProductResponse update(UUID uuid, ProductRequest request) {
        Product product = productService.readById(uuid);

        productService.checkCode(request.getCode());

        Product productToSave = converter.convert(request);
        productToSave = addCategoryAndTaxGroup(productToSave, request.getTaxGroupId(), request.getTaxGroupId());

        productToSave.setId(product.getId());

        productService.save(productToSave);

        return localConvert(productToSave);

    }

    public void delete(UUID uuid) {
        productService.delete(productService.readById(uuid));
    }


    private Product addCategoryAndTaxGroup(Product product, UUID catId, UUID tax) {

        Category category = categoryService.readById(catId);
        TaxGroup taxGroup = taxGroupService.readById(tax);
        product.setCategory(category);

        product.setTaxGroup(taxGroup);
        return product;


    }
}
