package com.leolmcoding.dscommerce.controllers;

import com.leolmcoding.dscommerce.dtos.ProductDTO;
import com.leolmcoding.dscommerce.entities.Product;
import com.leolmcoding.dscommerce.repositories.ProductRepository;
import com.leolmcoding.dscommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<String> findByid(@PathVariable Long id){
        Optional<ProductDTO> produto= productService.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("WHOOPS! NOT FOUND");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto.get().getName());
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        Optional<List<ProductDTO>> produto= productService.findAll();
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }
}
