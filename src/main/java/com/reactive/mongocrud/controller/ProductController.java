package com.reactive.mongocrud.controller;

import com.reactive.mongocrud.dto.ProductDto;
import com.reactive.mongocrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/allproducts")
    public Flux<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/allproducts/{productId}")
    public Mono<ProductDto> getSingleProduct(@PathVariable("productId") Integer id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping("/productrange")
    public Flux<ProductDto> getProductRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return productService.getProductInRange(min, max);
    }

    @PostMapping("/createproduct")
    public Mono<ProductDto> createProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.createProduct(productDtoMono);
    }

    @PutMapping("/updateproduct/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, Integer id) {
        return productService.updateProduct(productDtoMono, id);
    }

    @DeleteMapping("/deleteproduct/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") Integer id) {
        return productService.deleteProduct(id);
    }
}
