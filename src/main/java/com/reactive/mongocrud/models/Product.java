package com.reactive.mongocrud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {

    @Id
    private Integer Id;
    private String name;
    private Integer quantity;
    private Double price;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product() {
    }

    public Product(Integer id, String name, Integer quantity, Double price) {
        Id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
