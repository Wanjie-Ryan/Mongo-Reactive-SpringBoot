package com.reactive.mongocrud.utils;

import com.reactive.mongocrud.dto.ProductDto;
import com.reactive.mongocrud.models.Product;
import org.springframework.beans.BeanUtils;

// class to convert entity to DTO AND vice versa
public class Utils {

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        // an easier option for this conversion would be to use bean utils, the first argument is the source which is the entity product transformed to dto
        // beanUtils can only be used if both classes have the same attributes
        BeanUtils.copyProperties(product, productDto);
        return productDto;

    }

    public static Product DtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
