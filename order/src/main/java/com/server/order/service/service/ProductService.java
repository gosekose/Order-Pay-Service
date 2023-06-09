package com.server.order.service.service;

import com.server.order.domain.Product;
import com.server.order.repository.ProductRepository;
import com.server.order.service.dto.ProductDto;
import com.server.order.service.service.dto.ProductViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductViewDto> getProduct(Pageable pageable) {
        if (pageable == null) {
            pageable = PageRequest.of(0, 10);
        }
        return productRepository.findAll(pageable)
                .stream()
                .map(ProductViewDto::new)
                .collect(Collectors.toList());
    }

    public ProductViewDto getProductDetail(Long productId) {
        return new ProductViewDto(findById(productId));
    }
}
