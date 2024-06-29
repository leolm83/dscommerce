package com.leolmcoding.dscommerce.service;

import com.leolmcoding.dscommerce.dtos.ProductDTO;
import com.leolmcoding.dscommerce.entities.Product;
import com.leolmcoding.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findById(Long id){
        Optional<Product> result = productRepository.findById(id);
        if(result.isEmpty()){
            return Optional.empty();
        }
        Product product = result.get();

        ProductDTO productDTO = new ProductDTO(
        product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl()
        );
        return Optional.of(productDTO);
    }
    public Optional<Page<ProductDTO>> findAll(Pageable pageable){
       Page<Product> result = productRepository.findAll(pageable);
        if(result.isEmpty()){
            return Optional.empty();
        }
       return Optional.of(result.map(ProductDTO::new));
    }
}
