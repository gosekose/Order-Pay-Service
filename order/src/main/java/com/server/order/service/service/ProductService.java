package com.server.order.service.service;

import com.server.order.domain.Product;
import com.server.order.repository.ProductRepository;
import com.server.order.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Long save(ProductDto dto) {
        Product product = productRepository.save(Product.builder().name(dto.getName()).price(dto.getPrice())
                .amount(dto.getAmount()).build());
        return product.getId();
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
