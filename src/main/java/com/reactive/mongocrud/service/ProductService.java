package com.reactive.mongocrud.service;

import com.reactive.mongocrud.dto.ProductDto;
import com.reactive.mongocrud.models.Product;
import com.reactive.mongocrud.repository.ProductRepository;
import com.reactive.mongocrud.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // method to get all Products from DB
    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll().map(Utils::entityToDto);
    }

    public Mono<ProductDto> getSingleProduct(Integer Id) {
        return productRepository.findById(Long.valueOf(Id)).map(Utils::entityToDto);
    }

    public Flux<ProductDto> getProductInRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    // create a product, this is an asynchronous wrapper that will emit one ProductDto or no data at all
    public Mono<ProductDto> createProduct(Mono<ProductDto> productDtoMono) {
        //flatMap is used in one to many, while if you need to do single mapping use the .map
        return productDtoMono.map(Utils::DtoToEntity).flatMap(productRepository::insert).map(Utils::entityToDto);


        // flatMap is used to perform asyncrhonous ops while map perform synchronous ops

    }

    // update product
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, Integer Id) {
        // what happens here is that, find the product from the db first, take the request coming in from the client, convert it to an entity, keep the id as is, then save it then return the update as dto
        return productRepository.findById(Long.valueOf(Id)).flatMap(p -> productDtoMono.map(Utils::DtoToEntity)).doOnNext(e -> e.setId(Id)).flatMap(productRepository::save).map(Utils::entityToDto);
    }

    public Mono<Void> deleteProduct(Integer Id) {
        return productRepository.deleteById(Long.valueOf(Id));
    }


}
