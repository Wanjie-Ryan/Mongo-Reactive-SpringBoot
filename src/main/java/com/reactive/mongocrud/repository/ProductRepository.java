package com.reactive.mongocrud.repository;

import com.reactive.mongocrud.dto.ProductDto;
import com.reactive.mongocrud.models.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Long> {

    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
